package com.readyent.readyx.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.readyent.readyx.domain.dto.request.BizPpurioRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

@Slf4j
@RequiredArgsConstructor
public class BizppurioMessageUtil {
    /**
     * 인증 번호 전송
     * @param mobileNumber
     * @return
     */
    public static BizPpurioRequestDto.InsertVerificationRequest sendVerification(String token, String mobileNumber) {
//        public static void main (String[] args) {
        BizPpurioRequestDto.InsertVerificationRequest result = null;

//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50IjoiSlY1VXk0ZFJXN0cvTktuc0tUWDBKZz09IiwiYWxsb3dlZElwIjoibkg0amVNSkZHU2EyRitPM3VPRDlpVmxteFFPTXZ4eUw3QWRSZDFlQmxCSlNFZ3lZSjZCMXEvODB2aDJNZzRlVnYyS2JGSFNCT0VYMkR0RnFSZVFweCt4Y1ZmMlJZL1JwTUFHY0lla3R3eGlPSFlYU1VDN0ZpajBER2hTZFZmK1BJb01GTEJKSFUrZlNqUzhrVElhTm9RPT0iLCJyYXRlTGltaXQiOiI5OTk5IiwiaWF0IjoxNzI4NTMwNjE4LCJleHAiOjE3Mjg2MTcwMTgsImlzcyI6ImFwaS5iaXpwcHVyaW8uY29tIn0.yp-Tgv1umeQlYFgOa1JXLtq0LtzPe01_pvplrro5N5c";
//        String mobileNumber = "01049388846";

        int verificationCode = CommonUtil.generateRandomNumber();
        String message = String.format("인증번호 : [%d]", verificationCode);
        log.info("인증번호 {}", message);

        String input;
        StringBuilder responseResult = new StringBuilder();
        URL url;

        try {
            /** SSL 인증서 무시 : 비즈뿌리오 API 운영을 접속하는 경우 해당 코드 필요 없음 **/
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] chain, String authType) {}

                        public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            /** 운영 : https://api.bizppurio.com, 개발 : https://dev-api.bizppurio.com **/
//            url = new URL("https://dev-api.bizppurio.com/v3/message");
            url = new URL("https://api.bizppurio.com/v3/message");

            /** Connection 설정 **/
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Accept-Charset", "UTF-8");
            connection.addRequestProperty("Authorization", "Bearer " + token);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);

            /** Request **/
            OutputStream os = connection.getOutputStream();
            String sms = "{\"account\":\"musew_api\",\"refkey\":\"readyx\","
                    + "\"type\":\"sms\",\"from\":\"0234453222\",\"to\":\"" + mobileNumber + "\","
                    + "\"content\":{\"sms\":{\"message\":\""+ message +"\"}}}";
            os.write(sms.getBytes(StandardCharsets.UTF_8));
            os.flush();

            /** Response **/
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            while ((input = in.readLine()) != null) {
                responseResult.append(input);
            }
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                // JSON 문자열
//                String jsonResponse = "{\"code\":1000,\"description\":\"success\",\"refkey\":\"readyx\",\"messagekey\":\"241010103637321sms018616muserWXQ\"}";

                // ObjectMapper 인스턴스 생성
                ObjectMapper objectMapper = new ObjectMapper();

                try {
                    // JSON 문자열을 JsonNode로 파싱
                    JsonNode jsonNode = objectMapper.readTree(responseResult.toString());

                    // 각 필드 값 추출
                    int code = jsonNode.get("code").asInt();
                    String description = jsonNode.get("description").asText();
                    String refkey = jsonNode.get("refkey").asText();
                    String messagekey = jsonNode.get("messagekey").asText();

                    // 값 출력
                    log.info("Code: " + code);
                    log.info("Description: " + description);
                    log.info("Refkey: " + refkey);
                    log.info("Messagekey: " + messagekey);

                    //Response : {"code":1000,"description":"success","refkey":"readyx","messagekey":"241010094610207sms018623museH6b2"}
                    result = result.builder()
                            .code(code)
                            .description(description)
                            .refkey(refkey)
                            .messagekey(messagekey)
                            .mobileNumber(mobileNumber)
                            .verificationCode(String.valueOf(verificationCode))
                            .build();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            connection.disconnect();
            log.info("Response : {}", responseResult.toString());


        } catch (IOException e) {
            e.printStackTrace();  // 실제로 적절한 예외 처리를 수행하세요.
        } catch (KeyManagementException e) {
            e.printStackTrace();  // 실제로 적절한 예외 처리를 수행하세요.
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  // 실제로 적절한 예외 처리를 수행하세요.
        }

        return result;
    }
}
