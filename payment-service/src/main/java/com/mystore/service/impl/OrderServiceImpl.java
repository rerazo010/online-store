package com.mystore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mystore.config.OrderNotFoundException;
import com.mystore.config.PayOrderException;
import com.mystore.repository.OrderRepository;
import com.mystore.service.OrderService;
import com.mystore.util.SuccessResponse;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public ResponseEntity<SuccessResponse> payOrder(Long orderId) {

		boolean exists = orderRepository.existsById(orderId);
        if (!exists) {
            throw new OrderNotFoundException(orderId);
        }
        
        int updated = orderRepository.payOrder(orderId);
        if (updated == 0) {
            throw new PayOrderException(orderId);
        }
        
		return ResponseEntity.ok(new SuccessResponse("Order paid successfully","register updated "+ updated));
	}

}
