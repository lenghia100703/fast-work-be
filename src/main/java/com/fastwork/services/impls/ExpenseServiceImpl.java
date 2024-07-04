package com.fastwork.services.impls;

import com.fastwork.constants.PageableConstants;
import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;
import com.fastwork.dtos.expense.AddExpenseDto;
import com.fastwork.dtos.expense.EditExpenseDto;
import com.fastwork.dtos.expense.ExpenseDto;
import com.fastwork.dtos.user.UserDto;
import com.fastwork.entities.ConstructionEntity;
import com.fastwork.entities.ExpenseEntity;
import com.fastwork.entities.UserEntity;
import com.fastwork.enums.ResponseCode;
import com.fastwork.enums.Role;
import com.fastwork.exceptions.CommonException;
import com.fastwork.repositories.ConstructionRepository;
import com.fastwork.repositories.ExpenseRepository;
import com.fastwork.repositories.UserRepository;
import com.fastwork.services.ExpenseService;
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
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    ConstructionRepository constructionRepository;

    @Autowired
    UserService userService;

    @Override
    public PaginatedDataDto<ExpenseDto> getExpenseByPage(int page) {
        List<ExpenseEntity> allExpenses = expenseRepository.findAll();
        if (page >= 1) {
            Pageable pageable = PageRequest.of(page - 1, PageableConstants.LIMIT);
            Page<ExpenseEntity> expensePage = expenseRepository.findAll(pageable);

            List<ExpenseDto> expenseDtos = expensePage.getContent().stream()
                    .map(ExpenseDto::new)
                    .collect(Collectors.toList());

            return new PaginatedDataDto<>(expenseDtos, page, allExpenses.toArray().length);
        } else {
            List<ExpenseDto> expenseDtos = allExpenses.stream()
                    .map(ExpenseDto::new)
                    .collect(Collectors.toList());
            return new PaginatedDataDto<>(expenseDtos, 1, allExpenses.toArray().length);
        }
    }

    @Override
    public CommonResponseDto<ExpenseDto> getExpenseById(Long id) {
        return new CommonResponseDto<>(new ExpenseDto(expenseRepository.getById(id)));
    }

    @Override
    public CommonResponseDto<ExpenseDto> createExpense(AddExpenseDto addExpenseDto) {
        ConstructionEntity construction = constructionRepository.getById(addExpenseDto.getConstructionId());
        if (construction == null) {
            throw new CommonException(ResponseCode.NOT_FOUND);
        }


        ExpenseEntity expense = new ExpenseEntity();

        expense.setTitle(addExpenseDto.getTitle());
        expense.setDescription(addExpenseDto.getDescription());
        expense.setQuantity(addExpenseDto.getQuantity());
        expense.setPrice(addExpenseDto.getPrice());
        expense.setSellerPhone(addExpenseDto.getSellerPhone());
        expense.setCreatedAt(new Date(System.currentTimeMillis()));
        expense.setCreatedBy(userService.getCurrentUser().getEmail());
        expense.setPurchaseDate(new Date(System.currentTimeMillis()));
        expense.setTotalPrice(addExpenseDto.getPrice() * expense.getQuantity());
        expense.setConstruction(construction);

        return new CommonResponseDto<>(new ExpenseDto(expenseRepository.save(expense)));
    }

    @Override
    public CommonResponseDto<String> updateExpense(Long id, EditExpenseDto editExpenseDto) {
        ExpenseEntity expense = expenseRepository.getById(id);
        if (expense == null) {
            throw new CommonException(ResponseCode.NOT_FOUND);
        }

        expense.setTitle(editExpenseDto.getTitle());
        expense.setDescription(editExpenseDto.getDescription());
        expense.setQuantity(editExpenseDto.getQuantity());
        expense.setPrice(editExpenseDto.getPrice());
        expense.setSellerPhone(editExpenseDto.getSellerPhone());
        expense.setCreatedAt(new Date(System.currentTimeMillis()));
        expense.setCreatedBy(userService.getCurrentUser().getEmail());
        expense.setTotalPrice(editExpenseDto.getPrice() * expense.getQuantity());
        expenseRepository.save(expense);

        return new CommonResponseDto<>("Updated successfully");
    }

    @Override
    public CommonResponseDto<String> deleteExpense(Long id) {
        ExpenseEntity expense = expenseRepository.getById(id);
        if (expense == null) {
            throw new CommonException(ResponseCode.NOT_FOUND);
        }

        expenseRepository.delete(expense);
        return  new CommonResponseDto<>("Deleted successfully");
    }
}
