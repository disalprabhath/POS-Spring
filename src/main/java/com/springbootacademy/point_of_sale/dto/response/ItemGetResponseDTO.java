package com.springbootacademy.point_of_sale.dto.response;


import com.springbootacademy.point_of_sale.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemGetResponseDTO {

    private int itemId;

    private String itemName;

    private double balanceQty;

    private double supplierPrice;

    private double sellingPrice;

    private boolean activeState;
}
