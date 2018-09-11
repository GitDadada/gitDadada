package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Comment;
import bean.CommentUserView;
import bean.User;
import service.CommentService;
import service.NewsService;
import tools.Message;
import tools.PageInformation;
import tools.Tool;

public class CommentServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type=request.getParameter("type");
		String newsId=request.getParameter("newsId");
		CommentService commentService=new CommentService();
		//展示已有的评论
		if(type.equals("showCommnet")){
			PageInformation pageInformation=new PageInformation();
			//从commentUserView表中获取评论
			Tool.getPageInformation("commentUserView", request, pageInformation);
			pageInformation.setSearchSql(" (newsId="+newsId+") ");
			//升序排序
			pageInformation.setOrder("desc");
			//根据time字段排序
			pageInformation.setOrderField("time");
			//获取一页的评论
			List<CommentUserView> commentUserViews=commentService.getOnePage(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("commentUserViews", commentUserViews);
			getServletContext().getRequestDispatcher("/comment/showComment.jsp").include(request,response);
			return ;
		}else if(type.equals("praise")){//点赞
			String commentId=request.getParameter("commentId");
			
			commentService.paise(Integer.parseInt(commentId));
			String url="/servlet/NewsServlet?type1=showANews&newsId="+newsId
					+"&page"+request.getParameter("page")
					+"&pageSize"+request.getParameter("pageSize")
					+"&totalPageCount"+request.getParameter("totalPageCount");
			getServletContext().getRequestDispatcher(url).forward(request,response);
		}else if(type.equals("addCommnet")){//添加评论
			Comment comment=new Comment();
			comment.setContent(request.getParameter("content"));
			//记录新闻id，就是将新闻和评论联系起来
			comment.setNewsId(Integer.parseInt(newsId));
			User user=(User)request.getSession().getAttribute("user");
			//记录评论者的userId
			comment.setUserId(user.getUserId());
			
			String commentId=request.getParameter("commentId");
			
			String url;
			if(commentId==null || commentId.isEmpty()){
				commentService.addComment(comment);//对新闻的回复
				url="/servlet/NewsServlet?type1=showANews&newsId="+newsId
						+"&page=1&addCommnet=addCommnet";	
			}else{
				comment.setCommentId(Integer.parseInt(commentId));
				commentService.addCommentToComment(comment);//对回复的回复
				url="/servlet/NewsServlet?type1=showANews&newsId="+newsId
						+"&page=1";	
			}
			
			//用于刷新本页面
			getServletContext().getRequestDispatcher(url).forward(request,response);			
		}

	}

}
