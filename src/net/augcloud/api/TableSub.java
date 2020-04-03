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

package net.augcloud.api;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

/**
 * @author ：Arisa
 * @date ：Created in 2020/4/1 19:23
 * @description：
 * @version: $
 */
public class TableSub implements Builder{
    
    private int id;
    private String file;
    private String size;
    private String author;
    private String status;
    private String transfer_rate;
    private String date;
    private String threadNumber;
    private String uploaddate;
    
    public static Builder getTableSubFactory(){
       return new TableSub();
    }
    
    public TableSub(){
    
    }
    
//    // 定义为 final 好像是一种规范做法
//    private final StringProperty status = new SimpleStringProperty();
//
//    // * 特别说明： xxxProperty 方法名，是 fx 的规范，只要属性名加上 Peoperty() 作为方法名，fx 就能自动监听该属性的变化！
//    public StringProperty statusProperty() {
//        return status;
//    }
//
//    public StringProperty idProperty() {
//        return status;
//    }
//
//    public StringProperty fileProperty() {
//        return status;
//    }
//
//    public StringProperty sizeProperty() {
//        return status;
//    }
//
//    public StringProperty authorProperty() {
//        return status;
//    }
//
//    public StringProperty status_Property() {
//        return status;
//    }
//
//    public StringProperty transfer_rateProperty() {
//        return status;
//    }
//
//    public StringProperty dateProperty() {
//        return status;
//    }
//
//    public StringProperty threadNumberProperty() {
//        return status;
//    }
//
//    public StringProperty upload_dateProperty() {
//        return status;
//    }
//
    
    
    
    public TableSub(int id, String author,String file, String size, String status, String transfer_rate
            , String date,
                    String threadNumber, String upload_date) {
        this.id = id;
        this.author = author;
        this.file = file;
        this.size = size;
        this.status = status;
        this.transfer_rate = transfer_rate;
        this.date = date;
        this.threadNumber = threadNumber;
        this.uploaddate = upload_date;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableSub)) return false;
        TableSub tableSub = (TableSub) o;
        return getId() == tableSub.getId() &&
                Objects.equals(getFile(), tableSub.getFile()) &&
                Objects.equals(getSize(), tableSub.getSize()) &&
                Objects.equals(getStatus(), tableSub.getStatus()) &&
                Objects.equals(getTransfer_rate(), tableSub.getTransfer_rate()) &&
                Objects.equals(getDate(), tableSub.getDate()) &&
                Objects.equals(getThreadNumber(), tableSub.getThreadNumber()) &&
                Objects.equals(getUploaddate(), tableSub.getUploaddate());
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TableSub{");
        sb.append("id=").append(id);
        sb.append(", file='").append(file).append('\'');
        sb.append(", size='").append(size).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", transfer_rate='").append(transfer_rate).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", threadNumber='").append(threadNumber).append('\'');
        sb.append(", upload_date='").append(uploaddate).append('\'');
        sb.append('}');
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFile(), getSize(), getStatus(), getTransfer_rate(), getDate(), getThreadNumber(), getUploaddate());
    }
    
    public int getId() {
        return id;
    }
    
    public Builder setId(int id) {
        this.id = id;
//        this.status.set(String.valueOf(id));
        return this;
    }
    
    public String getFile() {
        return file;
    }
    
    public Builder setFile(String file) {
        this.file = file;
//        this.status.set(file);
        return this;
    }
    
    
    public String getSize() {
        return size;
    }
    
    public Builder setSize(String size) {
        this.size = size;
//        this.status.set(size);
        return this;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public Builder setAuthor(String author) {
        this.author = author;
//        this.status.set(author);
        return this;
    }
    
    public String getStatus() {
        return this.status;
    }

    public Builder setStatus(String status) {
        this.status = status;

        return this;
    }
    
    public String getTransfer_rate() {
        return transfer_rate;
    }
    
    public Builder setTransfer_rate(String transfer_rate) {
        this.transfer_rate = transfer_rate;
//        this.status.set(transfer_rate);
        return this;
    }
    
    public String getDate() {
        return date;
    }
    
    public Builder setDate(String date) {
        this.date = date;
//        this.status.set(date);
        return this;
    }
    
    public String getThreadNumber() {
        return threadNumber;
    }
    
    public Builder setThreadNumber(String threadNumber) {
        this.threadNumber = threadNumber;
//        this.status.set(threadNumber);
        return this;
    }
    
    
    public String getUploaddate() {
        return uploaddate;
    }
    
    public Builder setUploaddate(String upload_date) {
        this.uploaddate = upload_date;
//        this.status.set(upload_date);
        return this;
    }
    
    
    
    @Override
    public Builder builder() {
        return this;
    }
}
