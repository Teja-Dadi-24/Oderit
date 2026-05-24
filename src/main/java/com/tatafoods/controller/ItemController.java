package com.tatafoods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
import com.tatafoods.dto.UpdateRatingDTO;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@PostMapping("/add")
	public ResponseEntity<ItemResponseDTO> addItem(@RequestBody AddItemRequestDTO addItemRequestDTO) {
		 ItemResponseDTO itemResponseDTO = itemService.addItem(addItemRequestDTO);	
		 return new ResponseEntity<>(itemResponseDTO,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ItemResponseDTO>> getAllItems(){
		List<ItemResponseDTO> allItems = itemService.getAllItems();
//		return new ResponseEntity<>(allItems,HttpStatus.OK);
		return ResponseEntity.ok(allItems);//only for ok method
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
	
	@PutMapping("/uRating")
	public ItemResponseDTO updateRating(@RequestBody UpdateRatingDTO updateRatingDTO) {
		return itemService.updateRating(updateRatingDTO);
	}
	
	
	
	
	
	
	

}
