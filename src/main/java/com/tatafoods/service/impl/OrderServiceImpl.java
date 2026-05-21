package com.tatafoods.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.tatafoods.dao.ItemRepository;
import com.tatafoods.dao.OrderRepository;
import com.tatafoods.dto.OrderItemRequestDTO;
import com.tatafoods.dto.OrderItemResponseDTO;
import com.tatafoods.dto.OrderResponseDTO;
import com.tatafoods.dto.PlaceOrderRequestDTO;
import com.tatafoods.model.Item;
import com.tatafoods.model.Order;
import com.tatafoods.model.OrderItem;
import com.tatafoods.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	OrderRepository orderRepository;

	@Override
	public OrderResponseDTO placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO) {
		List<OrderItemRequestDTO> orderItemRequestList = placeOrderRequestDTO.getOrderItemRequestList();
		List<OrderItem> orderItemList=new ArrayList<OrderItem>();
		Order order =new Order();
		double finalOrderPrice=0;
		
		for(OrderItemRequestDTO i:orderItemRequestList) {
			Item item = itemRepository.findById(i.getItemId()).get();
			OrderItem orderItem=new OrderItem();
            orderItem.setItem(item);
            orderItem.setQuantity(i.getQuantity());
            double itemPrice=item.getPrice()*i.getQuantity();
            finalOrderPrice+=itemPrice;
            orderItem.setPrice(itemPrice);
            orderItem.setOrder(order);
            orderItemList.add(orderItem);	
		}
		order.setOrderItems(orderItemList);
		order.setPrice(finalOrderPrice);
		
		Order savedOrder = orderRepository.save(order);
		OrderResponseDTO orderResponseDTO=new OrderResponseDTO();
		List<OrderItemResponseDTO> orderItemResponseDTOs=new ArrayList<OrderItemResponseDTO>();
		List<OrderItem> orderItems = savedOrder.getOrderItems();
		for(OrderItem i:orderItems) {
			OrderItemResponseDTO orderItemResponseDTO=new OrderItemResponseDTO();
			orderItemResponseDTO.setItemName(i.getItem().getItemName());
			orderItemResponseDTO.setQuantity(i.getQuantity());
			orderItemResponseDTO.setPrice(i.getItem().getPrice());
			orderItemResponseDTO.setSubTotal(i.getPrice());
			orderItemResponseDTOs.add(orderItemResponseDTO);
		}
		orderResponseDTO.setOrderId(order.getOrderId());
		orderResponseDTO.setOrderItemResponseList(orderItemResponseDTOs);
		orderResponseDTO.setTotalPrice(order.getPrice());
		return orderResponseDTO;
		
	}
	
	

}
