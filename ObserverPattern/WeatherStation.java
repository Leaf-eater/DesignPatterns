package ObserverPattern;

import java.util.ArrayList;

/**
 * Created by 宇航 on 2017/2/7.
 * 设计模式：ObserverPatterns
 * 需求：我们有一个观察站，可以从传感器中获得实时温度信息，我们需要创造三个展示板。
 * 每个展示板都会展示相应的实时数据。
 */
public class WeatherStation {

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

class CurrentConditionsDisplay implements Observer , DisplayElement{
    private float temperature;
    private float humidity;
    private float pressure;

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

