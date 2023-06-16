/**
 * RegisterUserServlet.java
 * Getリクエストを受けると、新規ユーザ登録画面を表示するコントローラクラス
 * Postリクエストを受けると、ユーザ情報を登録する
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CheckUserLogic;
import model.EscapeCharacterLogic;
import model.InputIdCheckLogic;
import model.InputPwCheckLogic;
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
		String passWordConfirm = request.getParameter("pass-word-confirm");
		
		// ユーザID, パスワードに含まれるHTML特殊文字をエスケープする
		EscapeCharacterLogic ecpCharLgcBo = new EscapeCharacterLogic();
		userId = ecpCharLgcBo.execute(userId);
		passWord = ecpCharLgcBo.execute(passWord);
		passWordConfirm = ecpCharLgcBo.execute(passWordConfirm);
		
		InputIdCheckLogic iptIdChkLgcBo = new InputIdCheckLogic();
		ArrayList<String> idErrorMessageList = iptIdChkLgcBo.execute(userId);
		
		InputPwCheckLogic iptPwChkLgcBo = new InputPwCheckLogic();
		ArrayList<String> pwErrorMessageList = iptPwChkLgcBo.execute(passWord);
		ArrayList<String> pwConfirmErrorMessageList = iptPwChkLgcBo.execute(passWordConfirm);
		
		// パスワードと確認用パスワードが一致しているかチェックを行う
		if (!(passWord.equals(passWordConfirm))){
			// 正しくない場合、エラーメッセージを格納する
			pwConfirmErrorMessageList.add("※パスワードが一致しません。");
		}
		
		HashMap<String, ArrayList<String>> errorMessageMap = new HashMap<>();
		errorMessageMap.put("userId" ,idErrorMessageList);
		errorMessageMap.put("passWord", pwErrorMessageList);
		errorMessageMap.put("passWordConfirm", pwConfirmErrorMessageList);
		
		// フォワード先
		String forwardPath = null;
		
		// 入力チェックでエラーがない場合にのみデータベースでの検索を行い、処理のオーバーヘッドを減少させる
		if (errorMessageMap.get("userId").size() != 0 || errorMessageMap.get("passWord").size() != 0 || errorMessageMap.get("passWordConfirm").size() != 0) {
			// 入力チェックでエラーがある場合（エラーメッセージがある場合）	
			
			// リクエストスコープにユーザID, パスワード, 確認用パスワードを保存
			request.setAttribute("requestUserId", userId);
			request.setAttribute("requestPassWord", passWord);
			request.setAttribute("requestPassWordConfirm", passWordConfirm);
			
			// registerUserForm.jspをフォワード先へ指定する
			forwardPath = "WEB-INF/jsp/registerUserForm.jsp";
		
		} else {
			// 入力チェックでエラーがない場合（エラーメッセージがない場合）
			// 登録済みのユーザか確認する
			CheckUserLogic ChkUsrLgcBo = new CheckUserLogic();
			boolean resultCheckUser = ChkUsrLgcBo.execute(userId);
			
			// 入力されたユーザが登録済みかどうかで処理を分ける
			if (resultCheckUser) {
				// 既に登録されているユーザ情報の場合
				// エラーメッセージ生成
				ArrayList<String> existUserErrorMessage = new ArrayList<>();
				existUserErrorMessage.add("※既に登録されているユーザです");
				errorMessageMap.put("existUser", existUserErrorMessage);
				
				// リクエストスコープにユーザID, パスワード, 確認用パスワードを保存
				request.setAttribute("requestUserId", userId);
				request.setAttribute("requestPassWord", passWord);
				request.setAttribute("requestPassWordConfirm", passWordConfirm);
				
				// registerUserForm.jspをフォワード先へ指定する
				forwardPath = "WEB-INF/jsp/registerUserForm.jsp";
				
			} else {
				// 未登録のユーザ情報の場合
				
				// ユーザを生成
				User user = new User(userId, passWord);
				
				// セッションスコープにユーザ情報を保存
				HttpSession session = request.getSession();
				session.setAttribute("registerUser", user);
				
				// セッションスコープにユーザID, パスワード, 確認用パスワードを保存
				session.setAttribute("sessionUserId", userId);
				session.setAttribute("sessionPassWord", passWord);
				session.setAttribute("sessionPassWordConfirm", passWordConfirm);
				
				// registerUserConfirm.jspをフォワード先へ指定する
				forwardPath = "WEB-INF/jsp/registerUserConfirm.jsp";
			}
		}
		
		
		
		// リクエストスコープにエラーメッセージを保存
		request.setAttribute("errorMessageMap", errorMessageMap);
		
		
		// 指定されたページへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
		
	}

}
