package jzl.remotecraft.sensor.model.source;

import java.util.ArrayList;
import java.util.HashMap;

import jzl.remotecraft.sensor.model.DataBaseHelper;

/**
 * Created by jzl on 2016/6/19.
 */
public class SourceDB extends SourceBase{


    @Override
    public boolean write(boolean batch,ArrayList<String> data,HashMap<String,String> param) {
        try {
            DataBaseHelper.insertOrUpdate(batch,data,param.get("column"),param.get("tlb"),null,DataBaseHelper.INSERT);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public void read(HashMap<String,String> param) {
        try {
            ArrayList<String> data = null;
            data = DataBaseHelper.query(param.get("tlb"), param.get("column"), param.get("where"));
            notify(null, data);
        } catch(Exception e) {

        }
    }

    @Override
    public boolean update(boolean batch,ArrayList<String> data,HashMap<String,String> param) {
        try {
            DataBaseHelper.insertOrUpdate(batch,data,param.get("column"),param.get("tlb"),param.get("where"),DataBaseHelper.UPDATE);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
