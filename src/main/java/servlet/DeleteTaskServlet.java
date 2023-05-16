/**
 * DeleteTaskServlet.java
 * タスクの削除を処理するコントロールクラス
 * Getリクエストを受けると、確認画面の表示とタスク削除を実行する
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

import model.DeleteTaskLogic;
import model.GetTaskLogic;
import model.Task;

/**
 * Servlet implementation class DeleteTaskServlet
 */
@WebServlet("/DeleteTaskServlet")
public class DeleteTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * doGetメソッド
	 * リクエストパラメータに応じて、削除の確認画面と削除処理結果画面にフォワードする
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// フォワード先
		String forwardPath = null;
		
		// 動作を決定するリクエストパラメータを取得
		String action = request.getParameter("action");
		
		// セッションスコープ
		HttpSession session = request.getSession();
		
		if (action.equals("confirm")) {
			
			// タスク削除の確認をリクエストしたとき
			
			// 編集対象のタスクのタスクIDをリクエストパラメータから取得する
			int taskId = Integer.parseInt(request.getParameter("taskId"));
			
			// 編集対象のタスクを取得する
			GetTaskLogic getTskLgcBo = new GetTaskLogic();
			Task task = getTskLgcBo.execute(taskId);
			
			// タスクをセッションスコープに保存
			session.setAttribute("task", task);
			
			// タスク削除確認画面(deleteTaskConfirm.jsp)をフォワード先に指定
			forwardPath = "WEB-INF/jsp/deleteTaskConfirm.jsp";
		
		} else if (action.equals("delete")) {
			
			// タスク削除をリクエストした時
			
			// セッションスコープに保存されたタスク情報を取得
			Task deleteTask = (Task)session.getAttribute("task");
			
			// タスクを削除する
			DeleteTaskLogic bo = new DeleteTaskLogic();
			boolean result = bo.execute(deleteTask.getTaskId());
			
			if (result) {
				// タスクの登録に成功したとき
				// deleteTaskOK.jspをフォワード先に指定する
				forwardPath = "WEB-INF/jsp/deleteTaskOK.jsp";
			} else {
				// タスクの登録に失敗したとき
				// deleteTaskNG.jspをフォワード先に指定
				forwardPath = "WEB-INF/jsp/deleteTaskNG.jsp";
			}
		}
		
		// 遷移先へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
