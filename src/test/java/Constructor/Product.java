package Constructor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;

public class Product {
    String name;
    String description;
    String image;
    int price;
    Object stock;
    public Product(String aName, String aDescription, String anImage, int aPrice) {
        this.name = aName;
        this.description = aDescription;
        this.price = aPrice;
        this.image = anImage;

    }
    public Product(String aName, String aDescription, String anImage, int aPrice, Object aStock) {
        this.name = aName;
        this.description = aDescription;
        this.price = aPrice;
        this.image = anImage;
        this.stock = aStock;

    }
    public Product(String aName, String aDescription, int aPrice) {
        this.name = aName;
        this.description = aDescription;
        this.price = aPrice;

    }
    public Product(String aDescription, int aPrice) {
        this.description = aDescription;
        this.price = aPrice;

    }
    public Product(int aPrice) {
        this.price = aPrice;

    }
    public String toString(){
        return "Campaign: " + this.name;

    }
}

