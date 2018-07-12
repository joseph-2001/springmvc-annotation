package com.atguigu.controller;

import java.util.UUID;
import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.atguigu.service.DeferredResultQueue;

@Controller
public class AsyncController {

    @RequestMapping("/createorder")
    @ResponseBody
    public DeferredResult<Object> createOrder() {
        //创建一个DeferredResult<Object>
        DeferredResult<Object> deferredResult = new DeferredResult<Object>((long) 3000, "CreateOrderTimeOut...");

        //将DeferredResult<Object>保存到队列
        DeferredResultQueue.save(deferredResult);

        return deferredResult;
    }

    @RequestMapping("/create")
    @ResponseBody
    public String create() {
        //创建订单
        String order = UUID.randomUUID().toString();
        DeferredResult<Object> deferredResult = DeferredResultQueue.get();
        
        deferredResult.setResult(order);
        return "success===>" + order;
    }

    @RequestMapping("/async01")
    @ResponseBody
    public Callable<String> async1() {
        System.out.println("主线程开始..." + Thread.currentThread() + "-->" + System.currentTimeMillis());
        Callable<String> callable = new Callable<String>() {
            public String call() throws Exception {
                System.out.println("副线程开始..." + Thread.currentThread() + "-->" + System.currentTimeMillis());
                Thread.sleep(3000);
                System.out.println("副线程结束..." + Thread.currentThread() + "-->" + System.currentTimeMillis());
                return "Callable<String> async1()";
            }
        };
        System.out.println("主线程结束..." + Thread.currentThread() + "-->" + System.currentTimeMillis());
        return callable;
    }
}
