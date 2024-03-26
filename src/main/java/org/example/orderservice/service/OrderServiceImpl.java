package org.example.orderservice.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.OrderDto;
import org.example.orderservice.jpa.OrderEntity;
import org.example.orderservice.jpa.OrderRepository;
import org.example.orderservice.mapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;

	@Override
	public OrderDto createOrder(OrderDto orderDto) {
		orderDto.setOrderId(UUID.randomUUID().toString());
		orderDto.setTotalPrice(orderDto.getQty() * orderDto.getUnitPrice());
		OrderEntity orderEntity = orderMapper.toEntity(orderDto);

		orderRepository.save(orderEntity);

		return orderMapper.toDto(orderEntity);

	}

	@Override
	public OrderDto getOrderByOrderId(String orderId) {
		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		return orderMapper.toDto(orderEntity);
	}

	@Override
	public Iterable<OrderEntity> getOrdersByUserId(String userId) {
		return orderRepository.findByUserId(userId);
	}
}
