package confulence.client.wiki.model;

public class WikiPage {
	long id;
	//Wiki标题
	String title;
	
	//wiki正文内容
	String content;
	
	//上级页面id
    long parentPageId;
	
    //所属空间的key
	String spacekey;
	
	long version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;//this.id = id == null ? null : id.trim();
	}

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

	public long getParentPageId() {
		return parentPageId;
	}

	public void setParentPageId(long parentPageId) {
		this.parentPageId = parentPageId;//this.parentPageId = parentPageId == null ? null : parentPageId.trim();
	}

	public String getSpacekey() {
		return spacekey;
	}

	public void setSpacekey(String spacekey) {
		this.spacekey = spacekey == null ? null : spacekey.trim();
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;//this.version = version == null ? null : version.trim();
	}
	
	
}
