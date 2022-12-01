package Constructor;
import org.json.JSONArray;

public class Stock {
    String warehouse;
    String phoneNumber;
    int qty;

    public Stock(String aWareHouse, String aPhoneNumber, int aQty){
        this.warehouse = aWareHouse;
        this.qty = aQty;
        this.phoneNumber = aPhoneNumber;

    }

}
