/**
 * RegisterUserServlet.java
 * Getリクエストを受けると、新規ユーザ登録画面を表示するコントローラクラス
 * Postリクエストを受けると、ユーザ情報を登録する
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

import model.CheckUserLogic;
import model.InputIdPwCheckLogic;
import model.RegisterUserLogic;
import model.User;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * コンストラクタ
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * doGetメソッド
	 * リクエストパラメータに応じて、ユーザ登録画面とユーザ確認画面へフォワードする
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// フォワード先
		String forwardPath = null;
		
		// 動作を決定するリクエストパラメータを取得
		String action = request.getParameter("action");
		
		if (action.equals("input")) {
			
			// ユーザ入力をリクエストしたとき
			// ユーザ入力フォーム(addTaskForm.jsp)をフォワード先に指定する
			forwardPath = "WEB-INF/jsp/registerUserForm.jsp";
			
		} else if (action.equals("register")) {
			
			// ユーザ登録をリクエストしたとき			
			// リクエストスコープに保存されたユーザ情報を取得
			HttpSession session = request.getSession();
			User registerUser = (User)session.getAttribute("registerUser");
			
			// ユーザ情報を登録する
			RegisterUserLogic RgsUsrLgcBo = new RegisterUserLogic();
			boolean resultRegisterUser = RgsUsrLgcBo.execute(registerUser);
			
			if (resultRegisterUser) {
				// 新規ユーザ登録成功
				// セッションスコープにユーザ情報を保存する
				session.setAttribute("user", registerUser);
				
				// registerUserOK.jspをフォワード先へ指定する
				forwardPath = "WEB-INF/jsp/registerUserOK.jsp";
				
			} else {
				// 新規ユーザ登録失敗
				
				// registerUserNG.jspをフォワード先へ指定する
				forwardPath = "WEB-INF/jsp/registerUserNG.jsp";
			}
		}
		
		// 指定されたページへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	/**
	 * doPostメソッド
	 * 入力されたユーザID、パスワードを検証する。ユーザID、パスワードの要件を満たしている場合、登録する。
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
			
			// registerUserForm.jspをフォワード先へ指定する
			forwardPath = "WEB-INF/jsp/registerUserForm.jsp";
		
		} else {
			// 入力チェックでエラーがない場合（エラーメッセージがない場合）
			// 登録済みのユーザか確認する
			CheckUserLogic ChkUsrLgcBo = new CheckUserLogic();
			boolean resultCheckUser = ChkUsrLgcBo.execute(user.getUserId());
			
			// 入力されたユーザが登録済みかどうかで処理を分ける
			if (resultCheckUser) {
				// 既に登録されているユーザ情報の場合
				// エラーメッセージ生成
				errorMessages.add("※既に登録されているユーザです");				
				
				// registerUserForm.jspをフォワード先へ指定する
				forwardPath = "WEB-INF/jsp/registerUserForm.jsp";
				
			} else {
				// 未登録のユーザ情報の場合
				
				// registerUserConfirm.jspをフォワード先へ指定する
				forwardPath = "WEB-INF/jsp/registerUserConfirm.jsp";
			}
		}
		
		
		// リクエストスコープにエラーメッセージを保存
		request.setAttribute("errorMessages", errorMessages);
		
		// セッションスコープにユーザ情報を保存
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", user);
		
		// 指定されたページへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
		
	}

}
