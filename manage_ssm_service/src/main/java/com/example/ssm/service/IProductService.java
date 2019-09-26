package com.example.ssm.service;

import com.example.ssm.domain.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAll() throws Exception;
}
