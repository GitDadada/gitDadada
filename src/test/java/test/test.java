package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import bean.Comment;
import bean.CommentUserView;
import bean.User;
import service.CommentService;
import tools.PageInformation;

public class test {

	@Test
	public void testGetOnePage() {
//		fail("Not yet implemented");
		CommentService commentService=new CommentService();
		PageInformation pageInformation=new PageInformation();
		pageInformation.setTableName("commentUserView");
		pageInformation.setPageSize(5);
		pageInformation.setTotalPageCount(5);
		pageInformation.setAllRecordCount(6);
		pageInformation.setPage(1);
		pageInformation.setSearchSql(" (newsId=29) ");
		pageInformation.setOrder("desc");
		pageInformation.setOrderField("time");
		List<CommentUserView> commentUserViews=commentService.getOnePage(pageInformation);
		assertNotNull(commentUserViews);
	}
	
	@Test
	public void testPaise(){
		CommentService commentService=new CommentService();
		int meg = commentService.paise(27);
		assertEquals(meg,1);
	}
	@Test
	public void testAddComment(){
		CommentService commentService=new CommentService();
		Comment comment=new Comment();
		comment.setContent("junit test");
		comment.setNewsId(33);
		User user = new User();
		comment.setUserId(30);
		int meg = commentService.addComment(comment);
		assertEquals(meg, 1);
		
	}
	@Test
	public void testAddCommentToComment(){
		CommentService commentService=new CommentService();
		Comment comment=new Comment();
		comment.setContent("junit test2");
		comment.setNewsId(33);
		comment.setCommentId(23);
		User user = new User();
		comment.setUserId(30);
		int meg = commentService.addComment(comment);
		assertEquals(meg, 1);
	}
	

}
