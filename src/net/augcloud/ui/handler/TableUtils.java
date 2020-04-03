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

package net.augcloud.ui.handler;

import net.augcloud.api.TableSub;
import net.augcloud.ui.Controllers;
import net.augcloud.ui.controller.Controller;
import net.augcloud.ui.controller.TableController;
//import net.augcloud.ui.controller.TableController;

import javax.xml.bind.helpers.AbstractUnmarshallerImpl;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Arisa
 * @date ：Created in 2020/4/1 20:58
 * @description：
 * @version: $
 */
public class TableUtils {
    
    
    

    public static void add(TableSub builder){
        Controller.data.add(builder);
    }
    
    public static void test(){
        TableSub builder = (TableSub) TableSub.getTableSubFactory()
                .setId(1)
                .setDate("1")
                .setFile("1")
                .setStatus("1")
                .setThreadNumber("2")
                .setTransfer_rate("1")
                .setUploaddate("1")
                .builder();
        List<TableSub> list = new ArrayList<>();
        list.add(builder);
        TableController.init(list);
    }
}
