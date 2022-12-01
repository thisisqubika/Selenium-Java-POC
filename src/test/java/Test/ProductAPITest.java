package Test;

import API.BaseAPI;

import StockDataFactory.StockDataFactory;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class ProductAPITest extends BaseAPI {
    @Test
    public void simpleGetTest(){
        resp = apiHelper.getSimpleResponse(ContentType.JSON);
        responseBody = apiHelper.prettyPrintResponse(resp);
        statusCode = apiHelper.getStatusCode(resp);

        utilities.makeAnAssertFalse(responseBody.isEmpty(), "Response Body is empty");
        utilities.makeAnAssert(statusCode, 200, "Status is different than ok");

    }
    @Test
    public void simpleGetByIdTest(){
        resp = apiHelper.getSimpleResponse(ContentType.JSON, "a94fad5d-7421-4de3-bde0-f788f91ea796");
        responseBody = apiHelper.prettyPrintResponse(resp);

        jspth = new JsonPath(responseBody);

        id = jspth.get("id");
        name = jspth.get("name");
        description = jspth.get("description");
        price = jspth.get("price");
        imageUrl = jspth.get("image");

        isBlock = jspth.get("isBlock");
        isBlocked = String.valueOf(isBlock);

        utilities.makeAnAssert(id, "a94fad5d-7421-4de3-bde0-f788f91ea796", id + " is incorrect");
        utilities.makeAnAssert(name, "Chevrolet Corvette", name + " is incorrect");
        utilities.makeAnAssert(description, "Favourite car in Street Rod 2", description + " is incorrect");
        utilities.makeAnAssert(price, 1500, price + " is incorrect");
        utilities.makeAnAssert(imageUrl, "http://placeimg.com/640/480", id + " is incorrect");
        utilities.makeAnAssert(isBlocked, "false", id + " is incorrect");

        utilities.makeAnAssert(apiHelper.getStatusCode(resp), 200, "Status is different than ok");

    }
    int run = -1;
    @Test(dataProvider = "stockProvider", dataProviderClass = StockDataFactory.class)
    public void simpleGetByIdStockTest(String dpWarehouse, String dpPhoneNumber, int dpQty){
        run ++;

        resp = apiHelper.getSimpleResponse(ContentType.JSON, "a94fad5d-7421-4de3-bde0-f788f91ea799");
        responseBody = apiHelper.prettyPrintResponse(resp);

        jspth = new JsonPath(responseBody);
        stockArrayList = jspth.get("stock");

        JSONArray jsonArray = new JSONArray(stockArrayList);
        JSONObject jsonObject = apiHelper.jsonObjectFromJsonArray(jsonArray, run);

        warehouse = jsonObject.get("warehouse").toString();
        phoneNumber = jsonObject.get("phoneNumber").toString();
        qty = jsonObject.getInt("qty");

        utilities.makeAnAssert(warehouse, dpWarehouse, warehouse + " is incorrect");
        utilities.makeAnAssert(phoneNumber, dpPhoneNumber, phoneNumber + " is incorrect");
        utilities.makeAnAssert(qty, dpQty, qty + " is incorrect");

        utilities.makeAnAssert(apiHelper.getStatusCode(resp), 200, "Status is different than ok");

    }

}
