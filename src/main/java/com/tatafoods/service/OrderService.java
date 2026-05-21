package com.tatafoods.service;

import org.springframework.stereotype.Service;

import com.tatafoods.dto.OrderResponseDTO;
import com.tatafoods.dto.PlaceOrderRequestDTO;

@Service
public interface OrderService {
	
	public OrderResponseDTO placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO);

}
