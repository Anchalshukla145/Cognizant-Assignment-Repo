package myfirstjavaproject.DN5Practice;
interface PaymentProcessor {//Unified interface
  public void processPayment(double amount);
}
class PPgateway{
    void makePayment(double amount){//different gateways different payment process method
        System.out.println("Payment of Rs." + amount + " processed through PayPal");

    }
}
class Ptmgateway{
    void payPayment(double amount){
        System.out.println("Payment of Rs." + amount + " processed through PayTM");

    }
}
class PPAdapter implements PaymentProcessor{
    private PPgateway pay;// private so that client and others cannot access payment gateway directly
    public PPAdapter(PPgateway pay){//initializing payment gateway obj
        this.pay=pay;
    }
    public void processPayment(double amount) {//making a unified function
        pay.makePayment(amount);
    }
}
class PTMAdapter implements PaymentProcessor{
  private Ptmgateway pay;
  public PTMAdapter(Ptmgateway pay){
    this.pay=pay;
  }
   public void processPayment(double amount){
    pay.payPayment(amount);
   }
}
public class AdapterPatternExample {
    public static void main(String[] args) {
         PaymentProcessor paypal =
                new PPAdapter(new PPgateway());//The gateway object can be created inside the adapter,
                                             //  but passing it through the constructor is preferred


        PaymentProcessor PayTm =
                new PTMAdapter(new Ptmgateway());

        paypal.processPayment(5000);

        PayTm.processPayment(10000);
    }
}
