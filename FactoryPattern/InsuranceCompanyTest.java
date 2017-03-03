package FactoryPattern;

/**
 * Created by 宇航 on 2017/2/25.
 * 为一家保险公司的设计一套线上保险推销方案
 * 已知以下信息：
 * 1.每个都保险需要三个步骤：搜集信息，推销保险，提供售后
 * 2.目前坐落于北京，上海还有一家分店，以后准备开设更多的分公司
 * 3.需要针对每个城市的不同差异,我们有不同的推销方法，但是其他的部分是一样的
 * 4.目前有针对老人，中年人和小孩的保险类型
 * 4.受多方面的影响，我们可能随时添加或删除一个类型的保险
 */

public class InsuranceCompanyTest {
    public static void main(String[] args) {
        InsuranceCompany bjInsuranceCompany = new CompanyInBJ();
        InsuranceCompany shInsuranceCompany = new CompanyInSH();

        bjInsuranceCompany.collectInformation("老人");
        bjInsuranceCompany.afterSale();

        shInsuranceCompany.collectInformation("小孩");
        shInsuranceCompany.afterSale();
    }
}
/**
 * ------------------保险和保险公司的抽象类----------------
 * 提供了一个通用的接口和超类型，规定了高层业务的逻辑，即收集-销售-售后
 */

abstract class Insurance{
    String type;
    Integer price;
    public String getType(){
        return type;
    }

    public Integer getPrice() {
        return price;
    }

    void display(){
        System.out.println("正在准备您的基本信息"+
        "类型："+getType()+"价格："+getPrice());
    }

}

abstract class InsuranceCompany{

    public void collectInformation(String type){
        Insurance insurance;
        System.out.println("收集顾客信息");
        insurance = createInsurance(type);
        insurance.display();
    }

    protected abstract Insurance createInsurance(String type);

    public void afterSale(){
        System.out.println("提供售后服务");
    }

}

/**
 * -------------------保险公司的具体类-----------------------
 * 具体实现公司针对于不同地域的细节, 这里是工厂
 */

class CompanyInBJ extends InsuranceCompany{
    @Override
    protected Insurance createInsurance(String type) {
        switch (type) {
            case "老人":
                return new BJOlderInsurance();
            case "小孩":
                return new BJChildInsurance();
            default:
                return null;
        }
    }

}

class CompanyInSH extends InsuranceCompany{
    @Override
    protected Insurance createInsurance(String type) {
        switch (type){
            case "老人":
                return new SHOlderInsurance();
            case "小孩":
                return new SHChildInsurance();
            default:
                return null;
        }

    }

}

/**
 * ----------------------保险的具体类-------------------------
 * 规定了不同地域不同类型保险的名称，价格等细节
 */

class BJOlderInsurance extends Insurance{
    public BJOlderInsurance(){
        type = "北京市老人保险";
        price = 240;
    }
}

class BJChildInsurance extends Insurance{
    public BJChildInsurance(){
        type = "北京市儿童保险";
        price = 260;
    }
}

class SHOlderInsurance extends Insurance{
    public SHOlderInsurance(){
        type = "上海市老人保险";
        price = 280;
    }
}

class SHChildInsurance extends Insurance{
    public SHChildInsurance(){
        type = "上海市儿童保险";
        price = 290;
    }
}