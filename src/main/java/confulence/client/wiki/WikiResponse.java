package confulence.client.wiki;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import confulence.client.wiki.model.WikiPage;

public class WikiResponse {
		/**
		 * 根据creat接口返回json String 封装 对象
		 * @param response
		 * @return
		 */
		public static WikiPage pasreCreatResponse(String response){
			
			JSONObject json = JSON.parseObject(response);
			WikiPage page = new WikiPage();
			page.setId(json.getLongValue("id"));
			page.setTitle(json.getString("title"));
			page.setSpacekey(json.getJSONObject("space").getString("key"));
			return page;
		}
		
		/**
		 * 根据Update接口返回json String 封装 对象
		 * @param response
		 * @return
		 */
		public static WikiPage pasreUpdateResponse(String response){
			JSONObject json = JSON.parseObject(response);
			WikiPage page = new WikiPage();
			page.setId(json.getLongValue("id"));
			page.setTitle(json.getString("title"));
			page.setSpacekey(json.getJSONObject("space").getString("key"));
		
			return page;
		}
		
		/**
		 * 根据Find接口返回json String 封装 对象
		 * @param response
		 * @return
		 */
		public static List<WikiPage> pasreFindResponse(String response){
			JSONObject json = JSON.parseObject(response);
			List<WikiPage> list =new ArrayList<WikiPage>();
			JSONArray jsonList = json.getJSONArray("results");
			for(int i=0; i< jsonList.size() ; i++) {
				JSONObject pageJson = jsonList.getJSONObject(i);
				WikiPage page = new WikiPage();
				page.setId(pageJson.getLongValue("id"));
				page.setTitle(pageJson.getString("title"));
				page.setSpacekey(json.getJSONObject("space").getString("key"));
				page.setVersion(pageJson.getJSONObject("version").getLongValue("number"));
				list.add(page);
			}
			return list;
		}
		
		/**
		 * 根据FindById接口返回json String 封装 对象
		 * @param response
		 * @return
		 */
		public static WikiPage pasreFindByIdResponse(String response){
			JSONObject json = JSON.parseObject(response);
			WikiPage page = new WikiPage();
			page.setId(json.getLongValue("id"));
			page.setTitle(json.getString("title"));
			page.setSpacekey(json.getJSONObject("space").getString("key"));
			page.setVersion(json.getJSONObject("version").getLongValue("number"));
			return page;
		}
}
