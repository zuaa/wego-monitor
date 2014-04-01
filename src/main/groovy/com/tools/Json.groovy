package com.tools
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.heiji.fenling.Ling
class Json {

	static main(args) {
		Gson _gson = new Gson();
		def a=[:]
		a["a"]=111
		a["a2"]=111

		a["a3"]=111
		a["a4"]=111
		a["a5"]=111
		a["a6"]=111
		a["a7"]=111
		Ling _Ling=new Ling();
		_Ling.setName("aaaa");

		String json=_gson.toJson(_Ling);
		println parseData(json);

		println _gson.fromJson(json,Ling)
	}
	static Map<String, String> toMap(Object data){
		Gson _gson = new Gson();
		try{
			return   parseData(_gson.toJson(data));
		}catch(e){
			return null;
		}
	}

	static Map<String, String> parseData(String data){
		GsonBuilder gb = new GsonBuilder();
		Gson g = gb.create();
		Map<String, String> map = g.fromJson(data, new TypeToken<Map<String, String>>() {

				}.getType());
		return map;
	}
}
