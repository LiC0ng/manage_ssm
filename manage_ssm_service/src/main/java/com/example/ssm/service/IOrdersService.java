package com.example.ssm.service;

import com.example.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(int size, int page) throws Exception;

    Orders findById(String ordersId) throws Exception;
}
