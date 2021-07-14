import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class A03_ByAndroidUIAutomator extends A01_base {

    public static void main(String[] args) throws MalformedURLException {

        AndroidDriver<AndroidElement> driver=capabilities();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Searching UiSelector (for properties), and UiScrollable
        // driver.findElementByAndroidUIAutomator("attribute("value")")
        driver.findElementByAndroidUIAutomator("text(\"Views\")").click();

        System.out.println(driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)").size());
    }

}