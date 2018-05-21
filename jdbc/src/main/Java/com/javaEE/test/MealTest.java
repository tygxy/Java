package com.javaEE.test;

import com.javaEE.Bean.Meal;
import com.javaEE.Bean.MealInfo;
import com.javaEE.DAOImpl.MealDaoImal;

import java.util.List;

/**
 * Created by guoxingyu on 2018/5/21.
 */
public class MealTest {
    public static void main(String[] args) {
        Meal m = new Meal();
        m.setCreateTime("2018-05-22");
        m.setUserId(2);
        m.setMealType(1);
        m.setNum(5);

        MealTest test = new MealTest();
//        test.addTest(m);
//        test.listAllMealTest();
        test.queryMealById(1);
    }

    public void addTest(Meal m) {
        MealDaoImal mealImal = new MealDaoImal();
        mealImal.add(m);
    }

    public void listAllMealTest() {
        MealDaoImal mealImal = new MealDaoImal();
        List<MealInfo> list = mealImal.listAllMeal();
        for (MealInfo m :list) {
            int id = m.getId();
            String createTime = m.getCreatetime();
            String userName = m.getUserName();
            String mealName = m.getMealName();
            int num = m.getNum();
            double total = m.getTotal();
            String comment = m.getComment();

            System.out.println(id+" "+createTime+" "+userName+" "+mealName+" "+num+" "+total+" "+comment);
        }
    }

    public void queryMealById(int id) {
        MealDaoImal mealImal = new MealDaoImal();
        MealInfo m = mealImal.query(id);
        if (m != null) {
            String createTime = m.getCreatetime();
            String userName = m.getUserName();
            String mealName = m.getMealName();
            int num = m.getNum();
            double total = m.getTotal();
            String comment = m.getComment();

            System.out.println(id+" "+createTime+" "+userName+" "+mealName+" "+num+" "+total+" "+comment);
        } else {
            System.out.println("无此订单");
        }
    }
}
