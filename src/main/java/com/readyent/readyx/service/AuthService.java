package com.readyent.readyx.service;

import com.readyent.readyx.domain.dto.request.PhoneNumberRequest;
import com.readyent.readyx.domain.dto.response.AuthRequestDto;
import com.readyent.readyx.domain.dto.response.PaginationResponseDto;
import com.readyent.readyx.domain.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 인증 서비스
 */
public interface AuthService {
    ResponseDto.ResultResponse sendVerificationCode(@RequestBody PhoneNumberRequest phoneNumberRequest);
}
