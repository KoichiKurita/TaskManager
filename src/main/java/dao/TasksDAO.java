/**
 * TasksDAO.java
 * ユーザー情報のデータベースへの登録・取得を行うDAOモデルクラス
 */

// java.util.Dateからjava.sql.Dateへの変換について
// 参考サイト：https://www.delftstack.com/ja/howto/java/java-util-date-to-java-sql-date/

package dao;

import static contents.AuthenticationInfo.*;	// DB認証情報

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Task;

public class TasksDAO {
	
	/**
	 * getAllTasksメソッド
	 * ユーザ情報を使用しタスク一覧をデータベースから取得する
	 * @param ユーザ情報
	 * @return データベースから取得したタスク一覧
	 */
	public List<Task> getAllTasks(String userId) {
		
		List<Task> taskList = new ArrayList<>();
		
		// JDBCドライバ読み込み
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// データベースに接続（データベースの切断はプログラム終了時に自動で実行される）
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// SELECT文を準備
			String sql = "SELECT * FROM tasks WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// プレースホルダーに入れる値を挿入してSELECT文を完成させる(SQLインジェクション対策)
			pStmt.setString(1, userId);
			
			// SELECT文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				// ユーザ情報の検索結果がある場合、Taskインスタンスを生成し、ArrayListに格納する
				Task task = new Task(rs.getInt("task_id"), 
						rs.getString("user_id"), 
						rs.getString("title"), 
						rs.getString("contents"), 
						new java.util.Date(rs.getDate("deadline").getTime()), 
						rs.getString("priority"));
				taskList.add(task);
			}
			
		} catch (SQLException e) { // データベース処理中に例外が発生した場合、処理終了
			System.out.println("SQLExceptionが発生しました");
			System.out.println("処理を終了します");
			e.printStackTrace();
			return null;
		}
		
		return taskList;
	}
	
	
	/**
	 * getTaskメソッド
	 * 1件のタスク情報を取得する
	 * @param taskId タスクID
	 * @return 1件のタスク情報
	 */
	public Task getTask(int taskId) {
		
		Task task = null;
		
		// JDBCドライバ読み込み
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// データベースに接続（データベースの切断はプログラム終了時に自動で実行される）
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// SELECT文を準備
			String sql = "SELECT * FROM tasks WHERE task_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// プレースホルダーに入れる値を挿入してSELECT文を完成させる(SQLインジェクション対策)
			pStmt.setInt(1, taskId);
			
			// SELECT文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				// ユーザ情報の検索結果がある場合、Taskインスタンスを生成し、ArrayListに格納する
				task = new Task(rs.getInt("task_id"), 
						rs.getString("user_id"), 
						rs.getString("title"), 
						rs.getString("contents"), 
						new java.util.Date(rs.getDate("deadline").getTime()), 
						rs.getString("priority"));
			}
			
		} catch (SQLException e) { // データベース処理中に例外が発生した場合、処理終了
			System.out.println("SQLExceptionが発生しました");
			System.out.println("処理を終了します");
			e.printStackTrace();
			return null;
		}
		
		return task;
	}
	
	
	/**
	 * addTaskメソッド
	 * 1件分のタスクをデータベースに登録する
	 * @param 1件分のタスク情報
	 * @return 登録結果
	 */
	public boolean addTask(Task task) {
		
		// JDBCドライバ読み込み
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// データベースに接続（データベースの切断はプログラム終了時に自動で実行される）
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// INSERT文を準備
			String sql = "INSERT INTO tasks(user_id, title, contents, deadline, priority) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// プレースホルダーに入れる値を挿入してINSERT文を完成させる
			pStmt.setString(1, task.getUserId());
			pStmt.setString(2, task.getTitle());
			pStmt.setString(3, task.getContents());
			pStmt.setDate(4, new java.sql.Date(task.getDeadline().getTime()));
			pStmt.setString(5, task.getPriority());
			
			// INSERT文を実行
			int result = pStmt.executeUpdate();
			
			if(result != 1) {	// 追加したデータが1件でない場合、登録処理終了
				System.out.println("データベースへの登録件数が1件ではありませんでした");
				System.out.println("処理を終了します");
				return false;
			}
			
		} catch (SQLException e) {	// データベース処理中に例外が発生した場合、処理終了
			System.out.println("SQLExceptionが発生しました");
			System.out.println("処理を終了します");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * editTaskメソッド
	 * 既に登録されているタスクのタイトル、詳細、期限、優先度を修正する
	 * @param 修正後のタスク情報
	 * @return 修正結果
	 */
	public boolean editTask(Task task) {
		
		// JDBCドライバ読み込み
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// データベースに接続（データベースの切断はプログラム終了時に自動で実行される）
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// UPDATE文を準備
			String sql = "UPDATE tasks SET title = ?, contents = ?, deadline = ?, priority = ? WHERE task_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// プレースホルダーに入れる値を挿入してUPDATE文を完成させる
			pStmt.setString(1, task.getTitle());
			pStmt.setString(2, task.getContents());
			pStmt.setDate(3, new java.sql.Date(task.getDeadline().getTime()));
			pStmt.setString(4, task.getPriority());
			pStmt.setInt(5, task.getTaskId());
			
			// UPDATE文を実行
			int result = pStmt.executeUpdate();
			
			if(result != 1) {	// 追加したデータが1件でない場合、登録処理終了
				System.out.println("データベースへの変更件数が1件ではありませんでした");
				System.out.println("処理を終了します");
				return false;
			}
			
		} catch (SQLException e) {	// データベース処理中に例外が発生した場合、処理終了
			System.out.println("SQLExceptionが発生しました");
			System.out.println("処理を終了します");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * deleteTaskメソッド
	 * データベースから1件分のタスクを削除する
	 * @param 削除対象のタスクのタスクID
	 * @return 削除結果
	 */
	public boolean deleteTask(int taskId) {
		
		// JDBCドライバ読み込み
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// データベースに接続（データベースの切断はプログラム終了時に自動で実行される）
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// DELETE文を準備
			String sql = "DELETE FROM tasks WHERE task_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// プレースホルダーに入れる値を挿入してDELETE文を完成させる
			pStmt.setInt(1, taskId);
			
			// DELETE文を実行
			int result = pStmt.executeUpdate();
			
			if(result != 1) {	// 削除したデータが1件でない場合、登録処理終了
				System.out.println("データベースへの削除件数が1件ではありませんでした");
				System.out.println("処理を終了します");
				return false;
			}
			
		} catch (SQLException e) {	// データベース処理中に例外が発生した場合、処理終了
			System.out.println("SQLExceptionが発生しました");
			System.out.println("処理を終了します");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * deleteTasksForUserメソッド
	 * ユーザが保有する全タスクを削除する
	 * @param userId ユーザID
	 * @return　削除結果
	 */
	public boolean deleteAllTasksForUser(String userId) {
		
		// JDBCドライバ読み込み
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// データベースに接続（データベースの切断はプログラム終了時に自動で実行される）
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// DELETE文を準備
			String sql = "DELETE FROM tasks WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// プレースホルダーに入れる値を挿入してDELETE文を完成させる
			pStmt.setString(1, userId);
			
			// DELETE文を実行
			int result = pStmt.executeUpdate();
			
			if(result < 1) {	// 削除したデータが1件未満の場合、登録処理終了
				System.out.println("データベースへの削除件数が1件未満でした");
				System.out.println("処理を終了します");
				return false;
			}
			
		} catch (SQLException e) {	// データベース処理中に例外が発生した場合、処理終了
			System.out.println("SQLExceptionが発生しました");
			System.out.println("処理を終了します");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
