package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductDetailPage extends ProductsPage {
    public ProductDetailPage(WebDriver remoteDriver) {
        super(remoteDriver);

    }
    @FindBy(xpath = "//*[@class='product-image-wrapper']")
    List<WebElement> productList;
    public List<WebElement> getProductList(){
        return productList;

    }
    @FindBy(xpath = "//a[@style='color: brown;']")
    List<WebElement> viewProductBtnList;
    public ProductDetailPage getProductList(int productIndex){
        viewProductBtnList.get(productIndex)
                          .click();

        productDetailPage = new ProductDetailPage(driver);
        return productDetailPage;

    }

}