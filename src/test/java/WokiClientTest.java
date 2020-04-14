

import java.util.List;

import com.alibaba.fastjson.JSON;


import confulence.client.wiki.WIKIClient;
import confulence.client.wiki.auth.AuthMethod;
import confulence.client.wiki.auth.BasicAuth;
import confulence.client.wiki.ex.WikiClientException;
import confulence.client.wiki.model.WikiCreatReq;
import confulence.client.wiki.model.WikiFindReq;
import confulence.client.wiki.model.WikiPage;
import confulence.client.wiki.model.WikiUpdateReq;
import junit.framework.TestCase;

public class WokiClientTest extends TestCase {
	
	String wikihost;
	String username;
	String password;
	static String title = "测试自动生成wiki"+System.currentTimeMillis();
	static long id ;
	static long parentid;
	static String spaceSkey;
	
	protected void setUp(){
		wikihost = "";
		username = "";
		password = "";
		parentid = 0;
		spaceSkey = "";
		
	}
	
	public void testCreat() throws Exception, WikiClientException{
		WikiCreatReq wikiPageReq = new WikiCreatReq();
		wikiPageReq.setTitle(title);
		wikiPageReq.setContent("test"+System.currentTimeMillis());
		wikiPageReq.setParentPageId(parentid);//上级页面id，如果不存在则会默认放置空间根目�?
		wikiPageReq.setSpacekey(spaceSkey);//页面归属空间 必填
		
		AuthMethod basicAuth = new BasicAuth(username, password);
		WikiPage wikipage = WIKIClient.creatObj(wikihost,basicAuth,wikiPageReq);
		id = wikipage.getId();
		System.out.println("=============="+JSON.toJSONString(wikipage));
	}
	
	public void testFind() throws Exception, WikiClientException{
		AuthMethod basicAuth = new BasicAuth(username, password);
		WikiFindReq wikiFindReq = new WikiFindReq();
		wikiFindReq.setTitle(title);
		
		List<WikiPage> list = WIKIClient.findObj(wikihost, basicAuth, wikiFindReq);
		id = list.get(0).getId();
		System.out.println("=============="+JSON.toJSONString(list));
	}
	
	public void testFindById() throws WikiClientException{
		AuthMethod basicAuth = new BasicAuth(username, password);

		WikiPage wikipage= WIKIClient.findByIdObj(wikihost, basicAuth, id+2123213);
		System.out.println("=============="+JSON.toJSONString(wikipage));
	}
	
	public void testUpdate() throws WikiClientException{

		AuthMethod basicAuth = new BasicAuth(username, password);
		WikiUpdateReq updateReq = new WikiUpdateReq();
		updateReq.setId(id);
		updateReq.setTitle(title+"|"+System.currentTimeMillis());
		updateReq.setContent("test2|"+System.currentTimeMillis());

		WikiPage wikipage= WIKIClient.updateObj(wikihost, basicAuth, updateReq);
		System.out.println("=============="+JSON.toJSONString(wikipage));
	}

}
