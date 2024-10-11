package com.springbootacademy.point_of_sale.service;

import com.springbootacademy.point_of_sale.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.point_of_sale.dto.request.ItemSaveDTO;
import com.springbootacademy.point_of_sale.dto.response.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    public String saveItem(ItemSaveDTO itemSaveDTO);

    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);

    List<ItemGetResponseDTO> getItemByNameAndStatusMPT(String itemName);

    List<ItemGetResponseDTO> getItemByActiveStatus(boolean activeStatus);

    PaginatedResponseItemDTO getItemByActiveStatusWithPaginated(boolean activeStatus, int page, int size);
}
