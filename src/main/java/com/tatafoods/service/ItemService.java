package com.tatafoods.service;

import java.util.List;

import com.tatafoods.dto.AddItemRequestDTO;
import com.tatafoods.dto.ItemResponseDTO;
import com.tatafoods.dto.UpdateItemRequestDTO;
import com.tatafoods.dto.UpdateRatingDTO;

public interface ItemService {
	
	public  ItemResponseDTO addItem(AddItemRequestDTO addItemRequestDTO);
    public  List<ItemResponseDTO> getAllItems();
    public ItemResponseDTO getItem(int id);
    public  List<ItemResponseDTO> getItemsByDiscountGreaterThan(Double discount);
    public String deleteItem(int id);
    public ItemResponseDTO updateItem(int id,UpdateItemRequestDTO updateItemRequestDTO);
    public ItemResponseDTO updateRating(UpdateRatingDTO updateRatingDTO);
}
