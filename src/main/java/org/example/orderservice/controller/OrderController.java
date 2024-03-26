package org.example.orderservice.controller;

import jakarta.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.OrderDto;
import org.example.orderservice.jpa.OrderEntity;
import org.example.orderservice.mapper.OrderMapper;
import org.example.orderservice.service.OrderService;
import org.example.orderservice.vo.RequestOrder;
import org.example.orderservice.vo.ResponseOrder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {

	private final OrderService orderService;
	private final OrderMapper orderMapper;
	private final Environment env;

	@GetMapping("/health_check")
	public String status() {
		return String.format("It's Working in Order Service on PORT %s",
				env.getProperty("local.server.port"));
	}

	//http://127.0.0.1:0/order-service/{user_id}/orders
	@PostMapping("/{userId}/orders")
	public ResponseEntity<ResponseOrder> createOrder(@PathVariable String userId,
			@RequestBody RequestOrder orderDetails) {

		OrderDto orderDto = orderMapper.toDto(orderDetails);
		orderDto.setUserId(userId);
		OrderDto createdOrder = orderService.createOrder(orderDto);

		ResponseOrder responseOrder = orderMapper.toResponseOrder(createdOrder);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
	}

	@GetMapping("/{userId}/orders")
	public ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable String userId) {
		Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);

		List<ResponseOrder> result = new ArrayList<>();
		orderList.forEach(v -> {
			result.add(orderMapper.toResponseOrder(v));
		});

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
