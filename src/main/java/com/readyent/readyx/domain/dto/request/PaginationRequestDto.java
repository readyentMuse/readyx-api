package com.readyent.readyx.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationRequestDto {
    @Getter
    @Setter
    public static class PageRequest {
        @Schema(description = "페이지, 기본 페이지 번호는 0 (첫 페이지)", example = "0", defaultValue = "0")
        private int page = 0; // 기본 페이지 번호는 0 (첫 페이지)
        @Schema(description = "페이지 사이즈", example = "10", defaultValue = "10")
        private int size = 10; // 기본 페이지 크기는 10
        @Schema(description = "정렬", example = "DESC", defaultValue = "DESC")
        private String sortType = "DESC";

        public int getOffset() {
            return page * size;
        }
    }
}
