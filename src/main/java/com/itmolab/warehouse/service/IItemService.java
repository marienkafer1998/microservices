package com.itmolab.warehouse.service;

import com.itmolab.warehouse.model.Item;
import java.util.List;
import java.util.Optional;

public interface IItemService {
    List<Item> findAll();
    Optional<Item> findById(Long id);
    Item CreateItem(String name, int amount, float price);
    void IncreaseItemAmount(Long id, int amount);
}
