package com.springbootacademy.point_of_sale.dto.request;

import com.springbootacademy.point_of_sale.entity.Item;
import com.springbootacademy.point_of_sale.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSaveDTO {


    private String itemName;

    private double qty;

    private Double amount;

    private int items;

}
