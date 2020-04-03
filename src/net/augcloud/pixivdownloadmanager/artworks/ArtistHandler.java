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

package net.augcloud.pixivdownloadmanager.artworks;

import com.google.gson.Gson;
import net.augcloud.pixivdownloadmanager.core.cookie.Cookie_;
import net.augcloud.pixivdownloadmanager.core.cookie.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.*;
import java.util.List;
import java.util.Random;

/**
 * @author ：Arisa
 * @date ：Created in 2020/3/30 23:26
 * @description：
 * @version: $
 */
public class ArtistHandler implements Runnable {
    
    public static String url = "https://www.pixiv.net/users/867590/artworks";
    
    public static void main(String[] args) {
        getUrls();
    }
    
    
    @Override
    public void run() {
    
    }
    
    private final static String TEMP_ICON = FinalTable.URL+"\\.temp_icon\\";

    public static void download(String urlString) {
        try {
            URL url = new URL(urlString);
            url.openConnection(new Proxy(Proxy.Type.SOCKS,new InetSocketAddress("127.0.0.1",10808)));
            URLConnection con = url.openConnection();
            //https://i.pximg.net/c/250x250_80_a2/custom-thumb/img/2020/03/30/20/25/15/80455485_p0_custom1200.jpg
            //
            con.setRequestProperty("userAgent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0");
            con.setRequestProperty("Referer","https://www.pixiv.net/");
            con.setRequestProperty("Cookie","limited_ads=%7B%22responsive%22%3A%22%22%7D; " +
                    "__cfduid=d4e289fac8f01be185bc3779bc117f42f1585579425; first_visit_datetime_pc=2020-03-30+23%3A43%3A46; p_ab_id=3; p_ab_id_2=2; p_ab_d_id=599859021; yuid_b=QlRwkmI; __utma=235335808.190356084.1585579526.1585641071.1585645953.4; __utmz=235335808.1585641071.3.2.utmcsr=accounts.pixiv.net|utmccn=(referral)|utmcmd=referral|utmcct=/login; __utmv=235335808.|2=login%20ever=yes=1^3=plan=normal=1^5=gender=female=1^6=user_id=49864470=1^9=p_ab_id=3=1^10=p_ab_id_2=2=1^11=lang=ja=1; login_bc=1; _ga=GA1.2.190356084.1585579526; _gid=GA1.2.390033763.1585580074; device_token=39ed987fe6ab1ad00ad00139da80b2e0; c_type=32; a_type=0; b_type=2; privacy_policy_agreement=1; module_orders_mypage=%5B%7B%22name%22%3A%22sketch_live%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22tag_follow%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22recommended_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22everyone_new_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22following_new_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22mypixiv_new_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22spotlight%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22fanbox%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22featured_tags%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22contests%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22user_events%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22sensei_courses%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22booth_follow_items%22%2C%22visible%22%3Atrue%7D%5D; is_sensei_service_user=1; login_ever=yes; ki_t=1585580848203%3B1585641080669%3B1585646334101%3B2%3B10; ki_r=; tag_view_ranking=RTJMXD26Ak~Wzi7sMlG7S~AjBDLpRc95~0xsDLqCEW6~JXmGXDx4tL~VBEfuG5k7L~q303ip6Ui5~jH0uD88V6F~afQT0PrnAv~OY4dySQF6z~EC_zlSgtNf~HcBlC3F1Sy~ITqZ5UzdOC~PhZHqNj-vy~Lt-oEicbBr~KexWqtgzW1~CmGDG5RP-z~svvkfO5_78~98FF78f4J0~Xoxm0kU21B~0xfpLpFTDF~eVxus64GZU~4Pek0O-bhL~Ew4p81FXLl~MvA0gFnJsc~mxDE3obNef~Pt9XriSgeT~5jQydRTLzH~faHcYIP1U0~28gdfFXlY7~HffPWSkEm-~Ie2c51_4Sp~KN7uxuR89w~g0twlxZSYP~uCl78ezw0i~Ltq1hgLZe3~pzzjRSV6ZO~x_jB0UM4fe~ugo6_d0eA4~r1vRjXa1Om~-31sNyFsbV~5oPIfUbtd6~CiSfl_AE0h~_RfiUqtsxe~gFv6cfMyax~K8esoIs2eW~yqhVAkZ_Lh~ZnmOm5LdC3~m9jKUGptE9~GmCzj7c06U~dJgU3VsQmO~NxWnhUGLoU~q3eUobDMJW~9bXki1yrnh~gomrm8JRwe~H0KKRBjKCB~qpeZSmEVVP~p03HdY7Fpe~s2gzISit5p~i_dZaon0j6~xXhq3SGORQ~dR93h8zmFJ~wmxKAirQ_H~oMaQ-0Oxe-~liM64qjhwQ~OritT7bldw~8NQwqVqSfY~z6i8Mevt17~MjipLdRIMT~6hJ5eStKfK~MI2kUkwUjZ~cWm5eOk6wd~be8rLa7eEu~LX3_ayvQX4~SjMvBa3krH~ZY4i36tn84~cjpi1q2eFp~VNNjr-4cx-~YKc-3MpMoP~l81tLkxINL~D4bYCb0TOw~FgIJuX2QyK~ttdn0vjtXS~EAky5uwxRe~-TeGk6mN86~gqKsadmiG0~MhieHQxNXo~oAnKp9i65M~fMqdKpgiU5; p_b_type=1; cto_bundle=cIa7lF9WcEtKQ2wzdFI0RUJ2eVZtV0RlNzVGbXFGQjEyN3ZWNHElMkZhTlRKR1FhYzZVNEhHOXZHRVR6cHZaV21QcmNDYjU2Mmg0M1JUVFUlMkZKb0ZmTUpSYjVxa1NaYXVqenN1S3V1cTZBQ0ZZMnJ4dzRFSHZwZWJ6WVl0YUFwVGYwQWlBeko2RlNUUGdnRTl3TUM5OTZCVjU0YXBBJTNEJTNE; user_language=ja; adr_id=nOidO2Wsoe2Zp3wALpnXiJKg9HT4gwJtqcbBk2DW6krTMQhT; howto_recent_view_history=4076381; categorized_tags=AjBDLpRc95~BeQwquYOKY~IRbM9pb4Zw; __gads=ID=8bc77a82f30b6445:T=1585585727:S=ALNI_MYIwDD_LPh_DhyBkdyqmyOPRafpCw; __utmc=235335808; _td=450445d5-aaf0-4495-9a87-8d9674756d18; PHPSESSID=49864470_I1f7VIJ2VeNnlFmp4E3ZsoElXpwi4QMU");
            con.connect();
            InputStream is = con.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            String filename = TEMP_ICON+new Random().nextInt(100)+ ".jpg";
            File file = new File(filename);
            FileOutputStream os = new FileOutputStream(file, true);
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            
            os.close();
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static List<String> getUrls(){
        Document document = null;
        try {
            Connection connect = Jsoup.connect(url);
            Gson gson = new Gson();
            String s = FileUtils.FileRead();
            List<String> list = gson.fromJson(s, List.class);
            for (String s1 : list) {
                Cookie_ o = gson.fromJson(s1, Cookie_.class);
                connect.cookie(o.getName(),o.getData());
            }
            
            connect.header("accept-language", "zh-cn").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0");
            connect.proxy(new Proxy(Proxy.Type.SOCKS,new InetSocketAddress("127.0.0.1",10808))).timeout(100000);
            document = connect.get();
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        
        return null;
    }
    
}
