/**
 * EditTaskServlet.java
 * ユーザの編集を処理するコントロールクラス
 * Getリクエストを受けると、ユーザ入力画面と入力確認画面を表示する
 * Postリクエストを受けると、入力されたユーザ情報を検証し、遷移する
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

import model.EditUserLogic;
import model.InputIdPwCheckLogic;
import model.LoginLogic;
import model.User;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * doGetメソッド
	 * リクエストパラメータに応じて、ユーザ入力画面とユーザ登録完了画面にフォワードする
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// フォワード先
		String forwardPath = null;
		
		// 動作を決定するリクエストパラメータの取得
		String action = request.getParameter("action");
		
		if (action.equals("input")) {
			// ユーザの入力をリクエストしたとき
			// ユーザ入力フォーム(editUserForm.jsp)をフォワード先に指定
			forwardPath = "WEB-INF/jsp/editUserForm.jsp";
		
		} else if (action.equals("register")) {
			// ユーザ登録をリクエストしたとき
			// セッションスコープに保存されている変更前のユーザ情報を取得する
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			
			// セッションスコープから変更後のユーザ情報を取得する
			User editedUser = (User)session.getAttribute("editedUser");
			
			// 変更されたユーザを登録する
			EditUserLogic edtUsrLgcBo = new EditUserLogic();
			boolean result = edtUsrLgcBo.execute(user.getUserId(), editedUser);
			
			if (result) {
				// ユーザの変更に成功したとき
				
				// セッションスコープのuserをeditedUserで上書きする
				session.setAttribute("user", editedUser);
				
				// editUserOK.jspをフォワード先に指定する
				forwardPath = "WEB-INF/jsp/editUserOK.jsp";
			} else {
				// ユーザの変更に失敗したとき
				// editUserNG.jspをフォワード先に指定
				forwardPath = "WEB-INF/jsp/editUserNG.jsp";
			}
		}
		
		// 遷移先へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	/**
	 * doPostメソッド
	 * 入力されたユーザ情報を検証する。検証結果に応じて遷移先を変える。
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// postされたユーザ名、パスワードを取得
		String userId = request.getParameter("user-ID");
		String passWord = request.getParameter("pass-word");
		
		// 変更後のユーザ情報を作成
		User editedUser = new User(userId, passWord);
		
		// ユーザ情報の入力チェックを行う
		InputIdPwCheckLogic iptIdPwChkLogicBo = new InputIdPwCheckLogic();
		ArrayList<String> errorMessages = iptIdPwChkLogicBo.execute(editedUser.getUserId(), editedUser.getPassWord());
		
		// フォワード先
		String forwardPath = null;
		
		// 入力チェックでエラーがない場合にのみデータベースでの検索を行い、処理のオーバーヘッドを減少させる
		if (errorMessages.size() != 0) {
			// 入力チェックでエラーがある場合（エラーメッセージがある場合）	
			
			// editUserForm.jspをフォワード先へ指定する
			forwardPath = "WEB-INF/jsp/editUserForm.jsp";
		
		} else {
			// 入力チェックでエラーがない場合（エラーメッセージがない場合）
			// 登録済みのユーザか確認する
			LoginLogic LgnLgcBo = new LoginLogic();
			boolean resultCheckUser = LgnLgcBo.execute(editedUser);
			
			// 入力されたユーザが登録済みかどうかで処理を分ける
			if (resultCheckUser) {
				// 既に登録されているユーザ情報の場合
				// エラーメッセージ生成
				errorMessages.add("※既に登録されているユーザです");				
				
				// editUserForm.jspをフォワード先へ指定する
				forwardPath = "WEB-INF/jsp/editUserForm.jsp";
			
			} else {
				// 未登録のユーザ情報の場合
				
				// editUserConfirm.jspをフォワード先へ指定する
				forwardPath = "WEB-INF/jsp/editUserConfirm.jsp";
			}
		}
		
		// リクエストスコープにエラーメッセージを保存
		request.setAttribute("errorMessages", errorMessages);
		
		// セッションスコープに変更後のユーザ情報を保存
		HttpSession session = request.getSession();
		session.setAttribute("editedUser", editedUser);
		
		// 指定されたページへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
