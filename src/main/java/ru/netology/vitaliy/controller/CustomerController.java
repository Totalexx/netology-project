package ru.netology.vitaliy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.vitaliy.controller.dto.CustomerDto;
import ru.netology.vitaliy.controller.dto.GetClientsResponse;
import ru.netology.vitaliy.domain.Customer;
import ru.netology.vitaliy.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public GetClientsResponse getClients() {
        List<Customer> customers = customerService.getCustomers();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getName());
            customerDtos.add(customerDto);
        }

        return new GetClientsResponse(customerDtos);
    }

    @GetMapping("{customerId}")
    public CustomerDto getCustomer(@PathVariable int customerId) {
        return customerService.getCustomers().stream()
                .filter(c -> c.getId() == customerId)
                .map(c -> new CustomerDto(c.getId(), c.getName()))
                .findFirst().orElse(null);
    }

    @PostMapping("add")
    public ResponseEntity<Object> addCustomer(@RequestParam int customerId, @RequestParam String name) {
        customerService.addCustomer(customerId,name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Object> removeCustomer(@PathVariable int customerId) {
        customerService.removeCustomer(customerId);
        return ResponseEntity.ok().build();
    }

}
