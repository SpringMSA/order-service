package org.example.orderservice.mapper;

import org.example.orderservice.dto.OrderDto;
import org.example.orderservice.jpa.OrderEntity;
import org.example.orderservice.vo.RequestOrder;
import org.example.orderservice.vo.ResponseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface OrderMapper {
//	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

	OrderEntity toEntity(OrderDto orderDto);

	OrderDto toDto(OrderEntity orderEntity);

	OrderDto toDto(RequestOrder order);


	ResponseOrder toResponseOrder(OrderDto createdOrder);
	ResponseOrder toResponseOrder(OrderEntity orderEntity);
}
