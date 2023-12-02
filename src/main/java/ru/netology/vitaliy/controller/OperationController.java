package ru.netology.vitaliy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.netology.vitaliy.controller.dto.OperationDto;
import ru.netology.vitaliy.domain.operation.Operation;
import ru.netology.vitaliy.service.AsyncInputOperationService;
import ru.netology.vitaliy.service.StatementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationController {

    private final AsyncInputOperationService inputOperationService;
    private final StatementService statementService;

    @GetMapping("{customerId}")
    public List<OperationDto> getOperations(@PathVariable int customerId) {
        List<Operation> operations = statementService.getOperations(customerId);
        return operations.stream()
                .map(o -> new OperationDto(o.getId(),o.getCustomerId(),o.getSum(),o.getCurrency(),o.getMerchant()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addOperation(@RequestBody OperationDto operationDto) {
        Operation operation = new Operation(
                operationDto.getId(),
                operationDto.getCustomerId(),
                operationDto.getSum(),
                operationDto.getCurrency(),
                operationDto.getMerchant());

        inputOperationService.addOperation(operation);
    }

    @DeleteMapping("remove")
    public void removeOperation(@RequestParam int customerId, @RequestParam int operationId) {
        statementService.removeOperation(customerId,operationId);
    }
}
