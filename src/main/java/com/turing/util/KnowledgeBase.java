package com.turing.util;

import java.util.HashMap;
import java.util.Map;

public class KnowledgeBase {
	public String add(Map<String,String> input, String apiurl, String secret, String apiKey){
		String content = "{\"apikey\":\"<APIKEY>\",\"data\":<DATA>,\"token\":\"<MD5>\"}";
		content = content.replace("<APIKEY>", apiKey);
		String data = "";
		String pattern;
		for (String key : input.keySet()){
			pattern = "{\"question\":\"<QUESTION>\",\"answer\":\"<ANSWER>\"}";
			pattern = pattern.replace("<QUESTION>", key);
			pattern = pattern.replace("<ANSWER>", input.get(key));
			data += pattern + ",";
		}
		data = "[" + data + "]";
		content = content.replace("<DATA>", data);
		String md5Param = data + secret;
		String md5 = MD5.MD5(md5Param);
		content = content.replace("<MD5>", md5);
		System.out.println(content);
		String result = HttpPostSender.SendPost(content,apiurl);
		return result;
	}
	public static void main(String[] args) {
		KnowledgeBase kb = new KnowledgeBase();
		Map<String,String> map = new HashMap<String, String>();
		map.put("你叫什么", "我叫阿秀");
		System.out.println(kb.add(map, "http://www.tuling123.com/v1/setting/importfaq", "3904741e246bbf8d", "b1cb63a270ee4d21b4483478275753da"));
	}

}
