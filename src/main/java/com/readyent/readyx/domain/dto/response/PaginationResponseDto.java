package com.readyent.readyx.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class PaginationResponseDto {
    @Getter
    @Setter
    public static class TotalCountResponse {
        @Schema(description = "전체 카운트", example = "10", defaultValue = "10")
        private int totalCount = 0;
    }
}
