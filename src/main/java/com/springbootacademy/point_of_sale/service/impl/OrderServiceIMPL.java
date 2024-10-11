package com.springbootacademy.point_of_sale.service.impl;

import com.springbootacademy.point_of_sale.dto.paginated.PaginatedResponseOrderDetailsDTO;
import com.springbootacademy.point_of_sale.dto.queryinterfaces.OrderDetailInterface;
import com.springbootacademy.point_of_sale.dto.request.RequestOrderSaveDto;
import com.springbootacademy.point_of_sale.dto.response.ResponseOrderDetailsDTO;
import com.springbootacademy.point_of_sale.entity.Order;
import com.springbootacademy.point_of_sale.entity.OrderDetails;
import com.springbootacademy.point_of_sale.repo.CustomerRepo;
import com.springbootacademy.point_of_sale.repo.ItemRepo;
import com.springbootacademy.point_of_sale.repo.OrderDetailsRepo;
import com.springbootacademy.point_of_sale.repo.OrderRepo;
import com.springbootacademy.point_of_sale.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Override
    @Transactional
    public String saveOrder(RequestOrderSaveDto requestOrderSaveDto) {
        Order order=new Order(
                customerRepo.getReferenceById(requestOrderSaveDto.getCustomer()),
                requestOrderSaveDto.getDate(),
                requestOrderSaveDto.getTotal()
        );
        orderRepo.save(order);
        if(orderRepo.existsById(order.getOrderId())){

            List<OrderDetails> orderDetails=modelMapper.
                    map(requestOrderSaveDto.getOrderDetails(),
                    new TypeToken<List<OrderDetails>>(){}
                    .getType());

            for(int i=0; i<orderDetails.size();i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getReferenceById(requestOrderSaveDto.getOrderDetails().get(i).getItems()));
            }

            if(orderDetails.size()>0){
                orderDetailsRepo.saveAll(orderDetails);
            }else{
                throw new RuntimeException("Item Not Saved ");
            }
        }
        return "Saved";
    }


    //Join query
    @Override
    public PaginatedResponseOrderDetailsDTO getAllOrderDetails(boolean status, int page, int size) {
        List<OrderDetailInterface> orderDetailsDTOS = orderRepo.getAllOrderDetails(status, PageRequest.of(page, size));

        List<ResponseOrderDetailsDTO> list = new ArrayList<>();
        for (OrderDetailInterface o : orderDetailsDTOS) {
            ResponseOrderDetailsDTO r = new ResponseOrderDetailsDTO(
                    o.getCustomerName(),
                    o.getCustomerAddress(),
                    o.getContactNumber(),
                    o.getDate(),
                    o.getTotal()
            );
            list.add(r);
        }
        PaginatedResponseOrderDetailsDTO paginatedResponseOrderDetailsDTO = new PaginatedResponseOrderDetailsDTO(
                list,
                orderRepo.countAllOrderDetails(status)
        );

        return paginatedResponseOrderDetailsDTO;
    }

}

