package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.models.ItemModel;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public ItemModel addNewItem(ItemModel item) {
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
         return itemModels;


    }

    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }

    public void editItem(ItemModel itemModel,int id){
        ItemModel itemModel1=getItemById(id);
        itemModel1.setQuantity(itemModel.getQuantity());
        itemRepository.save(itemModel1);
    }

}
