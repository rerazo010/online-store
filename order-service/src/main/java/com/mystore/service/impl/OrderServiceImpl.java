package com.mystore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mystore.dto.OrderDTO;
import com.mystore.entity.Order;
import com.mystore.entity.OrderDetail;
import com.mystore.repository.OrderRepository;
import com.mystore.service.OrderService;
import com.mystore.util.SuccessResponse;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public ResponseEntity<SuccessResponse> createOrder(OrderDTO orderDTO) {

		Order order = new Order();
		order.setUserId(orderDTO.getUserId());
		order.setTotalAmount(orderDTO.getTotalAmount());
		order.setStatus(orderDTO.getStatus());
		order.setCreateDate(new Date());

		List<OrderDetail> details = new ArrayList<>();
		orderDTO.getDetails().forEach(detailDTO -> {
			OrderDetail detail = new OrderDetail();
			detail.setProductId(detailDTO.getProductId());
			detail.setPrice(detailDTO.getPrice());
			detail.setOrder(order);
			details.add(detail);
		});

		order.setDetails(details);

		return ResponseEntity.ok(new SuccessResponse("Order created successfully", orderRepository.save(order)));
	}

}
