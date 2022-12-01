package StockDataFactory;

import org.testng.annotations.DataProvider;

public class StockDataFactory {
    @DataProvider(name="stockProvider")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        { "Javascript and Sons branch office No. 14","501-659-960", 53 },
                        { "Javascript and Sons branch office No. 15","501-747-152", 68 },
                        { "Javascript and Sons branch office No. 16","501-926-535", 84 }
                };

    }

}
