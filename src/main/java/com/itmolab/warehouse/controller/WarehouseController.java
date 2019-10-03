package com.itmolab.warehouse.controller;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.itmolab.warehouse.model.Item;
import com.itmolab.warehouse.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WarehouseController {

    @Autowired
    private IItemService itemService;

    /*TODO
    *  GET api/warehouse/items -> get all items
    *  GET api/warehouse/items/{item_id} -> get item by id
    *  POST api/warehouse/items -> create item
    *  PUT api/warehouse/items/{id}/addition/{amount} -> increase amount
    * */

    @GetMapping("/api/warehouse/items")
    public List<Item> findItems() {

        return itemService.findAll();

    }

    @GetMapping("/api/warehouse/items/{item_id}")
    public Item findItemById(@PathVariable(value = "item_id") Long  id) {

        return itemService.findById(id).get();

    }

    @PostMapping ("/api/warehouse/items")
    public Item createItem(@Valid @RequestBody Item item) {
        return itemService.save(item);

    }

    @PutMapping("/api/warehouse/items/{id}/addition/{amount}")
    public Item createItem(@PathVariable(value = "id") Long id) {
        Item item = new Item();
        item.setName(name);
        item.setAmount(amount);
        item.setPrice(price);
        return item;

    }

}
