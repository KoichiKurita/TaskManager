/**
 * EditPassWordServlet.java
 * パスワードの変更を処理するコントロールクラス
 * Getリクエストを受けると、パスワード画面とパスワード確認画面を表示する
 * Postリクエストを受けると、入力されたパスワードを検証し、遷移する
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

import model.EditPassWordLogic;
import model.EscapeCharacterLogic;
import model.InputPwCheckLogic;
import model.User;

/**
 * Servlet implementation class EditPassWordServlet
 */
@WebServlet("/EditPassWordServlet")
public class EditPassWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPassWordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * doGetメソッド
	 * リクエストパラメータに応じて、パスワード入力画面とパスワード変更完了画面にフォワードする
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// フォワード先
		String forwardPath = null;
		
		// 動作を決定するリクエストパラメータを取得
		String action = request.getParameter("action");
		
		if (action.equals("input")) {
			// パスワードの入力をリクエストしたとき			
			
			// タスク入力フォーム(editTaskForm.jsp)をフォワード先に指定
			forwardPath = "WEB-INF/jsp/editPassWordForm.jsp";
			
		} else if (action.equals("register")) {
			
			// パスワード登録をリクエストしたとき
			
			// セッションスコープに保存されている新しいパスワードを含んだユーザ情報を取得する
			HttpSession session = request.getSession();
			User newUser = (User)session.getAttribute("newUser");
			
			// 変更されたパスワードを登録する
			EditPassWordLogic edtPWLgcBo = new EditPassWordLogic();
			boolean result = edtPWLgcBo.execute(newUser);
			
			if (result) {
				// パスワードの登録に成功したとき
				
				// セッションスコープに保存されているログインユーザ情報を新しいパスワードのもので更新する
				session.setAttribute("user", newUser);
				
				// editPassWordOK.jspをフォワード先に指定する
				forwardPath = "WEB-INF/jsp/editPassWordOK.jsp";
			} else {
				// パスワードの登録に失敗したとき
				// editPassWordNG.jspをフォワード先に指定
				forwardPath = "WEB-INF/jsp/editPassWordNG.jsp";
			}
		}
		
		// 遷移先へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	/**
	 * doPostメソッド
	 * 入力されたパスワードを検証する。検証結果に応じて遷移先を変える。
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// postされた現在のパスワード, 新しいパスワードを取得
		String currentPassWord = request.getParameter("current-pass-word");
		String newPassWord = request.getParameter("new-pass-word");
		String newPassWordConfirm = request.getParameter("new-pass-word-confirm");
		
		// ユーザID, パスワードに含まれるHTML特殊文字をエスケープする
		EscapeCharacterLogic ecpCharLgcBo = new EscapeCharacterLogic();
		currentPassWord = ecpCharLgcBo.execute(currentPassWord);
		newPassWord = ecpCharLgcBo.execute(newPassWord);
		newPassWordConfirm = ecpCharLgcBo.execute(newPassWordConfirm);
		
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("user");
		
		// 新しいパスワードの入力チェックを行う
		InputPwCheckLogic iptPwChkLgcBo = new InputPwCheckLogic();
		ArrayList<String> CurrentPwErrorMessageList = new ArrayList<>();
		ArrayList<String> newPwErrorMessageList = iptPwChkLgcBo.execute(newPassWord);
		ArrayList<String> newPwConfirmErrorMessageList = iptPwChkLgcBo.execute(newPassWordConfirm);
		
		HashMap<String, ArrayList<String>> errorMessageMap = new HashMap<>();
		
		// 入力された現在のパスワードが正しいかチェックする
		if (!(currentPassWord.equals(loginUser.getPassWord()))){
			// 正しくない場合、エラーメッセージを格納する
			CurrentPwErrorMessageList.add("※現在のパスワードが誤っております。");
		} 
		
		// 新しいパスワードと確認用パスワードが一致しているかチェックを行う
		if (!(newPassWord.equals(newPassWordConfirm))){
			// 正しくない場合、エラーメッセージを格納する
			newPwConfirmErrorMessageList.add("※新しいパスワードが一致しません。");
		}
		
		errorMessageMap.put("currentPassWord", CurrentPwErrorMessageList);
		errorMessageMap.put("newPassWord" ,newPwErrorMessageList);
		errorMessageMap.put("newPassWordConfirm", newPwConfirmErrorMessageList);
		
		
		// フォワード先
		String forwardPath = null;
		
		// パスワードのエラーがない場合にのみデータベースへの接続を行い、処理のオーバーヘッドを減少させる
		if (errorMessageMap.get("currentPassWord").size() != 0 || errorMessageMap.get("newPassWord").size() != 0 || errorMessageMap.get("newPassWordConfirm").size() != 0) {
			// パスワードのエラーがある場合（エラーメッセージがある場合）	
			
			// リクエストスコープに現在のパスワード, 新しいパスワード, 確認用パスワードを保存
			request.setAttribute("requestCurrentPassWord", currentPassWord);
			request.setAttribute("requestNewPassWord", newPassWord);
			request.setAttribute("requestNewPassWordConfirm", newPassWordConfirm);
			
			// editPassWordForm.jspをフォワード先へ指定する
			forwardPath = "WEB-INF/jsp/editPassWordForm.jsp";
		
		} else {
			// 入力チェックでエラーがない場合（エラーメッセージがない場合）
			
			// 新しいパスワードでユーザ情報を作成する
			User newUser = new User(loginUser.getUserId(), newPassWord);
			
			// セッションスコープに新しいユーザ情報を保存
			session.setAttribute("newUser", newUser);
			
			// セッションスコープに現在のパスワード, 新しいパスワード, 確認用パスワードを保存
			session.setAttribute("sessionCurrentPassWord", currentPassWord);
			session.setAttribute("sessionNewPassWord", newPassWord);
			session.setAttribute("sessionNewPassWordConfirm", newPassWordConfirm);
			
			// editPassWordConfirm.jspをフォワード先へ指定する
			forwardPath = "WEB-INF/jsp/editPassWordConfirm.jsp";
			
		}
		
		// リクエストスコープにエラーメッセージを保存
		request.setAttribute("errorMessageMap", errorMessageMap);
		
		// 指定されたページへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
