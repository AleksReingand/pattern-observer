package main;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        Observer observer = new CurrentConditionsDisplay();

        weatherData.setMeasurements(1, 2,3);

        weatherData.registerObserver(observer);

        weatherData.setMeasurements(10, 20, 30);
        weatherData.setMeasurements(11, 22, 33);
    }

    // delivery new data to observer
    interface Observer {
        void update(int hum, int pres, int tem);
    }

    // register new observer and notifier
    interface Observable {
        void registerObserver(Observer observer);

        void removeObserver(Observer observer);

        void notifyObservers();
    }

    // send events to observers
    static class WeatherData implements Observable {

        List<Observer> observerList;
        int humidity;
        int pressure;
        int temperature;

        public WeatherData() {
            observerList = new LinkedList<>();
        }

        @Override
        public void registerObserver(Observer observer) {
            observerList.add(observer);
        }

        @Override
        public void removeObserver(Observer observer) {
            observerList.remove(observer);
        }

        @Override
        public void notifyObservers() {
            for (Observer ob : observerList) {
                ob.update(humidity, pressure, temperature);
            }
        }

        void setMeasurements(int humidity, int pressure, int temperature) {
            this.humidity = humidity;
            this.pressure = pressure;
            this.temperature = temperature;
            notifyObservers();
        }
    }

    // listener events and printer
    static class CurrentConditionsDisplay implements Observer {
        int hum;
        int pres;
        int tem;

        @Override
        public void update(int hum, int pres, int tem) {
            this.hum = hum;
            this.pres = pres;
            this.tem = tem;
            print();
        }

        void print() {
            System.out.println("updated measurements: " + hum + " " + pres + " " + tem);
        }
    }
}
