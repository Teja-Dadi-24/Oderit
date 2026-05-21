package com.tatafoods.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateItemRequestDTO {
	private String itemName;
	private double price;
	private int stockQuantity;
	private double discount;
	private double rating;

}
