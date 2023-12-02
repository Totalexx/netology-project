package ru.netology.vitaliy.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetClientsResponse {
    private final List<CustomerDto> clients;
}
