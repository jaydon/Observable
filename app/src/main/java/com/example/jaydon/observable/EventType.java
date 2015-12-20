package com.example.jaydon.observable;

import java.util.HashSet;
import java.util.Set;

/**
 * 所有的业务类型，在这里写，方便管理
 * Created by Jaydon on 2015/12/20.
 */
public class EventType {

    private static volatile EventType mEventType;
    private final static Set<String> eventsTypes = new HashSet<String>();

    public final static String UPDATE_MAIN = "com.updateMain";
    public final static String UPDATE_TEXT = "com.updateText";
    private EventType(){
        eventsTypes.add(UPDATE_MAIN);
        eventsTypes.add(UPDATE_TEXT);
    }

    public static EventType getInstance(){
        if(null == mEventType){
            synchronized (EventType.class) {
                if(null == mEventType) {
                    mEventType =new EventType();
                }
            }
        }
        return mEventType;
    }

    public boolean contains(String eventType){
        return eventsTypes.contains(eventType);
    }

}

