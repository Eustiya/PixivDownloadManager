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

/**
 * @author ：Arisa
 * @date ：Created in 2020/3/31 22:27
 * @description：
 * @version: $
 */
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream ;
import java.io.RandomAccessFile;
class DownloadKits
{
    //定义下载资源的路径
    private String path;
    //指定所下载的文件的保存位置
    private String targetFile;
    //定义需要使用多少个线程下载资源
    private int threadNum;
    //定义下载的线程对象
    private DownThread[] threads;
    //定义下载的文件的总大小
    private int fileSize;
    
    private String cookies;
    
    public String getPath() {
        return path;
    }
    
    public DownloadKits setPath(String path) {
        this.path = path;
        return this;
    }
    
    public String getTargetFile() {
        return targetFile;
    }
    
    public DownloadKits setTargetFile(String targetFile) {
        this.targetFile = targetFile;
        return this;
    }
    
    public int getThreadNum() {
        return threadNum;
    }
    
    public DownloadKits setThreadNum(int threadNum) {
        this.threadNum = threadNum;
        return this;
    }
    
    public DownThread[] getThreads() {
        return threads;
    }
    
    public DownloadKits setThreads(DownThread[] threads) {
        this.threads = threads;
        return this;
    }
    
    public int getFileSize() {
        return fileSize;
    }
    
    public DownloadKits setFileSize(int fileSize) {
        this.fileSize = fileSize;
        return this;
    }
    
    public String getCookies() {
        return cookies;
    }
    
    public DownloadKits setCookies(String cookies) {
        this.cookies = cookies;
        return this;
    }
    
    //构造器
    public DownloadKits(String path, String targetFile, int threadNum)
    {
        this.path=path;
        this.threadNum=threadNum;
        //初始化threads数组
        threads=new DownThread[threadNum];
        this.targetFile=targetFile;
    }
    
    //构造器
    public DownloadKits(String path, String targetFile, int threadNum, String cookies)
    {
        this.path=path;
        this.threadNum=threadNum;
        //初始化threads数组
        threads=new DownThread[threadNum];
        this.targetFile=targetFile;
        this.cookies = cookies;
    }
    
    public void download() throws Exception
    {
        URL url=new URL(path);
        //1.通过调用URL对象的openConnection()方法来创建URLConnection对象
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
        //2.设置URLConnection的参数和普通请求属性
        conn.setConnectTimeout(5*1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty(
                "Accept",
                "image/gif,image/jpeg,image/pjpeg,image/pjpeg,"
                        +"application/x-shockwave-flash,application/xaml+xml,"
                        +"application/vnd.ms-xpsdocument,application/x-ms-xbap,"
                        +"application/x-ms-application,application/vnd.ms-excel,"
                        +"application/vnd.ms-powerpoint,application/msword,*/*");
        conn.setRequestProperty("userAgent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0");
        conn.setRequestProperty("Referer","https://www.pixiv.net/");
        conn.setRequestProperty("Cookie",cookies);
    
        conn.setRequestProperty("Accept-Language","zh-CN");
        conn.setRequestProperty("Charset","UTF-8");
        conn.setRequestProperty("Connection","Keep-Alive");
        
        //得到文件大小
        fileSize=conn.getContentLength();
        conn.disconnect();
        int currentPartSize=fileSize/threadNum+1;
        RandomAccessFile file=new RandomAccessFile(targetFile,"rw");
        //设置本地文件的大小
        file.setLength(fileSize);
        file.close();
        for(int i=0;i<threadNum;i++)
        {
            //计算每个线程下载开始的位置
            int startPos=i*currentPartSize;
            //每个线程使用一个RandomAccessFile进行下载
            RandomAccessFile currentPart=new RandomAccessFile(targetFile,"rw");
            //定位该线程的下载位置
            currentPart.seek(startPos);
            //创建下载线程
            threads[i]=new DownThread(startPos,currentPartSize,currentPart);
            //启动下载线程
            threads[i].start();
        }
    }
    
    //获取下载的完成百分比
    public double getCompleteRate()
    {
        //统计多个线程已经下载的总大小
        int sumSize=0;
        for(int i=0;i<threadNum;i++)
        {
            sumSize+=threads[i].length;
        }
        //返回已经完成的百分比
        return sumSize*1.0/fileSize;
    }
    
    public class DownThread extends Thread
    {
        //当前线程的下载位置
        private int startPos;
        //定义当前线程负责下载的文件大小
        private int currentPartSize;
        //当前线程需要下载的文件块
        private RandomAccessFile currentPart;
        //定义该线程已下载的字节数
        public int length;
        
        //构造器
        public DownThread(int startPos,int currentPartSize,RandomAccessFile currentPart)
        {
            this.startPos=startPos;
            this.currentPartSize=currentPartSize;
            this.currentPart=currentPart;
        }
        
        //下载线程的主函数体
        public void run()
        {
            try
            {
                URL url=new URL(path);
                //1.通过调用URL对象的openConnection()方法来创建URLConnection对象
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                //2.设置URLConnection的参数和普通请求属性
                conn.setConnectTimeout(5*1000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty(
                        "Accept",
                        "image/gif,image/jpeg,image/pjpeg,image/pjpeg,"
                                +"application/x-shockwave-flash,application/xaml+xml,"
                                +"application/vnd.ms-xpsdocument,application/x-ms-xbap,"
                                +"application/x-ms-application,application/vnd.ms-excel,"
                                +"application/vnd.ms-powerpoint,application/msword,*/*");
                conn.setRequestProperty("Accept-Language","zh-CN");
                conn.setRequestProperty("userAgent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) " +
                        "Gecko/20100101 Firefox/73.0");
                conn.setRequestProperty("Referer","https://www.pixiv.net/");
                conn.setRequestProperty("Cookie",cookies);
                
                conn.setRequestProperty("Charset","UTF-8");
                //4.远程资源变为可用，程序可以通过输入流读取远程资源的数据
                InputStream inStream=conn.getInputStream();
                
                //跳过stratPos个字节，表明该线程只下载自己负责的那部分文件
                //同时每个线程都会在指定的文件区域写入，所以不会因为多线程而出
                //现资源组合的错乱，从指定位置读取资源，写入到指定位置
                inStream.skip(this.startPos);
                byte[] buffer=new byte[1024];//自己设置一个缓冲区
                int hasread=0;
                
                //-----------------读取网路数据，并写入本地文件-------------------
                //inStream.read(buffer))==-1 表示读取到文件末尾
                while(length<currentPartSize&&(hasread=inStream.read(buffer))!=-1)
                {
                    currentPart.write(buffer,0,hasread);
                    //累计该线程下载的总大小
                    length+=hasread;
                    //System.out.println(getName()+" "+hasread);
                }
                //System.out.println(getName()+" length "+length+" currentPartSize "+currentPartSize);
                //即使length>currentPartSize会是的该线程多写入几个字节，
                //但是下一个线程会从文件的指定位置写入，就会覆盖掉之前线程多写的一部分内容
                currentPart.close();
                inStream.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
    }
}