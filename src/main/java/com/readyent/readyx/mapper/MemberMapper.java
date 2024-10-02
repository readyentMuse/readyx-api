package com.readyent.readyx.mapper;

import com.readyent.readyx.domain.dto.request.MemberRequestDto;
import com.readyent.readyx.domain.dto.response.MemberResponseDto;
import com.readyent.readyx.domain.dto.response.PaginationResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    int insertMember(MemberRequestDto.InsertRequest requestDto);

    int updateMember(@Param("memberId") Long memberId, @Param("requestDto") MemberRequestDto.UpdateRequest requestDto);

    int moveMemberToWithdrawn(@Param("memberId") Long memberId);

    int updateMemberToWithdrawn(@Param("memberId") Long memberId);

    MemberResponseDto.GetResponse getMember(@Param("memberId") Long memberId);

    List<MemberResponseDto.GetResponse> findMemberByStatuse(MemberRequestDto.FindMemberByStatuseRequest requestDto);

    List<MemberResponseDto.GetResponse> findMemberByNameAndMobileNumber(MemberRequestDto.FindMemberByNameAndMobileNumberRequest requestDto);

    List<MemberResponseDto.GetStatusGroupCountResponse> findMemberByDate(MemberRequestDto.FindMemberByDateRequest requestDto);

    PaginationResponseDto.TotalCountResponse findMemberByStatuseCount(MemberRequestDto.FindMemberByStatuseRequest requestDto);
    PaginationResponseDto.TotalCountResponse findMemberByNameAndMobileNumberCount(MemberRequestDto.FindMemberByNameAndMobileNumberRequest requestDto);
}