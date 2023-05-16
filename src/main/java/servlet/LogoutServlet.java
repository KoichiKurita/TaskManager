/**
 * LogoutServlet.java
 * Getリクエストを受けると、ログアウトするコントローラクラス
 */
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * コンストラクタ
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * doGetメソッド
	 * リクエストパラメータに応じて、ログアウトの確認画面とログアウト完了画面にフォワードする
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// フォワード先
		String forwardPath = null;
		
		// 動作を決定するリクエストパラメータの取得
		String action = request.getParameter("action");
		
		if (action.equals("confirm")) {
			// ログアウト確認画面をリクエストしたとき
			// ログアウト確認画面(logoutConfirm.jsp)をフォワード先に指定する
			forwardPath = "WEB-INF/jsp/logoutConfirm.jsp";
		
		} else if (action.equals("logout")) {
			// ログアウトをリクエストしたとき
			// ログアウトを実行する
			// セッションスコープに保存されているユーザ情報とタスク情報を削除する
			HttpSession session = request.getSession();
			session.invalidate();
			
			// ログアウト完了画面(logoutOK.jsp)をフォワード先に指定する
			forwardPath = "WEB-INF/jsp/logoutOK.jsp";
		}
		 
		// 遷移先へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
