import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class A04_Gestures extends A01_base {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        AndroidDriver <AndroidElement> driver = capabilities();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
        //First action: tap
        TouchAction tAction = new TouchAction(driver);
        WebElement expandLists = driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']");
        //While click belongs to WebDriver, tap belongs to Appium
        //Better to work with Appium java-client works directly with mobile native gestures.
        tAction.tap(tapOptions()
                .withElement(element(expandLists)))
                .perform();
        driver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']").click();
        //Second action: longPress
        WebElement peopleNames=	driver.findElementByXPath("//android.widget.TextView[@text='People Names']");
        tAction.longPress(longPressOptions()
                .withElement(element(peopleNames))
                .withDuration(Duration.ofSeconds(2))).release().perform();

        System.out.println(driver.findElementById("android:id/title").isDisplayed());

        //Third action: swipping
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        driver.findElementByXPath("//android.widget.TextView[@text='Date Widgets']").click();
        driver.findElementByAndroidUIAutomator("text(\"2. Inline\")").click();
        driver.findElementByXPath("//*[@content-desc='9']").click();

        WebElement first=driver.findElementByXPath("//*[@content-desc='15']");
        WebElement second=driver.findElementByXPath("//*[@content-desc='45']");
        tAction.longPress(longPressOptions().withElement(element(first))).moveTo(element(second)).release().perform();

        //Fourth action: scroll
        driver.navigate().back();
        driver.navigate().back();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));").click();

        //Fifth action: drag and drop
        driver.navigate().back();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Drag and Drop\"));").click();
        WebElement source = driver.findElementsByClassName("android.view.View").get(0);
        WebElement destination = driver.findElementsByClassName("android.view.View").get(1);
        tAction.longPress(element(source)).moveTo(element(destination)).release().perform();

        driver.closeApp();

        //UiSelector: text, textContains, id
        /*
        findByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"exact_text\"))");
        findByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(textContains(\"part_text\"))");
        findByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(resourceIdMatches(\".*part_id.*\"))");
        **/

        //TouchAction
        /*
        longPress(LongPressOptions longPressOptions)
        moveTo(PointOption moveToOptions)
        press(PointOption pressOptions)
        release()
        tap(TapOptions tapOptions)
        waitAction(WaitOptions waitOptions)
        * */
    }
}
