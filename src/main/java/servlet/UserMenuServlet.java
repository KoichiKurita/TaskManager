/**
 * UserMenuServlet.java
 * Getリクエストを受けると、ユーザ管理メニューを表示するコントローラクラス
 */
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserMenuServlet
 */
@WebServlet("/UserMenuServlet")
public class UserMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * コンストラクタ
     * @see HttpServlet#HttpServlet()
     */
    public UserMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * doGetメソッド
	 * ユーザメニュー(userMenu.jsp)へフォワードする
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// userMenu.jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userMenu.jsp");
		dispatcher.forward(request, response);
	}

}
