package com.readyent.readyx.service.impl;

import com.readyent.readyx.constant.ResponseStatusCode;
import com.readyent.readyx.domain.dto.request.MemberRequestDto;
import com.readyent.readyx.domain.dto.response.MemberResponseDto;
import com.readyent.readyx.domain.dto.response.ResponseDto;
import com.readyent.readyx.mapper.MemberMapper;
import com.readyent.readyx.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    @Override
    public ResponseDto.ResultResponse insertMember(MemberRequestDto.InsertRequest requestDto) {
        extracted(requestDto);

        int insertMemberCount = memberMapper.insertMember(requestDto);
//        log.info(String.format("Insert member: %d", count));

        ResponseStatusCode responseStatusCode = ResponseStatusCode.RESPONSE_SUCCESS_INST;
        return ResponseDto.ResultResponse.builder()
                .code(responseStatusCode.getCode())
                .message(responseStatusCode.getMessage())
                .status(responseStatusCode.getStatus())
                .build();
    }

    /**
     * 파일 -> Byte 변환
     * @param requestDto
     */
    private static void extracted(MemberRequestDto.InsertRequest requestDto) {
        MultipartFile file = requestDto.getBusinessCardFileData();
        if (file != null && !file.isEmpty()) {
            try {
                requestDto.setBusinessCardFileData(file);  // 바이트 배열로 변환 및 저장
            } catch (IOException e) {
//                log.error(e.getMessage() + "에러에러", e);
                throw new RuntimeException(e);
            }
//            log.info("Uploaded file name: " + file.getOriginalFilename());
        }
    }

    @Override
    public ResponseDto.ResultResponse updateMember(Long memberId, MemberRequestDto.UpdateRequest requestDto) {
        extracted(requestDto);

        int updateMemberCount = memberMapper.updateMember(memberId, requestDto);

        ResponseStatusCode responseStatusCode = ResponseStatusCode.RESPONSE_SUCCESS_UPDT;
        return ResponseDto.ResultResponse.builder()
                .code(responseStatusCode.getCode())
                .message(responseStatusCode.getMessage())
                .status(responseStatusCode.getStatus())
                .build();
    }

    @Override
    public ResponseDto.ResultResponse deleteMember(Long memberId) {
        int moveMemberToWithdrawnCount = memberMapper.moveMemberToWithdrawn(memberId);
        int updateMemberToWithdrawnCount = memberMapper.updateMemberToWithdrawn(memberId);

        ResponseStatusCode responseStatusCode = ResponseStatusCode.RESPONSE_SUCCESS_DELL;
        return ResponseDto.ResultResponse.builder()
                .code(responseStatusCode.getCode())
                .message(responseStatusCode.getMessage())
                .status(responseStatusCode.getStatus())
                .build();
    }

    @Override
    public MemberResponseDto.GetResponse getMember(Long memberId) {
        return memberMapper.getMember(memberId);
    }

    @Override
    public MemberResponseDto.FindMemberByStatuseResponse findMemberByStatuse(MemberRequestDto.FindMemberByStatuseRequest statusList) {
        return MemberResponseDto.FindMemberByStatuseResponse.builder()
                .memberList(memberMapper.findMemberByStatuse(statusList))
                .totalCount(memberMapper.findMemberByStatuseCount(statusList))
                .build();
//        return memberMapper.findMemberByStatuse(statusList);
    }

    @Override
    public MemberResponseDto.FindMemberByNameAndMobileNumberResponse findMemberByNameAndMobileNumber(MemberRequestDto.FindMemberByNameAndMobileNumberRequest requestDto) {
        return MemberResponseDto.FindMemberByNameAndMobileNumberResponse.builder()
                .memberList(memberMapper.findMemberByNameAndMobileNumber(requestDto))
                .totalCount(memberMapper.findMemberByNameAndMobileNumberCount(requestDto))
                .build();
    }


    @Override
    public List<MemberResponseDto.GetStatusGroupCountResponse> findMemberByDate(MemberRequestDto.FindMemberByDateRequest requestDto) {
        return memberMapper.findMemberByDate(requestDto);
    }
}
