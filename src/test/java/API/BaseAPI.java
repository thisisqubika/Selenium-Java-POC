package API;

import Constructor.Product;
import Constructor.Stock;
import Pages.BasePage;
import Test.BaseTest;

import Utilities.Utilities;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.List;

public class BaseAPI extends BasePage {
    @BeforeTest
    public void instanciateAPIHelper() throws IOException {
        setProps();

        utilities = new Utilities();
        apiHelper = new APIHelper();

    }
    @BeforeMethod
    public static void setUp() throws IOException {
        RestAssured.baseURI = utilities.getPropertyByValue(prop, "products-uri");
        RestAssured.basePath = utilities.getPropertyByValue(prop, "products-basepath");

    }
    public Response resp;

    public String responseBody;
    public int statusCode;

    public JsonPath jspth;
    public APIHelper apiHelper;

    public String id = "";
    public String name = "";
    public String description = "";
    public int price;
    public String imageUrl = "";
    public boolean isBlock;
    public String isBlocked = "";
    public String warehouse = "";
    public String phoneNumber = "";
    public int qty;

    public Product product;
    public Stock stock;

    public List<String> stockArrayList;

    public int respStCode;
    public String respError = "";
    public String respErrorMsg = "";


}
