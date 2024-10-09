package com.example.sop.datafetchers;

import com.example.sop.dtos.OrderDto;
import com.example.sop.enums.OrderStatusEnum;
import com.example.sop.models.MedicationOrder;
import com.example.sop.models.Order;
import com.example.sop.repositories.OrderRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class OrderDataFetcher {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderDataFetcher(ModelMapper modelMapper, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
    }

    @DgsQuery
    public OrderDto orderById(@InputArgument Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        return order != null ? modelMapper.map(order, OrderDto.class) : null;
    }

//    @DgsQuery
//    public List<OrderDto> ordersByStatus(@InputArgument OrderStatusEnum orderStatusEnum) {
//        List<Order> queryResult = orderRepository.findByOrderStatusEnum(orderStatusEnum);
//        return queryResult.stream()
//                .map(order -> modelMapper.map(order, OrderDto.class))
//                .collect(Collectors.toList());
//    }

    @DgsMutation
    public OrderDto addOrder(@InputArgument OrderDto orderDto) {
        Order newOrder = modelMapper.map(orderDto, Order.class);
        newOrder = orderRepository.save(newOrder);
        return modelMapper.map(newOrder, OrderDto.class);
    }

//    @DgsMutation
//    public OrderDto updateOrder(@InputArgument Long id, @InputArgument OrderDto orderDto) {
//        Order order = orderRepository.findById(id).orElseThrow(RuntimeException::new);
//        order.setOrderStatus(orderDto.getOrderStatus());
//        order.setMedicationOrders(modelMapper.map(orderDto.getMedicationOrders(), new TypeToken<List<MedicationOrder>>(){}.getType()));
//        Order updatedOrder = orderRepository.save(order);
//        return modelMapper.map(updatedOrder, OrderDto.class);
//    }

    @DgsMutation
    public boolean deleteOrder(@InputArgument Long id) {
        orderRepository.deleteById(id);
        return true;
    }
}
