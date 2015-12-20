package com.example.jaydon.observable;

/**
 * 观察者接口
 * Created by Jaydon on 2015/12/20.
 */
public interface EventObserverInterface {
    /**
     * 根据事件进行数据或者UI的更新
     * @param eventType
     */
    public void dispatchChange(String eventType);
}
