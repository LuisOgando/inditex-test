package com.inditex.logandotest.api.repository;

import com.inditex.logandotest.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIdInOrderBySequence(Set<Long> ids);
}
