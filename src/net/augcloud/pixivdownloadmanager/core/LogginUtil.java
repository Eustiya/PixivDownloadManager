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

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import net.augcloud.pixivdownloadmanager.artworks.FinalTable;
import net.augcloud.pixivdownloadmanager.core.cookie.Cookie_;
import net.augcloud.pixivdownloadmanager.core.cookie.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ：Arisa
 * @date ：Created in 2020/4/1 23:56
 * @description：
 * @version: $
 */
public class LogginUtil {
    
    private static final String URL = "https://accounts.pixiv.net/login";
    
    static {
        System.setProperty("webdriver.chrome.driver", FinalTable.URL+"\\lib\\chromedriver");
    }
    
    private static String username = "";
    private static String password = "";
    private static Gson gson = new Gson();
    
    public static ChromeDriver loggin(ChromeDriver lWebDriver) {
        String s = FileUtils.FileRead();
        if (s != null && s != "") {
            lWebDriver = logginByCookie(s);
        } else {
            lWebDriver = logginByDirect();
        }
        f5Cookies(lWebDriver);
        return lWebDriver;
    }
    
    /**
     * 刷新cookie
     *
     * @param webDriver Web驱动
     * @time 2020-04-02 0:24
     **/
    private static void f5Cookies(ChromeDriver webDriver) {
        List<Cookie_> cookies_ = new ArrayList<>();
        Set<Cookie> cookies = webDriver.manage().getCookies();
        for (Cookie cookie : cookies) {
            Cookie_ cookie_ = new Cookie_(cookie.getName(), cookie.getValue());
            cookies_.add(cookie_);
        }
        String s = gson.toJson(cookies_);
        FileUtils.FileWrite(s);
    }
    
    
    /**
     * 直接登入
     *
     * @time 2020-04-02 0:24
     * @return: webDriver 登入后的内容
     **/
    private static ChromeDriver logginByDirect() {
        ChromeDriver lWebDriver = CoreInitialization.inits();
        try {
            lWebDriver.get(URL);
            Thread.sleep(300);
            String title = lWebDriver.getTitle();
            System.out.println(title);
            Thread.sleep(300);
            List<WebElement> elements = lWebDriver.findElements(ByAll.xpath("//div[@class=\"input-field\"]"));
            elements.get(2).findElement(By.tagName("input")).sendKeys(username);
            System.out.println("输入账号 成功");
            elements.get(3).findElement(By.tagName("input")).sendKeys(password);
            System.out.println("输入密码 成功");
            List<WebElement> elements1 = lWebDriver.findElements(ByAll.xpath("//button[@type=\"submit\"]"));
            elements1.get(1).click();
            title = lWebDriver.getTitle();
            if(!title.equals("登录 | pixiv")){
                System.out.println("登陆 成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //登入失败
        }
        return lWebDriver;
    }
    
    /**
     * 传入cookies登录
     *
     * @param s cookies序列化后值
     * @time 2020-04-02 0:24
     * @return: webDriver 登入后的内容
     **/
    private static ChromeDriver logginByCookie(String s) {
        ChromeDriver lWebDriver = CoreInitialization.inits();
        try {
            lWebDriver.get(URL);
            String title = lWebDriver.getTitle();
            System.out.println(title);
            Thread.sleep(300);
            lWebDriver.manage().deleteAllCookies();
            List list = gson.fromJson(s, List.class);
            for (Object o : list) {
                if (o instanceof Cookie_) {
                    Cookie_ o1 = (Cookie_) o;
                    lWebDriver.manage().addCookie(o1.getCookie());
                } else {
                    LinkedTreeMap o1 = (LinkedTreeMap) o;
                    String name = String.valueOf(o1.get("name"));
                    String data = String.valueOf(o1.get("data"));
                    lWebDriver.manage().addCookie(new Cookie(name,data));
                }
            }
            System.out.println("cookie 传入成功");
            lWebDriver.navigate().to("https://www.pixiv.net/");
            title = lWebDriver.getTitle();
            if(!title.equals("登录 | pixiv")){
                System.out.println("登陆 成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //登入失败
        }
        return lWebDriver;
        
    }
    
    
}
