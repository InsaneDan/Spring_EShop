package ru.isakov.market.models.dtos;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
//@NoArgsConstructor
public class OrderDetailsDto {

//    @Pattern(regexp = "(+7|8)[0-9](10)") // TODO проверка?
    private String phone;

    private DeliveryAddressDto deliveryAddress;
//    private String deliveryAddress;
}
