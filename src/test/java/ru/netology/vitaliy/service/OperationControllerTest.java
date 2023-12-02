package ru.netology.vitaliy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.vitaliy.OperationHistoryApiApplicationTest;
import ru.netology.vitaliy.configuration.OperationProcessingProperties;
import ru.netology.vitaliy.controller.OperationController;
import ru.netology.vitaliy.controller.dto.OperationDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OperationControllerTest extends OperationHistoryApiApplicationTest {

    @Autowired
    OperationController operationController;

    @Autowired
    OperationProcessingProperties properties;

    @Test
    void addOperationsTest() throws InterruptedException {
        OperationDto dto = new OperationDto(1,1,100,"RUB","Steam");
        operationController.addOperation(dto);

        Thread.sleep(properties.getTimeout());

        List<OperationDto> operations = operationController.getOperations(1);
        OperationDto dto1 = operations.get(0);

        assertEquals(dto1.getId(),dto.getId());
        assertEquals(dto1.getCustomerId(),dto.getCustomerId());
        assertEquals(dto1.getSum(),dto.getSum());
        assertEquals(dto1.getCurrency(),dto.getCurrency());
        assertEquals(dto1.getMerchant(),dto.getMerchant());
    }

    @Test
    void getOperationsTest() throws InterruptedException {
        OperationDto operation1 = new OperationDto(1,1,100,"RUB","Steam");
        operationController.addOperation(operation1);
        Thread.sleep(properties.getTimeout());
        OperationDto operation2 = new OperationDto(2,1,100,"RUB","Steam");
        operationController.addOperation(operation2);
        Thread.sleep(properties.getTimeout());

        List<OperationDto> operations = operationController.getOperations(1);
        assertEquals(operation1,operations.get(0));
        assertEquals(operation2, operations.get(1));
    }

    @Test
    void removeOperationsTest() throws InterruptedException {
        OperationDto operation1 = new OperationDto(1,1,100,"RUB","Steam");
        operationController.addOperation(operation1);
        Thread.sleep(properties.getTimeout());

        operationController.removeOperation(1, 1);
        assertEquals(0, operationController.getOperations(1).size());
    }
}
