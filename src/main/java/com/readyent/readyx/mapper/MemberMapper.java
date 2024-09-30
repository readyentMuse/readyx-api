package com.readyent.readyx.mapper;

import com.readyent.readyx.domain.dto.request.MemberRequestDto;
import com.readyent.readyx.domain.dto.response.MemberResponseDto;
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

    List<MemberResponseDto.GetResponse> findMemberByStatuse(@Param("statusList") List<String> statusList);

    List<MemberResponseDto.GetResponse> findMemberByNameOrMobileNumber(MemberRequestDto.FindMemberByNameAndMobileNumber request);

    List<MemberResponseDto.GetStatusGroupCountResponse> findMemberByDate(MemberRequestDto.FindMemberByDateRequest requestDto);
}