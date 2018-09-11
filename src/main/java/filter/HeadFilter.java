package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.WebProperties;

public class HeadFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//从web.properties中获取地址,并include到除了manageNes.jsp的页面顶部
		if(!req.getRequestURI().endsWith("manageNews.jsp")){
			req.getServletContext().getRequestDispatcher(
				 WebProperties.config.getString("headJsp")).include(req,res);
		}
		chain.doFilter(request, response);
		
		//从web.properties中获取地址,并include到除了addNews.jsp的页面底部
		if(!req.getRequestURI().endsWith("addNews.jsp")){
			req.getServletContext().getRequestDispatcher(
				 WebProperties.config.getString("tailJsp")).include(req,res);
		}		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	
	}
	public void destroy() {}

}
