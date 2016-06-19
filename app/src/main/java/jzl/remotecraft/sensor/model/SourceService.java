package jzl.remotecraft.sensor.model;

/**
 * Created by jzl on 2016/6/19.
 */
public class SourceService {
    private final int FROM_DB = 0x001;
    private final int FROM_BUFFER = 0x002;
    private final int FROM_FILE = 0x002;
    private final int FROM_NETWORK = 0x003;

    private static SourceService instance;
    public static SourceService getInstance(){
        if(instance == null) {
            instance = new SourceService();
        }
        return instance;
    }


    private SourceService(){

    }
}
