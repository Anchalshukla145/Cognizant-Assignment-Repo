public class Main {

    public static void main(String[] args) {

        Order[] orders = {
            new Order(101,"Anchal",5000),
            new Order(102,"Rahul",2000),
            new Order(103,"Aman",8000),
            new Order(104,"Priya",3000)
        };

        OrderSorter.quickSort(orders,0,orders.length-1);

        System.out.println("Sorted Orders:");

        for(Order order : orders) {
            System.out.println(order);
        }
    }
}