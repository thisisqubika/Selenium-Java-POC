package Test;

import extentReport.FareyeListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Utilities.Constants.*;

@Listeners(FareyeListener.class)
public class AutomationExerciseProductsTest extends BaseTest {
    @Test(description = "Test Case 8: Verify All Products and product detail page")
    public void verifyAllProductsAndProductDetailPageTest() throws InterruptedException {
        landingPage = startTest();
        productsPage = landingPage.clickOnProductsBtn();

        makeAnAssert(productsPage.getPagesTitlesList().size(), 1, "Size mismatch");
        makeAnAssert(productsPage.getTextFromAWebElement(productsPage.getPagesTitlesList().get(0)),
                     ALL_PRODUCTS_TITLE,
            "Titles in contact page mismatch");


        makeAnAssert(productsPage.getProductList().size() > 0, "The list of products is null ");

        productDetailPage = productsPage.clickOnProductDetailBtn(0);
        makeAnAssert(productsPage.getCurrentUrl(),
            utilities.getPropertyByValue(prop, "url") + PRODUCTS_BASEPATH + "1",
            "url mismatch" );

    }
/*
* Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click on 'Products' button
5. Verify user is navigated to ALL PRODUCTS page successfully
6. The products list is visible
7. Click on 'View Product' of first product
8. User is landed to product detail page
9. TODO Verify that detail detail is visible: product name, category, price, availability, condition, brand
* */
}
