package com.tatafoods.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tatafoods.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{

}
