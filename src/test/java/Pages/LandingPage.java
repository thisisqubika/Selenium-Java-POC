package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage{
    public LandingPage(WebDriver remoteDriver) {
        driver = remoteDriver;
        initElements(driver, this);

    }
    @FindBy(xpath="//a[@href='/login']")
    WebElement signUpLoginBtn;
    public LoginPage clickOnSignUpLoginBtn(){
        signUpLoginBtn.click();

        loginPage = new LoginPage(driver);
        return loginPage;

    }
    @FindBy(xpath="//a[@href='/logout']")
    WebElement logoutBtn;
    public WebElement getLogoutBtn(){
        return logoutBtn;

    }
    public void clickOnLogoutBtn(){
        this.getLogoutBtn()
            .click();

    }
    @FindBy(xpath="//a[@href='/delete_account']")
    WebElement deleteAccountBtn;
    public WebElement getDeleteAccountBtn(){
        return deleteAccountBtn;

    }
    public DeletedAccountPage clickOnDeletedAccountBtn(){
        this.getDeleteAccountBtn()
            .click();

        deletedAccountPage = new DeletedAccountPage(driver);
        return deletedAccountPage;

    }
    @FindBy(xpath="//ul[@class='nav navbar-nav']//li//a//b")
    WebElement loggedAsUsername;
    public WebElement getLoggedAsUsername(){
        return loggedAsUsername;

    }
    @FindBy(xpath="//a[@href='/contact_us']")
    WebElement contactUsBtn;

    public ContactUsPage clickOnContactUsBtn(){
        contactUsBtn.click();

        contactUsPage = new ContactUsPage(driver);
        return contactUsPage;

    }
    @FindBy(xpath="//a[@href='/products']")
    WebElement productsBtn;

    public ProductsPage clickOnProductsBtn() throws InterruptedException {
        productsBtn.click();
        clickOnDismissBtn(5);

        System.out.println("ASD");
        productsPage = new ProductsPage(driver);
        return productsPage;

    }

}
