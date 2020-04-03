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

package net.augcloud.ui.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Arisa
 * @date ：Created in 2020/4/3 3:22
 * @description：
 * @version: $
 */
public class AuthorController {
    
    public AuthorController(){
        
    }
    
    @FXML public JFXTextField submitTextField = new JFXTextField();
    
    @FXML public TextArea textArea = new TextArea();
    
    public static List<String> urls = new ArrayList<>();
    
    public void submit2Text(ActionEvent actionEvent){
        String text = submitTextField.getText();
        this.addAuthor(text);
        System.out.println(getURLs().toString());
    }
    
    private void addAuthor(String text){
        textArea.appendText(text+"\n");
        urls.add(text);
    }
    
    public List<String> getURLs(){
        return urls;
    }
}
