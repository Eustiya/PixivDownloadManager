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

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.*;
import java.util.Random;

/**
 * @author ：Arisa
 * @date ：Created in 2020/3/31 18:13
 * @description：
 * @version: $
 */
class MutiDownload {
    
    private static final int THREAD_COUNT  = 4;
    
    public static int  THREAD_MAX = 12;
    // c/250x250_80_a2/custom-thumb -> img-master
    // custom -> master
    //https://i.pximg.net/  c/250x250_80_a2/custom-thumb /img/2020/03/30/20/25/15/80455485_p0_ custom 1200.jpg
    //https://i.pximg.net/ img-master                   /img/2020/03/30/20/25/15/80455485_p0_ master 1200.jpg
//    private static final String DOWNLOAD_URL  = "https://i.pximg.net/img-master/img/2019/10/28/23/46/27/77529168_p0_square1200.jpg";
    private static final String fileName  = "D:\\IDEA-Project\\Pixivhelper\\.temp_icon\\";
    
    public void downloadUrl(String DOWNLOAD_URL){
        
        long fileSize;
        HttpURLConnection connection = null;
        try {
            String name = String.valueOf(new Random().nextInt(10000));
            URL url = new URL(DOWNLOAD_URL);
            connection = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.SOCKS,new InetSocketAddress("127.0.0.1",10808)));
            connection.setRequestProperty("userAgent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0");
            connection.setRequestProperty("Referer","https://www.pixiv.net/");
            connection.setRequestProperty("Cookie","limited_ads=%7B%22responsive%22%3A%22%22%7D; " +
                    "__cfduid=d4e289fac8f01be185bc3779bc117f42f1585579425; first_visit_datetime_pc=2020-03-30+23%3A43%3A46; p_ab_id=3; p_ab_id_2=2; p_ab_d_id=599859021; yuid_b=QlRwkmI; __utma=235335808.190356084.1585579526.1585641071.1585645953.4; __utmz=235335808.1585641071.3.2.utmcsr=accounts.pixiv.net|utmccn=(referral)|utmcmd=referral|utmcct=/login; __utmv=235335808.|2=login%20ever=yes=1^3=plan=normal=1^5=gender=female=1^6=user_id=49864470=1^9=p_ab_id=3=1^10=p_ab_id_2=2=1^11=lang=ja=1; login_bc=1; _ga=GA1.2.190356084.1585579526; _gid=GA1.2.390033763.1585580074; device_token=39ed987fe6ab1ad00ad00139da80b2e0; c_type=32; a_type=0; b_type=2; privacy_policy_agreement=1; module_orders_mypage=%5B%7B%22name%22%3A%22sketch_live%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22tag_follow%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22recommended_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22everyone_new_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22following_new_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22mypixiv_new_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22spotlight%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22fanbox%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22featured_tags%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22contests%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22user_events%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22sensei_courses%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22booth_follow_items%22%2C%22visible%22%3Atrue%7D%5D; is_sensei_service_user=1; login_ever=yes; ki_t=1585580848203%3B1585641080669%3B1585646334101%3B2%3B10; ki_r=; tag_view_ranking=RTJMXD26Ak~Wzi7sMlG7S~AjBDLpRc95~0xsDLqCEW6~JXmGXDx4tL~VBEfuG5k7L~q303ip6Ui5~jH0uD88V6F~afQT0PrnAv~OY4dySQF6z~EC_zlSgtNf~HcBlC3F1Sy~ITqZ5UzdOC~PhZHqNj-vy~Lt-oEicbBr~KexWqtgzW1~CmGDG5RP-z~svvkfO5_78~98FF78f4J0~Xoxm0kU21B~0xfpLpFTDF~eVxus64GZU~4Pek0O-bhL~Ew4p81FXLl~MvA0gFnJsc~mxDE3obNef~Pt9XriSgeT~5jQydRTLzH~faHcYIP1U0~28gdfFXlY7~HffPWSkEm-~Ie2c51_4Sp~KN7uxuR89w~g0twlxZSYP~uCl78ezw0i~Ltq1hgLZe3~pzzjRSV6ZO~x_jB0UM4fe~ugo6_d0eA4~r1vRjXa1Om~-31sNyFsbV~5oPIfUbtd6~CiSfl_AE0h~_RfiUqtsxe~gFv6cfMyax~K8esoIs2eW~yqhVAkZ_Lh~ZnmOm5LdC3~m9jKUGptE9~GmCzj7c06U~dJgU3VsQmO~NxWnhUGLoU~q3eUobDMJW~9bXki1yrnh~gomrm8JRwe~H0KKRBjKCB~qpeZSmEVVP~p03HdY7Fpe~s2gzISit5p~i_dZaon0j6~xXhq3SGORQ~dR93h8zmFJ~wmxKAirQ_H~oMaQ-0Oxe-~liM64qjhwQ~OritT7bldw~8NQwqVqSfY~z6i8Mevt17~MjipLdRIMT~6hJ5eStKfK~MI2kUkwUjZ~cWm5eOk6wd~be8rLa7eEu~LX3_ayvQX4~SjMvBa3krH~ZY4i36tn84~cjpi1q2eFp~VNNjr-4cx-~YKc-3MpMoP~l81tLkxINL~D4bYCb0TOw~FgIJuX2QyK~ttdn0vjtXS~EAky5uwxRe~-TeGk6mN86~gqKsadmiG0~MhieHQxNXo~oAnKp9i65M~fMqdKpgiU5; p_b_type=1; cto_bundle=cIa7lF9WcEtKQ2wzdFI0RUJ2eVZtV0RlNzVGbXFGQjEyN3ZWNHElMkZhTlRKR1FhYzZVNEhHOXZHRVR6cHZaV21QcmNDYjU2Mmg0M1JUVFUlMkZKb0ZmTUpSYjVxa1NaYXVqenN1S3V1cTZBQ0ZZMnJ4dzRFSHZwZWJ6WVl0YUFwVGYwQWlBeko2RlNUUGdnRTl3TUM5OTZCVjU0YXBBJTNEJTNE; user_language=ja; adr_id=nOidO2Wsoe2Zp3wALpnXiJKg9HT4gwJtqcbBk2DW6krTMQhT; howto_recent_view_history=4076381; categorized_tags=AjBDLpRc95~BeQwquYOKY~IRbM9pb4Zw; __gads=ID=8bc77a82f30b6445:T=1585585727:S=ALNI_MYIwDD_LPh_DhyBkdyqmyOPRafpCw; __utmc=235335808; _td=450445d5-aaf0-4495-9a87-8d9674756d18; PHPSESSID=49864470_I1f7VIJ2VeNnlFmp4E3ZsoElXpwi4QMU");
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                fileSize = connection.getContentLength();
                long eachSize = fileSize / THREAD_COUNT;
                RandomAccessFile raf = new RandomAccessFile(fileName+name+".jpg", "rw");
                /**
                 * 打开一个RandomAccessFile文件,打开方式为读写(rw)
                 * setLength是先在存储设备占用一块空间,防止下载到一半空间不足
                 */
//                raf.setLength(fileSize);
                raf.close();
                
