package com.sicnu.baby.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;
import org.junit.Test;

import com.sicnu.baby.dto.ConAnalysis;


public class TestJson {
	@Test  
    public void test() {  
        System.out.println(prepareJSONObject());  
        System.out.println(prepareJSONObject2());  
    }  
	@Test
    public void test2(){
		String  jsonTest ="{\"comAnalysis\":{\"A\":5,\"B\":2,\"C\":3,\"D\":2},\"conAnalysis\":[{\"id\":1,\"option\":\"A\",\"number\":1},{\"id\":1,\"option\":\"B\",\"number\":1},{\"id\":2,\"option\":\"B\",\"number\":1},{\"id\":2,\"option\":\"C\",\"number\":1},{\"id\":3,\"option\":\"A\",\"number\":1},{\"id\":3,\"option\":\"C\",\"number\":1},{\"id\":4,\"option\":\"A\",\"number\":1},{\"id\":4,\"option\":\"D\",\"number\":1},{\"id\":5,\"option\":\"A\",\"number\":1},{\"id\":5,\"option\":\"D\",\"number\":1},{\"id\":7,\"option\":\"A\",\"number\":1},{\"id\":7,\"option\":\"C\",\"number\":1}]}";
		System.out.println(jsonTest.toString());
		System.out.println(getJSONContent(jsonTest));
	}
    private static String prepareJSONObject(){  
        JSONObject studentJSONObject = new JSONObject();  
        try {  
        	List<String> list = new ArrayList<>();
        	for(int i=0;i<4;i++){
        		list.add(i+2+"");
        	}
            studentJSONObject.put("name", "Jason");  
            studentJSONObject.put("id", 20130001);  
            studentJSONObject.put("phone", "13579246810");
            studentJSONObject.put("list", list);
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
          
        return studentJSONObject.toString();  
    }  
      
    private static String prepareJSONObject2(){  
        JSONStringer jsonStringer = new JSONStringer();  
        try {  
            jsonStringer.object();  
            jsonStringer.key("name");  
            jsonStringer.value("Jason");  
            jsonStringer.key("id");  
            jsonStringer.value(20130001);  
            jsonStringer.key("phone");  
            jsonStringer.value("13579246810");  
            jsonStringer.endObject();  
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
        return jsonStringer.toString();  
    }  
	
    private static String getJSONContent(String jsonText){  
        JSONTokener jsonTokener = new JSONTokener(jsonText);   
        JSONObject studentJSONObject;  
        String name = null;  
        int id = 0;  
        String phone = null;  
        JSONArray list = null ;
        JSONObject map = null;
        try {  
            studentJSONObject = (JSONObject) jsonTokener.nextValue();   
            list = studentJSONObject.optJSONArray("conAnalysis");  
            map = studentJSONObject.getJSONObject("comAnalysis");
            
        } catch (JSONException e) {  
            e.printStackTrace();  
        }  
        for(int i=0;i<list.length();i++){
        	try {
        		JSONObject jObject = list.getJSONObject(i);
				ConAnalysis conAnalysis = new ConAnalysis(jObject.getInt("id"), jObject.getString("option"), jObject.getInt("number"));
			    System.out.println(conAnalysis);
        	} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        Iterator<String> iterator = map.keys();
        while(iterator.hasNext()){
        	String key = iterator.next();
        	System.out.println(key);
        	try {
				System.out.println(map.getString(key));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return name + "  " + id + "   " + phone;  
    }
    
    @Test
    public void printDate(){
    	System.out.println((new Date()).toString());
    }
}
