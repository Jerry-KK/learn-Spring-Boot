package cn.lethekk.chapter4.proxy;

import cn.lethekk.chapter4.intercept.Interceptor;
import cn.lethekk.chapter4.invoke.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBean implements InvocationHandler {

    private Object target = null;

    private Interceptor interceptor = null;

    /**
     * 绑定代理对象
     * @param target    被代理对象
     * @param interceptor   拦截器
     * @return  代理对象
     */
    public static Object getProxyBean(Object target, Interceptor interceptor){
        ProxyBean proxyBean = new ProxyBean();
        //保存被代理对象
        proxyBean.target = target;
        //保存拦截器
        proxyBean.interceptor = interceptor;
        //生成代理对象
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxyBean);
        //返回代理对象
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //异常标识
        boolean exceptionFlag = false;
        Invocation invocation = new Invocation(target,method,args);
        this.interceptor.before();
        Object retObj = null;
        try{
            if(this.interceptor.useAround()){
                retObj = this.interceptor.around(invocation);
            }else {
                retObj = method.invoke(target,args);
            }
        }catch (Exception e){
            exceptionFlag = true;
        }
        this.interceptor.after();
        if(exceptionFlag){
            this.interceptor.afterThrowing();
        }else {
            this.interceptor.afterReturning();
        }
        return null;
    }
}
