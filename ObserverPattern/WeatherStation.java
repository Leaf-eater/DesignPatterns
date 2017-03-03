package ObserverPattern;

import java.util.ArrayList;

/**
 * Created by 宇航 on 2017/2/7.
 * 设计模式：ObserverPatterns
 * 需求：我们有一个观察站，可以从传感器中获得实时温度信息，我们需要创造三个展示板。
 * 每个展示板都有不同的功能，有的实时显示数据，有的预测未来走向，有的用来将信息转发给电视台
 * 以后可能会有新的布告板所以要方便添加
 */
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay conditionsDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(12,24,35);
    }
}

interface Subject{
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver();
}

interface Observer{
    void update(float temp, float humidity, float pressure);
}

interface DisplayElement{
    void display();
}

/**
 * 在这个例子中WeatherData负责推送消息，也就是subject
 */
class WeatherData implements Subject{
    private ArrayList<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if(i >= 0) observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        for(int i = 0;i<observers.size();i++){
            Observer observer = observers.get(i);
            observer.update(temperature,humidity,pressure);
        }
    }

    public void measurementsChanged(){
        notifyObserver();
    }

    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

}

/**
 * 其中一个布告板的实现
 * 布告板实时显示温度，气压，湿度
 * 每个布告板都是一个observer
 */
class CurrentConditionsDisplay implements Observer , DisplayElement{
    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
//  每当数据更新的时候我们将更新数据显示出来
    public void update (float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.println("weather:"+"temperature:"+temperature+"humidity:"
                +humidity+"pressure:"+pressure);
    }
}

