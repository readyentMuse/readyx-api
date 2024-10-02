package com.readyent.readyx.service;

import com.readyent.readyx.domain.dto.request.MemberRequestDto;
import com.readyent.readyx.domain.dto.response.MemberResponseDto;
import com.readyent.readyx.domain.dto.response.ResponseDto;

import java.util.List;

/**
 * 회원 컨트롤러
 */
public interface MemberService {
    ResponseDto.ResultResponse insertMember(MemberRequestDto.InsertRequest requestDto);

    ResponseDto.ResultResponse updateMember(Long memberId, MemberRequestDto.UpdateRequest requestDto);

    ResponseDto.ResultResponse deleteMember(Long memberId);

    MemberResponseDto.GetResponse getMember(Long memberId);

    MemberResponseDto.FindMemberByStatuseResponse findMemberByStatuse(MemberRequestDto.FindMemberByStatuseRequest statusList);

    MemberResponseDto.FindMemberByNameAndMobileNumberResponse findMemberByNameAndMobileNumber(MemberRequestDto.FindMemberByNameAndMobileNumberRequest request);

    List<MemberResponseDto.GetStatusGroupCountResponse> findMemberByDate(MemberRequestDto.FindMemberByDateRequest requestDto);
}
