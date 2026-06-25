import java.util.Arrays;

public class Main {
    public static void main(String [] args){
      Product[] products = {
                new Product(101, "Laptop", "Electronics"),
                new Product(102, "Shoes", "Fashion"),
                new Product(103, "Mobile", "Electronics"),
                new Product(104, "Watch", "Accessories"),
                new Product(105, "Tablet", "Electronics")
        };
        Product result=Ecommerce.linearSearch(products,"Laptop");
        System.out.println("Linear Search Result:");
        System.out.println(result);
Arrays.sort(products, (p1, p2) ->
        p1.productName.compareTo(p2.productName));        
        Product binaryResult =
                Ecommerce.binarySearch(products, "Watch");

        System.out.println("\nBinary Search Result:");
        System.out.println(binaryResult);


    }
}
