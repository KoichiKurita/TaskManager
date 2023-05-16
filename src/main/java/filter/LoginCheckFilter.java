/**
 * LoginCheckFilter.java
 * ログインしているか確認し、していない場合は、トップページ(TopPegeServlet)にリダイレクトする
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter(urlPatterns = {"/MainMenuServlet", "/TaskMenuServlet", "/UserMenuServlet", "/AddTaskServlet", "/DeleteTaskServlet", "/EditTaskServlet", "/EditUserServlet"})
public class LoginCheckFilter extends HttpFilter implements Filter {
       
    /**
     * コンストラクタ
     * @see HttpFilter#HttpFilter()
     */
    public LoginCheckFilter() {
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
	 * ログインしているユーザを調べ、存在しない場合は、トップページ(TopPageServlet.java)へリダイレクトする
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		// セッションスコープからユーザー情報を取得
		HttpSession session = ((HttpServletRequest)request).getSession();
		User loginUser = (User)session.getAttribute("user");
		
		// ログインしているか検証する
		if (loginUser == null) {
			// ログインしていない場合、TopPageServletにリダイレクトする

			((HttpServletResponse)response).sendRedirect("TopPageServlet");
		} else {
			// ログインしている場合、通常の遷移を行う
			chain.doFilter(request, response);
		}
	}

	/**
	 * initメソッド
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
