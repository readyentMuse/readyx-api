package com.readyent.readyx.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;

//@RestController
//@Tag(name = "Sample API", description = "이것은 샘플 API입니다.")
public class SampleController {

    @GetMapping("/hello")
    @Operation(summary = "Hello API", description = "Hello 메시지를 반환합니다.")
    public String hello() {
        return "Hello, Swagger!";
    }
}
