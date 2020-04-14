# confulenceclient

用于访问Atlassian Confluence 本地部署版本的REST API

实测Confluence 7.3.1可以使用
目前仅支持creat,update,find 操作

creat 调用方法:

   		WikiCreatReq wikiPageReq = new WikiCreatReq();
		wikiPageReq.setTitle("标题");
		wikiPageReq.setContent("内容");
		wikiPageReq.setParentPageId(parentid);//上级页面id，如果不存在则会默认放置spaceSkey对应的空间根目录
		wikiPageReq.setSpacekey(spaceSkey);//页面归属空间 必填
		AuthMethod basicAuth = new BasicAuth(username, password);//访问wiki api使用的账号信息
		WikiPage wikipage = WIKIClient.creatObj(wikihost,basicAuth,wikiPageReq);
    
update 调用方法:
    
		WikiUpdateReq updateReq = new WikiUpdateReq();
		updateReq.setId(id);//要修改的wiki page id 必填
		updateReq.setTitle("标题");
		updateReq.setContent("内容");
		AuthMethod basicAuth = new BasicAuth(username, password);
		WikiPage wikipage= WIKIClient.updateObj(wikihost, basicAuth, updateReq);
    
find by title 调用方法:
    
		WikiFindReq wikiFindReq = new WikiFindReq();
		wikiFindReq.setTitle(title);  
		AuthMethod basicAuth = new BasicAuth(username, password);
		List<WikiPage> list = WIKIClient.findObj(wikihost, basicAuth, wikiFindReq);
    
find by id 调用方法:

		AuthMethod basicAuth = new BasicAuth(username, password);
		WikiPage wikipage= WIKIClient.findByIdObj(wikihost, basicAuth, id);
