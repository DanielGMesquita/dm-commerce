package dev.danielmesquita.dmcommerce.repositories;

import dev.danielmesquita.dmcommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
