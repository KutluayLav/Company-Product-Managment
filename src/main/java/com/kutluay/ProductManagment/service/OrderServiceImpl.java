package com.kutluay.ProductManagment.service;

import com.kutluay.ProductManagment.model.Order;
import com.kutluay.ProductManagment.model.OrderItem;
import com.kutluay.ProductManagment.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
    }

    @Override
    public Order addOrder(Order order) {
        Set<OrderItem> orderItems = order.getOrderItems();

        for (OrderItem orderItem : orderItems) {
            double productPrice = orderItem.getProduct().getPrice();
            int quantity = orderItem.getQuantity();
            double totalPrice = productPrice * quantity;
            orderItem.setTotolPrice(totalPrice);
            orderItemService.createOrderItem(orderItem);
        }

        return orderRepository.save(order);
    }
}
