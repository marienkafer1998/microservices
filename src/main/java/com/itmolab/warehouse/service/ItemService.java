package com.itmolab.warehouse.service;

import com.itmolab.warehouse.model.Item;
import com.itmolab.warehouse.repository.ItemRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; //!!!!
import org.springframework.stereotype.Service;

@Service
public class ItemService implements IItemService{

    @Autowired
    private ItemRepository repository;

    public ItemService (ItemRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Item> findAll(){
        var items = (List<Item>) repository.findAll();

        return items;
    }

    @Override
    public Optional<Item> findById(Long id){
        var item = repository.findById(id);

        return item;
    }

    @Override
    public Item CreateItem(String name, int amount, float price){
        Item itemModel = new Item();
        itemModel.setName(name);
        itemModel.setAmount(amount);
        itemModel.setPrice(price);

        return repository.save(itemModel);
    }

    @Override
    public void IncreaseItemAmount(Long id, int amount){
        if (repository.existsById(id)) {
            Optional<Item> itemOptional = repository.findById(id);
            Item item= itemOptional.get();
            item.setAmount(item.getAmount()+amount);
            repository.save(item);
        }
    }
}
