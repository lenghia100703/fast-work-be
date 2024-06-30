package com.fastwork.controllers.impls;

import com.fastwork.controllers.AdvanceController;
import com.fastwork.dtos.advance.AddAdvanceDto;
import com.fastwork.dtos.advance.AdvanceDto;
import com.fastwork.dtos.advance.EditAdvanceDto;
import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;
import com.fastwork.services.AdvanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvanceControllerImpl implements AdvanceController {
    @Autowired
    AdvanceService advanceService;

    @Override
    public PaginatedDataDto<AdvanceDto> getAdvanceByPage(int page) {
        return advanceService.getAdvanceByPage(page);
    }

    @Override
    public CommonResponseDto<AdvanceDto> getAdvanceById(Long id) {
        return advanceService.getAdvanceById(id);
    }

    @Override
    public CommonResponseDto<AdvanceDto> createAdvance(AddAdvanceDto addAdvanceDto) {
        return advanceService.createAdvance(addAdvanceDto);
    }

    @Override
    public CommonResponseDto<String> updateAdvance(Long id, EditAdvanceDto editAdvanceDto) {
        return advanceService.updateAdvance(id, editAdvanceDto);
    }

    @Override
    public CommonResponseDto<String> deleteAdvance(Long id) {
        return advanceService.deleteAdvance(id);
    }
}
