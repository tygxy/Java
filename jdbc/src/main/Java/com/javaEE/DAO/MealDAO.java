package com.javaEE.DAO;

import com.javaEE.Bean.Meal;
import com.javaEE.Bean.MealInfo;

import java.util.List;

/**
 * Created by guoxingyu on 2018/5/21.
 */
public interface MealDAO {
    /**
     * 添加订单
     * @param m
     */
    public void add(Meal m);

    /**
     * 输出所有订单
     * @return
     */
    public List listAllMeal();

    /**
     * 查询某个订单
     * @param id
     * @return
     */
    public MealInfo query(int id);

}
