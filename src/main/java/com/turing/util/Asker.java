package com.turing.util;

import com.alibaba.fastjson.JSONObject;

public class Asker {
	public static final String SECRET = "3904741e246bbf8d";
	public static final String APIKEY = "b1cb63a270ee4d21b4483478275753da";
	public static final String APIURL = "http://www.tuling123.com/openapi/api";
	public String ask(String input, String apiurl, String secret, String apiKey) {
		String payLoad = "{\"key\":\"" + apiKey + "\",\"info\":\"" + input + "\"}";		// 载荷业务数据
		String timestamp = String.valueOf(System.currentTimeMillis());		// 时间戳

		// 基于MD5生成AES密钥
		String keyParam = secret + timestamp + apiKey;
		String key = MD5.MD5(keyParam);

		// 用AES对载荷数据加密
		AES mc = new AES(key);
		String data = mc.encrypt(payLoad);

		// 封装请求参数
		JSONObject json = new JSONObject();
		json.put("key", apiKey);
		json.put("timestamp", timestamp);
		json.put("data", data);
		
		// 请求图灵api
		String result = HttpPostSender.SendPost(json.toString(),apiurl);
		return result;
	}

	public static void main(String[] args) {
		Asker asker = new Asker();
		System.out.println(asker.ask("98/19", APIURL, SECRET, APIKEY));
	}
}
