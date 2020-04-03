package net.augcloud.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.augcloud.pixivdownloadmanager.core.CoreInitialization;

public class BaseInterface extends Application {
    
    @Override
    public void init() throws Exception {
        super.init();
        
    }
    
    @Override
    public void stop() throws Exception {
        super.stop();
        CoreInitialization.stop();
    }
    
    
    
    

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        
       
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        
        Scene scene = new Scene(root,921,466);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Pixiv Download Manager - V1.0 2020/4/1");
        primaryStage.getIcons().add(new Image("file://D:/IDEA-Project/Pixivhelper/icon/icon.png"));
        primaryStage.setScene(scene);
        //TODO 测试
        
        primaryStage.show();
        Controllers.controller.inits();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
