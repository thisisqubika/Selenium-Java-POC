package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver remoteDriver) {
        driver = remoteDriver;
        initElements(driver, this);

    }
    @FindBy(xpath = "//*[@class='product-image-wrapper']")
    List<WebElement> productList;
    public List<WebElement> getProductList(){
        return productList;

    }
    @FindBy(xpath = "//a[@style='color: brown;']")
    List<WebElement> viewProductBtnList;
    public ProductDetailPage clickOnProductDetailBtn(int productIndex){
        WebElement productChosen = viewProductBtnList.get(productIndex);

        utilities.scrollIntoView(productChosen);
        productChosen.click();

        productDetailPage = new ProductDetailPage(driver);
        return productDetailPage;

    }

}