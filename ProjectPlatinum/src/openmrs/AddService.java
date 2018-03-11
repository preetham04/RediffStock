package openmrs;





import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;




public class AddService {
	
	WebDriver driver;
	
	public AddService(WebDriver driver) {
		this.driver = driver;
	}


	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","ChromeDriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.openmrs.org/openmrs/appointmentschedulingui/manageAppointmentTypes.page");
		driver.findElement(By.id("username")).sendKeys("Admin");
		driver.findElement(By.id("password")).sendKeys("Admin123");
		driver.findElement(By.id("Inpatient Ward")).click();
		driver.findElement(By.xpath("//form[@id='login-form']")).submit();
		AddService add = new AddService(driver);
		add.addService("Abc");
		
				
}

	
	public void addService(String sName)
	
	{
		
		//WebDriverWait wait=new WebDriverWait(driver,10);
		driver.findElement(By.xpath(".//*[@id='content']/div/div[1]/button")).click();
		driver.findElement(By.id("name-field")).clear();
		driver.findElement(By.id("name-field")).sendKeys(sName);
		
		driver.findElement(By.id("duration-field")).clear();
		driver.findElement(By.id("duration-field")).sendKeys("30");
		
		driver.findElement(By.id("description-field")).clear();
		driver.findElement(By.id("description-field")).sendKeys("Xyz");
		driver.findElement(By.xpath(".//*[@id='save-button']")).click();
		
		System.out.println("Department   " + sName + "   is added");
		
		
	}
}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
