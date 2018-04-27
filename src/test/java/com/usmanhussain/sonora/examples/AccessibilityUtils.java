package com.usmanhussain.sonora.examples;

import com.usmanhussain.sonora.AccessibilityScanner;
import com.usmanhussain.sonora.Result;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccessibilityUtils {

    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\usman\\projects\\Accessibility\\webdriver-accessibility\\src\\test\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        AccessibilityScanner scanner = new AccessibilityScanner(driver);
        Map<String, Object> audit_report = scanner.runAccessibilityAudit();

        driver.get("https://dlg-sit2.ssp-hosting.com/DirectLineCT/");
        Thread.sleep(5000);


        driver.findElement(By.name("DIRECTLINE[1].DUMMYLOGIN[1].PASSWORD")).sendKeys("D1r3ctS3cur3@");
        driver.findElement(By.name("__046FC879087B6FA0 FormButton 28")).click();


        Thread.sleep(3000);
        driver.findElement(By.name("__046FC879087B6FA0 FormButton 28")).click();
        Thread.sleep(3000);

        runAudit(audit_report);


        driver.findElement(By.id("what")).sendKeys("Hairdresser");
        driver.findElement(By.xpath("//*[@id='profession2']/ul/li[1]")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//span[contains(text(),'Mobile business')]")).click();
        driver.findElement(By.xpath("//*[@id=\"business\"]/div[4]/button")).click();

        driver.findElement(By.xpath("//*[@id='employee']/div[1]/div/div[2]/div[1]/div[1]/label/span")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"covers\"]/div[1]/div/div[2]/div[1]/div[1]/label/span")).click();

        Thread.sleep(3000);


        Map<String, Object> audit_report_page2 = scanner.runAccessibilityAudit();

        runAudit(audit_report_page2);


        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='wizardTemplate']/div[1]/div/div/div[3]/button")).click();

        Thread.sleep(5000);


        Map<String, Object> audit_report_page3 = scanner.runAccessibilityAudit();
        runAudit(audit_report_page3);

        Thread.sleep(3000);
//        System.out.println(runAudit(audit_report_page3));

    }


    public static List<String> runAudit(Map<String, Object> audit) {
        ArrayList<String> al = new ArrayList<String>();
        List<Result> errors1 = (List<Result>) audit.get("warning");
        for (Result error : errors1) {
//            al.add( error.getRule());
//            al.add( error.getUrl());
            System.out.println("Rule : -----------------" + error.getRule());
            System.out.println("Audit Rules URL : ------" + error.getUrl());
            for (String element : error.getElements())
//                al.add(element);
                System.out.println("Violated Elements : ------" + element);
        }
        return al;
    }
}
