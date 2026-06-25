public class Ecommerce {
    public static Product linearSearch(Product[] products,String targetName){
        for(Product p:products){
            if(p.productName.equalsIgnoreCase(targetName))return p;
        }
        return null;
    }
    //make sure the array for binnary search is sorted array only
    public static Product binarySearch(Product[] products,String targetName){
     int left=0;
     int right=products.length;
     while(left<right){
        int mid=left+(right-left)/2;
        int compare=products[mid].productName.compareTo(targetName);
        if(compare==0)return products[mid];
        else if(compare<0){
            left=mid+1;
        }
        else {
            right=mid-1;
        }
     }
     return null;
    }
}

