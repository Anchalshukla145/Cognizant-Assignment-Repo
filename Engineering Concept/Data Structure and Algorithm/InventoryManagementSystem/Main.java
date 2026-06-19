public class Main {

    public static void main(String[] args) {

        InventoryManager manager =new InventoryManager();
        Product p1 =new Product(101,"Laptop",10,50000);

        Product p2 =new Product(102, "Mouse", 50,500);

        manager.addProduct(p1);
        manager.addProduct(p2);

        manager.displayProducts();

        manager.updateProduct(101,"Gaming Laptop",8,65000);

        manager.displayProducts();

        manager.deleteProduct(102);

        manager.displayProducts();
    }
}