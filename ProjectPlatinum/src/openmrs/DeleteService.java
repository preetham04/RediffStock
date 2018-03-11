package openmrs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DeleteService {

	WebDriver driver;

	public DeleteService(WebDriver driver) {

		this.driver = driver;

	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "ChromeDriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.openmrs.org/openmrs/appointmentschedulingui/manageAppointmentTypes.page");
		driver.findElement(By.id("username")).sendKeys("Admin");
		driver.findElement(By.id("password")).sendKeys("Admin123");
		driver.findElement(By.id("Inpatient Ward")).click();
		driver.findElement(By.xpath("//form[@id='login-form']")).submit();
		DeleteService del = new DeleteService(driver);
		del.verifyDeleteService("Abc");

	}

	public boolean verifyDeleteService(String sName) {

		List<WebElement> pageList = driver.findElements(By.xpath(".//*[@id='appointmentTypesTable_paginate']/span/a"));
		outer: for (int i = 0; i < pageList.size(); i++) {
			List<WebElement> tdList = driver.findElements(By.xpath(".//*[@id='appointmentTypesTable']/tbody/tr/td[1]"));
			pageList = driver.findElements(By.xpath(".//*[@id='appointmentTypesTable_paginate']/span/a"));
			pageList.get(i).click();
			for (int j = 0; j < tdList.size(); j++) {
				if (tdList.get(j).getText().contains(sName)) {
					driver.findElement(By.xpath("//tr[" + (j + 1) + "]/td[4]/span/i[@title ='Delete']")).click();
					break outer;
				}
			}

		}

		boolean result = false;
		List<WebElement> buttonList = driver.findElements(
				By.xpath(".//*[@id='delete-appointment-type-dialog']/div[2]/button[@class='confirm right']"));
		System.out.println("Number of Buttons" + buttonList.size());
		for (int n = 0; n < buttonList.size(); n++) {
			try {
				if (buttonList.get(n).isDisplayed() || buttonList.get(n).isEnabled()) {
					buttonList.get(n).click();
					result = true;
					break;
				}
			} catch (Exception e) {

				System.out.println("Element is not visible|| enabled" + e.getMessage());
			}
		}

		return result;

	}

}
