package com.fastwork.controllers;

import com.fastwork.dtos.advance.AddAdvanceDto;
import com.fastwork.dtos.advance.AdvanceDto;
import com.fastwork.dtos.advance.EditAdvanceDto;
import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/advance")
public interface AdvanceController {
    @GetMapping("")
    PaginatedDataDto<AdvanceDto> getAdvanceByPage(@RequestParam(name = "page") int page);

    @GetMapping("/{id}")
    CommonResponseDto<AdvanceDto> getAdvanceById(@PathVariable("id") Long id);

    @PostMapping("")
    CommonResponseDto<AdvanceDto> createAdvance(@RequestBody AddAdvanceDto addAdvanceDto);

    @PutMapping("/{id}")
    CommonResponseDto<String> updateAdvance(@PathVariable("id") Long id, EditAdvanceDto editAdvanceDto);

    @DeleteMapping("/{id}")
    CommonResponseDto<String> deleteAdvance(@PathVariable("id") Long id);
}
