package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.PageInformation;
import dao.CommentDao;
import dao.DatabaseDao;
import bean.Comment;
import bean.CommentUserView;

public class CommentService {
	public List<CommentUserView> getOnePage(PageInformation pageInformation) {	
		List<CommentUserView> commentUserViews=new ArrayList<CommentUserView>();
		try {
			DatabaseDao databaseDao=new DatabaseDao();			
			CommentDao commentDao=new CommentDao();		
			commentUserViews=commentDao.getOnePage(pageInformation,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return commentUserViews;
	}
	//����
	public Integer paise(Integer commentId){
		try {
			CommentDao commentDao=new CommentDao();
			if(commentDao.paise(commentId)>0)
				return 1;//
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;//
		} catch (Exception e) {			
			e.printStackTrace();
			return -3;//
		}	
	}
	//�����ŵĻظ�
	public Integer addComment(Comment comment){
		CommentDao commentDao=new CommentDao();		
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			//��ȡ��¥�㡱������ȡ�����µ���������
			Integer stair=commentDao.getStairByNewsId(comment.getNewsId(),databaseDao);
			//��¼�����۵�¥��
			comment.setStair(stair+1);
			return commentDao.addComment(comment,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		}
	}
	//�Իظ��Ļظ�
	public Integer addCommentToComment(Comment comment){
		CommentDao commentDao=new CommentDao();		
		try {
			DatabaseDao databaseDao=new DatabaseDao();
			CommentUserView oldCommentUserView=commentDao.getByIdFromView(comment.getCommentId(),databaseDao);
			String content=	oldCommentUserView.getContent();
			if(content.contains("<br><br>")){//����֮ǰ������
				content=content.substring(content.indexOf("<br><br>")+8);
			}
			String s="�ظ���"+oldCommentUserView.getStair()+"¥��&nbsp;"
						+oldCommentUserView.getUserName()+"&nbsp;�����ԣ�"
						+content+"<br><br>";

			comment.setContent(s+comment.getContent());
			Integer stair=commentDao.getStairByNewsId(comment.getNewsId(),databaseDao);
			comment.setStair(stair+1);
			return commentDao.addComment(comment,databaseDao);
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		}
	}
}
