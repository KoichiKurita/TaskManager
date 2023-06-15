/**
 * AutoDeleteRegisterUserFilter.java
 * LoginServletにアクセスした際に、セッションスコープに保存されているregisterUserを削除する
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

import model.User;

/**
 * Servlet Filter implementation class AutoDeleteRegisterUserFilter
 */
@WebFilter("/LoginServlet")
public class AutoDeleteRegisterUserFilter extends HttpFilter implements Filter {
       
    /**
     * コンストラクタ
     * @see HttpFilter#HttpFilter()
     */
    public AutoDeleteRegisterUserFilter() {
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
	 * セッションスコープに保存されているregisterUserがある場合は削除する
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		User registerUser = (User)session.getAttribute("registerUser");
		
		if (registerUser != null) {
			// registerUserが存在する場合、削除する
			session.removeAttribute("registerUser");
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
