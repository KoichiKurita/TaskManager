/**
 * EditTaskServlet.java
 * タスクの編集を処理するコントロールクラス
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

import model.EditTaskLogic;
import model.EscapeCharacterLogic;
import model.GetTaskLogic;
import model.InputTaskCheckLogic;
import model.Task;
import model.User;

/**
 * Servlet implementation class EditTaskServlet
 */
@WebServlet("/EditTaskServlet")
public class EditTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTaskServlet() {
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
		
		// セッションスコープに保存されているタスクを取得する
		HttpSession session = request.getSession();
		Task task = (Task)session.getAttribute("task");
		
		if (action.equals("input")) {
			// タスクの入力をリクエストしたとき			
			
			if (task == null) {
				
				// セッションスコープにタスクが存在しない場合
				// （初めて、タスクの編集画面にアクセスした場合）
				
				// 編集対象のタスクのタスクIDをリクエストパラメータから取得する
				int taskId = Integer.parseInt(request.getParameter("taskId"));
				
				// 編集対象のタスクを取得する
				GetTaskLogic getTskLgcBo = new GetTaskLogic();
				task = getTskLgcBo.execute(taskId);
				
				// タスクをセッションスコープに保存
				session.setAttribute("task", task);
				
			}
			
			// タスク入力フォーム(editTaskForm.jsp)をフォワード先に指定
			forwardPath = "WEB-INF/jsp/editTaskForm.jsp";
			
		} else if (action.equals("register")) {
			
			// タスク登録をリクエストしたとき
			
			// 編集されたタスクを登録する
			EditTaskLogic edtTskLgcBo = new EditTaskLogic();
			boolean result = edtTskLgcBo.execute(task);
			
			if (result) {
				// タスクの登録に成功したとき
				// editTaskOK.jspをフォワード先に指定する
				forwardPath = "WEB-INF/jsp/editTaskOK.jsp";
			} else {
				// タスクの登録に失敗したとき
				// editTaskNG.jspをフォワード先に指定
				forwardPath = "WEB-INF/jsp/editTaskNG.jsp";
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
		
		// セッションスコープから編集前のタスクを取得
		HttpSession session = request.getSession();
		Task beforeEditTask = (Task)session.getAttribute("task");
		
		// postされたタスク名、タスク内容、期限、優先度を取得
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String strDeadline = request.getParameter("deadline");
		String priority = request.getParameter("priority");
		
		// タイトル, コンテンツに含まれるHTML特殊文字をエスケープする
		EscapeCharacterLogic ecpCharLgcBo = new EscapeCharacterLogic();
		title = ecpCharLgcBo.execute(title);
		contents = ecpCharLgcBo.execute(contents);
		
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
		User loginUser = (User)session.getAttribute("user");
		
		// タスクを生成
		Task newTask = new Task(beforeEditTask.getTaskId(), loginUser.getUserId(), title, contents, dateDeadline, priority);
		
		// タスクの入力チェックを行う
		InputTaskCheckLogic iptTskChkBo = new InputTaskCheckLogic();
		ArrayList<String> errorMessages = iptTskChkBo.execute(newTask);
		
		// フォワード先
		String forwardPath = null;
		
		if (errorMessages.size() != 0) {
			// 入力チェックでエラーがある場合（エラーメッセージがある場合）
			// リクエストスコープにエラーメッセージを保存
			request.setAttribute("errorMessages", errorMessages);
			
			// editTaskForm.jspをフォワード先へ指定
			forwardPath = "WEB-INF/jsp/editTaskForm.jsp";
		} else {
			// 入力チェックでエラーがない場合（エラーメッセージがない場合）
			// editTaskConfirm.jspをフォワード先に指定
			forwardPath = "WEB-INF/jsp/editTaskConfirm.jsp";
		}
		
		// セッションスコープにタスク情報を保存
		session.setAttribute("task", newTask);
		
		// 遷移先へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
