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
		//չʾ���е�����
		if(type.equals("showCommnet")){
			PageInformation pageInformation=new PageInformation();
			//��commentUserView���л�ȡ����
			Tool.getPageInformation("commentUserView", request, pageInformation);
			pageInformation.setSearchSql(" (newsId="+newsId+") ");
			//��������
			pageInformation.setOrder("desc");
			//����time�ֶ�����
			pageInformation.setOrderField("time");
			//��ȡһҳ������
			List<CommentUserView> commentUserViews=commentService.getOnePage(pageInformation);
			request.setAttribute("pageInformation", pageInformation);
			request.setAttribute("commentUserViews", commentUserViews);
			getServletContext().getRequestDispatcher("/comment/showComment.jsp").include(request,response);
			return ;
		}else if(type.equals("praise")){//����
			String commentId=request.getParameter("commentId");
			
			commentService.paise(Integer.parseInt(commentId));
			String url="/servlet/NewsServlet?type1=showANews&newsId="+newsId
					+"&page"+request.getParameter("page")
					+"&pageSize"+request.getParameter("pageSize")
					+"&totalPageCount"+request.getParameter("totalPageCount");
			getServletContext().getRequestDispatcher(url).forward(request,response);
		}else if(type.equals("addCommnet")){//�������
			Comment comment=new Comment();
			comment.setContent(request.getParameter("content"));
			//��¼����id�����ǽ����ź�������ϵ����
			comment.setNewsId(Integer.parseInt(newsId));
			User user=(User)request.getSession().getAttribute("user");
			//��¼�����ߵ�userId
			comment.setUserId(user.getUserId());
			
			String commentId=request.getParameter("commentId");
			
			String url;
			if(commentId==null || commentId.isEmpty()){
				commentService.addComment(comment);//�����ŵĻظ�
				url="/servlet/NewsServlet?type1=showANews&newsId="+newsId
						+"&page=1&addCommnet=addCommnet";	
			}else{
				comment.setCommentId(Integer.parseInt(commentId));
				commentService.addCommentToComment(comment);//�Իظ��Ļظ�
				url="/servlet/NewsServlet?type1=showANews&newsId="+newsId
						+"&page=1";	
			}
			
			//����ˢ�±�ҳ��
			getServletContext().getRequestDispatcher(url).forward(request,response);			
		}

	}

}
