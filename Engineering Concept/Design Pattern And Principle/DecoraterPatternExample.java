package myfirstjavaproject.DN5Practice;
interface Notifier{
    public void send();
}
class EmailNotifier implements Notifier{
    public void send(){
             System.out.println("Sending Notification via Email");

    }
}
abstract class NotifierDecorator implements Notifier{
    protected Notifier notify; 
    NotifierDecorator(Notifier notify){
        this.notify=notify;
    }
        public void send(){
            notify.send();
        }

}
class SMSNotifierDecorator extends NotifierDecorator {

    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Sending Notification via SMS");
    }
}
class SlackNotifierDecorator extends NotifierDecorator {

    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send() {
        super.send();
        System.out.println("Sending Notification via Slack");
    }
}
public class DecoraterPatternExample {
    public static void main(String[] args) {
        
    
    Notifier notifier =new SlackNotifierDecorator(new SMSNotifierDecorator(new EmailNotifier()) );

        notifier.send();
    }
}
