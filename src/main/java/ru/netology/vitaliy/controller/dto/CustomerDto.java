package ru.netology.vitaliy.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDto {
    private int id;
    private String name;
}
