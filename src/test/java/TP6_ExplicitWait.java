import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TP6_ExplicitWait {
    WebDriver driver;

    @BeforeMethod
    public void ouvrirChrome(){
        driver = new ChromeDriver();
        driver.get("https://www.amazon.fr/");

        String acceptCookiesXpath = "sp-cc-accept";
        WebElement cookiesButton = driver.findElement(By.id(acceptCookiesXpath));
        cookiesButton.click();
        driver.manage().window().maximize();

    }
    @AfterMethod
    public void fermerChrome(){
        driver.quit();
    }


    @Test
    public void testAmazon(){
        //Arrange

        //Act
        By menuButton = By.id("nav-hamburger-menu");
        driver.findElement(menuButton).click();

        By amazonPrimeButton = By.cssSelector(".hmenu-item[data-menu-id='2']");

        WebDriverWait wait =new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(amazonPrimeButton));

        driver.findElement(amazonPrimeButton).click();


        By filmButton = By.cssSelector(".hmenu-visible[data-menu-id='2'] li:nth-of-type(4)");
        wait.until(ExpectedConditions.elementToBeClickable(filmButton));

        driver.findElement(filmButton).click();
        //Assert

        WebElement accueilButton = driver.findElement(By.id("pv-nav-home"));

        //wait.until(ExpectedConditions.visibilityOf(accueilButton));


        Assert.assertTrue(accueilButton.isDisplayed(), "Le bouton accueil n'est pas visible");
        //Assert.assertTrue( textIphoneSeachOne.getText().contains( resultatAttendu ),"L'iphone n'a pas été trouvé  "+textIphoneSeachOne.getText());
    }
}
