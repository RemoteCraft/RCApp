package jzl.remotecraft.sensor.util.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jzl.remotecraft.sensor.util.network.AsyncWebService.Connection;

public class ConnectionBuffer{
	private static ConnectionBuffer cb = null;
	private static List<Connection> cList = null;
	private ConnectionBuffer(){
		
	}
	public static ConnectionBuffer getInstance(){
		if(cb == null)
			cb = new ConnectionBuffer();
		if(cList == null)
			cList = Collections.synchronizedList(new ArrayList<Connection>());
		return cb;
	}
	
	public void add(Connection c){
		cList.add(c);
	}
	public void remove(Connection c){
		cList.remove(c);
	}
	public boolean busy(Connection c){
		return c.getStatus();
	}
}
