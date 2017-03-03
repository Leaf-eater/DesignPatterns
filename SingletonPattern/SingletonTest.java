package SingletonPattern;

import java.util.Objects;

/**
 * Created by 宇航 on 2017/2/28.
 * 需求：我们决定建立一个全球限量三部的超级跑车，请你为我们的生产线设计代码
 * 当跑车数量多于三部时拒绝生产并发出警告
 */
public class SingletonTest {
public static void main(String[] args) {
//    CarFactory carFactory = new CarFactory(); 无法实例化
    for (int i = 0; i < 5; i++) {
        CarFactory.createCar();
    }
}
}

class CarFactory{
    private static int count = 0;

    private CarFactory(){

     }

     public static CarFactory createCar(){
         if (count < 3){
             System.out.println("创建了一辆车");
             count++;
             return new CarFactory();
         }else {
             System.out.println("生产已达上限");
             return null;
         }
     }

}
