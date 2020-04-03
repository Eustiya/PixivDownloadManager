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
 * @date ：Created in 2020/4/1 18:14
 * @description：
 * @version: $
 */
public class DownloadUtils {
    
    public static String getSize(int size){
        String unit = "kb";
        String result = null;
        double size_d = Double.parseDouble(String.valueOf(size));
         if(size>=1048576){
             double v = size_d / 1048576D;
             unit="mb";
             result = String.valueOf(v).substring(0,5)+unit;
         }else if(size>=1024){
             double v = size_d / 1024D;
             result = String.valueOf(v).substring(0,6)+unit;
         }else if(size<=1024){
             double v = size_d;
             unit="b";
             result = String.valueOf(v).substring(0,6)+unit;
         }else{
             double v = size_d/1073741824D;
             unit="gb";
             result = String.valueOf(v).substring(0,4)+unit;
         }
         return result;
    }
}
