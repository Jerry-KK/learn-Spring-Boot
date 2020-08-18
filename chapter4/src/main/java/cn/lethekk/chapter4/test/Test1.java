package cn.lethekk.chapter4.test;

import cn.lethekk.chapter4.intercept.MyInterceptor;
import cn.lethekk.chapter4.proxy.ProxyBean;
import cn.lethekk.chapter4.service.HelloService;
import cn.lethekk.chapter4.service.impl.HelloServiceImpl;

public class Test1 {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloService proxy = (HelloService)ProxyBean.getProxyBean(helloService, new MyInterceptor());
        proxy.sayHello("kjn");
        System.out.println("-------------当输入null时------------");
        proxy.sayHello(null);
    }
}
