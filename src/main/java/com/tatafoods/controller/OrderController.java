package com.tatafoods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tatafoods.dto.OrderResponseDTO;
import com.tatafoods.dto.PlaceOrderRequestDTO;
import com.tatafoods.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@PostMapping
	public OrderResponseDTO placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequestDTO) {
		return orderService.placeOrder(placeOrderRequestDTO);

		
	}
	

}