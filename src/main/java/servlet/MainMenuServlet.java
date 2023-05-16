/**
 * MainMenuServlet.java
 * Getリクエストを受けると、ログインしているユーザの有無を確認し、メインメニューを表示するコントローラクラス
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
 * Servlet implementation class MainMenuServlet
 */
@WebServlet("/MainMenuServlet")
public class MainMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * コンストラクタ
     * @see HttpServlet#HttpServlet()
     */
    public MainMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * doGetメソッド
	 * ログインしているユーザを調べ、存在する場合はメインメニュー(mainMenu.jsp)へフォワードする
	 * 存在しない場合は、トップページ(TopPageServlet.java)へリダイレクトする
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("user");
		
		// ログインしている場合、タスク一覧を取得し、セッションスコープに保存する
		GetAllTasksLogic bo = new GetAllTasksLogic();
		List<Task> taskList = bo.execute(loginUser.getUserId());
		session.setAttribute("taskList", taskList);
		
		// mainMenu.jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mainMenu.jsp");
		dispatcher.forward(request, response);
	}

}
