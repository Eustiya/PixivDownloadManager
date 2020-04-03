package net.augcloud.ui.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import net.augcloud.api.CoreUtils;
import net.augcloud.api.TableSub;
import net.augcloud.pixivdownloadmanager.core.InvokeDriver_phantomjs;
import net.augcloud.pixivdownloadmanager.core.test.InvokeDriver_google;
import net.augcloud.ui.AuthorInterface;
import net.augcloud.ui.Controllers;
import net.augcloud.ui.OptionsInterface;
import net.augcloud.ui.ProxyInterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Controller {
    
    @FXML
    public TableView<TableSub> mainTable = new TableView<>();
    
    @FXML
    public TableColumn<TableSub, String> sizeColumn = new TableColumn<>();
    
//    @FXML
//    public TableColumn<TableSub, String> statusColumn = new TableColumn<>();
    
    @FXML
    public TableColumn<TableSub, String> fileColumn = new TableColumn<>();
    
    @FXML
    public TableColumn<TableSub, String> authorColumn = new TableColumn<>();
    
//    @FXML
//    public TableColumn<TableSub, String> transferrateColumn = new TableColumn<>();
    
    @FXML
    public TableColumn<TableSub, String> dateColumn = new TableColumn<>();
    
    @FXML
    public TableColumn<TableSub, String> threadNumberColumn = new TableColumn<>();
    
    @FXML
    public TableColumn<TableSub, String> uploaddateColumn1 = new TableColumn<>();
    
    @FXML
    public TableColumn<TableSub, Integer> idColumn = new TableColumn<>();
    
    
    public static ObservableList<TableSub> observeList = FXCollections.observableArrayList();
    
    @FXML
    public TextFlow textFlow = new TextFlow();
    
    public List<String> texts$ = new ArrayList<>();
    
    
    public void addText_(String text) {
        texts$.add(text+"\n");
    }
    
    public void f5Text() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    
                        ArrayList<String> texts_ = new ArrayList<>();
                        texts_.addAll(texts$);
                        texts$.clear();
                        for (String text : texts_) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    textFlow.getChildren().addAll(new Text(text));
                                }
                            });
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    
                }
            }
        }).start();
    }
    
    public void start_() {
        new Thread(new Runnable() {
    
            @Override
            public void run() {
                try {
                    while (true) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Set<TableSub> dataclone = new HashSet<>(data);
                        data.clear();
                        for (TableSub datum : dataclone) {
                            observeList.add(datum);
                            System.out.println(datum);
                        }
                        mainTable.refresh();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        
}
    
    public Controller() {
        Controllers.controller = this;
        f5Text();
        addText_("Pixiv Download Manager [版本 1.0-Snapshot]");
        addText_("By Arisa 请在遵守当地法律下使用");
        addText_(" ");
        addText_(System.getProperty("user.dir"));
        start_();
        inits();
        
    }
    
    
    public void start(ActionEvent actionEvent) {
        InvokeDriver_phantomjs.block = false;
        InvokeDriver_google.block = false;
        inits();
        new Thread(new Runnable() {
            @Override
            public void run() {
                CoreUtils.start();
            }
        }).start();
    }
    
    public void author(ActionEvent actionEvent){
//        Platform.runLater();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                AuthorInterface authorInterface = new AuthorInterface();
                authorInterface.start(null);
                Controllers.authorController = new AuthorController();
                
            }
        });
    }
    
    public void proxy(ActionEvent actionEvent){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
              new ProxyInterface().start();
            }
        });
    }
    
    public void options(ActionEvent actionEvent){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
              new OptionsInterface().start();
            }
        });
    }
    
    
    
    
    
    public static List<TableSub> data = new ArrayList<>();
    
    public static void add(TableSub tableSub){
        data.add(tableSub);
    }
    
    
    public void inits() {
        loadTable();
    }
    
    
    
    
    /**
     * 显示表格
     *
     */
    public void loadTable() {
        
        if(observeList == null){
           return ;
        }
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        System.out.println(observeList);
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        
        fileColumn.setCellValueFactory(new PropertyValueFactory<>("file"));
        
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        threadNumberColumn.setCellValueFactory(new PropertyValueFactory<>("threadNumber"));
        uploaddateColumn1.setCellValueFactory(new PropertyValueFactory<>("uploaddate"));
        
    
        authorColumn.setCellFactory((col) -> {
            TableCell<TableSub, String> cell = new TableCell<TableSub, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
    
                    if (!empty) {
                        this.setText(item);
                    }
                }
            };
            return cell;
        });
    
        fileColumn.setCellFactory((col) -> {
            TableCell<TableSub, String> cell = new TableCell<TableSub, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
    
                    if (!empty) {
                        this.setText(item);
                    }
                }
            };
            return cell;
        });
        
    
        dateColumn.setCellFactory((col) -> {
            TableCell<TableSub, String> cell = new TableCell<TableSub, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        this.setText(item);
                    }
                }
            };
            return cell;
        });
    
        threadNumberColumn.setCellFactory((col) -> {
            TableCell<TableSub, String> cell = new TableCell<TableSub, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
    
                    if (!empty) {
                        this.setText(item);
                    }
                }
            };
            return cell;
        });
    
        sizeColumn.setCellFactory((col) -> {
            TableCell<TableSub, String> cell = new TableCell<TableSub, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
    
                    if (!empty) {
                        this.setText(item);
                    }
                }
            };
            return cell;
        });
    
        idColumn.setCellFactory((col) -> {
            TableCell<TableSub, Integer> cell = new TableCell<TableSub, Integer>() {
                @Override
                public void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                
                    if (!empty) {
                        int rowIndex = this.getIndex() + 1;
                        this.setText(String.valueOf(rowIndex));
                    }
                }
            };
            return cell;
        });
    
        uploaddateColumn1.setCellFactory((col) -> {
            TableCell<TableSub, String> cell = new TableCell<TableSub, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
    
                    if (!empty) {
                        this.setText(item);
                    }
                }
            };
            return cell;
        });
    
    
        this.mainTable.setItems(observeList);
        
    }
    
    
}
