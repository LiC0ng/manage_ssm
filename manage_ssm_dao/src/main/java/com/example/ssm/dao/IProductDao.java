package com.example.ssm.dao;

import com.example.ssm.domain.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {

    /**
     * 查询所有的产品信息
     * @return 返回查询结果的List集合
     */
    @Select("select * from product")
    public List<Product> findAll() throws Exception;
}
