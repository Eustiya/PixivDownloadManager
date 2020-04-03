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

/**
 * @author ：Arisa
 * @date ：Created in 2020/4/1 23:57
 * @description：
 * @version: $
 */
public class StringFormat {
    
    private static final String[] date = {"2020","2019","2018","2017","2016","2015","2014","2013","2012","2011","2010"};
    
    public static String format(String s){
        return s.replace("c/250x250_80_a2/custom-thumb","img-master")
                .replace("c/250x250_80_a2/img-master","img-master")
                .replace("custom","master")
                .replace("_square1200","");
        
    }
    
    public static String getAuthor(String s,String name){
        String substring = s.substring(s.indexOf(name));
        String author = substring.replace("のイラスト - pixiv", "")
                .replace(" - ", "")
                .replace("#オリジナル ", "")
                .replace(name, "")
                .replace("的插画pixiv", "");
        
        return author;
    }
    
    public static int getIndex(String a){
        for (String s : date) {
            int i = a.indexOf(s);
            if(i!=-1){
              return i;
            }
        }
        return -1;
    }
}
