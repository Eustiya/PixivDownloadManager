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

package net.augcloud.pixivdownloadmanager.core.cookie;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author ：Arisa
 * @date ：Created in 2020/3/29 20:15
 * @description：持久化储存
 * @version: $
 */
public class FileUtils {
    
    private final static String URL = "D:\\IDEA-Project\\Pixivhelper\\user.txt";
    
    public static void write(String username,String password){
        FileWrite(username+","+password);
    }
    
    public static void FileWrite(String content) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(URL);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public static String FileRead() {
        try {
            File file=new File(URL);
            if(file.isFile() && file.exists()){
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                     return lineTxt;
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

    
}
