package com.springbootacademy.point_of_sale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDto {

    private int customer;

    private Date date;

    private Double total;

    private List<RequestOrderDetailsSaveDTO> orderDetails;
}
