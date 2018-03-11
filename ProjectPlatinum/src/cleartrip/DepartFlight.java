package cleartrip;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DepartFlight {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "ChromeDriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.cleartrip.com/");

		driver.findElement(By.id("FromTag")).sendKeys("New");
		Thread.sleep(5000);
		// get the list of elements selected - FROM list
		List<WebElement> frList = driver.findElements(By.xpath("//ul[@id = 'ui-id-1']/li"));
		System.out.println("list size      " + frList.size());
		// for loop to select a specific destination from the "FROM" list
		for (int i = 0; i < frList.size(); i++) {
			if (frList.get(i).getText().contains("New York LGA")) {
				frList.get(i).click();
				System.out.println("Match Found");
				break;
			}
		}

		driver.findElement(By.id("ToTag")).clear();
		driver.findElement(By.id("ToTag")).sendKeys("Sydney");
		Thread.sleep(5000);
		// get the list of elements - TO list
		List<WebElement> toList = driver.findElements(By.xpath("//ul[@id = 'ui-id-2']/li"));
		System.out.println("List Size::::" + toList.size());
		// for loop to select a specific destination from the "TO" list
		for (int j = 0; j < toList.size(); j++) {
			if (toList.get(j).getText().contains("CA")) {
				toList.get(j).click();
				System.out.println("Match Found");
			}
		}

		driver.findElement(By.id("DepartDate")).clear();
		driver.findElement(By.id("DepartDate")).click();

		String expMonth = "March";
		String expYear = "2018";
		String expDate = "16";

		// select the year
		String currYear = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[1]/div/div/span[2]")).getText();
		if (currYear.equals(expYear)) {
			System.out.println(" Current Year selected");
		}
		while (!currYear.equals(expYear)) {
			driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/a")).click();
			currYear = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/div/span[2]")).getText();
		}
		System.out.println("Year selected");

		// Select month
		String currMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[1]/div/div/span[1]"))
				.getText();
		if (currMonth.equals(expMonth)) {
			System.out.println(" Current Month selected");
		}
		while (!currMonth.equals(expMonth)) {
			driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/a")).click();
			currMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[1]/div/div/span[1]")).getText();
		}
		System.out.println("Month selected");

		// Select Date
		List<WebElement> tableList = driver.findElements(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr/td"));

		for (WebElement e : tableList) {
			String date = e.getText();
			if (date.equals(expDate)) {
				e.click();
				break;
			}
		}

		System.out.println("Date Selected");
		Thread.sleep(5000);

		driver.findElement(By.id("SearchBtn")).click();
		Thread.sleep(10000);
		// List<WebElement> bookList =
		// driver.findElements(By.xpath("//tbody[2]/tr[1]/th[6]"));
		List<WebElement> bookList = driver.findElements(By.id("BaggageBundlingTemplate"));
		System.out.println("Price List  " + bookList.size());

	}
}
