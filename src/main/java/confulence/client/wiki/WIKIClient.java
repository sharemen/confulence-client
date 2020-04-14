package confulence.client.wiki;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import confulence.client.wiki.auth.AuthMethod;
import confulence.client.wiki.ex.WikiClientException;
import confulence.client.wiki.model.WikiCreatReq;
import confulence.client.wiki.model.WikiFindReq;
import confulence.client.wiki.model.WikiPage;
import confulence.client.wiki.model.WikiUpdateReq;



public class WIKIClient {
	private static Logger logger = LoggerFactory.getLogger(WIKIClient.class);
    
	private AuthMethod authMethod;
	 
	//创建文档的rest api 接口
	private final static  String WIKI_API_CREAT = "/rest/api/content" ;
	
	//修改文档的rest api 接口
	private final static  String WIKI_API_FIND = "/rest/api/content" ;

	//修改文档的rest api 接口
	private final static  String WIKI_API_UPDATE = "/rest/api/content" ;
	 

	
	
	
	
	
	
	/**
	 * 创建wiki页面
	 * @param wikihost
	 * @param auth
	 * @param wikipage
	 * @return Json String
	 * @throws WikiClientException 
	 */
	public static String creat(String wikihost,AuthMethod auth,WikiCreatReq wikipage) throws WikiClientException {

		String responseRet = null;
		Map<String, String> headers = new HashMap<String,String>();

		 
			headers.put("Content-Type", "application/json;charset=utf-8");
			//登录
			headers = logon(headers,auth);
			HttpResponse httpResponse;
			try {
				httpResponse = HttpUtils.doPost(wikihost, WIKI_API_CREAT, headers, JSON.toJSONString(wikipage.getPostParams()));
				responseRet = HttpUtils.parseString(httpResponse);
				if(httpResponse.getStatusLine().getStatusCode() != 200) {
					throw new WikiClientException(responseRet);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return responseRet;
	}
	
	/**
	 * 
	 * @param wikihost
	 * @param auth
	 * @param wikipage
	 * @return WikiPage
	 * @throws WikiClientException 
	 */
	public static WikiPage creatObj(String wikihost,AuthMethod auth,WikiCreatReq wikipage) throws WikiClientException {
		return WikiResponse.pasreCreatResponse(creat( wikihost, auth, wikipage));
	}
	
	/**
	 * 查询wiki page数据列表
	 * @param wikihost
	 * @param auth
	 * @param findReq
	 * @return Page对象
	 * @throws WikiClientException 
	 */
	public static String find(String wikihost,AuthMethod auth,WikiFindReq findReq) throws WikiClientException {
		String responseRet = null;
		Map<String, String> headers = new HashMap<String,String>();

		 
			headers.put("Content-Type", "application/json;charset=utf-8");
			//登录
			headers = logon(headers,auth);
			HttpResponse httpResponse;
			try {
				StringBuilder url = new StringBuilder();
				url.append(WIKI_API_FIND);
				if(findReq.getId() != 0) {
					url =url.append("/").append(String.valueOf(findReq.getId()));
				}
				httpResponse = HttpUtils.doGet(wikihost, url.toString(), headers, findReq.getGetParams());
				responseRet = HttpUtils.parseString(httpResponse);
				if(httpResponse.getStatusLine().getStatusCode() != 200) {
					throw new WikiClientException(responseRet);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return responseRet;
	}
	
	/**
	 * 查询wiki page数据列表
	 * @param wikihost
	 * @param auth
	 * @param findReq
	 * @return Page对象
	 * @throws WikiClientException 
	 */
	public static List<WikiPage>  findObj(String wikihost,AuthMethod auth,WikiFindReq findReq) throws WikiClientException {
		return  WikiResponse.pasreFindResponse(find( wikihost, auth, findReq));
	}
	

	
	/**
	 * 查询wiki page数据列表
	 * @param wikihost
	 * @param auth
	 * @param findReq
	 * @return
	 * @throws WikiClientException 
	 */
	public static String findById(String wikihost,AuthMethod auth,long id) throws WikiClientException {
		if(id == 0) {
			 throw new WikiClientException("param 'id' can't be 0");
		}
		WikiFindReq findReq = new WikiFindReq();
		findReq.setId(id);
		return find( wikihost, auth, findReq);
	}
	
	/**
	 * 查询单个wiki page数据
	 * @param wikihost
	 * @param auth
	 * @param findReq
	 * @return Page对象
	 * @throws WikiClientException 
	 */
	public static WikiPage findByIdObj(String wikihost,AuthMethod auth,long id) throws WikiClientException {
		return  WikiResponse.pasreFindByIdResponse(findById( wikihost, auth, id)); 
	}
	
	/**
	 * 更新wiki数据
	 * @param wikihost
	 * @param auth
	 * @param updateReq
	 * @return
	 * @throws WikiClientException 
	 */
	public static String update(String wikihost,AuthMethod auth,WikiUpdateReq updateReq) throws WikiClientException {
		String responseRet = null;
		Map<String, String> headers = new HashMap<String,String>();

		 
			headers.put("Content-Type", "application/json;charset=utf-8");
			//登录
			headers = logon(headers,auth);
			HttpResponse httpResponse;
			StringBuilder url = new StringBuilder();
			url.append(WIKI_API_UPDATE);
			if(updateReq.getId() != 0) {
				url =url.append("/").append(String.valueOf(updateReq.getId()));
			}else {
				 throw new WikiClientException("WikiUpdateReq's propertie 'id' can't be 0");
			}
			
			//自动累加版本号
			WikiPage oldpage= findByIdObj(wikihost,auth,updateReq.getId());
			
			if(oldpage == null) {
				 throw new WikiClientException("WikiUpdateReq's propertie 'id' "+updateReq.getId()+" is not exists");
			}else {
				updateReq.setVersion(oldpage.getVersion()+1);
			}
			
			try {
			
				
				httpResponse = HttpUtils.doPut(wikihost, url.toString(), headers, null,JSON.toJSONString(updateReq.getPostParams()));
				responseRet = HttpUtils.parseString(httpResponse);
				if(httpResponse.getStatusLine().getStatusCode() != 200) {
					throw new WikiClientException(responseRet);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return responseRet;
	}
	
	/**
	 * 更新wiki数据
	 * @param wikihost
	 * @param auth
	 * @param updateReq
	 * @return
	 * @throws WikiClientException 
	 */
	public static WikiPage updateObj(String wikihost,AuthMethod auth,WikiUpdateReq updateReq) throws WikiClientException {
		return WikiResponse.pasreUpdateResponse( update(wikihost, auth, updateReq));
	}
	
	
	
	/**
	 * 获取登录headers
	 * @param headers
	 * @param auth
	 * @return
	 */
	private  static Map<String,String> logon(Map<String,String> headers,AuthMethod auth) {
		if(headers != null) {
			headers.put("Authorization", auth.getAuthHeaderValue());
			//remoteRequest.setHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
			//remoteRequest.setHeader(HttpHeaders.AUTHORIZATION, String.format("Basic %s", Base64.getEncoder().encodeToString((username + ":" + password).getBytes())));
		
		}
		return headers;
	}

}
