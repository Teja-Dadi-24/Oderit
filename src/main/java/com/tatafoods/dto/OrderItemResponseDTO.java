package com.tatafoods.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDTO {
	
	private String itemName;
	private double price;
	private int quantity;
	
	private double subTotal;

}
