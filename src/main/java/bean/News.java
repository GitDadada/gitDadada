package bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class News {
	private Integer newsId;//新闻id
	private String newsType ;//新闻类型，体育，社会等
	private String author;//新闻作者
	private String caption;//新闻概要
	private String content;//新闻内容
	private LocalDateTime newsTime;//
	private Timestamp publishTime;//发布新闻的时间
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
	
	public LocalDateTime getNewsTime() {
		return newsTime;
	}
	public void setNewsTime(LocalDateTime newsTime) {
		this.newsTime = newsTime;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	
	
}
