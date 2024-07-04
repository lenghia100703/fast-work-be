package com.fastwork.controllers.impls;

import com.fastwork.controllers.ExpenseController;
import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;
import com.fastwork.dtos.expense.AddExpenseDto;
import com.fastwork.dtos.expense.EditExpenseDto;
import com.fastwork.dtos.expense.ExpenseDto;
import com.fastwork.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseControllerImpl implements ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @Override
    public PaginatedDataDto<ExpenseDto> getExpenseByPage(int page) {
        return expenseService.getExpenseByPage(page);
    }

    @Override
    public CommonResponseDto<ExpenseDto> getExpenseById(Long id) {
        return expenseService.getExpenseById(id);
    }

    @Override
    public CommonResponseDto<ExpenseDto> createExpense(AddExpenseDto addExpenseDto) {
        return expenseService.createExpense(addExpenseDto);
    }

    @Override
    public CommonResponseDto<String> updateExpense(Long id, EditExpenseDto editExpenseDto) {
        return expenseService.updateExpense(id, editExpenseDto);
    }

    @Override
    public CommonResponseDto<String> deleteExpense(Long id) {
        return expenseService.deleteExpense(id);
    }
}
