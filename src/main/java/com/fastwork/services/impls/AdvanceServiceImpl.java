package com.fastwork.services.impls;

import com.fastwork.constants.PageableConstants;
import com.fastwork.dtos.advance.AddAdvanceDto;
import com.fastwork.dtos.advance.AdvanceDto;
import com.fastwork.dtos.advance.EditAdvanceDto;
import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;
import com.fastwork.entities.AdvanceEntity;
import com.fastwork.entities.UserEntity;
import com.fastwork.exceptions.CommonException;
import com.fastwork.repositories.AdvanceRepository;
import com.fastwork.repositories.UserRepository;
import com.fastwork.services.AdvanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.fastwork.enums.ResponseCode.ERROR;
import static com.fastwork.enums.ResponseCode.NOT_FOUND;

@Service
public class AdvanceServiceImpl implements AdvanceService {
    @Autowired
    AdvanceRepository advanceRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public PaginatedDataDto<AdvanceDto> getAdvanceByPage(int page) {
        List<AdvanceEntity> allAdvances = advanceRepository.findAll();
        if (page >= 1) {
            Pageable pageable = PageRequest.of(page - 1, PageableConstants.LIMIT);
            Page<AdvanceEntity> advancePage = advanceRepository.findAll(pageable);

            List<AdvanceDto> advanceDtos = advancePage.getContent().stream()
                    .map(AdvanceDto::new)
                    .collect(Collectors.toList());

            return new PaginatedDataDto<>(advanceDtos, page, allAdvances.toArray().length);
        } else {
            List<AdvanceDto> advanceDtos = allAdvances.stream()
                    .map(AdvanceDto::new)
                    .collect(Collectors.toList());
            return new PaginatedDataDto<>(advanceDtos, 1, allAdvances.toArray().length);
        }
    }

    @Override
    public CommonResponseDto<AdvanceDto> getAdvanceById(Long id) {
        return new CommonResponseDto<>(new AdvanceDto(advanceRepository.getById(id)));
    }

    @Override
    public CommonResponseDto<AdvanceDto> createAdvance(AddAdvanceDto addAdvanceDto) {
        UserEntity owner = userRepository.getOne(addAdvanceDto.getOwnerId());
        if (owner == null) {
            throw new CommonException(ERROR, "Người dùng không tồn tại");
        }

        AdvanceEntity advance = new AdvanceEntity();
        advance.setAdvanceDate(new Date(System.currentTimeMillis()));
        advance.setDescription(addAdvanceDto.getDescription());
        advance.setNote(addAdvanceDto.getNote());
        advance.setGiver(addAdvanceDto.getGiver());
        advance.setResponder(addAdvanceDto.getResponder());
        advance.setAmount(addAdvanceDto.getAmount());
        advance.setOwner(owner);
        advance.setCreatedAt(new Date(System.currentTimeMillis()));
        advance.setCreatedBy(owner.getEmail());

        return new CommonResponseDto<>(new AdvanceDto(advanceRepository.save(advance)));
    }

    @Override
    public CommonResponseDto<String> updateAdvance(Long id, EditAdvanceDto editAdvanceDto) {
        AdvanceEntity advance = advanceRepository.getById(id);

        if (advance == null) {
            throw new CommonException(NOT_FOUND, "Không tìm thấy");
        }

        advance.setAmount(editAdvanceDto.getAmount());
        advance.setDescription(editAdvanceDto.getDescription());
        advance.setNote(editAdvanceDto.getNote());
        advance.setGiver(editAdvanceDto.getGiver());
        advance.setResponder(editAdvanceDto.getResponder());

        advanceRepository.save(advance);

        return new CommonResponseDto<>("Edited successfully");
    }

    @Override
    public CommonResponseDto<String> deleteAdvance(Long id) {
        AdvanceEntity advance = advanceRepository.getById(id);

        if (advance == null) {
            throw new CommonException(NOT_FOUND, "Không tìm thấy");
        }

        advanceRepository.delete(advance);

        return new CommonResponseDto<>("Deleted successfully");
    }
}
