package com.hneb.fwk.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {

	public static JSONObject map2Json(Map<String, Object> map) {
		JSONObject json = new JSONObject();
		for (String key : map.keySet()) {
			json.put(key, map.get(key));
		}
		return json;
	}

	
	
	public static JSONArray listObject2JsonArray(List list){
		JSONArray result = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			JSONObject json = JsonUtils.object2Json(list.get(i));
			result.add(json);
		}
		return result;
	}
	
	public static JSONObject[] listObject2Json(List list) {
		JSONObject[] result = new JSONObject[list.size()];
		for (int i = 0; i < list.size(); i++) {
			JSONObject json = JsonUtils.object2Json(list.get(i));
			result[i] = json;
		}
		return result;
	}

	public static Map<Object, Object> json2Map(JSONObject json) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (Object key : json.keySet()) {
			map.put(key, json.get(key));
		}
		return map;
	}

	public static JSONObject object2Json(Object obj) {
		JSONObject jsonObj = JSONObject.fromObject(obj, getJsonConfig());
		return jsonObj;
	}

	public static JsonConfig getJsonConfig(){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class,
				new DateJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class,
				new DateJsonValueProcessor());
		jsonConfig.registerDefaultValueProcessor(Integer.class,
				new DefaultValueProcessor() {
					public Object getDefaultValue(Class type) {
						return null;
					}
				});
		jsonConfig.registerDefaultValueProcessor(Double.class,
				new DefaultValueProcessor() {
					public Object getDefaultValue(Class type) {
						return null;
					}
				});
		return jsonConfig;
	}
}
