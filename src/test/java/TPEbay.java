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

import java.util.List;

public class TPEbay {
    WebDriver driver;
    @BeforeMethod
    public void ouvrirChrome(){
        driver = new ChromeDriver();
        driver.get("https://www.ebay.fr/");

        driver.manage().window().maximize();

    }
    @AfterMethod
    public void fermerChrome(){

        driver.quit();
    }

    @Test
    public void testEbay(){
        //Arrange

        String recherche = "1";

        //Act
        By ExplorerButton = By.id("gh-shop-a");

        driver.findElement(ExplorerButton).click();

        List<WebElement> listMenu = driver.findElements(By.cssSelector("#gh-sbc a.scnd"));
        listMenu.get(14).click();

        driver.findElement(By.cssSelector(".b-list__items_nofooter.srp-results>li:nth-of-type(1)")).click();

        // prise du titre de l'article
        String resultatAttendu = driver.findElement(By.id("itemTitle")).getText();

        // clique ajout panier
        driver.findElement(By.id("isCartBtn_btn")).click();

        //Assert
        WebElement textMain = driver.findElement(By.className("main-title"));
        Assert.assertTrue( textMain.getText().contains(recherche) );

        String textMaillot = driver.findElement(By.className("BOLD")).getText();

        Assert.assertEquals( textMaillot, resultatAttendu);

    }
}
