package com.fastwork.services;

import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;
import com.fastwork.dtos.expense.ExpenseDto;
import com.fastwork.dtos.expense.AddExpenseDto;
import com.fastwork.dtos.expense.EditExpenseDto;

public interface ExpenseService {
    PaginatedDataDto<ExpenseDto> getExpenseByPage(int page);

    CommonResponseDto<ExpenseDto> getExpenseById(Long id);

    CommonResponseDto<ExpenseDto> createExpense(AddExpenseDto addExpenseDto);

    CommonResponseDto<String> updateExpense(Long id, EditExpenseDto editExpenseDto);

    CommonResponseDto<String> deleteExpense(Long id);
}
