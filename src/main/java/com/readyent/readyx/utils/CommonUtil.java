package com.readyent.readyx.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Random;

@Slf4j
public class CommonUtil {

    /**
     * String 값을 Map 형태로 재구성
     * @param response
     * @return
     */
    public static Map<String, String> getKeyValueMap(StringBuilder response) {
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

    // 8자리 숫자로 된 랜덤값 생성 메서드
    public static int generateRandomNumber() {
        Random random = new Random();
        // 10000000 이상 99999999 이하의 랜덤 숫자를 생성
        return 10000000 + random.nextInt(90000000);  // 0 ~ 89999999 범위에 10000000을 더하여 8자리 수를 보장
    }
}
