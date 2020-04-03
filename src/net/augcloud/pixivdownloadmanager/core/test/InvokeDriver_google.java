/*
 * ©2021 August-soft Corporation. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.augcloud.pixivdownloadmanager.core.test;

import net.augcloud.api.TableSub;
import net.augcloud.pixivdownloadmanager.artworks.FinalTable;
import net.augcloud.pixivdownloadmanager.download.MultiThreadDown;
import net.augcloud.ui.BaseInterface;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.pagefactory.ByAll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Arisa
 * @date ：Created in 2020/4/1 16:21
 * @description：
 * @version: $
 */
public class InvokeDriver_google {
    private static ChromeDriver chromeDriver;
    
    static {
        System.setProperty("webdriver.chrome.driver", FinalTable.URL+"\\lib\\chromedriver");
    }
    
    
    private final static String URL_1 = "https://www.pixiv.net/users/375096/artworks";
    public static List<String> imgUrl = new ArrayList<>();
    
    private static String format(String s){
        return s.replace("c/250x250_80_a2/custom-thumb","img-master")
                .replace("c/250x250_80_a2/img-master","img-master")
                .replace("custom","master")
                .replace("_square1200","");
        
    }
    
    public static boolean block = true;
    
    public static void stop(){
        chromeDriver.quit();
    }
    
    public static WebDriver invoke(){
        try {
    
            ChromeOptions lChromeOptions = new ChromeOptions();
            HashMap<String, Object> lChromePrefs = new HashMap<String, Object>();
            lChromePrefs.put("profile.default_content_settings.popups", 0);
            lChromeOptions.addArguments("--proxy-server=http://coslight.cc:39519");
    
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("httpProxy", "coslight.cc:39519");
            Proxy proxy = new Proxy(map);
            
            lChromePrefs.put("download.default_directory", "C:\\Users\\arisa\\Desktop");
            lChromePrefs.put("browser.set_download_behavior", "{ behavior: 'allow' , downloadPath: '"+"C:\\Users\\arisa\\Desktop"+"'}");
            lChromeOptions.addArguments("--headless");
            lChromeOptions.addArguments("--disable-gpu");
            lChromeOptions.setExperimentalOption("prefs", lChromePrefs);
            lChromeOptions.setProxy(proxy);
            ChromeDriver lWebDriver = new ChromeDriver(lChromeOptions);
            lWebDriver.manage().window().setSize(new Dimension(1024, 768));
            lWebDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            lWebDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            lWebDriver.get("https://accounts.pixiv.net/login");
            
            
            
            lWebDriver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
            
            List<WebElement> elements = lWebDriver.findElements(ByAll.xpath("//div[@class=\"input-field\"]"));
            elements.get(2).findElement(By.tagName("input")).sendKeys("t-arisa");
            elements.get(3).findElement(By.tagName("input")).sendKeys("guanjiahao123");
            List<WebElement> elements1 = lWebDriver.findElements(ByAll.xpath("//button[@type=\"submit\"]"));
            //signup-form__submit
            lWebDriver.findElements(By.className("signup-form__submit")).get(1).click();
//            elements1.get(1).click();
            
            Thread.sleep(2000);
            lWebDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            try {
                lWebDriver.navigate().to(URL_1);
            }catch (Exception e){
                lWebDriver.navigate().to(URL_1);
            }
            while (block) {
                Thread.sleep(100);
            }
            Thread.sleep(300);
            lWebDriver.getKeyboard().sendKeys(Keys.END);
            Thread.sleep(300);
            List<WebElement> img = lWebDriver.findElements(ByAll.tagName("img"));
            for (WebElement webElement : img) {
                String src = webElement.getAttribute("src");
                if (src.indexOf(".svg") != -1 || src.indexOf("user-profile") != -1) {
                    continue;
                }
                synchronized (Integer.valueOf(imgUrl.hashCode())) {
                    imgUrl.add(format(src));
                }
            }
            System.out.println(img);
            System.out.println(img.size());
            Thread.sleep(1000);
            for (String s1 : imgUrl) {
                String substring = s1.substring(s1.lastIndexOf("/") + 1, s1.lastIndexOf("_")).replace("_p0", "");
                System.out.println(substring);
                if (substring == "" || !substring.matches("^[1-9]\\d*$")) {
                    continue;
                }
                lWebDriver.navigate().to("https://www.pixiv.net/artworks/" + substring);
                List<WebElement> a = lWebDriver.findElements(ByAll.tagName("a"));
                for (WebElement webElement : a) {
                    String href = webElement.getAttribute("href");
                    if (href.indexOf("png") == -1 && href.indexOf("jpg") == -1) {
                        continue;
                    }
                    lWebDriver.navigate().to(href);
                    String s2 = lWebDriver.manage().getCookies().toString();
                    MultiThreadDown.download_(href, s2.replace("[", "").replace("]", ""),new TableSub());
                    break;
                }
            }
    
//            chromeDriver.quit();
            return lWebDriver;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BaseInterface.main(null);
            }
        }).start();
        invoke();
    }
}
