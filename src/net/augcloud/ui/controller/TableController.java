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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.augcloud.api.TableSub;
import net.augcloud.ui.Controllers;

import java.util.List;

/**
 * @author ：Arisa
 * @date ：Created in 2020/4/1 18:59
 * @description：
 * @version: $
 */
public class TableController{
    
    

    public static void init(List<TableSub> s){
      loadTable(pushTableData(s));
    }


    public static ObservableList<TableSub> pushTableData(List<TableSub> s) {
        ObservableList<TableSub> a = FXCollections.observableArrayList(s);
        return a;
    }
    

    /**
     * 显示表格
     *
     * @param a
     */
    public static void loadTable(ObservableList<TableSub> a) {
        if(Controllers.controller.idColumn==null){
            Controllers.controller.idColumn = new TableColumn<>();
        }
        Controllers.controller.idColumn.setCellValueFactory(new PropertyValueFactory<>("idColumn"));
        if(Controllers.controller.sizeColumn==null){
            Controllers.controller.sizeColumn = new TableColumn<>();
        }
        System.out.println(Controllers.controller);
        Controllers.controller.sizeColumn.setCellValueFactory(new PropertyValueFactory<>("sizeColumn"));
//        Controllers.controller.statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusColumn"));
        if(Controllers.controller.authorColumn==null){
            Controllers.controller.authorColumn = new TableColumn<>();
        }
        Controllers.controller.authorColumn.setCellValueFactory(new PropertyValueFactory<>("artistColumn"));
        if(Controllers.controller.fileColumn==null){
            Controllers.controller.fileColumn = new TableColumn<>();
        }
        Controllers.controller.fileColumn.setCellValueFactory(new PropertyValueFactory<>("fileColumn"));

//        Controllers.controller.transferrateColumn.setCellValueFactory(new PropertyValueFactory<>("transferrateColumn"));
        if(Controllers.controller.dateColumn==null){
            Controllers.controller.dateColumn = new TableColumn<>();
        }
        Controllers.controller.dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateColumn"));
        if(Controllers.controller.threadNumberColumn==null){
            Controllers.controller.threadNumberColumn = new TableColumn<>();
        }
        Controllers.controller.threadNumberColumn.setCellValueFactory(new PropertyValueFactory<>("threadNumberColumn"));
        if(Controllers.controller.uploaddateColumn1==null){
            Controllers.controller.uploaddateColumn1 = new TableColumn<>();
        }
        Controllers.controller.uploaddateColumn1.setCellValueFactory(new PropertyValueFactory<>("uploaddateColumn"));
        
    
        Controllers.controller.authorColumn.setCellFactory((col) -> {
            TableCell<TableSub, String> cell = new TableCell<TableSub, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
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
    
        Controllers.controller.  fileColumn.setCellFactory((col) -> {
            TableCell<TableSub, String> cell = new TableCell<TableSub, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
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
        
    
        Controllers.controller. dateColumn.setCellFactory((col) -> {
            TableCell<TableSub, String> cell = new TableCell<TableSub, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
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
    
        Controllers.controller. threadNumberColumn.setCellFactory((col) -> {
            TableCell<TableSub, String> cell = new TableCell<TableSub, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
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
    
        Controllers.controller.sizeColumn.setCellFactory((col) -> {
            TableCell<TableSub, String> cell = new TableCell<TableSub, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
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
    
        Controllers.controller.idColumn.setCellFactory((col) -> {
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
    
        Controllers.controller.uploaddateColumn1.setCellFactory((col) -> {
            TableCell<TableSub, String> cell = new TableCell<TableSub, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
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

//        delCol.setCellFactory((col) -> {
//            TableCell<TableSub, String> cell = new TableCell<TableSub, String>() {
//
//                @Override
//                public void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    this.setText(null);
//                    this.setGraphic(null);
//
//                    if (!empty) {
//                        ImageView delICON = new ImageView(getClass().getResource("delete.png").toString());
//                        Button delBtn = new Button("删除", delICON);
//                        this.setGraphic(delBtn);
//                        delBtn.setOnMouseClicked((me) -> {
//                            TableSub clickedStu = this.getTableView().getItems().get(this.getIndex());
//                            System.out.println("删除 " + clickedStu.getId()+" 的记录");
//                        });
//                    }
//                }
//
//            };
//            return cell;
//        });
        Controllers.controller.mainTable = new TableView();
        Controllers.controller.mainTable.setItems(a);
    }
    
    
}