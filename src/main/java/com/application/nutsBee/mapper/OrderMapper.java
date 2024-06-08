package com.application.nutsBee.mapper;

import com.application.nutsBee.Dto.OrderDto;
import com.application.nutsBee.Entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order orderDtoToOrder(OrderDto orderDto);

}
