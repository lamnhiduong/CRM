package Practice;

import Common.BaseTest;
import Locators.LocatorsCRM;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddNewContact extends BaseTest {
    String COMPANY_NAME = "LamNhi ne hihi";

    public void loginCRM() {

        driver.get("https://crm.anhtester.com/admin/authentication");
        Assert.assertTrue(driver.findElement(By.xpath(LocatorsCRM.headerLoginPage)).isDisplayed(), "Header không tồn tại, không phải trang Login");
//        driver.findElement(By.xpath(LocatorCRM.inputEmail)).sendKeys("admin@example.com");
//        driver.findElement(By.xpath(LocatorCRM.inputPassword)).sendKeys("123456");
//        driver.findElement(By.xpath(LocatorCRM.buttonLogin)).click();
        setText(LocatorsCRM.inputEmail, "admin@example.com");
        setText(LocatorsCRM.inputPassword, "123456");
        clickElement(LocatorsCRM.buttonLogin);
        sleep(1);
        Assert.assertTrue(driver.findElement(By.xpath(LocatorsCRM.menuDashboard)).isDisplayed(), "Không đến được trang Dashboard.");
    }

    private class ActionKeywords {
        public ActionKeywords(WebDriver driver) {
        }

        @Test(priority = 1)
        public void testAddNewCustomer() {

            //Khởi tạo đối tượng class cho ActionKeywords để nhận giá trị driver
            new ActionKeywords(driver);

            loginCRM();

            driver.findElement(By.xpath(LocatorsCRM.menuCustomers)).click();
            Assert.assertTrue(driver.findElement(By.xpath(LocatorsCRM.headerCustomersPage)).isDisplayed(), "Không đến được trang Customer.");
            Assert.assertEquals(driver.findElement(By.xpath(LocatorsCRM.headerCustomersPage)).getText(), "Customers Summary", "Tên header Customer page không đúng.");
            sleep(1);
            driver.findElement(By.xpath(LocatorsCRM.buttonAddNewCustomer)).click();

            //Add New Customer
            driver.findElement(By.xpath(LocatorsCRM.inputCompanyName)).sendKeys(COMPANY_NAME);
            driver.findElement(By.xpath(LocatorsCRM.inputVatNumber)).sendKeys("100");
            driver.findElement(By.xpath(LocatorsCRM.inputPhone)).sendKeys("123456789");
            driver.findElement(By.xpath(LocatorsCRM.inputWebsite)).sendKeys("https://anhtester.com");
            driver.findElement(By.xpath(LocatorsCRM.dropdownGroups)).click();
            sleep(1);
            driver.findElement(By.xpath(LocatorsCRM.inputSearchGroup)).sendKeys("VIP");
            sleep(1);
            driver.findElement(By.xpath(LocatorsCRM.inputSearchGroup)).sendKeys(Keys.ENTER);
            driver.findElement(By.xpath(LocatorsCRM.dropdownGroups)).click();
            driver.findElement(By.xpath(LocatorsCRM.inputAddress)).sendKeys("TTH");
            driver.findElement(By.xpath(LocatorsCRM.inputCity)).sendKeys("Hue");
            driver.findElement(By.xpath(LocatorsCRM.inputState)).sendKeys("Hue");
            driver.findElement(By.xpath(LocatorsCRM.inputZipCode)).sendKeys("514633");
            driver.findElement(By.xpath(LocatorsCRM.buttonCountry)).click();
            sleep(1);
            driver.findElement(By.xpath(LocatorsCRM.inputSearchCountry)).sendKeys("VietNam");
            sleep(1);
            driver.findElement(By.xpath(LocatorsCRM.inputSearchCountry)).sendKeys(Keys.ENTER);
            driver.findElement(By.xpath(LocatorsCRM.buttonSaveCustomer)).click(); //Lưu FORM add new
            sleep(3);

            //Search lại Customer vừa Add New
            driver.findElement(By.xpath(LocatorsCRM.menuCustomers)).click();
            driver.findElement(By.xpath(LocatorsCRM.inputSearchCustomers)).sendKeys(COMPANY_NAME);
            sleep(2);
            Assert.assertTrue(driver.findElement(By.xpath(LocatorsCRM.firstItemCustomerOnTable)).isDisplayed(), "Không tìm thấy Customer.");

            //Kiểm tra giá trị sau khi Add New
            SoftAssert softAssert = new SoftAssert();
            driver.findElement(By.xpath(LocatorsCRM.firstItemCustomerOnTable)).click();
            softAssert.assertEquals(driver.findElement(By.xpath(LocatorsCRM.inputCompanyName)).getAttribute("value"), COMPANY_NAME, "Giá trị Tên Company không đúng");
            softAssert.assertEquals(driver.findElement(By.xpath(LocatorsCRM.inputVatNumber)).getAttribute("value"), "100", "Giá trị VAT không đúng");
            softAssert.assertEquals(driver.findElement(By.xpath(LocatorsCRM.inputPhone)).getAttribute("value"), "123456789", "Giá trị Phone không đúng");
            softAssert.assertEquals(driver.findElement(By.xpath(LocatorsCRM.inputWebsite)).getAttribute("value"), "https://anhtester.com", "Giá trị Website không đúng");

            softAssert.assertAll();
        }

        @Test(priority = 2)
        public void AddNewContactForCustomer() {
            String CONTACTS_NAME = "Lamnhi";
            loginCRM();
            driver.findElement(By.xpath(LocatorsCRM.menuCustomers)).click();
            Assert.assertTrue(driver.findElement(By.xpath(LocatorsCRM.headerCustomersPage)).isDisplayed(), "Không đến được trang Customer.");
            Assert.assertEquals(driver.findElement(By.xpath(LocatorsCRM.headerCustomersPage)).getText(), "Customers Summary", "Tên header Customer page không đúng.");
            sleep(1);
            //Search lại Customer vừa Add New
            driver.findElement(By.xpath(LocatorsCRM.menuCustomers)).click();
            sleep(1);
            driver.findElement(By.xpath(LocatorsCRM.inputSearchCustomers)).sendKeys(COMPANY_NAME);
            sleep(2);
            Assert.assertTrue(driver.findElement(By.xpath(LocatorsCRM.firstItemCustomerOnTable)).isDisplayed(), "Không tìm thấy Customer.");
            driver.findElement(By.xpath(LocatorsCRM.firstItemCustomerOnTable)).click();
            driver.findElement(By.xpath(LocatorsCRM.menuContacts)).click();
            sleep(1);
            Assert.assertTrue(driver.findElement(By.xpath(LocatorsCRM.headerContactPage)).isDisplayed(), "Không tìm thấy trang Contact.");
            driver.findElement(By.xpath(LocatorsCRM.buttonAddNewContact)).click();
            sleep(2);
            Assert.assertTrue(driver.findElement(By.xpath(LocatorsCRM.headerAddNewContactDialog)).isDisplayed(), "Không tìm thấy dialog Add New Contact.");
            sleep(2);

            driver.findElement(By.xpath(LocatorsCRM.inputProfileImage)).sendKeys(System.getProperty("user.dir") + "\\src\\test\\resources\\datatest\\lamnhinehihi.jpg");
            sleep(2);
            setText(LocatorsCRM.inputFirstName, CONTACTS_NAME);
            setText(LocatorsCRM.inputLastName, "Duong");
            setText(LocatorsCRM.inputPosition, "tandinh");
            setText(LocatorsCRM.inputEmailContact, "nhi@gmail.com");
            setText(LocatorsCRM.inputPhoneContact, "123456789");
            setText(LocatorsCRM.inputPasswordContact, "123456");
            clickElement(LocatorsCRM.checkboxDoNotSendEmail);
            clickElement(LocatorsCRM.buttonSaveContact);
            sleep(2);
            Assert.assertTrue(driver.findElement(By.xpath(LocatorsCRM.headerContactPage)).isDisplayed(), "Failed. Không phải header của contacts.");
            clickElement(LocatorsCRM.inputSearchContacts);
            setText(LocatorsCRM.inputSearchContacts, CONTACTS_NAME);
            sleep(2);
            Assert.assertTrue(driver.findElement(By.xpath(LocatorsCRM.fistItemContactsOnTable)).isDisplayed(), "Failed. Không tìm thấy Contacts đã nhập.");
            clickElement(LocatorsCRM.fistItemContactsOnTable);

            //Click vào dữ liệu và check contact
            SoftAssert softAssert = new SoftAssert();

            softAssert.assertEquals(driver.findElement(By.xpath(LocatorsCRM.inputFirstName)).getAttribute("value"), CONTACTS_NAME, "FirstName không đúng.");
            softAssert.assertEquals(driver.findElement(By.xpath(LocatorsCRM.inputLastName)).getAttribute("value"), "Duong", "LastName không đúng.");
            softAssert.assertEquals(driver.findElement(By.xpath(LocatorsCRM.inputEmailContact)).getAttribute("value"), "nhi@gmail.com", "Email không đúng.");
            softAssert.assertEquals(driver.findElement(By.xpath(LocatorsCRM.inputPasswordContact)).getAttribute("value"), "123456", "Password không đúng.");

            softAssert.assertAll();
        }
    }
}

