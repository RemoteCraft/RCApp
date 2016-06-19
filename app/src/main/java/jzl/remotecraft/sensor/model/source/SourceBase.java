package jzl.remotecraft.sensor.model.source;

import java.util.ArrayList;
import java.util.HashMap;

import jzl.remotecraft.sensor.util.prototype.observer.Subject;

/**
 * Created by jzl on 2016/6/19.
 */
public class SourceBase extends Subject<ArrayList<String>> {
    public final static int OPERATION_READ = 0;
    public final static int OPERATION_WRITE = 1;
    public final static int OPERATION_UPDATE = 2;
    public final static int OPERATION_DELETE = 3;

    public SourceBase() {

    }
    public boolean update(boolean batch,ArrayList<String> data,HashMap<String,String> param){
        return true;
    }
    public void read(HashMap<String,String> param){

    }
    public boolean write(boolean batch,ArrayList<String> data,HashMap<String,String> param){
        return true;
    }
    public boolean delete() {
        return true;
    }
}
