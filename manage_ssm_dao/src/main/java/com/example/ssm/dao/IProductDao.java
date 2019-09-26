package com.example.ssm.dao;

import com.example.ssm.domain.Product;

import java.util.List;

public interface IProductDao {

    /**
     * 查询所有的产品信息
     * @return 返回查询结果的List集合
     */
    public List<Product> findAll() throws Exception;
}
