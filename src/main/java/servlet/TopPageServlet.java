/**
 * TopPageServlet.java
 * Getリクエストを受けると、Webアプリ訪問回数を更新し、トップページの画面を表示するコントローラクラス
 */

/**
 * サイト訪問回数のカウントについて
 * https://nok-lab-life.hatenablog.com/entry/2018/06/23/185625
 */

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TopPageServlet
 */
@WebServlet("/TopPageServlet")
public class TopPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * コンストラクタ
     * @see HttpServlet#HttpServlet()
     */
    public TopPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * initメソッド
	 * Webアプリ訪問回数を初期化する
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		// タスク管理アプリの訪問回数
		Integer count = 0;
		
		// 訪問回数をアプリケーションスコープに保存する
		ServletContext application = config.getServletContext();
		application.setAttribute("count", count);
	}
    
	/**
	 * doGetメソッド
	 * Webアプリ訪問回数を更新し、トップページの画面（topPage.jsp）へフォワードする
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Webアプリ訪問回数をアプリケーションスコープから取得する
		ServletContext application = this.getServletContext();
		Integer count = (Integer)application.getAttribute("count");
		
		// 訪問回数を更新する
		count++;
		
		// 訪問回数をアプリケーションスコープに保存する
		application.setAttribute("count", count);
		
		// topPage.jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/topPage.jsp");
		dispatcher.forward(request, response);
	}

}
