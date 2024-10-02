package com.readyent.readyx.controller;

import com.readyent.readyx.domain.dto.request.MemberRequestDto;
import com.readyent.readyx.domain.dto.response.MemberResponseDto;
import com.readyent.readyx.domain.dto.response.ResponseDto;
import com.readyent.readyx.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 회원 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearerAuth") // 이 API는 인증이 필요
@Tag(name = "회원 API", description = "회원 API")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원 등록", description = "회원를 등록합니다.")
    @PostMapping(value = "/member", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto.ResultResponse insertMember(@Valid @ModelAttribute MemberRequestDto.InsertRequest requestDto) throws IOException {
        return memberService.insertMember(requestDto);
    }

    @Operation(summary = "회원 정보 수정/승인", description = "회원 정보를 수정/승인 합니다.")
    @PutMapping(value = "/member/{memberId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto.ResultResponse updateMember(
            @Parameter(description = "회원 일련번호", required = true, example = "1")
            @PathVariable(value = "memberId") @NotNull(message = "회원 일련번호는 필수 항목입니다.") Long memberId,
            @Valid @ModelAttribute MemberRequestDto.UpdateRequest requestDto) throws IOException {
        return memberService.updateMember(memberId, requestDto);
    }

    @Operation(summary = "회원 탈퇴", description = "회원을 탈퇴처리 합니다.")
    @DeleteMapping(value = "/member/{memberId}")
    public ResponseDto.ResultResponse deleteMember(
            @Parameter(description = "회원 일련번호", required = true, example = "1")
            @PathVariable(value = "memberId") @NotNull(message = "회원 일련번호는 필수 항목입니다.") Long memberId
    ) throws IOException {
        return memberService.deleteMember(memberId);
    }

    @Operation(summary = "회원 정보 조회", description = "회원 정보를 조회 합니다.")
    @GetMapping(value = "/member/{memberId}")
    public MemberResponseDto.GetResponse getMember(
            @Parameter(description = "회원 일련번호", required = true, example = "1")
            @PathVariable(value = "memberId") @NotNull(message = "회원 일련번호는 필수 항목입니다.") Long memberId
    ) {
//        log.info(String.format("%s", memberId));
        return memberService.getMember(memberId);
    }

    @Operation(summary = "회원 찾기", description = "회원 상태별 찾기")
    @PostMapping(value = "/member/find/by-status")
    public MemberResponseDto.FindMemberByStatuseResponse findMemberByStatuse(@RequestBody MemberRequestDto.FindMemberByStatuseRequest statusList

    ) {
        return memberService.findMemberByStatuse(statusList);
    }

    @Operation(summary = "회원 찾기", description = "회원 이름/전화번호 찾기")
    @PostMapping(value = "/member/find/by-name-and-phone")
    public MemberResponseDto.FindMemberByNameAndMobileNumberResponse findMemberByNameAndMobileNumber(@RequestBody MemberRequestDto.FindMemberByNameAndMobileNumberRequest requestDto
    ) {
        return memberService.findMemberByNameAndMobileNumber(requestDto);
    }

    @Operation(summary = "회원상태 별 카운트", description = "회원상태 별 카운트")
    @PostMapping(value = "/member/find/by-date")
    public List<MemberResponseDto.GetStatusGroupCountResponse> findMemberByDate(@RequestBody MemberRequestDto.FindMemberByDateRequest requestDto
    ) {
        return memberService.findMemberByDate(requestDto);
    }
}
