package net.augcloud.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.augcloud.ui.controller.Controller;

public class AuthorInterface  {
    public AuthorInterface() {
    
    }
    
    public void start(Stage primaryStage) {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("author.fxml"));
            Scene scene = new Scene(root, 625, 671);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Pixiv Download Manager - 作者设置");
            stage.getIcons().add(new Image("file://D:/IDEA-Project/Pixivhelper/icon/icon.png"));
            stage.setScene(scene);
            //TODO 测试
    
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
