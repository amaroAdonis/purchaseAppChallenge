package entities;

import java.util.ArrayList;
import java.util.List;

public class Purchase<T>{

    Product product = new Product();
    User user = new User();
    ArrayList<T> list = new ArrayList<T>();

    public Purchase(){

    }

    public Purchase(Product product, User user, ArrayList<T> list) {
        this.product = product;
        this.user = user;
        this.list = list;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;

    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    private String addPurchase (){
        return user.toString() + product.toString();
    }
}
