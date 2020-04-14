package confulence.client.wiki.model;

import java.util.HashMap;
import java.util.Map;

public class WikiFindReq {
	String spaceKey;
	String title;

	long id;
	int start = 0;
	int limit = 25;
	public String getSpaceKey() {
		return spaceKey;
	}
	public void setSpaceKey(String spaceKey) {
		this.spaceKey = spaceKey == null ? null : spaceKey.trim();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;//this.start = start == null ? null : start.trim();
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;//this.limit = limit == null ? null : limit.trim();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;//this.id = id == null ? null : id.trim();
	}
	
	

	/**
	 * 组装参数
	 * @return
	 */
	public Map<String,String> getGetParams(){
		Map<String,String> params = new HashMap<String,String>();
		if(title !=null && !"".equals(title)) {
			params.put("title", title);
		}
		if(spaceKey !=null && !"".equals(spaceKey)) {
			params.put("spaceKey", spaceKey);
		}

		
		params.put("start", String.valueOf(start));
		
		params.put("limit", String.valueOf(limit));
		
		return params;
		
	}
}
