package com.springbootacademy.point_of_sale.dto.request;

import com.springbootacademy.point_of_sale.entity.enums.MeasuringUnitType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemSaveDTO {

    private String itemName;

    private MeasuringUnitType measuringUnitType;

    private double balanceQty;

    private double supplierPrice;

    private double sellingPrice;

}