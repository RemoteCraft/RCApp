package jzl.remotecraft.sensor.model.source;

import jzl.remotecraft.sensor.util.prototype.observer.Subject;

/**
 * Created by jzl on 2016/6/19.
 */
public class SourceBase extends Subject<String[]> {
    public SourceBase() {

    }
    protected boolean update(){
        return true;
    }
    protected String[] read(){
        return null;
    }
}
