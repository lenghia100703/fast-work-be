package com.fastwork.services;

import com.fastwork.dtos.advance.AddAdvanceDto;
import com.fastwork.dtos.advance.AdvanceDto;
import com.fastwork.dtos.advance.EditAdvanceDto;
import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;

public interface AdvanceService {
    PaginatedDataDto<AdvanceDto> getAdvanceByPage(int page);

    CommonResponseDto<AdvanceDto> getAdvanceById(Long id);

    CommonResponseDto<AdvanceDto> createAdvance(AddAdvanceDto addAdvanceDto);

    CommonResponseDto<String> updateAdvance(Long id, EditAdvanceDto editAdvanceDto);

    CommonResponseDto<String> deleteAdvance(Long id);
}
