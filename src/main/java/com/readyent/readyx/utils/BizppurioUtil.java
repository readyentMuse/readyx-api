package com.readyent.readyx.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readyent.readyx.domain.dto.request.BizPpurioRequestDto;
import com.readyent.readyx.domain.dto.response.BizPpurioResponseDto;
import com.readyent.readyx.domain.dto.response.ResponseDto;
import com.readyent.readyx.service.BizPpurioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class BizppurioUtil {
    private final BizPpurioService bizPpurioService;

    /**
     * 토큰 생성
     */
    public BizPpurioResponseDto.TokenResponse generateToken() {
        String accesstoken = "";
        String type = "";
        String expired = "";

        try {
            // 계정과 암호를 Base64로 인코딩
            String account = "musew_api";
            String password = "rdmusew2025!";
            String auth = account + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            log.info(encodedAuth);

            // 요청할 URL 설정
            //dev-api.bizppurio.com
            URL url = new URL("https://api.bizppurio.com/v1/token");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 메서드 설정
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // 요청 헤더 설정
            conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            // 필요 시 요청 본문 작성
            String jsonInputString = "{}";  // 실제로 보낼 데이터가 있다면 이 부분에 JSON 문자열 작성
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
// 응답 결과를 읽어옴
            BufferedReader in;
            if (responseCode >= 200 && responseCode < 300) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                // 에러 응답일 경우 getErrorStream() 사용
                in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 응답 결과 출력
            log.info("Response Body: " + response.toString());
            log.info("Response Code: " + responseCode);

            Map<String, String> biztokenMap = getKeyValueMap(response);
            accesstoken = biztokenMap.get("accesstoken");
            type = biztokenMap.get("type");
            expired = biztokenMap.get("expired");

            // 토큰 저장
            insertToken(accesstoken, type, expired);
            // 연결 종료
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return BizPpurioResponseDto.TokenResponse.builder().accesstoken(accesstoken).type(type).expired(expired).build();
    }

    /**
     * String 값을 Map 형태로 재구성
     *
     * @param response
     * @return
     */
    private static Map<String, String> getKeyValueMap(StringBuilder response) {
        Map<String, String> responseMap = null;

        try {
            // ObjectMapper 인스턴스 생성
            ObjectMapper objectMapper = new ObjectMapper();

            // JSON 문자열을 Map으로 변환
            responseMap = objectMapper.readValue(response.toString(), Map.class);

            // Map 내용 출력
            responseMap.forEach((key, value) -> log.info(key + ": " + value));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseMap;
    }

    /**
     * 뿌리오 토큰 가져오기
     *
     * @return
     */
    public String getToken() {
        String accesstoken = "";
        //TODO 토큰값 DB 확인
//        BizPpurioResponseDto.TokenResponse tokenResponse = BizPpurioResponseDto.TokenResponse.builder().accesstoken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50IjoiSlY1VXk0ZFJXN0cvTktuc0tUWDBKZz09IiwiYWxsb3dlZElwIjoibkg0amVNSkZHU2EyRitPM3VPRDlpVmxteFFPTXZ4eUw3QWRSZDFlQmxCSlNFZ3lZSjZCMXEvODB2aDJNZzRlVnYyS2JGSFNCT0VYMkR0RnFSZVFweCt4Y1ZmMlJZL1JwTUFHY0lla3R3eGlPSFlYU1VDN0ZpajBER2hTZFZmK1BJb01GTEJKSFUrZlNqUzhrVElhTm9RPT0iLCJyYXRlTGltaXQiOiI5OTk5IiwiaWF0IjoxNzI4MzUwMDY0LCJleHAiOjE3Mjg0MzY0NjQsImlzcyI6ImFwaS5iaXpwcHVyaW8uY29tIn0.5bBh7JxME5xd5GJG_RWVQQc7tGLUVIc5-_fxrviW2eI")
//                .type("Bearer").expired("20241009101424").build();
//
        BizPpurioResponseDto.TokenResponse tokenResponse = bizPpurioService.getToken();

        // 현재 시간 가져오기
        Date currentDate = new Date();

        // 현재 시간에서 1시간을 빼기
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.HOUR_OF_DAY, -1);  // 1시간 전으로 설정
        Date oneHourAgo = cal.getTime();

        // SimpleDateFormat 설정
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        if (tokenResponse != null) {
            try {
                // 문자열을 Date 객체로 변환
                Date parsedDate = dateFormat.parse(tokenResponse.getExpired());

                // 날짜 비교 (1시간 전인지 확인)
//                if (parsedDate.before(currentDate) && parsedDate.after(oneHourAgo)) {
//                    System.out.println("20241009101424는 현재 시간 1시간 전입니다.");
                if (oneHourAgo.before(parsedDate)) {
                    accesstoken = tokenResponse.getAccesstoken();
                    log.info("{}(만료시간) 전 입니다.", parsedDate);
                } else {
                    accesstoken = generateToken().getAccesstoken();
                    log.info("{}(만료시간)이 지났습니다.", parsedDate);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            accesstoken = generateToken().getAccesstoken();
        }

        return accesstoken;
    }

    /**
     * 토큰 DB 저장
     */
    public ResponseDto.ResultResponse insertToken(String accesstoken, String type, String expired) {
        log.info("토큰 저장");
        return bizPpurioService.insertToken(BizPpurioRequestDto.InsertTokenRequest.builder().accesstoken(accesstoken).type(type).expired(expired).build());
    }

    public ResponseDto.ResultResponse sendSMS(String mobile_number) {

        return null;
    }
}
