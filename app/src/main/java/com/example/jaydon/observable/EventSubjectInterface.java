package com.example.jaydon.observable;

/**
 * 抽象的主题角色
 * Created by Jaydon on 2015/12/20.
 */
public interface EventSubjectInterface {
    /**
     * 注册观察者
     * @param observer
     */
    public void registerObserver(EventObserver observer, String eventType) throws Exception;

    /**
     * 反注册观察者
     * @param observer
     */
    public void removeObserver(EventObserver observer, String eventType) throws Exception;

    /**
     * 通知注册的观察者进行数据或者UI的更新
     */
    public void notifyObserver(String eventType) throws Exception;
}
