package net.augcloud.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.augcloud.ui.controller.Controller;

public class ProxyInterface {
    
    
    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            
        
            Parent root = FXMLLoader.load(getClass().getResource("proxy.fxml"));
            //"351.0" prefWidth="576.0">
            Scene scene = new Scene(root, 576,351);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Pixiv Download Manager - 代理设置");
            stage.getIcons().add(new Image("file://D:/IDEA-Project/Pixivhelper/icon/icon.png"));
            stage.setScene(scene);
            //TODO 测试
    
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
}
