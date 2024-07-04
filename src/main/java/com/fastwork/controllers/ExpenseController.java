package com.fastwork.controllers;

import com.fastwork.dtos.common.CommonResponseDto;
import com.fastwork.dtos.common.PaginatedDataDto;
import com.fastwork.dtos.construction.AddConstructionDto;
import com.fastwork.dtos.construction.ConstructionDto;
import com.fastwork.dtos.construction.EditConstructionDto;
import com.fastwork.dtos.expense.AddExpenseDto;
import com.fastwork.dtos.expense.EditExpenseDto;
import com.fastwork.dtos.expense.ExpenseDto;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/expense")
public interface ExpenseController {
    @GetMapping("")
    PaginatedDataDto<ExpenseDto> getExpenseByPage(@RequestParam(name = "page") int page);

    @GetMapping("/{id}")
    CommonResponseDto<ExpenseDto> getExpenseById(@PathVariable("id") Long id);

    @PostMapping("")
    CommonResponseDto<ExpenseDto> createExpense(@RequestBody AddExpenseDto addExpenseDto);

    @PutMapping("/{id}")
    CommonResponseDto<String> updateExpense(@PathVariable("id") Long id, EditExpenseDto editExpenseDto);

    @DeleteMapping("/{id}")
    CommonResponseDto<String> deleteExpense(@PathVariable("id") Long id);
}
