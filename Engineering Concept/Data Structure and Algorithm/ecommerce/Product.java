public class Product{
     int productID;
    String productName;
    String category;
    Product(int id,String name,String category){
     productID=id;
     productName=name;
     this.category=category;
    }
     @Override
    public String toString() {
        return productID + " - " + productName + " - " + category;
    }
}