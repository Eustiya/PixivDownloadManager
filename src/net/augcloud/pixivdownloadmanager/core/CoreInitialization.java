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

import net.augcloud.pixivdownloadmanager.artworks.FinalTable;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.filechooser.FileSystemView;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Arisa
 * @date ：Created in 2020/4/2 0:00
 * @description：
 * @version: $
 */
public class CoreInitialization {
    
    private static ChromeDriver webDriver;
    private static final String downlaodPath = FileSystemView.getFileSystemView() .getHomeDirectory().getAbsolutePath();
    
    static {
        System.setProperty("webdriver.chrome.driver", FinalTable.URL+"\\lib\\chromedriver");
    }
    
    public static void stop(){
        webDriver.quit();
    }
    
    public static ChromeDriver inits(){
        ChromeOptions lChromeOptions = new ChromeOptions();
        HashMap<String, Object> lChromePrefs = new HashMap<String, Object>();
        lChromePrefs.put("profile.default_content_settings.popups", 0);
        lChromePrefs.put("download.default_directory", downlaodPath);
        lChromeOptions.addArguments("--proxy-server=http://coslight.cc:39519");
//        lChromePrefs.put("profile.managed_default_content_settings.images",2);
        lChromePrefs.put("browser.set_download_behavior", "{ behavior: 'allow' , downloadPath: '"+downlaodPath+"'}");
        lChromeOptions.addArguments("--headless");
        lChromeOptions.addArguments("--disable-gpu");
        lChromeOptions.setExperimentalOption("prefs", lChromePrefs);
        
        webDriver = new ChromeDriver(lChromeOptions);
        webDriver.manage().window().setSize(new Dimension(1024, 768));
        return webDriver;
    }
    
    public static ChromeDriver inits(boolean Visible){
       if(!Visible){
           ChromeOptions lChromeOptions = new ChromeOptions();
           HashMap<String, Object> lChromePrefs = new HashMap<String, Object>();
           lChromePrefs.put("profile.default_content_settings.popups", 0);
           lChromePrefs.put("download.default_directory", downlaodPath);
           lChromeOptions.addArguments("--proxy-server=http://coslight.cc:39519");
           lChromeOptions.addArguments("--disable-gpu");
           lChromeOptions.setExperimentalOption("prefs", lChromePrefs);
           webDriver = new ChromeDriver(lChromeOptions);
       } else
        webDriver =  new ChromeDriver();
        webDriver.manage().window().setSize(new Dimension(1024, 768));
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
       return webDriver;
    }
    
    
}
