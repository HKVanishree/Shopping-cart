package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.models.ItemModel;
import com.example.demo.repository.ItemRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    static Logger logger = Logger.getLogger(ItemService.class);

    @Autowired
    ItemRepository itemRepository;

    public ItemModel addNewItem(ItemModel item) {
        if(item.getProduct().getQuantity()<=0)
            return null;
        item.getProduct().setQuantity(item.getProduct().getQuantity()-1);
        logger.info("New item created");
        return itemRepository.save(item);
    }




    public ItemModel getItemById(int id) {
        List<ItemModel> itemModels = new ArrayList<>();
        itemRepository.findAll()
                .forEach(itemModels::add);
        for (ItemModel itemModel : itemModels) {
            if (itemModel.getItemId() == id) {
                return itemModel;
            }

        }
        return null;
    }

    public List<ItemModel> getItems()
    {
                List<ItemModel> itemModels = new ArrayList<>();
        itemRepository.findAll()
                .forEach(itemModels::add);
        logger.info("Fetching all items from inventory");
         return itemModels;


    }

    public void deleteItem(int id) {
        itemRepository.deleteById(id);
        logger.warn("Requested item deleted");
    }

    public void editItem(ItemModel itemModel,int id){
        ItemModel itemModel1=getItemById(id);
        itemModel1.setQuantity(itemModel.getQuantity());
        itemRepository.save(itemModel1);
        logger.warn("Requested item edited");
    }

}
