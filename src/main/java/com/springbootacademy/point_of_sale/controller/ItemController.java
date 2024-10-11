package com.springbootacademy.point_of_sale.controller;

import com.springbootacademy.point_of_sale.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.point_of_sale.dto.request.ItemSaveDTO;
import com.springbootacademy.point_of_sale.dto.response.ItemGetResponseDTO;
import com.springbootacademy.point_of_sale.exception.NotFoundException;
import com.springbootacademy.point_of_sale.service.ItemService;
import com.springbootacademy.point_of_sale.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(path = "/save_item")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveDTO itemSaveDTO) {
        String message = itemService.saveItem(itemSaveDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Success", message),
                HttpStatus.CREATED
        );
    }

//    @GetMapping(path = "get_by_name", params = "name")
//    public List<ItemGetResponseDTO> getItemByNameAndStatus(@RequestParam (value = "name") String itemName){
//
//        List<ItemGetResponseDTO> itemDTOS=itemService.getItemByNameAndStatus(itemName);
//
//        return itemDTOS;
//    }

    @GetMapping(path = "get_by_name", params = "name")
    public ResponseEntity<StandardResponse> getItemByNameAndStatus(@RequestParam(value = "name") String itemName) {
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatus(itemName);
        if (itemDTOS.size() > 0) {
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(201, "success", itemDTOS), HttpStatus.OK
            );
        } else {
            throw new NotFoundException("No Item Found");
        }
    }
        @GetMapping(path = "get_by_name_maptrck", params = "name")
        public ResponseEntity<StandardResponse> getItemByNameAndStatusMPT (@RequestParam(value = "name") String itemName)
        {

            List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatusMPT(itemName);

            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(201, "success", itemDTOS), HttpStatus.OK
            );
        }
        @GetMapping(
                path = "get_all_item_by_status",
                params = {"activeStatus","page","size"}
        )
        public ResponseEntity<StandardResponse> getItemsByActive(
                @RequestParam(value = "activeStatus") boolean activeStatus,
                @RequestParam(value = "page") int page,
                @RequestParam(value = "size") @Max(50) int size
        ) {

            //List<ItemGetResponseDTO> itemDTOS = itemService.getItemByActiveStatus(activeStatus);
            PaginatedResponseItemDTO paginatedResponseItemDTO= itemService.getItemByActiveStatusWithPaginated(activeStatus,page,size);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "success", paginatedResponseItemDTO), HttpStatus.OK
            );
        }

    }
