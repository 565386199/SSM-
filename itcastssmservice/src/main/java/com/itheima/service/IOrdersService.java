package com.itheima.service;

import com.itcast.domain.Orders;

import java.util.List;

public interface IOrdersService {
    List<Orders> findAll(int page ,int size);

    Orders findById(String id);
}
