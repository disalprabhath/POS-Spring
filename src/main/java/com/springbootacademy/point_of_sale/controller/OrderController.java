package com.springbootacademy.point_of_sale.controller;

import com.springbootacademy.point_of_sale.dto.paginated.PaginatedResponseOrderDetailsDTO;
import com.springbootacademy.point_of_sale.dto.request.RequestOrderSaveDto;
import com.springbootacademy.point_of_sale.service.OrderService;
import com.springbootacademy.point_of_sale.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/save_order")
    public ResponseEntity<StandardResponse> saveOrder(@RequestBody RequestOrderSaveDto requestOrderSaveDto) {
        String message = orderService.saveOrder(requestOrderSaveDto);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Success", message),
                HttpStatus.CREATED
        );
    }

    @GetMapping(
            path = {"/get_order_details"},
            params = {"stateType","page","size"}
    )
    public ResponseEntity<StandardResponse> getAllOrderDetails(
            @RequestParam(value = "stateType") String stateType,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ){
        PaginatedResponseOrderDetailsDTO p=null;
        if(stateType.equalsIgnoreCase("active") | stateType.equalsIgnoreCase("inactive")){
            boolean status=stateType.equalsIgnoreCase("active") ? true:false;
            p=orderService.getAllOrderDetails(status,page,size);
        }
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", p),
                HttpStatus.OK
        );
    }
}
