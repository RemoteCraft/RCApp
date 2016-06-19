package jzl.remotecraft.sensor.util.prototype.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jzl on 2016/6/19.
 */
public class Subject<T> {
    private HashMap<String,Observer> observers = null;
    public Subject(){
        this.observers = new HashMap<String,Observer>();
    }
    public void add(String key,Observer observer) {
        if (this.observers == null) {
            this.observers = new HashMap<String,Observer>();
        }
        this.observers.put(key,observer);
    }
    public void remove(String key){
        if (this.observers != null && this.observers.containsKey(key)) {
            this.observers.remove(key);
        }
    }
    public void notify(String key,T data) {
        if (this.observers != null) {
            if (key == null) {
                Iterator<Map.Entry<String,Observer>> iterator = this.observers.entrySet().iterator();
                while(iterator.hasNext()) {
                    Observer observer = iterator.next().getValue();
                    observer.update(data);
                }
            } else {
                if (this.observers.containsKey(key)) {
                    this.observers.get(key).update(data);
                }
            }
        }
    }
    @Override
    public String toString(){
        return "";
    }
}
