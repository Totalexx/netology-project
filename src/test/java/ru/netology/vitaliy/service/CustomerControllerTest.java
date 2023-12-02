package ru.netology.vitaliy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.vitaliy.OperationHistoryApiApplicationTest;
import ru.netology.vitaliy.controller.CustomerController;
import ru.netology.vitaliy.controller.dto.CustomerDto;
import ru.netology.vitaliy.controller.dto.GetClientsResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerControllerTest extends OperationHistoryApiApplicationTest {

    @Autowired
    CustomerController customerController;

    @Test
    void getClientsTest() {
        GetClientsResponse customers = customerController.getClients();
        CustomerDto customer1 = customers.getClients().get(0);
        CustomerDto customer2 = customers.getClients().get(1);

        assertEquals(1, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals(2, customer2.getId());
        assertEquals("Boot", customer2.getName());

        assertEquals(2, customers.getClients().size());
    }

    @Test
    void getCustomer() {
        CustomerDto customer = customerController.getCustomer(1);

        assertEquals(1, customer.getId());
        assertEquals("Spring", customer.getName());
    }

    @Test
    void addCustomer() {
        GetClientsResponse customers = customerController.getClients();
        int size = customers.getClients().size();

        customerController.addCustomer(3,"Test");
        GetClientsResponse newCustomers = customerController.getClients();
        int newSize = newCustomers.getClients().size();
        assertEquals(1, newSize - size);

        CustomerDto customerDto = newCustomers.getClients().get(newSize - 1);
        assertEquals(3, customerDto.getId());
        assertEquals("Test", customerDto.getName());
    }

    @Test
    void removeCustomer() {
        customerController.removeCustomer(1);
        GetClientsResponse customers = customerController.getClients();

        assertEquals(1, customers.getClients().size());

        CustomerDto customer = customers.getClients().get(0);
        assertEquals(2, customer.getId());
        assertEquals("Boot", customer.getName());
    }
}
