package com.itmolab.warehouse.service;

import com.itmolab.warehouse.model.Item;
import java.util.List;
import java.util.Optional;

public interface IItemService {
    List<Item> findAll();
    Optional<Item> findById(Long id);
    void CreateItem(String name, Integer amount, Float price);
    void IncreaseItemAmount(Long id, Integer amount);
}
