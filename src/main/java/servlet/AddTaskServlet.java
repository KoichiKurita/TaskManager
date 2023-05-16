/**
 * AddTaskServlet.java
 * タスクの追加を処理するコントロールクラス
 * Getリクエストを受けると、タスク入力画面と入力確認画面を表示する
 * Postリクエストを受けると、入力されたタスク情報を検証し、遷移する
 */
package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AddTaskLogic;
import model.InputTaskCheckLogic;
import model.Task;
import model.User;

/**
 * Servlet implementation class AddTaskServlet
 */
@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * doGetメソッド
	 * リクエストパラメータに応じて、タスク入力画面とタスク登録完了画面にフォワードする
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// フォワード先
		String forwardPath = null;
		
		// 動作を決定するリクエストパラメータを取得
		String action = request.getParameter("action");
		
		if (action.equals("input")) {
			
			// タスク入力をリクエストしたとき
			// タスク入力フォーム(addTaskForm.jsp)をフォワード先に指定する
			forwardPath = "WEB-INF/jsp/addTaskForm.jsp";
			
		} else if (action.equals("register")){
			
			// タスク登録をリクエストしたとき
			// セッションスコープに保存されたタスク情報を取得
			HttpSession session = request.getSession();
			Task registerTask = (Task)session.getAttribute("task");
			
			// タスクを登録
			AddTaskLogic addTskLgcBo = new AddTaskLogic();
			boolean result = addTskLgcBo.execute(registerTask);
			
			if (result) {
				// タスクの登録に成功したとき
				// addTaskOK.jspをフォワード先に指定する
				forwardPath = "WEB-INF/jsp/addTaskOK.jsp";
			} else {
				// タスクの登録に失敗したとき
				// addTaskNG.jspをフォワード先に指定
				forwardPath = "WEB-INF/jsp/addTaskNG.jsp";
			}
		}
		
		// 遷移先へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	/**
	 * doPostメソッド
	 * 入力されたタスク情報を検証する。検証結果に応じて遷移先を変える。
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// postされたタスク名、タスク内容、期限、優先度を取得
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String strDeadline = request.getParameter("deadline");
		String priority = request.getParameter("priority");
		
		// deadlineをDate型へ変換
		Date dateDeadline = null;
		try {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			dateDeadline = f.parse(strDeadline);
		} catch (Exception e) {
			System.out.println("文字列からDate型への変換で例外が発生");
			e.printStackTrace();
		}
		
		// セッションスコープに保存されたユーザ情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("user");
		
		// タスクを生成
		Task task = new Task(loginUser.getUserId(), title, contents, dateDeadline, priority);
		
		// タスク名の入力チェックを行う
		InputTaskCheckLogic iptTskChkBo = new InputTaskCheckLogic();
		ArrayList<String> errorMessages = iptTskChkBo.execute(task);
		
		// フォワード先
		String forwardPath = null;
		
		if (errorMessages.size() != 0) {
			// 入力チェックでエラーがある場合（エラーメッセージがある場合）
			// リクエストスコープにエラーメッセージを保存
			request.setAttribute("errorMessages", errorMessages);
			
			// addTaskForm.jspをフォワード先へ指定
			forwardPath = "WEB-INF/jsp/addTaskForm.jsp";
		} else {
			// 入力チェックでエラーがない場合（エラーメッセージがない場合）
			// addTaskConfirm.jspをフォワード先に指定
			forwardPath = "WEB-INF/jsp/addTaskConfirm.jsp";
		}
		
		// セッションスコープにタスク情報を保存
		session.setAttribute("task", task);
		
		// 遷移先へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
