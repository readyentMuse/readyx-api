package com.readyent.readyx.service.impl;

import com.readyent.readyx.constant.ResponseStatusCode;
import com.readyent.readyx.domain.dto.request.BizPpurioRequestDto;
import com.readyent.readyx.domain.dto.request.PhoneNumberRequest;
import com.readyent.readyx.domain.dto.response.BizPpurioResponseDto;
import com.readyent.readyx.domain.dto.response.ResponseDto;
import com.readyent.readyx.mapper.AuthMapper;
import com.readyent.readyx.mapper.BizPpurioMapper;
import com.readyent.readyx.service.AuthService;
import com.readyent.readyx.service.BizPpurioService;
import com.readyent.readyx.utils.BizppurioUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class BizPpurioServiceImpl implements BizPpurioService {
    private final BizPpurioMapper bizPpurioMapper;

    @Override
    public ResponseDto.ResultResponse insertToken(BizPpurioRequestDto.InsertTokenRequest requestDto) {
        log.info("{}} 매서드 실행", this.getClass().getSimpleName());
        int count = bizPpurioMapper.insertToken(requestDto);
        log.info("Insert token result: {}", count);

        ResponseStatusCode responseStatusCode = ResponseStatusCode.RESPONSE_SUCCESS_INST;
        return ResponseDto.ResultResponse.builder()
                .code(responseStatusCode.getCode())
                .message(responseStatusCode.getMessage())
                .status(responseStatusCode.getStatus())
                .build();
    }

    @Override
    public ResponseDto.ResultResponse sendVerificationCode(PhoneNumberRequest phoneNumberRequest) {
        log.info("메시지 발송");
        return null;
    }

    @Override
    public BizPpurioResponseDto.TokenResponse getToken() {
        return bizPpurioMapper.getToken();
    }

    @Override
    public ResponseDto.ResultResponse insertVerification(BizPpurioRequestDto.InsertVerificationRequest requestDto) {
        int count = bizPpurioMapper.insertVerification(requestDto);

        ResponseStatusCode responseStatusCode = ResponseStatusCode.RESPONSE_SUCCESS_INST;
        return ResponseDto.ResultResponse.builder()
                .code(responseStatusCode.getCode())
                .message(responseStatusCode.getMessage())
                .status(responseStatusCode.getStatus())
                .build();
    }

    @Override
    public BizPpurioResponseDto.GetVerificationResponse getVerification(BizPpurioRequestDto.GetVerificationRequest requestDto) {
//        verificationAddCreatedAtRequest.setMobileNumber(verificationRequest.getMobileNumber());
//        verificationAddCreatedAtRequest.setVerificationCode(verificationRequest.getVerificationCode());
        // 현재 시간
        LocalDateTime currentTime = LocalDateTime.now();

        // 5분 전 시간 계산
        LocalDateTime timeMinusFiveMinutes = currentTime.minusMinutes(5);

        // 출력
        log.info("현재 시간: " + currentTime);
        log.info("5분 전 시간: " + timeMinusFiveMinutes);

        BizPpurioResponseDto.GetVerificationResponse response = bizPpurioMapper.getVerification(BizPpurioRequestDto.GetVerificationAddCreatedAtRequest.builder()
                .createdAt(timeMinusFiveMinutes)
                .mobileNumber(requestDto.getMobileNumber())
                .verificationCode(requestDto.getVerificationCode())
                .build());
        // 추가 필드 설정 (생성일 설정)
//        verificationAddCreatedAtRequest.setCreateAt(LocalDateTime.now());

//        BizPpurioResponseDto.InsertVerificationResponse.builder().
        return response;
    }

//    @Override
//    public ResponseDto.ResultResponse sendVerificationCode(PhoneNumberRequest phoneNumberRequest) {
//        int totalCount = authMapper.findMemberByMobileNumberCount(phoneNumberRequest).getTotalCount();
//        String msg = "SMS가 발송되었습니다.";
//
//        ResponseStatusCode responseStatusCode = null;
//        if (totalCount > 0) {
//            responseStatusCode = ResponseStatusCode.RESPONSE_FAIL;
//            msg = "이미 존재하는 사용자 입니다.";
//        }
//        else {
//            //TODO SMS 발송
//            responseStatusCode = ResponseStatusCode.RESPONSE_SUCCESS_FIND;
//            msg = "인증문자 발송 되었습니다.";
//        }
//
//
//        return ResponseDto.ResultResponse.builder().code(responseStatusCode.getCode())
//                .status(responseStatusCode.getStatus())
//                .message(msg).build();
//    }
}
