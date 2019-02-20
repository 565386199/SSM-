package com.itheima.dao;

import com.itcast.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IproductDao {

    /**
     * 查询所有产品
     * @return
     */
    @Select("select * from product")
    List<Product> findAl();


    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    /**
     * 根据id查询
     * @return
     */
    @Select("select * from product where id = #{id}")
    public Product findById(String id);
}

