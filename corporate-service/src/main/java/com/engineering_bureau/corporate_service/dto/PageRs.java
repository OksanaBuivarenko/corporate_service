package com.engineering_bureau.corporate_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageRs<T> {

    @Builder.Default
    @Schema(description = "Current time", example = "12432857239")
    private Long timestamp = System.currentTimeMillis();

    @Schema(example = "Collection of objects or just object any type")
    private T data;

    @Schema(example = "0")
    private Integer offset;

    @Schema(example = "20")
    private Integer limit;
}
