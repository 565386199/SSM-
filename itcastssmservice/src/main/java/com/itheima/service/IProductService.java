package com.itheima.service;

import com.itcast.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProductService {

    public List<Product> findAll();

    void save(Product product);
}
