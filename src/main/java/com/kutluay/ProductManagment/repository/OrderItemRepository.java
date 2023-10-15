package com.kutluay.ProductManagment.repository;

import com.kutluay.ProductManagment.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,String> {
}
