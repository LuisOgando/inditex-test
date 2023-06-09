package com.inditex.logandotest.api.service;

import com.inditex.logandotest.api.model.Product;
import com.inditex.logandotest.api.repository.ProductRepository;
import com.inditex.logandotest.api.repository.SizeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
}

@Service
@AllArgsConstructor
class ProductServiceImpl implements ProductService {

    private GetProductInStock getProductInStock;
    private ProductRepository repository;
    private SizeRepository sizeRepository;

    @Override
    public List<Product> findAll() {
        var sizeList = sizeRepository.findAll();
        var onStockProductIds = Collections.EMPTY_SET;
        if (!sizeList.isEmpty()) {
            onStockProductIds = getProductInStock.apply(sizeList);
        }
        return repository.findAllByIdInOrderBySequence(onStockProductIds);
    }
}