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

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.io.PrintStream;
import java.lang.reflect.Method;

/**
 * @author ：Arisa
 * @date ：Created in 2020/4/2 17:39
 * @description：
 * @version: $
 */
public class PrintStreamCglib implements MethodInterceptor {
    
    
    //TODO 什么时候学会再用
    //TODO 改线程池
    /**
     * 被代理的out对象
     */
    private Object target = System.out;
    
    public  PrintStreamCglib(Object target) {
        this.target = target;
    }
    
    public PrintStreamCglib(){
    
    }
    
    
    
    
    /**
     *  实现回调方法
     * @param obj 代理的对象
     * @param method 被代理对象的方法
     * @param args  参数集合
     * @param proxy 生成的代理类的方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 开始执行时间
        Long startTime = System.currentTimeMillis();
        // //调用业务类（父类中）的方法
        Object result = proxy.invokeSuper(obj, args);
        // 执行结束
        Long endTime = System.currentTimeMillis();
        System.out.println(target.getClass().getName()+"执行耗时"+(endTime-startTime)+"ms");
        return result;
    }

}
