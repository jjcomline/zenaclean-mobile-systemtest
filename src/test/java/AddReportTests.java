import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;



public class AddReportTests {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("udid", "emulator-5554");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "8.1");
        desiredCapabilities.setCapability("deviceName", "NEXUS_5X_API_27");
        desiredCapabilities.setCapability("appPackage", "com.zenaclean");
        desiredCapabilities.setCapability("appActivity", ".MainActivity");
        desiredCapabilities.setCapability("skipUnlock","true");
        desiredCapabilities.setCapability("noReset","false");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
    }

    @Test
    public void clicButtonAndOpenModal() throws InterruptedException{
        TimeUnit.SECONDS.sleep(5); // Wait for app to open

        // Clic on + button
        WebElement addReportButton = driver.findElementByXPath("//*[@text='+']");
        addReportButton.click();

        // Wait for modal to open and then identify title
        TimeUnit.SECONDS.sleep(5);
        String titleText = driver.findElement(By.xpath("//*[@text='Titolo Segnalazione']")).getAttribute("text");

        Assert.assertEquals("Titolo Segnalazione", titleText);
    }

    //Test not working due to bad usage of IDs on React
    @Test
    public void addNewReport() throws InterruptedException{
        TimeUnit.SECONDS.sleep(5); // Wait for app to open

        // Clic on + button
        WebElement addReportButton = driver.findElementByXPath("//*[@text='+']");
        addReportButton.click();

        // Identify title form and input "titolo di prova"
        TimeUnit.SECONDS.sleep(5);
        WebElement titleInput = driver.findElementByAccessibilityId("inputTitle");
        titleInput.clear();
        titleInput.sendKeys("titolo di prova");
        driver.hideKeyboard();

        TimeUnit.SECONDS.sleep(5);
        // Identify description form and input "descrizione di prova"
        WebElement descriptionInput = driver.findElementByAccessibilityId("inputDescr");

        descriptionInput.clear();
        TimeUnit.SECONDS.sleep(1);
        descriptionInput.sendKeys("descrizione di prova");
        driver.hideKeyboard();

        // Clic on "Invia", wait for popup and check "ok"
        TimeUnit.SECONDS.sleep(5);
        //WebElement buttonSend = driver.findElementByAccessibilityId("buttonSend");
        WebElement buttonSend = driver.findElement(By.xpath("//*[@text='Invia']"));
        buttonSend.click();
        TimeUnit.SECONDS.sleep(5);

        WebElement okText = driver.findElement(By.xpath("//*[@text='OK']"));
        Assert.assertEquals(okText.getAttribute("text"), "OK");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
