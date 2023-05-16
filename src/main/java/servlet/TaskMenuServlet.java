/**
 * TaskMenuServlet.java
 * Getリクエストを受けると、タスク管理メニューを表示するコントローラクラス
 */
package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetAllTasksLogic;
import model.Task;
import model.User;

/**
 * Servlet implementation class TaskMenuServlet
 */
@WebServlet("/TaskMenuServlet")
public class TaskMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * コンストラクタ
     * @see HttpServlet#HttpServlet()
     */
    public TaskMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * doGetメソッド
	 * タスクメニュー(taskMenu.jsp)へフォワードする
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("user");
		
		// タスク一覧を取得し、セッションスコープに保存する
		GetAllTasksLogic bo = new GetAllTasksLogic();
		List<Task> taskList = bo.execute(loginUser.getUserId());
		session.setAttribute("taskList", taskList);
		
		// taskMenu.jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/taskMenu.jsp");
		dispatcher.forward(request, response);
	}

}
