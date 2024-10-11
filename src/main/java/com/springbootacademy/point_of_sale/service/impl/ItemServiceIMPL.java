package com.springbootacademy.point_of_sale.service.impl;

import com.springbootacademy.point_of_sale.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.point_of_sale.dto.request.ItemSaveDTO;
import com.springbootacademy.point_of_sale.dto.response.ItemGetResponseDTO;
import com.springbootacademy.point_of_sale.entity.Item;
import com.springbootacademy.point_of_sale.exception.NotFoundException;
import com.springbootacademy.point_of_sale.repo.ItemRepo;
import com.springbootacademy.point_of_sale.service.ItemService;
import com.springbootacademy.point_of_sale.utill.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String saveItem(ItemSaveDTO itemSaveDTO) {

//        Item = new Item(
//                1,
//                itemSaveDTO.getItemName(),
//                itemSaveDTO.getMeasuringUnitType(),
//                itemSaveDTO.getBalanceQty(),
//                itemSaveDTO.getSupplierPrice(),
//                itemSaveDTO.getSellingPrice(),
//                true
//        );
//        itemRepo.save(item);
//        return itemSaveDTO.getItemName();

        Item item = modelMapper.map(itemSaveDTO,Item.class);
        if(!itemRepo.existsById(item.getItemId())){
            itemRepo.save(item);
            return item.getItemId()+ " item saved";
        }else {
            throw new DuplicateKeyException("Already Added");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {
        boolean activeState= true;
        List<Item> items= itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName,activeState);
        if(items.size()>0){
            List<ItemGetResponseDTO> itemGetResponseDTO= modelMapper.map(items,new TypeToken<List<ItemGetResponseDTO>>(){}.getType());
            return itemGetResponseDTO;
        }else {
            throw new NotFoundException("No Item Found");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusMPT(String itemName) {
        boolean activeState= true;
        List<Item> items= itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName,activeState);
        if(items.size()>0){

            List<ItemGetResponseDTO> itemGetResponseDTO= itemMapper.entityListToDtoList(items);

            return itemGetResponseDTO;
        }else {
            throw new NotFoundException("No Item Found");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByActiveStatus(boolean activeStatus){

        List<Item> items= itemRepo.findAllByActiveStateEquals(activeStatus);
        if(items.size()>0){

            List<ItemGetResponseDTO> itemGetResponseDTO= itemMapper.entityListToDtoList(items);

            return itemGetResponseDTO;
        }else {
            throw new NotFoundException("No Item Found");
        }
    }

    @Override
    public PaginatedResponseItemDTO getItemByActiveStatusWithPaginated(boolean activeStatus, int page, int size) {
        Page<Item> items=itemRepo.findAllByActiveStateEquals(activeStatus, PageRequest.of(page,size));
    //    int count=itemRepo.countAllByActiveStateEquals(activeStatus);
        if(items.getSize()<1){
            throw new NotFoundException("No Data");
        }else{
            PaginatedResponseItemDTO paginatedResponseItemDTO= new PaginatedResponseItemDTO(
                    itemMapper.ListDTOToPage(items),
                    itemRepo.countAllByActiveStateEquals(activeStatus)
            );
            return paginatedResponseItemDTO;
        }
    }
}
