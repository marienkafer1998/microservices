package com.itmolab.warehouse.repository;

import com.itmolab.warehouse.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends  CrudRepository<Item, Long>{
}
