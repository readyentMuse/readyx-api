package com.readyent.readyx.mapper;

import com.readyent.readyx.domain.dto.request.BizPpurioRequestDto;
import com.readyent.readyx.domain.dto.response.BizPpurioResponseDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BizPpurioMapper {
//    PaginationResponseDto.TotalCountResponse findMemberByMobileNumberCount(PhoneNumberRequest phoneNumberRequest);

    int insertToken(BizPpurioRequestDto.InsertTokenRequest requestDto);

    BizPpurioResponseDto.TokenResponse getToken();
    int insertVerification(BizPpurioRequestDto.InsertVerificationRequest requestDto);

    BizPpurioResponseDto.GetVerificationResponse getVerification(BizPpurioRequestDto.GetVerificationAddCreatedAtRequest requestDto);
}