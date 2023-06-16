/**
 * AutoDeletPassWordFilter.java
 * MainMenuServlet, TaskMenuServlet, UserMenuServletにアクセスした際に、セッションスコープに保存されているパスワード情報を削除する
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
 * Servlet Filter implementation class AutoDeletePassWordFilter
 */
@WebFilter(urlPatterns = {"/MainMenuServlet", "/TaskMenuServlet", "/UserMenuServlet"})
public class AutoDeletePassWordFilter extends HttpFilter implements Filter {
       
    /**
     * コンストラクタ
     * @see HttpFilter#HttpFilter()
     */
    public AutoDeletePassWordFilter() {
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
	 * セッションスコープに現在のパスワード, 新しいパスワード, 確認用パスワードが保存されているか調べ、存在する場合は、削除する
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		// セッションスコープからパスワード情報を取得
		HttpSession session = ((HttpServletRequest)request).getSession();
		String sessionCurrentPassWord = (String)session.getAttribute("sessionCurrentPassWord");	// 現在のパスワード
		String sessionNewPassWord = (String)session.getAttribute("sessionNewPassWord");	// 新しいパスワード
		String sessionNewPassWordConfirm = (String)session.getAttribute("sessionNewPassWordConfirm"); // 確認用パスワード
		
		if (sessionCurrentPassWord != null ) {
			session.removeAttribute("sessionCurrentPassWord");
		}
		
		if (sessionNewPassWord != null ) {
			session.removeAttribute("sessionNewPassWord");
		}
		
		if (sessionNewPassWordConfirm != null ) {
			session.removeAttribute("sessionNewPassWordConfirm");
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
