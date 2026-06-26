package myfirstjavaproject.DN5Practice;

import java.util.ArrayList;
interface Observer {
    void update(String stockName, double price);
}
interface Stock {

    void registerObserver(Observer observer);

    void deregisterObserver(Observer observer);

    void notifyObservers();
}
class StockMarket implements Stock {

    private ArrayList<Observer> observers = new ArrayList<>();

    private String stockName;
    private double price;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {

        for (Observer observer : observers) {
            observer.update(stockName, price);
        }
    }

    public void setStockPrice(String stockName, double price) {

        this.stockName = stockName;
        this.price = price;

        notifyObservers();
    }
}
class MobileApp implements Observer {

    private String userName;

    public MobileApp(String userName) {
        this.userName = userName;
    }

    @Override
    public void update(String stockName, double price) {

        System.out.println(
                "Mobile App [" + userName +
                "] : " + stockName +
                " price updated to Rs." + price
        );
    }
}
class WebApp implements Observer {

    private String userName;

    public WebApp(String userName) {
        this.userName = userName;
    }

    @Override
    public void update(String stockName, double price) {

        System.out.println(
                "Web App [" + userName +
                "] : " + stockName +
                " price updated to Rs." + price
        );
    }
}
public class ObserverPatternExample {
        public static void main(String[] args) {

        StockMarket stockMarket = new StockMarket();

        Observer mobileUser = new MobileApp("Anchal");

        Observer webUser = new WebApp("Rahul");

        stockMarket.registerObserver(mobileUser);
        stockMarket.registerObserver(webUser);

        stockMarket.setStockPrice("TCS", 4200);

        System.out.println();

        stockMarket.setStockPrice("Infosys", 1800);
        }
}
