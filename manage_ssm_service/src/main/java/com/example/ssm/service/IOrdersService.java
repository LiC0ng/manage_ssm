package com.example.ssm.service;

import com.example.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll() throws Exception;
}
