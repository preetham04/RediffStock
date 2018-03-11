package openmrs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyMrs {

	WebDriver driver;

	public VerifyMrs(WebDriver driver) {

		this.driver = driver;

	}

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "ChromeDriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.openmrs.org/openmrs/appointmentschedulingui/manageAppointmentTypes.page");
		driver.findElement(By.id("username")).sendKeys("Admin");
		driver.findElement(By.id("password")).sendKeys("Admin123");
		driver.findElement(By.id("Inpatient Ward")).click();
		driver.findElement(By.xpath("//form[@id='login-form']")).submit();

		VerifyMrs acc = new VerifyMrs(driver);
		acc.verifyServiceType("Urology");
		

	}

	public boolean verifyServiceType(String sName) throws InterruptedException {

		List<WebElement> pageList = driver.findElements(By.xpath(".//*[@id='appointmentTypesTable_paginate']/span/a"));
		boolean result = false;
		outer: 
			for (int i = 0; i < pageList.size(); i++) {
			List<WebElement> tdList = driver.findElements(By.xpath(".//*[@id='appointmentTypesTable']/tbody/tr/td[1]"));
			pageList = driver.findElements(By.xpath(".//*[@id='appointmentTypesTable_paginate']/span/a"));
			pageList.get(i).click();
		    tdList =driver.findElements(By.xpath(".//*[@id='appointmentTypesTable']/tbody/tr/td[1]"));
			System.out.println(tdList.size());

			for (int j = 0; j < tdList.size(); j++) {
				if (tdList.get(j).getText().contains(sName))

				{
					System.out.println("Match Found!!!");
					result = true;
					break outer;
				}

			}
//			pageList = driver.findElements(By.xpath(".//*[@id='appointmentTypesTable_paginate']/span/a"));
//			pageList.get(i).click();
//			tdList = driver.findElements(By.xpath(".//*[@id='appointmentTypesTable']/tbody/tr/td[1]"));
		}
		return result;

	}
}
