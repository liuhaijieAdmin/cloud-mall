package com.zhuzi.mallserverorder.mapper;

import com.zhuzi.mallbase.model.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
