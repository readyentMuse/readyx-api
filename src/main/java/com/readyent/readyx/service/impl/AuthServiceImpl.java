package com.readyent.readyx.service.impl;

import com.readyent.readyx.constant.ResponseStatusCode;
import com.readyent.readyx.domain.dto.request.BizPpurioRequestDto;
import com.readyent.readyx.domain.dto.request.PhoneNumberRequest;
import com.readyent.readyx.domain.dto.response.AuthRequestDto;
import com.readyent.readyx.domain.dto.response.MemberResponseDto;
import com.readyent.readyx.domain.dto.response.PaginationResponseDto;
import com.readyent.readyx.domain.dto.response.ResponseDto;
import com.readyent.readyx.mapper.AuthMapper;
import com.readyent.readyx.mapper.MemberMapper;
import com.readyent.readyx.service.AuthService;
import com.readyent.readyx.service.BizPpurioService;
import com.readyent.readyx.utils.BizppurioMessageUtil;
import com.readyent.readyx.utils.BizppurioUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthMapper authMapper;
    private final BizPpurioService bizPpurioService;
    private final BizppurioUtil bizppurioUtil;

    @Override
    public ResponseDto.ResultResponse sendVerificationCode(PhoneNumberRequest phoneNumberRequest) {
        int totalCount = authMapper.findMemberByMobileNumberCount(phoneNumberRequest).getTotalCount();
        String msg = "SMS가 발송되었습니다.";

        ResponseStatusCode responseStatusCode = null;
        if (totalCount > 0) {
            responseStatusCode = ResponseStatusCode.RESPONSE_FAIL;
            msg = "이미 존재하는 사용자 입니다.";
        }
        else {
            String token = bizppurioUtil.getToken();
            log.info("token: {}", token);
            BizPpurioRequestDto.InsertVerificationRequest response = BizppurioMessageUtil.sendVerification(token, phoneNumberRequest.getPhoneNumber());
            log.info("response: {}", response);
            if (response != null) {
                bizPpurioService.insertVerification(response);
                responseStatusCode = ResponseStatusCode.RESPONSE_SUCCESS_FIND;
                msg = "인증문자 발송 되었습니다.";
            }
            else {
                responseStatusCode = ResponseStatusCode.RESPONSE_FAIL;
                msg = "발송에 실패 했습니다.";
            }
        }


        return ResponseDto.ResultResponse.builder().code(responseStatusCode.getCode())
                .status(responseStatusCode.getStatus())
                .message(msg).build();
    }
}
