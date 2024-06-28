package com.fastwork.services;

import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;
import com.fastwork.dtos.construction.AddConstructionDto;
import com.fastwork.dtos.construction.ConstructionDto;
import com.fastwork.dtos.construction.EditConstructionDto;

public interface ConstructionService {
    PaginatedDataDto<ConstructionDto> getConstructionByPage(int page);

    CommonResponseDto<ConstructionDto> getConstructionById(Long id);

    CommonResponseDto<ConstructionDto> createConstruction(AddConstructionDto addConstructionDto);

    CommonResponseDto<String> updateConstruction(Long id, EditConstructionDto editConstructionDto);

    CommonResponseDto<String> deleteConstruction(Long id);
}
