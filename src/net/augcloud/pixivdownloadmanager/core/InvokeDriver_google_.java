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

import net.augcloud.api.Builder;
import net.augcloud.api.TableSub;
import net.augcloud.pixivdownloadmanager.download.MultiThreadDown;
import net.augcloud.ui.controller.Controller;
import net.augcloud.ui.controller.PDMPrintStream;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByAll;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Arisa
 * @date ：Created in 2020/4/1 23:57
 * @description：
 * @version: $
 */
public class InvokeDriver_google_ {
    
    private static Logger logger = Logger.getLogger(InvokeDriver_google_.class);
    
    private static Set<String> imgUrls = new HashSet<>();
    private static ChromeDriver lWebDriver;
    
    //?p=4
    private static String url = "https://www.pixiv.net/users/27207/artworks?p=1";
    
    public static void invoke(String url) throws Exception {
        
        lWebDriver = LogginUtil.loggin(CoreInitialization.inits());
        try {
               
                for(int k = 1,size = 3;k<=size;size--) {
                    getUrls(lWebDriver,url+"?p="+size);
                }
            getUrls(lWebDriver,url);
        }catch (Exception e){
            e.printStackTrace();
        }
        PDMPrintStream.inits();
        System.out.println(imgUrls.size());
        System.out.println(imgUrls);
        download();
    
        for (int i = falledImg.size() - 1; i >= 0; i--) {
            System.out.println(falledImg);
        }
        
    }
    
    private static void getUrls(ChromeDriver lWebDriver,String url) throws InterruptedException {
        ChromeDriver chrome = lWebDriver;
        lWebDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        System.out.println(url);
        chrome.navigate().to(url);
        Thread.sleep(300);
        lWebDriver.getKeyboard().sendKeys(Keys.END);
        Thread.sleep(300);
        List<WebElement> img = lWebDriver.findElements(ByAll.tagName("img"));
        for (WebElement webElement : img) {
            String src = webElement.getAttribute("src");
            if (src.indexOf(".svg") != -1 || src.indexOf("user-profile") != -1) {
                continue;
            }
            synchronized (imgUrls) {
                imgUrls.add(StringFormat.format(src));
            }
        }
    }
    
    
    public static class multidownload implements Runnable{
    
        private WebDriver webDriver;
        private String url;
        
        public multidownload(WebDriver webDriver,String url){
           this.webDriver = webDriver;
           this.url = url;
        }
    
    
        @Override
        public void run() {
            try {
                download_(webDriver,url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    //lWebDriver.switchTo().defaultContent()
    private static void download_(WebDriver webDriver,String url) throws Exception {
            String substring = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("_")).replace("_p0", "");
            System.out.println(substring);
            if (substring == "" || !substring.matches("^[1-9]\\d*$")) {
                return;
            }
            lWebDriver.navigate().to("https://www.pixiv.net/artworks/" + substring);
            System.out.println(lWebDriver.getTitle());
            if(lWebDriver.equals("403 Forbidden")){
                System.out.println(url+" 访问被阻止");
            }
            Thread.sleep(300);
             lWebDriver.getKeyboard().sendKeys(Keys.END);
             Thread.sleep(300);
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
    
    private static List<String> falledImg = new ArrayList<>();
    
    private static void download(){
            for (String s1 : imgUrls) {
                try {
                    String substring = s1.substring(s1.lastIndexOf("/") + 1, s1.lastIndexOf("_")).replace("_p0", "");
                    System.out.println(substring);
                    if (substring == "" || !substring.matches("^[1-9]\\d*$")) {
                        continue;
                    }
                    lWebDriver.navigate().to("https://www.pixiv.net/artworks/" + substring);
                    Thread.sleep(300);
                    lWebDriver.getKeyboard().sendKeys(Keys.END);
                    Thread.sleep(300);
                    String title = lWebDriver.getTitle();
                    System.out.println(title);
                    if (title.equals("403 Forbidden")) {
                        falledImg.add(title);
                        System.out.println(s1 + " 访问被阻止");
                    }
                    //("//div[@class=\"input-field\"]"));
                    WebElement element = lWebDriver.findElement(By.xpath("//meta[@property=\"twitter:title\"]"));
                    String file = element.getAttribute("content");
                    String athor = StringFormat.getAuthor(title,file);
                    Thread.sleep(300);
                    lWebDriver.getKeyboard().sendKeys(Keys.PAGE_UP);
                    lWebDriver.getKeyboard().sendKeys(Keys.ARROW_UP);
                    Thread.sleep(300);
                    List<WebElement> a = lWebDriver.findElements(ByAll.tagName("a"));
                        Builder builder = TableSub.getTableSubFactory()
                                .setFile(file)
                                .setAuthor(athor)
                                .setStatus("wait")
                                .setThreadNumber("4")
                                
                                
                                .setDate(new Date().toLocaleString())
//                                .setTransfer_rate(new Date().toLocaleString());
                    .setUploaddate(new Date().toLocaleString());
                                
                        
                    for (WebElement webElement : a) {
                        
                        String href = webElement.getAttribute("href");
                        if (href.indexOf("png") == -1 && href.indexOf("jpg") == -1) {
                            continue;
                        }
                        lWebDriver.navigate().to(href);
                        String s2 = lWebDriver.manage().getCookies().toString();
                        int index = StringFormat.getIndex(href);
                        String substring1 = href.substring(index, index + 10);
                        builder.setUploaddate(substring1);
    
    
                        builder = MultiThreadDown.download$(href, s2.replace("[", "")
                                .replace("]", ""), builder);
                        Controller.data.add((TableSub) builder.builder());
                        break;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        
    }
}
