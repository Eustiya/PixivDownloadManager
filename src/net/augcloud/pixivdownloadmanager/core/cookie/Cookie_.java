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

import org.openqa.selenium.Cookie;

import java.util.Objects;

/**
 * @author ：Arisa
 * @date ：Created in 2020/3/31 15:59
 * @description：
 * @version: $
 */
public class Cookie_ {
    
    public String getName() {
        return name;
    }
    
    public Cookie getCookie(){
        return new Cookie(this.name,this.data);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cookie_{");
        sb.append("name='").append(name).append('\'');
        sb.append(", data='").append(data).append('\'');
        sb.append('}');
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cookie_)) return false;
        Cookie_ cookie_ = (Cookie_) o;
        return Objects.equals(getName(), cookie_.getName()) &&
                Objects.equals(getData(), cookie_.getData());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getData());
    }
    
    public Cookie_ setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getData() {
        return data;
    }
    
    public Cookie_ setData(String data) {
        this.data = data;
        return this;
    }
    
    private String name;
    
    public Cookie_(String name, String data) {
        this.name = name;
        this.data = data;
    }
    
    private String data;

}
