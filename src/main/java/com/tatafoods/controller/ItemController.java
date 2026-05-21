package com.tatafoods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tatafoods.dto.AddItemRequestDTO;
import com.tatafoods.dto.ItemResponseDTO;
import com.tatafoods.service.ItemService;
import com.tatafoods.dto.UpdateItemRequestDTO;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@PostMapping("/add")
	public ItemResponseDTO addItem(@RequestBody AddItemRequestDTO addItemRequestDTO) {
		return itemService.addItem(addItemRequestDTO);	
	}
	
	@GetMapping
	public List<ItemResponseDTO> getAllItems(){
		return itemService.getAllItems();
	}
	
	@GetMapping("/{itemId}")
	public ItemResponseDTO getItems(@PathVariable int itemId) {
		return itemService.getItem(itemId);
		 
	}
	@GetMapping("/byDiscount")
	public List<ItemResponseDTO> getItemsByDiscountGreaterThan(@RequestParam(name="discount",required = false) Double discount){
		return itemService.getItemsByDiscountGreaterThan(discount);
		
	}
	@DeleteMapping("/delete/{itemId}")
	public String deleteItem(@PathVariable int itemId) {
		return itemService.deleteItem(itemId);
	}
	
	@PutMapping("/update/{id}")
	public ItemResponseDTO UpdateItemRequestDTO(@PathVariable int id,@RequestBody UpdateItemRequestDTO updateItemRequestDTO) {
		return itemService.updateItem(id, updateItemRequestDTO);
		
		
	}
	
	
	
	
	
	
	

}
