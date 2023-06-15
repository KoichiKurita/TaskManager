/**
 * SetEncodingFilter.java
 * 全てのサーブレットクラスに対して、文字コードをUTF-8に設定するフィルタクラス
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

/**
 * Servlet Filter implementation class SetEncodingFilter
 */
@WebFilter("/*")	// 全てのサーブレットクラス, JSPファイル, HTMLファイルが対象
public class SetEncodingFilter extends HttpFilter implements Filter {
       
    /**
     * コンストラクタ
     * @see HttpFilter#HttpFilter()
     */
    public SetEncodingFilter() {
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
	 * 文字コードをUTF-8に設定する
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		// 文字コードをUTF-8に設定する
		request.setCharacterEncoding("UTF-8");
		
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
