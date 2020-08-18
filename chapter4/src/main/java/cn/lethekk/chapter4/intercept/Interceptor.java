package cn.lethekk.chapter4.intercept;

import cn.lethekk.chapter4.invoke.Invocation;

import java.lang.reflect.InvocationTargetException;

public interface Interceptor {

    //事前方法
    public boolean before();

    //事后方法
    public void after();

    /**
     * 取代原有事件方法
     * @param invocation    --回调参数，可以通过它的proceed方法，回调原有方法
     * @return  原有事件返回对象
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object around(Invocation invocation) throws InvocationTargetException,IllegalAccessException;

    //事件返回方法。事件没有发生异常时执行
    public void afterReturning();

    //事件异常方法，当事件发生异常后执行
    public void afterThrowing();

    //是否使用around方法取代原有的方法
    boolean useAround();
}
