package com.springbootacademy.point_of_sale.service;

import com.springbootacademy.point_of_sale.dto.paginated.PaginatedResponseOrderDetailsDTO;
import com.springbootacademy.point_of_sale.dto.request.RequestOrderSaveDto;

public interface OrderService {
    public String saveOrder(RequestOrderSaveDto requestOrderSaveDto);

    PaginatedResponseOrderDetailsDTO getAllOrderDetails(boolean status, int page, int size);
}
