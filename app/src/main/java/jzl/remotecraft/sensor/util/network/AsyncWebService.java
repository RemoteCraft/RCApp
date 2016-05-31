package jzl.remotecraft.sensor.util.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.cache.CachingHttpClientBuilder;
import org.apache.http.client.methods.HttpGet;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;

public class AsyncWebService {
	private final int GET = 0x00;
	private final int POST = 0x01;
	private Handler handler = null;
    private Context context = null;
	public AsyncWebService(Context c,Handler h){
		context = c;
		handler = h;
	}
    public class Connection implements Runnable{
    	public HttpGet get;
		public HttpClient client;
		public HttpResponse response;
		public HttpEntity entity;
		private String reqType = null;
		private String reqKey = null;
		protected boolean status = false;
		
		public Connection(String rk){
			reqKey = rk;
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			client = httpClientBuilder.build();
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
		
		public boolean getStatus(){
			return status;
		}
		protected void sendMessage(String rst){
			Message msg = handler.obtainMessage();
			Bundle data = new Bundle();
			data.putStringArray(reqType, new String[]{reqKey,rst});
			msg.setData(data);
			handler.sendMessage(msg);
		}
		
		private class TimeCount extends CountDownTimer {
			
			private boolean running = false;
			public TimeCount(long millisInFuture, long countDownInterval) {
				super(millisInFuture, countDownInterval);
				running = true;
			}
			
			@Override
			public void onFinish() {// ��ʱ���ʱ����
				running = false;
			}

			@Override
			public void onTick(long millisUntilFinished) {// ���ڼ�ʱ����
				running = true;
			}
			
			public boolean isRunning(){
				return running;
			}
			
		}
    }
    
    public class ConnectionGet extends Connection{
		public ConnectionGet(String host,HashMap<String,String> getParam,String rk){
			super(rk);
			String param = "";
			Iterator<Map.Entry<String,String>> it = getParam.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String, String> entry = it.next();
				param += entry.getKey()+"="+entry.getValue();
			}
			get = new HttpGet(param);
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				status = false;
				response = client.execute(get);
				entity = response.getEntity();
				InputStream is = entity.getContent();
				BufferedReader br=new BufferedReader(new InputStreamReader(is));
				String line=null;  
				String rst="";  
				while((line=br.readLine())!=null){
					rst += line;
					rst += "\n";
				}
				status = true;
				sendMessage(rst);
				ConnectionBuffer.getInstance().remove(this);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    
    public class ConnectionPost extends Connection{

		public ConnectionPost(String host, HashMap<String, String> params, String reqKey) {
			super(reqKey);
			// TODO Auto-generated constructor stub
		}
    	
    }
    
    public void newRequest(int type,String host,HashMap<String,String> params,String reqKey){
    	Connection c = null;
    	switch(type){
    		case GET:
    			c = new ConnectionGet(host, params, reqKey);
    			ConnectionBuffer.getInstance().add(c);
    			break;
    		case POST:
    			c = new ConnectionPost(host, params, reqKey);
    			ConnectionBuffer.getInstance().add(c);
    			break;
    		default:break;
    	}
    	try{
    		new Thread(c).start();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
