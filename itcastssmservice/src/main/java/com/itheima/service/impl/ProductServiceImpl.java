package com.itheima.service.impl;

import com.itcast.domain.Product;
import com.itheima.dao.IproductDao;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IproductDao iproductDao;

    @Override
    public List<Product> findAll() {
        return iproductDao.findAl();
    }

    @Override
    public void save(Product product) {
        iproductDao.save(product);
    }
}
