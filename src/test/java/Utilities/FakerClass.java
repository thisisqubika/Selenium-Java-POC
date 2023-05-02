package Utilities;

import com.github.javafaker.Faker;

public class FakerClass {
    public FakerClass(){

    }
    Faker faker = new Faker();
    public String getFakerAddress(){
        return faker.address().fullAddress();

    }
    public String getFakerCompany(){
        return faker.company().name();

    }
    public String getState(){
        return faker.address().stateAbbr();

    }
    public String getCity(){
        return faker.address().city();

    }
    public String getZipCode(){
        return faker.address().zipCode();

    }
    public String getMobileNumber(){
        return faker.phoneNumber().cellPhone();

    }

}
