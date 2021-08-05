package tests;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import datadriven.ReadExcel;
import io.github.bonigarcia.wdm.WebDriverManager;

public class User_signup {

	public WebDriver driver;

	@Test

	public void validate_registration() throws Exception {
		ReadExcel exceldata = new ReadExcel();

		ArrayList<String> data = exceldata.getData("createUser2");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("https://tauria.com/signin");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/button")).click();

		driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(data.get(1));
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div/form/div/button")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id=\"first\"]")).sendKeys("CHIBUGO");
		driver.findElement(By.xpath("//*[@id=\"last\"]")).sendKeys("KAINE");
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(data.get(2));
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/form/div/button")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Test1");
		driver.findElement(By.xpath("//*[@id=\"confirm\"]")).sendKeys("Test1");

		Thread.sleep(3000);

		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/form/div/button")).click();

		Thread.sleep(70000);

		String actual_result = (driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[1]")).getText());
		String expected_result = ("What would you like to start?");

		Assert.assertEquals(actual_result, expected_result);

	}

	@AfterTest

	public void teardown() {
		driver.close();

	}

}
