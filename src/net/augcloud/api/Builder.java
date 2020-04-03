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

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @author ：Arisa
 * @date ：Created in 2020/4/1 19:59
 * @description：
 * @version: $
 */
public interface Builder {
    
    static Builder getTableSubFactory() {
        return new TableSub();
    }
    
    Builder setId(int id);
    
    Builder setFile(String file);
    
    Builder setSize(String size);
    
    Builder setStatus(String status);
    
    Builder setTransfer_rate(String transfer_rate);
    
    Builder setDate(String date);
    
    Builder setThreadNumber(String threadNumber);
    
    Builder setUploaddate(String uploaddate);
    
    Builder setAuthor(String author);
    
    Builder builder();
    
}
