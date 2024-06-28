package com.fastwork.services.impls;

import com.fastwork.constants.PageableConstants;
import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;
import com.fastwork.dtos.construction.AddConstructionDto;
import com.fastwork.dtos.construction.ConstructionDto;
import com.fastwork.dtos.construction.EditConstructionDto;
import com.fastwork.dtos.user.UserDto;
import com.fastwork.entities.ConstructionEntity;
import com.fastwork.enums.ResponseCode;
import com.fastwork.exceptions.CommonException;
import com.fastwork.repositories.ConstructionRepository;
import com.fastwork.services.ConstructionService;
import com.fastwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConstructionServiceImpl implements ConstructionService {
    @Autowired
    ConstructionRepository constructionRepository;

    @Autowired
    UserService userService;

    @Override
    public PaginatedDataDto<ConstructionDto> getConstructionByPage(int page) {
        List<ConstructionEntity> allConstructions = constructionRepository.findByOwnerId(userService.getCurrentUserId());
        if (page >= 1) {
            Pageable pageable = PageRequest.of(page - 1, PageableConstants.LIMIT);
            Page<ConstructionEntity> constructionPage = constructionRepository.findAll(pageable);

            List<ConstructionDto> constructionDtos = constructionPage.getContent().stream()
                    .map(ConstructionDto::new)
                    .collect(Collectors.toList());

            return new PaginatedDataDto<>(constructionDtos, page, allConstructions.toArray().length);
        } else {
            List<ConstructionDto> constructionDtos = allConstructions.stream()
                    .map(ConstructionDto::new)
                    .collect(Collectors.toList());
            return new PaginatedDataDto<>(constructionDtos, 1, allConstructions.toArray().length);
        }
    }

    @Override
    public CommonResponseDto<ConstructionDto> getConstructionById(Long id) {
        return new CommonResponseDto<>(new ConstructionDto(constructionRepository.getById(id)));
    }

    @Override
    public CommonResponseDto<ConstructionDto> createConstruction(AddConstructionDto addConstructionDto) {
        ConstructionEntity construction = new ConstructionEntity();
        construction.setHostName(addConstructionDto.getHostName());
        construction.setPhone(addConstructionDto.getPhone());
        construction.setAddress(addConstructionDto.getAddress());
        construction.setDescription(addConstructionDto.getDescription());
        construction.setCreatedAt(new Date(System.currentTimeMillis()));
        construction.setCreatedBy(userService.getCurrentUser().getEmail());
        return new CommonResponseDto<>(new ConstructionDto(constructionRepository.save(construction)));
    }

    @Override
    public CommonResponseDto<String> updateConstruction(Long id, EditConstructionDto editConstructionDto) {
        ConstructionEntity construction = constructionRepository.getById(id);

        if (construction == null) {
            throw new CommonException(ResponseCode.NOT_FOUND, "Không tìm thấy công trình!");
        }

        construction.setHostName(editConstructionDto.getHostName());
        construction.setPhone(editConstructionDto.getPhone());
        construction.setAddress(editConstructionDto.getAddress());
        construction.setDescription(editConstructionDto.getDescription());
        construction.setUpdatedAt(new Date(System.currentTimeMillis()));
        construction.setUpdatedBy(userService.getCurrentUser().getEmail());
        constructionRepository.save(construction);

        return new CommonResponseDto<>("Edited successfully");
    }

    @Override
    public CommonResponseDto<String> deleteConstruction(Long id) {
        ConstructionEntity construction = constructionRepository.getById(id);

        if (construction == null) {
            throw new CommonException(ResponseCode.NOT_FOUND, "Không tìm thấy công trình!");
        }

        constructionRepository.delete(construction);
        return new CommonResponseDto<>("Deleted successfully");
    }
}
