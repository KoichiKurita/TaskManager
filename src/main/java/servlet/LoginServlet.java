/**
 * LoginServlet.java
 * Getリクエストを受けると、ログイン画面を表示するコントローラクラス
 * Postリクエストを受けると、ユーザ情報を認証する
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.InputIdPwCheckLogic;
import model.LoginLogic;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * コンストラクタ
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * doGetメソッド
	 * ログイン画面(loginForm.jsp)へフォワードする
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// loginForm.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginForm.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * doPostメソッド
	 * 入力されたユーザID、パスワードを検証する。検証結果に応じて遷移先を変える。
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// postされたユーザID, パスワードを取得
		String userId = request.getParameter("user-ID");
		String passWord = request.getParameter("pass-word");
		
		// ユーザを生成
		User user = new User(userId, passWord);
		
		// ユーザID, パスワードの入力チェックを行う
		InputIdPwCheckLogic iptIdPwChkLogicBo = new InputIdPwCheckLogic();
		ArrayList<String> errorMessages = iptIdPwChkLogicBo.execute(user.getUserId(), user.getPassWord());
		
		// フォワード先
		String forwardPath = null;
		
		// 入力チェックでエラーがない場合にのみデータベースでの検索を行い、処理のオーバーヘッドを減少させる
		if (errorMessages.size() != 0) {
			// 入力チェックでエラーがある場合（エラーメッセージがある場合）
			
			// リクエストスコープにエラーメッセージを保存
			request.setAttribute("errorMessages", errorMessages);
			
			// リクエストスコープにユーザ情報を保存する
			request.setAttribute("loginUser", user);
			
			// loginForm.jspをフォワード先へ指定
			forwardPath = "WEB-INF/jsp/loginForm.jsp";
		
		} else {
			// 入力チェックでエラーがない場合（エラーメッセージがない場合）
			
			// ログイン処理を実行する
			LoginLogic loginLoginBo = new LoginLogic();
			boolean resultLogin = loginLoginBo.execute(user);
			
			// ログイン処理の結果によって、フォワード先を変える
			if (resultLogin) {
				// ログインに成功した場合
				
				// セッションスコープにユーザ情報を保存する
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				// loginOK.jspをフォワード先へ指定
				forwardPath = "WEB-INF/jsp/loginOK.jsp";
				
			} else {
				// ログインに失敗した場合
				
				// エラーメッセージを生成
				errorMessages.add("※ユーザIDまたはパスワードが誤っております。");
				
				// リクエストスコープにエラーメッセージを保存
				request.setAttribute("errorMessages", errorMessages);
				
				// リクエストスコープにユーザ情報を保存する
				request.setAttribute("loginUser", user);
				
				// loginForm.jspをフォワード先へ指定
				forwardPath = "WEB-INF/jsp/loginForm.jsp";
			}
		
		}
		
		// 指定されたページへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
		
	}

}
