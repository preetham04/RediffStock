package rediffselect;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

// program to print the "current price/stock price" of companies(from column 4) by match the company names in column 1
public class StockPriceRediff {

	
	  static WebDriver driver;
	public static void main(String[] args) {
		
   //river.manage().deleteAllCookies();
   
	System.setProperty("webdriver.chrome.driver","chromedriver.exe");
	 driver = new ChromeDriver();
	
	
	 driver.get("https://money.rediff.com/gainers/bse/daily");
	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	
      fetchStockPrice("Bank of India");
     
 }
	public static void fetchStockPrice(String sName)
	{
		
		//String sPrice = null;
		//get the header list to check if we have "current price" on header list
		List<WebElement> thList = driver.findElements(By.cssSelector(".dataTable>thead>tr>th"));
		System.out.println(thList.size());
		int colNo = 0;
		 for(int i=0;i<thList.size();i++)
		 {
			 WebElement e = thList.get(i);
		   if(e.getText().contains("Current Price"))
				   {
			   
			   colNo = i+0;
			   System.out.println("Column no:of the Current Price is::::::" +colNo);// prints the column num which has "current price" on header
			                                                                             //will show the index vale 3( actual column 4)
				   }
		 
		 }
	    
		 //get all the rows from first coulumn
		List<WebElement> trList = driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr/td[1]"));
	    int k=0;
	    while(k<trList.size())
	    {
	    	if(trList.get(k).getText().contains(sName))
	    			{        
	    	// while loop searches each row for sName..hence tr[" + (k+1) + "]
	    		
	    	String text =driver.findElement(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr[" + (k+1) + "]/td[4]")).getText();
	    	System.out.println(" Current price of " + sName + "::::::::" + text);
	    	break;
	    		    			}
	    	k++;
	    
	    }
		
		
		
		
	}
}
