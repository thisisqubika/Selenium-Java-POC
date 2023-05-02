package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SignupPage extends LoginPage {
    public SignupPage(WebDriver remoteDriver) {
        super(remoteDriver);

    }
    @FindBy(xpath="//*[@class='title text-center']")
    List<WebElement> signupTitleList;
    public List<WebElement> getSignuptitleList(){
        return signupTitleList;

    }
    @FindBy(id="id_gender1")
    WebElement mrGenderRadiobutton;
    public void clickOnMrRadiobutton(){
        mrGenderRadiobutton.click();

    }
    @FindBy(id="id_gender2")
    WebElement mrsGenderRadiobutton;
    public void clickOnMrsRadiobutton(){
        mrsGenderRadiobutton.click();

    }
    public void chooseTitleRadiobutton(String title){
        if(title.equals("mr")){
            this.clickOnMrRadiobutton();

        }
        if(title.equals("mrs")){
            this.clickOnMrsRadiobutton();

        }

    }
    @FindBy(id="name")
    WebElement nameInput;
    public WebElement getNameInput(){
        return nameInput;

    }
    @FindBy(id="email")
    WebElement emailInput;
    public WebElement getEmailInput(){
        return emailInput;

    }
    @FindBy(id="password")
    WebElement passwordInput;
    public WebElement getPasswordInput(){
        return passwordInput;

    }
    @FindBy(id="days")
    WebElement daysListOfValues;
    public WebElement getDaysListOfValues(){
        return daysListOfValues;

    }

    @FindBy(id="months")
    WebElement monthsListOfValues;
    public WebElement getMonthsListOfValues(){
        return monthsListOfValues;

    }

    @FindBy(id="years")
    WebElement yearsListOfValues;
    public WebElement getYearsListOfValues(){
        return yearsListOfValues;

    }
    public void setDateOfBirth(String day, String month, String year){
        selectByVisibleText(this.getDaysListOfValues(), day);
        selectByVisibleText(this.getMonthsListOfValues(), month);
        selectByVisibleText(this.getYearsListOfValues(), year);

    }
    @FindBy(id="newsletter")
    WebElement newsletterCheckbox;
    public void clickOnNewsletterCheckbox(){
        newsletterCheckbox.click();

    }
    @FindBy(id="optin")
    WebElement specialOffersCheckbox;
    public void clickOnSpecialOffersCheckbox(){
        specialOffersCheckbox.click();

    }
    @FindBy(id="first_name")
    WebElement firstName;
    public WebElement getFirstName(){
        return firstName;

    }
    @FindBy(id="last_name")
    WebElement lastName;
    public WebElement getLastName(){
        return lastName;

    }
    @FindBy(id="address1")
    WebElement firstAddress;
    public WebElement getFirstAddress(){
        return firstAddress;

    }
    @FindBy(id="company")
    WebElement company;
    public WebElement getCompany(){
        return company;

    }
    @FindBy(id="country")
    WebElement countryListOfValues;
    public void selectCountriesListOfValues(String value){
        selectByVisibleText(countryListOfValues, value);

    }
    @FindBy(id="state")
    WebElement state;
    public WebElement getState(){
        return state;

    }
    @FindBy(id="city")
    WebElement city;
    public WebElement getCity(){
        return city;

    }
    @FindBy(id="zipcode")
    WebElement zipCode;
    public WebElement getZipCode(){
        return zipCode;

    }
    @FindBy(id="mobile_number")
    WebElement mobileNumber;
    public WebElement getMobileNumber(){
        return mobileNumber;

    }
    @FindBy(xpath="//*[@data-qa='create-account']")
    WebElement createAccountBtn;
    public AccountCreatedPage clickOnCreateAccountBtn(){
        createAccountBtn.click();

        accountCreatedPage = new AccountCreatedPage(driver);
        return accountCreatedPage;

    }

}