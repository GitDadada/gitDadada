package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.PageInformation;
import tools.Tool;
import tools.WebProperties;
import dao.DatabaseDao;
import dao.NewsDao;
import dao.NewsDao;
import bean.News;
import bean.News;


public class NewsService {
	public Integer add(News news){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			NewsDao newsDao=new NewsDao();
			return newsDao.add( news, databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
	}
	
	//获取一页“更多”新闻页
	public List<News> getOnePage(PageInformation pageInformation) {	
		List<News> newses=new ArrayList<News>();
		try {
			DatabaseDao databaseDao=new DatabaseDao();			
			NewsDao newsDao=new NewsDao();		
			newses=newsDao.getOnePage(pageInformation,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return newses;
	}
	
	public News getNewsById(Integer newsId){
		NewsDao newsDao=new NewsDao();		
		try {
			return newsDao.getById(newsId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//删除多条记录
	public List<News> deletes(PageInformation pageInformation) {	
		List<News> newses=null;
		try {
			DatabaseDao databaseDao=new DatabaseDao();			
			NewsDao newsDao=new NewsDao();
			newsDao.deletes(pageInformation.getTableName(),pageInformation.getIds(),databaseDao);
			pageInformation.setIds(null);
			newses=newsDao.getOnePage(pageInformation,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return newses;
	}	
	
	public List<List<News>> getByTypesTopN(String[] newsTypes, Integer n){
		List<List<News>>  newsesList=new ArrayList<List<News>>();
		try {			
			DatabaseDao databaseDao=new DatabaseDao();
			NewsDao newsDao=new NewsDao();
			for(String type: newsTypes){
				List<News> newses=newsDao.getByTypesTopN(type, n, databaseDao);
				newsesList.add(newses);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return newsesList;
	}
//	参数（newsTypes, homePageNewsN,newsCaptionsList）
	public List<List<News>> getByTypesTopN1(String[] newsTypes, Integer n,List<List<String>> newsCaptionsList){
		List<List<News>>  newsesList=new ArrayList<List<News>>();
		try {			
			DatabaseDao databaseDao=new DatabaseDao();
			NewsDao newsDao=new NewsDao();
			//遍历nesTypes = all,社会,体育,汽车
			for(String type: newsTypes){
				//根据类型，条数，databaseDao获取新闻（获取新闻完整内容）
				List<News> newses=newsDao.getByTypesTopN(type, n, databaseDao);
				
				//根据完整的标题内容，将标题内容修改成规定的长度(15)和格式（获取新闻精简标题）
				List<String> newsCaptions=new ArrayList<String>();
				for(News news:newses){
					String newsCaption=Tool.getStringByMaxLength(news.getCaption(),
						Integer.parseInt(WebProperties.config.getString("homePageNewsCaptionMaxLength")));
					//保存精简标题到标题内容中
					newsCaptions.add(newsCaption);
				}
				//保存到（所有）新闻列表中  （返回值）
				newsesList.add(newses);
				//保存到（所有）新闻精简标题中 （参数）
				newsCaptionsList.add(newsCaptions);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		//返回（所有）新闻列表
		return newsesList;
	}	
	
	public Integer update(News news){
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			NewsDao newsDao=new NewsDao();
			return newsDao.update( news, databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}		
	}
}
