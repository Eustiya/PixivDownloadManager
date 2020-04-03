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

package net.augcloud.pixivdownloadmanager.download;


import net.augcloud.api.Builder;
import net.augcloud.api.TableSub;

import java.util.Random;

/**
 * @author ：Arisa
 * @date ：Created in 2020/3/31 22:30
 * @description：
 * @version: $
 */
public class  MultiThreadDown
{
    public static void download(String url)
            throws Exception
    {
        //初始化DownUtil对象
        String hz = url.substring(url.lastIndexOf("."));
        int i = new Random().nextInt(100000);
        final DownloadKits downUtil=new DownloadKits(url,".temp_icon\\"+i+hz,64);
        downUtil.download();
        new Thread(()->
        {
            while(downUtil.getCompleteRate()<1)
            {
                String s = String.valueOf(downUtil.getCompleteRate() * 100.00);
                if(s.length()>4){
                    s = s.substring(0,4);
                }
                
                System.out.println(i+" 已完成："+s+"%");
                try
                {
                    Thread.sleep(100);
                }
                catch (Exception ex){}
                
            }
        }).start();
       
    }
    
    public static Builder download_(String url, String cookie,Builder builder)
            throws Exception
    {
        //初始化DownUtil对象
        String hz = url.substring(url.lastIndexOf("."));
        int i = new Random().nextInt(100000);
        final DownloadKits downUtil=new DownloadKits(url,".temp_icon\\"+i+hz,64,cookie);
        downUtil.download();
        new Thread(()->
        {
            while(downUtil.getCompleteRate()<1)
            {
                String s = String.valueOf(downUtil.getCompleteRate() * 100.00);
                if(s.length()>4){
                    s = s.substring(0,4);
                }
                
                System.out.println(i+" 已完成："+s+"%");
                try
                {
                    Thread.sleep(100);
                }
                catch (Exception ex){}
                
            }
        }).start();
        builder.setSize(String.valueOf(downUtil.getFileSize()));
        return builder;
    }
    
    public static Builder download$(String url, String cookie,Builder builder)
            throws Exception
    {
        //初始化DownUtil对象
        String hz = url.substring(url.lastIndexOf("."));
        final DownloadKits downUtil=new DownloadKits(url,".temp_icon\\"+((TableSub)builder).getFile()+hz,64,cookie);
        downUtil.download();
        new Thread(()->
        {
            while(downUtil.getCompleteRate()<1)
            {
                String s = String.valueOf(downUtil.getCompleteRate() * 100.00);
                if(s.length()>4){
                    s = s.substring(0,4);
                }
                //TODO 改！
                System.out.println(((TableSub)builder).getFile()+" 已完成："+s+"%");
                try
                {
                    Thread.sleep(100);
                }
                catch (Exception ex){}
                
            }
        }).start();
        builder.setSize(DownloadUtils.getSize(downUtil.getFileSize()));
        return builder;
    }
}