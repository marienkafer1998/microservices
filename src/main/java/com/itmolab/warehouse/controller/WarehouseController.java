package com.itmolab.warehouse.controller;

import com.itmolab.warehouse.model.Item;
import com.itmolab.warehouse.model.Message;
import com.itmolab.warehouse.service.IItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    @Autowired
    private IItemService itemService;

    /*TODO
    *  GET api/warehouse/items -> get all items
    *  GET api/warehouse/items/{item_id} -> get item by id
    *  POST api/warehouse/items -> create item
    *  PUT api/warehouse/items/{item_id}/addition/{amount} -> increase amount
    * */

    @GetMapping("/items")
    public List<Item> findItems() {
        return itemService.findAll();
    }

    @GetMapping("/items/{item_id}")
    public Item findItemById(@PathVariable(value = "item_id") Long  id) {
        return itemService.findById(id).get();

    }

    @PostMapping ("/items")
    @ResponseBody
    public Message createItem(@Valid @RequestBody Item item ) {
        //System.out.println(item);
        itemService.CreateItem(item.getName(), item.getAmount(),item.getPrice());
        var message = new Message("Item is succesfully created!", 200);
        return message;

    }

    @PutMapping("/items/{item_id}/addition/{amount}")
    public Message increaseItemAmount(@PathVariable(value = "item_id") Long id, @PathVariable(value = "amount") Integer amount) {

        itemService.IncreaseItemAmount(id, amount);
        var message = new Message("Updated succesfully!", 200);
        return message;
    }
}
