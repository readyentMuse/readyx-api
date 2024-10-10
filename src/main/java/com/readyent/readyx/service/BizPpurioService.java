package com.readyent.readyx.service;

import com.readyent.readyx.domain.dto.request.BizPpurioRequestDto;
import com.readyent.readyx.domain.dto.request.PhoneNumberRequest;
import com.readyent.readyx.domain.dto.response.BizPpurioResponseDto;
import com.readyent.readyx.domain.dto.response.ResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 인증 서비스
 */
public interface BizPpurioService {
    ResponseDto.ResultResponse insertToken(BizPpurioRequestDto.InsertTokenRequest requestDto);
    ResponseDto.ResultResponse sendVerificationCode(@RequestBody PhoneNumberRequest phoneNumberRequest);

    BizPpurioResponseDto.TokenResponse getToken();
    //인증 번호 및 발송 로그 저장
    ResponseDto.ResultResponse insertVerification(BizPpurioRequestDto.InsertVerificationRequest requestDto);
    //인증 번호 확인
    BizPpurioResponseDto.GetVerificationResponse getVerification(BizPpurioRequestDto.GetVerificationRequest requestDto);
}
