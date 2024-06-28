package com.fastwork.controllers.impls;

import com.fastwork.controllers.ConstructionController;
import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;
import com.fastwork.dtos.construction.AddConstructionDto;
import com.fastwork.dtos.construction.ConstructionDto;
import com.fastwork.dtos.construction.EditConstructionDto;
import com.fastwork.services.ConstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConstructionControllerImpl implements ConstructionController {
    @Autowired
    ConstructionService constructionService;

    @Override
    public PaginatedDataDto<ConstructionDto> getConstructionByPage(int page) {
        return constructionService.getConstructionByPage(page);
    }

    @Override
    public CommonResponseDto<ConstructionDto> getConstructionById(Long id) {
        return constructionService.getConstructionById(id);
    }

    @Override
    public CommonResponseDto<ConstructionDto> createConstruction(AddConstructionDto addConstructionDto) {
        return constructionService.createConstruction(addConstructionDto);
    }

    @Override
    public CommonResponseDto<String> updateConstruction(Long id, EditConstructionDto editConstructionDto) {
        return constructionService.updateConstruction(id, editConstructionDto);
    }

    @Override
    public CommonResponseDto<String> deleteConstruction(Long id) {
        return constructionService.deleteConstruction(id);
    }
}