                for (int i = 0; i < THREAD_COUNT; i++) {
                    long startIndex = i * eachSize;
                    long endIndex = (i + 1) * eachSize - 1;
                    if (i == THREAD_COUNT - 1) {
                        endIndex = fileSize;
                    }
                    /**
                     * 当时最后一个线程的时候,endIndex的值就由文件大小
                     */
                    new DownloadThread(DOWNLOAD_URL, fileName+name+".jpg", i, startIndex, endIndex).start();
                    Thread.sleep(300);
                }
                
                
            }else if(connection.getResponseCode() == 403){
                System.out.println("ip被封");
                Thread.sleep(10000);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            if (connection != null) {
//                connection.disconnect();
//                connection = null;
//            }
    
    }
    
}
class DownloadThread extends Thread {
    
    private String url;
    private String fileName;
    private int threadID;
    private long startIndex;
    private long endIndex;
    private HttpURLConnection connection;
    private RandomAccessFile raf;
    private InputStream inputStream;
    
    public DownloadThread(String url, String fileName, int threadID, long startIndex, long endIndex) {
        super();
        this.url = url;
        this.fileName = fileName;
        this.threadID = threadID;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
    
    @Override
    public void run() {
        boolean block = true;
        MutiDownload.THREAD_MAX--;
        while (block) {
            try {
        
                HttpURLConnection connection = (HttpURLConnection) new URL(url + "?ts=" + System.currentTimeMillis())
                        .openConnection();
                connection.setRequestProperty("userAgent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0");
                connection.setRequestProperty("Referer", "https://www.pixiv.net/");
                connection.setRequestProperty("Cookie", "limited_ads=%7B%22responsive%22%3A%22%22%7D; " +
                        "__cfduid=d4e289fac8f01be185bc3779bc117f42f1585579425; first_visit_datetime_pc=2020-03-30+23%3A43%3A46; p_ab_id=3; p_ab_id_2=2; p_ab_d_id=599859021; yuid_b=QlRwkmI; __utma=235335808.190356084.1585579526.1585641071.1585645953.4; __utmz=235335808.1585641071.3.2.utmcsr=accounts.pixiv.net|utmccn=(referral)|utmcmd=referral|utmcct=/login; __utmv=235335808.|2=login%20ever=yes=1^3=plan=normal=1^5=gender=female=1^6=user_id=49864470=1^9=p_ab_id=3=1^10=p_ab_id_2=2=1^11=lang=ja=1; login_bc=1; _ga=GA1.2.190356084.1585579526; _gid=GA1.2.390033763.1585580074; device_token=39ed987fe6ab1ad00ad00139da80b2e0; c_type=32; a_type=0; b_type=2; privacy_policy_agreement=1; module_orders_mypage=%5B%7B%22name%22%3A%22sketch_live%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22tag_follow%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22recommended_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22everyone_new_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22following_new_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22mypixiv_new_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22spotlight%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22fanbox%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22featured_tags%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22contests%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22user_events%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22sensei_courses%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22booth_follow_items%22%2C%22visible%22%3Atrue%7D%5D; is_sensei_service_user=1; login_ever=yes; ki_t=1585580848203%3B1585641080669%3B1585646334101%3B2%3B10; ki_r=; tag_view_ranking=RTJMXD26Ak~Wzi7sMlG7S~AjBDLpRc95~0xsDLqCEW6~JXmGXDx4tL~VBEfuG5k7L~q303ip6Ui5~jH0uD88V6F~afQT0PrnAv~OY4dySQF6z~EC_zlSgtNf~HcBlC3F1Sy~ITqZ5UzdOC~PhZHqNj-vy~Lt-oEicbBr~KexWqtgzW1~CmGDG5RP-z~svvkfO5_78~98FF78f4J0~Xoxm0kU21B~0xfpLpFTDF~eVxus64GZU~4Pek0O-bhL~Ew4p81FXLl~MvA0gFnJsc~mxDE3obNef~Pt9XriSgeT~5jQydRTLzH~faHcYIP1U0~28gdfFXlY7~HffPWSkEm-~Ie2c51_4Sp~KN7uxuR89w~g0twlxZSYP~uCl78ezw0i~Ltq1hgLZe3~pzzjRSV6ZO~x_jB0UM4fe~ugo6_d0eA4~r1vRjXa1Om~-31sNyFsbV~5oPIfUbtd6~CiSfl_AE0h~_RfiUqtsxe~gFv6cfMyax~K8esoIs2eW~yqhVAkZ_Lh~ZnmOm5LdC3~m9jKUGptE9~GmCzj7c06U~dJgU3VsQmO~NxWnhUGLoU~q3eUobDMJW~9bXki1yrnh~gomrm8JRwe~H0KKRBjKCB~qpeZSmEVVP~p03HdY7Fpe~s2gzISit5p~i_dZaon0j6~xXhq3SGORQ~dR93h8zmFJ~wmxKAirQ_H~oMaQ-0Oxe-~liM64qjhwQ~OritT7bldw~8NQwqVqSfY~z6i8Mevt17~MjipLdRIMT~6hJ5eStKfK~MI2kUkwUjZ~cWm5eOk6wd~be8rLa7eEu~LX3_ayvQX4~SjMvBa3krH~ZY4i36tn84~cjpi1q2eFp~VNNjr-4cx-~YKc-3MpMoP~l81tLkxINL~D4bYCb0TOw~FgIJuX2QyK~ttdn0vjtXS~EAky5uwxRe~-TeGk6mN86~gqKsadmiG0~MhieHQxNXo~oAnKp9i65M~fMqdKpgiU5; p_b_type=1; cto_bundle=cIa7lF9WcEtKQ2wzdFI0RUJ2eVZtV0RlNzVGbXFGQjEyN3ZWNHElMkZhTlRKR1FhYzZVNEhHOXZHRVR6cHZaV21QcmNDYjU2Mmg0M1JUVFUlMkZKb0ZmTUpSYjVxa1NaYXVqenN1S3V1cTZBQ0ZZMnJ4dzRFSHZwZWJ6WVl0YUFwVGYwQWlBeko2RlNUUGdnRTl3TUM5OTZCVjU0YXBBJTNEJTNE; user_language=ja; adr_id=nOidO2Wsoe2Zp3wALpnXiJKg9HT4gwJtqcbBk2DW6krTMQhT; howto_recent_view_history=4076381; categorized_tags=AjBDLpRc95~BeQwquYOKY~IRbM9pb4Zw; __gads=ID=8bc77a82f30b6445:T=1585585727:S=ALNI_MYIwDD_LPh_DhyBkdyqmyOPRafpCw; __utmc=235335808; _td=450445d5-aaf0-4495-9a87-8d9674756d18; PHPSESSID=49864470_I1f7VIJ2VeNnlFmp4E3ZsoElXpwi4QMU");
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(8000);
                connection.setReadTimeout(8000);
                connection.setRequestProperty("RANGE", "bytes=" + startIndex + "-" + endIndex);
                if (connection.getResponseCode() == 206) {
                    inputStream = connection.getInputStream();
                    byte[] bs = new byte[1024];
                    int len;
                    raf = new RandomAccessFile(fileName, "rwd");
                    raf.seek(startIndex);
                    long total = 0;
                    while ((len = inputStream.read(bs)) != -1) {
                        total += len;
                        System.out.println("线程" + threadID + ":" + total);
                        raf.write(bs, 0, len);
                    }
                }
        
            } catch (Exception e) {
                System.out.println("线程" + threadID + ": 连接失败");
            }
//            finally {
//                try {
//                    if (connection != null) {
//                        connection.disconnect();
//                        connection = null;
//                    }
//                    if (raf != null) {
//                        raf.close();
//                        raf = null;
//                    }
//                    if (inputStream != null) {
//                        inputStream.close();
//                        inputStream = null;
//                    }
//                    MutiDownload.THREAD_MAX++;
//                    block = false;
//                    return;
//                } catch (Exception e2) {
//                    System.out.println("线程" + threadID + ": 关闭失败");
//                }
//            }
            
        }
    }
}
