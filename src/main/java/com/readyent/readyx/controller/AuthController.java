package com.readyent.readyx.controller;

import com.readyent.readyx.domain.dto.request.BizPpurioRequestDto;
import com.readyent.readyx.domain.dto.request.PhoneNumberRequest;
import com.readyent.readyx.domain.dto.response.AuthRequestDto;
import com.readyent.readyx.domain.dto.response.BizPpurioResponseDto;
import com.readyent.readyx.domain.dto.response.JwtResponse;
import com.readyent.readyx.domain.dto.response.ResponseDto;
import com.readyent.readyx.service.AuthService;
import com.readyent.readyx.service.BizPpurioService;
import com.readyent.readyx.service.MemberService;
import com.readyent.readyx.utils.BizppurioUtil;
import com.readyent.readyx.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "인증 API", description = "인증 API<br/>bearerAuth 불필요")
public class AuthController {
    private final JwtTokenUtil jwtTokenUtil;
    private final MemberService memberService;
    private final AuthService authService;
    private final BizPpurioService bizPpurioService;

//    public AuthController(JwtTokenUtil jwtTokenUtil, MemberService memberService, AuthService authService) {
//        this.jwtTokenUtil = jwtTokenUtil;
//        this.memberService = memberService;
//        this.authService = authService;
//    }

    @Operation(summary = "Token 발급", description = "Token 발급 합니다.")
    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticate(@RequestBody PhoneNumberRequest phoneNumberRequest) {
        String token = jwtTokenUtil.generateToken(phoneNumberRequest.getPhoneNumber());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Operation(summary = "SMS 인증번호 발송 (회원가입용)", description = "SMS 인증번호 발송 합니다.<br>이미 회원인 경우 존재하는 사용자로 메시지 리턴됨")
    @PostMapping("/auth/send-code")
    public ResponseDto.ResultResponse sendVerificationCode(@RequestBody PhoneNumberRequest phoneNumberRequest) {
//        String phoneNumber = request.get("phoneNumber");
//        smsAuthService.sendVerificationCode(phoneNumber);
        return authService.sendVerificationCode(phoneNumberRequest);
    }

    //TODO SMS 인증번호 확인
    @Operation(summary = "SMS 인증번호 확인 (회원가입용)", description = "SMS 인증번호 확인 합니다. 인증번호가 틀리거나 인증시간 5분 초과된 경우 리턴값 없음")
    @PostMapping("/auth/verify-code")
    public BizPpurioResponseDto.GetVerificationResponse verifyCode(@RequestBody BizPpurioRequestDto.GetVerificationRequest verificationRequest) {
        return bizPpurioService.getVerification(verificationRequest);
    }

    @PostMapping("/auth/test")
    public BizPpurioResponseDto.TokenResponse test(@RequestBody BizPpurioRequestDto.InsertTokenRequest requestDto) {
//        bizPpurioService.sendVerificationCode(phoneNumberRequest);
//        bizPpurioService.insertToken(requestDto);
//        bizppurioUtil.generateToken();
//        BizPpurioResponseDto.TokenResponse tokenResponse = bizPpurioService.getToken();
        return bizPpurioService.getToken();
    }
}

