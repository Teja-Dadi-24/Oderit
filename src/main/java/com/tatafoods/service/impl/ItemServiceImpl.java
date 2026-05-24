package com.tatafoods.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatafoods.dao.ItemRepository;
import com.tatafoods.dto.AddItemRequestDTO;
import com.tatafoods.dto.ItemResponseDTO;
import com.tatafoods.dto.UpdateItemRequestDTO;
import com.tatafoods.dto.UpdateRatingDTO;
import com.tatafoods.exceptions.ItemNotFoundException;
import com.tatafoods.model.Item;
import com.tatafoods.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
     ItemRepository itemRepository;
	@Override
	public ItemResponseDTO addItem(AddItemRequestDTO addItemRequestDTO) {
		Item item=new Item();
		item.setItemName(addItemRequestDTO.getItemName());
		item.setPrice(addItemRequestDTO.getPrice());
		item.setStockQuantity(addItemRequestDTO.getStockQuantity());
		item.setDiscount(addItemRequestDTO.getDiscount());
		item.setRating(0);
		Item savedItem = itemRepository.save(item);
		ItemResponseDTO response=new ItemResponseDTO();
		BeanUtils.copyProperties(savedItem, response);
		return response;
	}
	@Override
	public List<ItemResponseDTO> getAllItems() {
		 List<Item> items=itemRepository.findAll();
		 List<ItemResponseDTO> list = items.stream().map(item->{
			 ItemResponseDTO dto=new ItemResponseDTO();
			 BeanUtils.copyProperties(item, dto);
			 return dto;
		 }).toList();
		 return list;
	}
	@Override
	public ItemResponseDTO getItem(int id) {
		// TODO Auto-generated method stub
		Item item = itemRepository.findById(id).
				orElseThrow(()->new ItemNotFoundException("Item not Found with id "+id));
		ItemResponseDTO response=new ItemResponseDTO();
		BeanUtils.copyProperties(item, response);
		return response;
	}
	@Override
	public List<ItemResponseDTO> getItemsByDiscountGreaterThan(Double discount) {
		List<Item> list = itemRepository.findAll();
		if(discount==null) {
			return list.stream().map(item->{
				 ItemResponseDTO dto=new ItemResponseDTO();
				 BeanUtils.copyProperties(item, dto);
				 return dto;
			 }).toList();
			
		}
		
		List<ItemResponseDTO> dtoList2 = list
		.stream()
		.filter(item->item.getDiscount()>discount)
		.map(item->{
			ItemResponseDTO responseDto=new ItemResponseDTO();
			BeanUtils.copyProperties(item, responseDto);
			return responseDto;
		}).toList();
		return dtoList2;
		
	}
	@Override
	public String deleteItem(int id) {
		itemRepository.deleteById(id);
		return "Deleted item with id: "+id;
	}
	@Override
	public ItemResponseDTO updateItem(int id, UpdateItemRequestDTO updateItemRequestDTO) {
		Item item = itemRepository.findById(id).get();
		BeanUtils.copyProperties(updateItemRequestDTO, item);
		Item savedItem = itemRepository.save(item);
		ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
		BeanUtils.copyProperties(savedItem,itemResponseDTO);
		return itemResponseDTO;
		
	}
	@Override
	public ItemResponseDTO updateRating(UpdateRatingDTO updateRatingDTO) {
		int itemId = itemRepository.updateRating(updateRatingDTO.getItemId(),updateRatingDTO.getRating());
		Item item = itemRepository.findById(itemId).get();
		ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
		BeanUtils.copyProperties(item, itemResponseDTO);
		return itemResponseDTO;
		
		
	}
	
	

}
