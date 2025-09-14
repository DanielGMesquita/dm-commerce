package dev.danielmesquita.dmcommerce.repositories;

import dev.danielmesquita.dmcommerce.models.OrderItem;
import dev.danielmesquita.dmcommerce.models.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {}
