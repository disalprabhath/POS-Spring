package com.springbootacademy.point_of_sale.utill.mappers;

import com.springbootacademy.point_of_sale.dto.response.ItemGetResponseDTO;
import com.springbootacademy.point_of_sale.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    //List<Item> items -----> List<ItemGetResponseDTO>
    List<ItemGetResponseDTO> entityListToDtoList(List<Item> items);

    //    Page<Item> items ----> List<ItemGetResponseDTO> list;
    List<ItemGetResponseDTO> ListDTOToPage(Page<Item> items);

}
