package com.fastwork.controllers;

import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;
import com.fastwork.dtos.construction.AddConstructionDto;
import com.fastwork.dtos.construction.ConstructionDto;
import com.fastwork.dtos.construction.EditConstructionDto;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/construction")
public interface ConstructionController {
    @GetMapping("")
    PaginatedDataDto<ConstructionDto> getConstructionByPage(@RequestParam(name = "page") int page);

    @GetMapping("/{id}")
    CommonResponseDto<ConstructionDto> getConstructionById(@PathVariable("id") Long id);

    @PostMapping("")
    CommonResponseDto<ConstructionDto> createConstruction(@RequestBody AddConstructionDto addConstructionDto);

    @PutMapping("/{id}")
    CommonResponseDto<String> updateConstruction(@PathVariable("id") Long id, EditConstructionDto editConstructionDto);

    @DeleteMapping("/{id}")
    CommonResponseDto<String> deleteConstruction(@PathVariable("id") Long id);
}
