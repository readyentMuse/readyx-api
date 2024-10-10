package com.readyent.readyx.mapper;

import com.readyent.readyx.domain.dto.request.MemberRequestDto;
import com.readyent.readyx.domain.dto.request.PhoneNumberRequest;
import com.readyent.readyx.domain.dto.response.AuthRequestDto;
import com.readyent.readyx.domain.dto.response.MemberResponseDto;
import com.readyent.readyx.domain.dto.response.PaginationResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Mapper
public interface AuthMapper {
    PaginationResponseDto.TotalCountResponse findMemberByMobileNumberCount(PhoneNumberRequest phoneNumberRequest);
}