/**
 * AutoDeleteSessionScopeFilter.java
 * TopPageServlet, LoginServlet, RegisterUserServletにアクセスした際に、セッションスコープに保存されているインスタンスを削除するフィルタ
 */

/*
 * URLパターンの複数指定について
 * 参考サイト：
 * http://web.javastudy.biz/?p=183
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

/**
 * Servlet Filter implementation class AutoDeleteSessionScopeFilter
 */
@WebFilter(urlPatterns = {"/TopPageServlet"})
public class AutoDeleteSessionScopeFilter extends HttpFilter implements Filter {
       
    /**
     * コンストラクタ
     * @see HttpFilter#HttpFilter()
     */
    public AutoDeleteSessionScopeFilter() {
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
	 * セッションスコープに保存されているユーザ情報がある場合は削除する
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		if (session != null) {
			// トップページにアクセスした際、セッションスコープに保存されているユーザ情報やタスク情報を全て削除する
			session.invalidate();
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
