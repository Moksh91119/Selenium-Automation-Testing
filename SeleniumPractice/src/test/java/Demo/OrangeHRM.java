package Demo;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.FileWriter;
//import junit.framework.Assert;

public class OrangeHRM {

	public String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public WebDriver driver ; 
	private FileWriter csvWriter;
	@BeforeTest
	public void setup() throws IOException
	{
		System.out.println("Before Test executed");
		
		csvWriter = new FileWriter("testresults.csv");
		csvWriter.append("Sr.no,TestCase,Result\n");
		
		// TODO Auto-generated method stub
		driver=new ChromeDriver();

		driver.manage().window().maximize();

		driver.get(baseUrl);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

	@Test(priority = 1, enabled=true)
	public void doLoginWithInvalidCredential() throws InterruptedException, IOException
	{
		try {
			driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("1234");//wrong password

			driver.findElement(By.xpath("//button[@type='submit']")).submit();


			String message_expected = "Invalid credentials";

			String message_actual = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();


			Assert.assertEquals(message_expected, message_actual);

			Thread.sleep(1500);
			csvWriter.append("01,doLoginWithInvalidCredential,Pass\n");
		} catch (AssertionError | Exception e) {
			csvWriter.append("01,doLoginWithInvalidCredential,Fail\n");
		}
	}

	@Test(priority = 2, enabled=true)
	public void loginTestWithValidCredential() throws IOException
	{
		try {
			driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
			driver.findElement(By.xpath("//button[@type='submit']")).submit();

			String pageTitle = driver.getTitle();
			logOut();
			Assert.assertEquals("OrangeHRM", pageTitle);
			csvWriter.append("02,loginTestWithValidCredential,Pass\n");
		} catch (AssertionError | Exception e) {
			csvWriter.append("02,loginTestWithValidCredential,Fail\n");
		}
	}

	@Test(priority =3, enabled=true)
	public void addEmployee() throws InterruptedException, IOException
	{
		try {
			logIn();
			driver.findElement(By.xpath("//span[text()='PIM']")).click();

			driver.findElement(By.xpath("//a[text()='Add Employee']")).click();

			driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Radha");

			driver.findElement(By.xpath(" //input[@placeholder='Last Name']")).sendKeys("Gupta");


			driver.findElement(By.xpath("//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']")).click();


//			Thread.sleep(5000);//pause of 5 seconds

			Runtime.getRuntime().exec("D://Projects//automatedtestorange//SeleniumPractice//AddImageOrangeHRM.exe");


//			Thread.sleep(5000);


			//Thread.sleep(2000);
			driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

			String confirmationMessage = driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();


			if (confirmationMessage.contains("Personal Details")) {
				System.out.println("Employee added successfully!");
			} else {
				System.out.println("Failed to add employee!");
			}

			logOut();
			Assert.assertEquals("Personal Details", confirmationMessage);
			csvWriter.append("03,addEmployee,Pass\n");
		} catch (AssertionError | Exception e) {
			csvWriter.append("03,addEmployee,Fail\n");
		}

	}

	@Test(priority=4, enabled = true)
	public void searchEmployeeNyName() throws InterruptedException, IOException
	{
		try {
			logIn();
	
			driver.findElement(By.xpath("//span[text()='PIM']")).click();
	
			driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
	
			driver.findElements(By.tagName("input")).get(1).sendKeys("John");
	
			//Click the search button.
			driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
	
			//    //span[@class='oxd-text oxd-text--span']
			Thread.sleep(5000)	;
			List<WebElement> element=	driver.findElements(By.xpath("//span[@class='oxd-text oxd-text--span']"));
	
			String expected_message = "Records Found";
			String message_actual = element.get(0).getText();
			System.out.println(message_actual);
	
			logOut();
	
			Assert.assertTrue(message_actual.contains(expected_message));
	
			csvWriter.append("04,searchEmployeeNyName,Pass\n");
		} catch (AssertionError | Exception e) {
			csvWriter.append("04,searchEmployeeNyName,Fail\n");
		}

	}

	@Test(priority =5, enabled=true)
	public void searchEmployeeById() throws InterruptedException, IOException
	{
		try {
			String empId = "0392";
			String message_actual ="";
			logIn();
	
			driver.findElement(By.xpath("//span[text()='PIM']")).click();
	
			driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
	
			driver.findElements(By.tagName("input")).get(2).sendKeys(empId);
	
			driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
	
			Thread.sleep(2000)	;
	
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("window.scrollBy(0," + 500 + ")");
	
			Thread.sleep(2000)	;
	
	
			List<WebElement> rows = driver.findElements(By.xpath("(//div[@role='row'])"));
	
	
			if(rows.size()>1)
			{
				message_actual = driver.findElement(By.xpath("((//div[@role='row'])[2]/div[@role='cell'])[2]")).getText();
	
			}
	
			logOut();
			Assert.assertEquals(empId, message_actual);
			csvWriter.append("05,searchEmployeeById,Pass\n");
		} catch (AssertionError | Exception e) {
			csvWriter.append("05,searchEmployeeById,Fail\n");
		}

	}

	@Test(priority=6, enabled = true)	
	public void fileUpload() throws IOException, InterruptedException
	{
		try {
			logIn();	
	
			driver.findElement(By.xpath("//span[text()='PIM']")).click();
	
			driver.findElement(By.xpath("//span[@class='oxd-topbar-body-nav-tab-item']")).click();
	
			driver.findElement(By.partialLinkText("Data ")).click();
	
			driver.findElement(By.xpath("//div[@class='oxd-file-button']")).click();
	
	
			Thread.sleep(5000);//pause of 5 seconds
	
			Runtime.getRuntime().exec("D://Projects//automatedtestorange//SeleniumPractice//FileUploadOrangeHRM.exe");
	
			Thread.sleep(5000);
	
			driver.findElement(By.xpath("//button[@type='submit']")).submit();
	
			logOut();
			csvWriter.append("06,fileUpload,Pass\n");
		} catch (AssertionError | Exception e) {
			csvWriter.append("06,fileUpload,Fail\n");
		}


	}

	public void logIn()
	{
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");

		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");

		driver.findElement(By.xpath("//button[@type='submit']")).submit();

	}

	public void logOut() 
	{
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();

		List <WebElement> elementlist = driver.findElements(By.xpath("//a[@class='oxd-userdropdown-link']"));


		elementlist.get(3).click();



	}
	
	@Test(priority=7, enabled=true)
	public void ListEmployees() throws InterruptedException, IOException
	{
		try {
			logIn();
			driver.findElement(By.xpath("//span[text()='PIM']")).click();
	
			driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
			Thread.sleep(3000);
	
			List<WebElement> totalLinksElements = driver.findElements(By.xpath("//ul[@class='oxd-pagination__ul']/li"));
	
			int totalLinks = totalLinksElements.size();
	
			for (int i=0; i<totalLinks; i++ )//0,1,2,3,
			{
	
				try
				{
					String currentLinkText = totalLinksElements.get(i).getText();
	
	
					int page = Integer.parseInt(currentLinkText);
					System.out.println("Page: " + page);
	
					totalLinksElements.get(i).click();
	
					Thread.sleep(2000);
	
					List <WebElement> emp_list = driver.findElements(By.xpath("//div[@class='oxd-table-card']/div /div[4]"));
	
					for(int j=0; j<emp_list.size();j++)
					{
						String lastName = emp_list.get(j).getText();
						System.out.println(lastName);
					}
				}
				catch(Exception e)
				{
					System.out.println("Not a number.");
				}
	
	
			}
	
			Thread.sleep(5000);
			logOut();
			csvWriter.append("07,ListEmployees,Pass\n");
		} catch (AssertionError | Exception e) {
			csvWriter.append("07,ListEmployees,Fail\n");
		}
	}
	
	@Test(priority=8, enabled=true)
	public void applyLeave() throws InterruptedException, IOException
	{
		try {
			driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
	
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
	
			driver.findElement(By.xpath("//button[@type='submit']")).submit();
			
			driver.findElement(By.linkText("Leave")).click();
			
			driver.findElement(By.linkText("Apply")).click();
			
			driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")).click();
			
			driver.findElement(By.xpath("//*[contains(text(),'CAN')]")).click();
			
			driver.findElement(By.xpath("//div[@class='oxd-date-input']/input")).sendKeys("2024-12-05");
			
			driver.findElement(By.tagName("textarea")).sendKeys("This is my personal leave");
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			Thread.sleep(5000);
			logOut();
			csvWriter.append("08,applyLeave,Pass\n");
		} catch (AssertionError | Exception e) {
			csvWriter.append("08,applyLeave,Fail\n");
		}
																												

	}
	
	
	@Test(priority=9, enabled=true)
	public void deleteEmployee() throws InterruptedException, IOException
	{
		try {
				
			logIn();
	
			driver.findElement(By.xpath("//span[text()='PIM']")).click();
	
			driver.findElement(By.xpath("//a[text()='Employee List']")).click();
	
			driver.findElements(By.tagName("input")).get(1).sendKeys("Radha");
	
			driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
	
	
			Thread.sleep(3000);
			driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']")).click();
	
			driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")).click();
	
			Thread.sleep(5000);
			logOut();
			csvWriter.append("09,deleteEmployee,Pass\n");
			csvWriter.flush();
		} catch (AssertionError | Exception e) {
			csvWriter.append("09,deleteEmployee,Fail\n");
			csvWriter.flush();
		}

	}






	@AfterTest
	public void tearDown() throws InterruptedException, IOException
	{

		//	logOut();

		Thread.sleep(5000);//wait for 5 secs before quit
		driver.close();
		driver.quit();
		
		Thread.sleep(5000);
		csvWriter.flush();
		csvWriter.close();

	}

}