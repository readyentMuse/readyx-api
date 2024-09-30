package com.readyent.readyx.controller;

import com.readyent.readyx.domain.dto.request.PhoneNumberRequest;
import com.readyent.readyx.domain.dto.response.JwtResponse;
import com.readyent.readyx.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "인증 API", description = "인증 API<br/>bearerAuth 불필요")
public class AuthController {
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Operation(summary = "Token 발급", description = "Token 발급 합니다.")
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody PhoneNumberRequest phoneNumberRequest) {
        String token = jwtTokenUtil.generateToken(phoneNumberRequest.getPhoneNumber());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}

