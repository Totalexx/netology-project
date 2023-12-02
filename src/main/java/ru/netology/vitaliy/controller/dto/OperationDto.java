package ru.netology.vitaliy.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class OperationDto {
    private final int id;
    private final int customerId;
    private int sum;
    private String currency;
    private String merchant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationDto that = (OperationDto) o;

        if (id != that.id) return false;
        if (customerId != that.customerId) return false;
        if (sum != that.sum) return false;
        if (!Objects.equals(currency, that.currency)) return false;
        return Objects.equals(merchant, that.merchant);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + customerId;
        result = 31 * result + sum;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (merchant != null ? merchant.hashCode() : 0);
        return result;
    }
}
