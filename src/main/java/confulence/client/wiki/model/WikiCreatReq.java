package confulence.client.wiki.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import confulence.client.wiki.model.WikiCreatReq.Ancestor;

public class WikiCreatReq {	
			//Wiki标题
			String title;
			
			//wiki正文内容
			String content;
			
			//上级页面id
		    long parentPageId;
			
		    //所属空间的key
			String spacekey;
			
			public String getTitle() {
				return title;
			}
			public void setTitle(String title) {
				this.title = title == null ? null : title.trim();
			}
			public String getContent() {
				return content;
			}
			public void setContent(String content) {
				this.content = content == null ? null : content.trim();
			}
			public String getSpacekey() {
				return spacekey;
			}
			public void setSpacekey(String spacekey) {
				this.spacekey = spacekey;//this.spacekey = spacekey == null ? null : spacekey.trim();
			}
			public long getParentPageId() {
				return parentPageId;
			}
			public void setParentPageId(long parentPageId) {
				this.parentPageId = parentPageId;//this.parentPageId = parentPageId == null ? null : parentPageId.trim();
			}
			
			public static class Ancestor{
				String id;

				public String getId() {
					return id;
				}

				public void setId(String id) {
					this.id = id == null ? null : id.trim();
				}
				
			}
			
			
			/**
			 * 组装参数
			 * @return
			 */
			public Map<String,Object> getPostParams(){
				Map<String,Object> params = new HashMap<String,Object>();
				
				
				List<Ancestor> ancestors = new ArrayList<Ancestor>();
				if(this.getParentPageId() != 0) {
					Ancestor ancestor = new Ancestor();
					ancestor.setId(String.valueOf(this.getParentPageId()));
					ancestors.add(ancestor);
					params.put("ancestors", ancestors);
				}
				params.put("type", "page");
				params.put("title", this.getTitle());

				
				if(this.getSpacekey()!=null && !"".equals(this.getSpacekey())) {
					Map<String,String> space = new HashMap<String,String>();
					space.put("key",this.getSpacekey());
					params.put("space", space);
				}

				Map<String,String> storage = new HashMap<String,String>();
				storage.put("value", this.getContent());
				storage.put("representation", "storage");
				Map<String,Object> body = new HashMap<String,Object>();
				body.put("storage",storage);
				params.put("body", body);
				
				return params;
			}
}
