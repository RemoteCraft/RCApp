package jzl.remotecraft.sensor.model;

import java.util.ArrayList;
import java.util.HashMap;

import jzl.remotecraft.sensor.model.source.SourceBase;
import jzl.remotecraft.sensor.model.source.SourceDB;
import jzl.remotecraft.sensor.model.source.SourceNetwork;
import jzl.remotecraft.sensor.model.source.SourceStorage;
import jzl.remotecraft.sensor.util.prototype.observer.Observer;

/**
 * Created by jzl on 2016/6/19.
 */
public class SourceService {
    public final static int DB = 0;
    public final static int STORAGE = 1;
    public final static  int NETWORK = 2;
    private SourceBase[] sources = null;
    private static SourceService instance;
    public static SourceService getInstance(){
        if(instance == null) {
            instance = new SourceService();
        }
        return instance;
    }

    private class Task implements Runnable {
        private SourceBase sb = null;
        private int ot = 0;
        private HashMap<String,String> param;
        private ArrayList<String> data = null;
        public Task(SourceBase sb,int ot,HashMap<String,String> param,ArrayList<String> data) {
            this.sb = sb;
            this.ot = ot;
            this.param = param;
            this.data = data;
        }
        @Override
        public void run() {
            switch (this.ot) {
                case SourceBase.OPERATION_READ:
                    this.sb.read(this.param);
                case SourceBase.OPERATION_WRITE:
                    this.sb.write(true,this.data,this.param);
                case SourceBase.OPERATION_UPDATE:
                    this.sb.update(true,this.data,this.param);;
                case SourceBase.OPERATION_DELETE:
                    break;
                default:return;
            }
        }
    }

    public Task getTask(SourceBase sb,int operation_type,HashMap<String,String> param,ArrayList<String> data) {
        return new Task(sb,operation_type,param,data);
    }

    public static void execute(int source_id,int operation_type,HashMap<String,String> param,ArrayList<String> data) {
        SourceBase sb = getInstance().getSource()[source_id];
        getInstance().getTask(sb,operation_type,param,data).run();
    }


    public static void addObserver(String ok,Observer<ArrayList<String>> observer,int source_id) {
        getInstance().add(ok,observer,source_id);
    }

    public void add(String ok,Observer<ArrayList<String>> observer,int source_id) {
        this.sources[source_id].add(ok,observer);
    }


    public SourceBase[] getSource() {
        return this.sources;
    }
    private SourceService(){
        this.sources = new SourceBase[3];
        this.sources[DB] = new SourceDB();
        this.sources[STORAGE] = new SourceStorage();
        this.sources[NETWORK] = new SourceNetwork();
    }
}
