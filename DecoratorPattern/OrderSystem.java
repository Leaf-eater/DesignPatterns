package DecoratorPattern;

/**
 * Created by 宇航 on 2017/2/18.
 * 需求：要求为一家煎饼店做一个点餐系统
 * 煎饼类型： 杂粮煎饼 6元  白面煎饼 5元
 * 加料： 辣条 1元 鸡蛋 1元 火腿 2元
 */
public class OrderSystem {
    public static void main(String[] args) {
        Pancakes pancakes = new MixedPancakes();
        pancakes = new Ham(pancakes);
        pancakes = new Egg(pancakes);
        Pancakes pancakes1 = new Latiao(new Egg(new FlourPancakes()));
        System.out.println("订单:" + pancakes.getDescription());
        System.out.println("价格：" + pancakes.cost());
        System.out.println("订单:" + pancakes1.getDescription());
        System.out.println("价格：" + pancakes1.cost());
    }
}

interface Pancakes {
    public abstract String getDescription();

    public abstract int cost();
}

abstract class Seasoning implements Pancakes {
    @Override
    public abstract String getDescription();
}

class Ham extends Seasoning {

    Pancakes pancakes;

    public Ham(Pancakes pancakes) {
        this.pancakes = pancakes;
    }

    @Override
    public int cost() {
        return pancakes.cost() + 2;
    }

    @Override
    public String getDescription() {
        return pancakes.getDescription() + "+火腿";
    }

    public void hamState() {
        System.out.println("火腿切碎");
    }

}

class Egg extends Seasoning {

    Pancakes pancakes;

    public Egg(Pancakes pancakes) {
        this.pancakes = pancakes;
    }

    @Override
    public int cost() {
        return pancakes.cost() + 1;
    }

    @Override
    public String getDescription() {
        return pancakes.getDescription() + "+鸡蛋";
    }

    public void eggState() {
        System.out.println("鸡蛋打碎");
    }
}

class Latiao extends Seasoning {

    Pancakes pancakes;

    public Latiao(Pancakes pancakes) {
        this.pancakes = pancakes;
    }

    @Override
    public int cost() {
        return pancakes.cost() + 1;
    }

    @Override
    public String getDescription() {
        return pancakes.getDescription() + "+辣条";
    }
}

class MixedPancakes implements Pancakes {

    @Override
    public String getDescription() {
        return "五谷杂粮煎饼";
    }

    @Override
    public int cost() {
        return 6;
    }
}

class FlourPancakes implements Pancakes {

    @Override
    public String getDescription() {
        return "白面煎饼";
    }

    @Override
    public int cost() {
        return 5;
    }
}
