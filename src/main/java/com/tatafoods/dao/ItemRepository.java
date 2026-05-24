package com.tatafoods.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tatafoods.model.Item;

import jakarta.transaction.Transactional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
	    @Modifying
	    @Transactional
	    @Query("update Item set rating =?2 where itemId=?1")
        public int updateRating(int itemId,double rating) ;
}
