package com.itmolab.warehouse.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itmolab.warehouse.model.Item;
import com.itmolab.warehouse.model.Message;
import com.itmolab.warehouse.service.IItemService;
import io.swagger.annotations.Api;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@EnableRabbit
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
        itemService.CreateItem(item.getName(), item.getAmount(),item.getPrice());
        var message = new Message("Item is succesfully created!", 200);
        return message;

    }

    @PutMapping("/items/{item_id}/addition/{amount}")
    public Message increaseItemAmount(@PathVariable(value = "item_id") Long id, @PathVariable(value = "amount") Integer amount) {
        //var message = new Message("", 0);
        itemService.IncreaseItemAmount(id, amount);
        var message = new Message("Updated succesfully!", 200);
//        Item item = itemService.findById(id).get();
//        if ((item.getAmount()+amount) >= 0) {
//            itemService.IncreaseItemAmount(id, amount);
//            message = new Message("Updated succesfully!", 200);
//        } else {message = new Message("Not enought items", 410);}

        return message;
    }

    @RabbitListener(queues = "queue")
    public void updateItemAmount(byte[] message) throws IOException {
        String mess = new String(message);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(mess);
        Long id = actualObj.get("item_id").asLong();
        Integer amount = actualObj.get("amount").asInt();
        itemService.IncreaseItemAmount(id, amount);
    }
}
