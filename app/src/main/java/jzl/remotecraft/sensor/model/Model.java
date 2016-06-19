package jzl.remotecraft.sensor.model;

/**
 * Created by jzl on 2016/6/17.
 */
public class Model {
    public final static String TLB_RUNNING_STAT = "tlb_running_stat";
    public final static String TLB_PUSHED_UP_STAT = "tlb_pushed_up_stat";
    public final static String TLB_PLAN = "tlb_plan";

    public final static String GET_PERSONAL_HISTORY(String tlb,String start,String end) {
        return "";
    }


    public final static String CREATE_RUNNING_STAT_TABLE() {
        return "create table if not exists " + TLB_RUNNING_STAT
            + " (userid varchar(32) primary key,"
            + "usertel varchar(32),"
            + "age varchar(32),"
            + "country varchar(32),"
            + "weight integer,"
            + "height integer,"
            + "gender enum('M','F'),"
            + "create_date TIMESTAMP default (datetime('now', 'localtime'))"
            + ")";
    }
    public final static String CREATE_PUSHUP_STAT_TABLE() {
        return "create table if not exists " + TLB_PUSHED_UP_STAT
            + " (userid varchar(32) primary key,"
            + "usertel varchar(32),"
            + "age varchar(32),"
            + "country varchar(32),"
            + "weight integer,"
            + "height integer,"
            + "gender enum('M','F'),"
            + "create_date TIMESTAMP default (datetime('now', 'localtime'))"
            + ")";
    }

    public final static String CREATE_PLAN_TABLE() {
        return "create table if not exists " + TLB_PLAN
            + " (userid varchar(32) primary key,"
            + "usertel varchar(32),"
            + "age varchar(32),"
            + "country varchar(32),"
            + "weight integer,"
            + "height integer,"
            + "gender enum('M','F'),"
            + "create_date TIMESTAMP default (datetime('now', 'localtime'))"
            + ")";
    }
}
