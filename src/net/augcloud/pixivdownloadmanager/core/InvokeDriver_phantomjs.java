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

package net.augcloud.pixivdownloadmanager.core;

import net.augcloud.api.TableSub;
import net.augcloud.pixivdownloadmanager.artworks.FinalTable;
import net.augcloud.pixivdownloadmanager.download.MultiThreadDown;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.pagefactory.ByAll;
import net.augcloud.ui.BaseInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Arisa
 * @date ：Created in 2020/3/29 17:12
 * @description：
 * @version: $
 */
public class InvokeDriver_phantomjs implements Runnable{
    
    public InvokeDriver_phantomjs(){
    
    }
    
    static {
        System.setProperty("webdriver.phantomjs.driver", FinalTable.URL+"\\phantomjs-2.1.1-windows\\bin\\phantomjs" +
                ".exe");
    }
    
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BaseInterface.main(null);
            }
        }).start();
        getCookies();
    }
    
    
    //https://accounts.pixiv.net/login
    //https://www.pixiv.net/users/24234/artworks
    private final static String URL_1 = "https://blog.csdn.net/p_programmer/article/details/100677391";
    public static List<String> imgUrl = new ArrayList<>();
    //继续运行
    public static boolean block = true;
    
    
    public static void getCookies() {
        while(true) {
            try {
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                //ssl证书支持
                desiredCapabilities.setCapability("acceptSslCerts", true);
                //截屏支持，这里不需要
                desiredCapabilities.setCapability("takesScreenshot", true);
                //css搜索支持
                desiredCapabilities.setCapability("cssSelectorsEnabled", true);
                desiredCapabilities.setBrowserName("FireFox");
                desiredCapabilities.setAcceptInsecureCerts(true);
                desiredCapabilities.setVersion("1.0");
                //js支持
                desiredCapabilities.setJavascriptEnabled(true);
                //驱动支持
                desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                        FinalTable.URL + "\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
                //创建无界面浏览器对象
                
                PhantomJSDriver driver = new PhantomJSDriver(desiredCapabilities);
                
                //这里注意，把窗口的大小调整为最大，如果不设置可能会出现元素不可用的问题
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
                Thread.sleep(500);
//                driver.get("https://accounts.pixiv.net/login");
//                Thread.sleep(500);
//                List<WebElement> elements = driver.findElements(ByAll.xpath("//div[@class=\"input-field\"]"));
//                elements.get(2).findElement(By.tagName("input")).sendKeys("t-arisa");
//                elements.get(3).findElement(By.tagName("input")).sendKeys("guanjiahao123");
//                List<WebElement> elements1 = driver.findElements(ByAll.xpath("//button[@type=\"submit\"]"));
//                elements1.get(1).click();
//                Thread.sleep(1000);
                
                driver.navigate().to(URL_1);
                while (block) {
                    Thread.sleep(100);
                }
                List<WebElement> img = driver.findElements(ByAll.tagName("img"));
                for (WebElement webElement : img) {
                    String src = webElement.getAttribute("src");
                    if (src.indexOf(".svg") != -1 || src.indexOf("user-profile") != -1) {
                        continue;
                    }
                    synchronized (Integer.valueOf(imgUrl.hashCode())) {
                        imgUrl.add(format(src));
                    }
                }
                
                for (String s1 : imgUrl) {
                    String substring = s1.substring(s1.lastIndexOf("/") + 1, s1.lastIndexOf("_")).replace("_p0", "");
                    System.out.println(substring);
                    if (substring == "" || !substring.matches("^[1-9]\\d*$")) {
                        continue;
                    }
                    driver.navigate().to("https://www.pixiv.net/artworks/" + substring);
                    List<WebElement> a = driver.findElements(ByAll.tagName("a"));
                    for (WebElement webElement : a) {
                        String href = webElement.getAttribute("href");
                        if (href.indexOf("png") == -1 && href.indexOf("jpg") == -1) {
                            continue;
                        }
                        driver.navigate().to(href);
                        String s2 = driver.manage().getCookies().toString();
                        MultiThreadDown.download_(href, s2.replace("[", "").replace("]", ""),new TableSub());
                        break;
                    }
                }
    
                driver.quit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    
    
    private static String format(String s){
        return s.replace("c/250x250_80_a2/custom-thumb","img-master")
                .replace("c/250x250_80_a2/img-master","img-master")
                .replace("custom","master")
                .replace("_square1200","");
        
    }
    
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
    
    }
}
