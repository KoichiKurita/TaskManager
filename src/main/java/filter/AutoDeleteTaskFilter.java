/**
 * AutoDeleteTaskFilter.java
 * TopPageServletにアクセスした際に、セッションスコープに保存されているタスク情報を削除する
 */
package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Task;

/**
 * Servlet Filter implementation class AutoDeleteTaskFilter
 */
@WebFilter(urlPatterns = {"/MainMenuServlet", "/TaskMenuServlet", "/UserMenuServlet"})
public class AutoDeleteTaskFilter extends HttpFilter implements Filter {
       
    /**
     * コンストラクタ
     * @see HttpFilter#HttpFilter()
     */
    public AutoDeleteTaskFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * destroyメソッド
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * doFilterメソッド
	 * セッションスコープに保存されているタスク情報がある場合は削除する
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		Task task = (Task)session.getAttribute("task");
		
		if (task != null) {
			// タスク情報が存在する場合、削除する
			session.removeAttribute("task");
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * initメソッド
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
