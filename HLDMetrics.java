package com.metric;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HLDMetrics {

	public static void main(String[] args) throws IOException {
		
		
		
		System.out.println("Testing");

		String chrome = "C:\\TOOLS\\Selenium\\new\\chromedriver.exe";
		String Edge = "C:\\Users\\gautham_r\\Desktop\\Automation\\JARS\\MicrosoftWebDriver.exe";
		String Firefox = "C:\\Users\\gautham_r\\Desktop\\Automation\\JARS\\geckodriver.exe";

		String userName = "raghavendar.s";
		String password = "connect";

		System.setProperty("webdriver.chrome.driver", chrome);
		WebDriver driver = new ChromeDriver();

		/*
		 * System.setProperty("webdriver.ie.driver", Edge); WebDriver driver = new
		 * InternetExplorerDriver(); driver.manage().window().maximize();
		 */

		driver.manage().window();

		// WebDriverWait wait = new WebDriverWait(driver, 20);

		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		String URL = "http://10.161.58.140:8080/connect/RSS/TRQ-198";

		

		driver.get(URL);

		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement frame = driver.findElement(By.id("loginFrame"));
		driver.switchTo().frame(frame);
		  WebElement inputUser = driver.findElement(By.xpath("//input[@id='inputUser']"));
		  
		  inputUser.sendKeys(userName);
		  
		  System.out.println(inputUser.getText());
		  
		  WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		  
		  inputPassword.sendKeys(password);
		  
		  WebElement inputComment = driver.findElement(By.id("inputComment"));
		  
		  inputComment.sendKeys("HLD update");
		  
		  WebElement signin = driver.findElement(By.id("signin"));
		  signin.click();
		 
		  try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		  driver.switchTo().defaultContent();
		
		
		
		
		
		
		// TODO Auto-generated method stub
        //I have placed an excel file 'Test.xlsx' in my D Driver 
			FileInputStream fis = new FileInputStream("C:\\TOOLS\\Selenium\\Traceability_Matrix_hld.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
                        //I have added test data in the cell A1 as "SoftwareTestingMaterial.com"
                        //Cell A1 = row 0 and column 0. It reads first row as 0 and Column A as 0.
			
			for (int i=1;i<4;i++) {
				
				Row row = sheet.getRow(i);
				//TRQ ID
				Cell cell = row.getCell(0);
	           
				//System.out.println(sheet.getRow(0).getCell(0));
				
				String cellval = cell!=null?cell.getStringCellValue():"";
				
				
				//HLD ID
				
				Cell cell2 = row.getCell(1);
	           
				//System.out.println(sheet.getRow(0).getCell(0));
				
				String cellval2 = cell2!=null?cell2.getStringCellValue():"";
				
				 System.out.println((i+1)+". TRQL"+cellval+"="+cellval2);
				 
				 
				 if(cellval2.indexOf(",")!=-1) {
					 String[]  listVal=cellval2.split(","); 
					 
					 // looping if value has commas
					 for(String inputValue:listVal) {
					 
						 mapHLD(cellval,inputValue,driver);
						 System.out.println(cell2);
					 
					 }
				 }
				 else {
					
					 mapHLD(cellval,cellval2,driver);
					 System.out.println(cell2);
				 }
				
				
			}
			driver.close();
			
	}
	
	public static void mapHLD(String trq,String hld,WebDriver driver) {
		try {
			System.out.println("mapHLD");

		
			/*
			 * System.setProperty("webdriver.ie.driver", Edge); WebDriver driver = new
			 * InternetExplorerDriver(); driver.manage().window().maximize();
			 */
			

			// WebDriverWait wait = new WebDriverWait(driver, 20);

			// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			String URL = "http://10.161.58.140:8080/connect/RSS/"+trq;

			String select = hld;
			System.out.println("begin of :"+select);
			
			driver.get(URL);

			Thread.sleep(5000L);

			
			  
			  
			 // WebElement selectExisting = driver.findElement(By.className("button.buttonCreateSelect.btn.btn-default.sel_SelectExisting"));
			  //selectExisting.click();
			  
				

				// System.out.println(highlightContext.getText());
				
				Boolean isPresent1 = driver.findElements(By.id("highlightContext")).size() > 0;
				System.out.println("highlightContext - Element found :"+isPresent1);
				waitEvent(isPresent1);
				Thread.sleep(20000L);
				
				WebElement highlightContext = driver.findElement(By.id("highlightContext"));
				
				WebElement mainObj = highlightContext.findElement(By.id("main"));
				
				// System.out.println(mainObj.getText());

				WebElement itemDetails = mainObj.findElement(By.id("itemDetails"));
				
				// System.out.println(itemDetails.findElements(By.className("panel-body-v-scroll")));

				Boolean isPresent2=itemDetails.findElements(By.className("panel-body-v-scroll")).size()>0;
				
				waitEvent(isPresent2);
				
				WebElement panelBodyClass = itemDetails.findElements(By.className("panel-body-v-scroll")).get(0);
				System.out.println("panelBodyClass - Element found :");
				WebElement panelBody = panelBodyClass.findElements(By.className("panel-body")).get(0);
				System.out.println("panel-body - Element found :");
				WebElement ft_links = panelBody.findElements(By.className("ft_links")).get(0);
				System.out.println("ft_links - Element found :");
				// System.out.println(ft_links.getText());
				Thread.sleep(10000L);
				// WebElement hiddenPrint =
				// ft_links.findElements(By.className("hidden-print")).get(0);

				ft_links.findElements(By.className("hidden-print")).forEach(e -> {
					e.findElements(By.className("sel_SelectExisting")).forEach(s -> {
						s.click();

						try {
							Thread.sleep(5000L);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						WebElement uiDialog = driver.findElements(By.className("ui-dialog")).get(0);

						WebElement treeContent = uiDialog.findElements(By.className("treeContent")).get(0);

						List<WebElement> links = treeContent.findElements(By.tagName("li"));

						WebElement searchContent = uiDialog.findElements(By.className("searchNoX")).get(0);
						
						// HLD to search and select
						searchContent.sendKeys(select);
						
						WebElement searchbtnContent = uiDialog.findElements(By.className("btn-sssearch")).get(0);
						searchbtnContent.click();
						
						
						Boolean isPresent = driver.findElements(By.className("searchResultSelect")).size() > 0;
						
						if(!isPresent) {
							try {
								Thread.sleep(5000L);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						try {
							Thread.sleep(5000L);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						WebElement searchResultContent = uiDialog.findElements(By.className("searchResultSelect")).get(0);
						searchResultContent.click();
						
						
						//WebElement textDemo = uiDialog.findElement(By.xpath(xPath));
						
						
						//WebElement textDemo = driver.findElement(By.xpath(xPath1));
						WebElement textDemo = driver.findElement(By.xpath("//div[@id='selectItemDlg']//input[@type='checkbox']"));
						if(textDemo.isDisplayed())
						{
						System.out.println("Element found using text");
						//driver.findElement(By.xpath("//div[@id='selectItemDlg']//span[text()='Un/Select all']"));
						
						
						if(!textDemo.isSelected()) {
							textDemo.click();
						}
						}
						 
						else {
						System.out.println("Element not found");
						driver.quit();
						}
						
						try {
							Thread.sleep(5000L);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						WebElement submitContent = uiDialog.findElements(By.className("btnDoIt")).get(0);
						submitContent.click();
						
						try {
							Thread.sleep(5000L);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						System.out.println("end of submit:"+select);
			
						// System.out.println(links.size());

					});
				});
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	private static void waitEvent(boolean isPresent) {
		
		if(!isPresent) {
			try {
				Thread.sleep(5000L);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	

}
