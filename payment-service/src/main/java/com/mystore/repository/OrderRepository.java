package com.mystore.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mystore.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Serializable> {
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE Order o SET o.status = 'PAID' WHERE o.orderId = :orderId and o.status = 'CREATED'")
	public int payOrder(@Param("orderId") Long orderId);

}
