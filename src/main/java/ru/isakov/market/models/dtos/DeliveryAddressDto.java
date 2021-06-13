package ru.isakov.market.models.dtos;

import lombok.Data;

@Data
public class DeliveryAddressDto {
//    private Integer zipCode;
//    private Country country; // ссылки в БД
//    private Region region; // ссылки в БД
//    private City city; // ссылки в БД
    private String address;
}
