package com.aliyun.api.gateway.demo;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.aliyun.api.gateway.demo.util.HttpUtils;

public class Laugh {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String host = "http://jisuxhdq.market.alicloudapi.com";
		    String path = "/xiaohua/all";
		    String method = "GET";
		    Map<String, String> headers = new HashMap<String, String>();
		    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		    headers.put("Authorization", "APPCODE b16beb839f73425cb97d53ab6d69aca0");
		    Map<String, String> querys = new HashMap<String, String>();
		    querys.put("pagenum", "1");   //页码
		    querys.put("pagesize", "3");  //每页条数 最大20
		    querys.put("sort", "addtime");
		    
		    try {
		   	
		    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
		    	System.out.println(response.toString());
		    	
		    	String dataString=EntityUtils.toString(response.getEntity());
		    	System.out.println(dataString);                                 //1：抽离第一层数据
		    	
		    	JSONObject jsonObject1=new JSONObject(dataString);
		    	String result=jsonObject1.getString("result");
		    	System.out.println(result);                                       //2：抽离第二层数据
		    	
		    	JSONObject jsonObject2=new JSONObject(result);
		    	String list=jsonObject2.getString("list");
		    	System.out.println(list);										//3：抽离第三层数据
		    	
		    	JSONArray jsonArray=new JSONArray(list);
		    	for(int i=0;i<3;i++){
		    		JSONObject object=jsonArray.getJSONObject(i);
		    		String contentString=object.getString("content");
		    		System.out.println("第"+(i+1)+"条笑话:"+contentString);
		    	}    
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}

}
