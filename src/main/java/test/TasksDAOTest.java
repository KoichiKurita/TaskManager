/**
 * UsersDAOTest.java
 * UsersDAOの処理のテストを行う
 */
package test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.TasksDAO;
import model.Task;

public class TasksDAOTest {
	
	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
//		testGetAllTasksOK();
//		testGetAllTasksNG();
//		testGetTaskOK();
//		testGetTaskNG();
//		testAddTask();
//		testEditTaskOK();
//		testEditTaskNG();
//		testDeleteTaskOK();
//		testDeleteTaskNG();
		testDeleteAllTasksForUserOK();
		testDeleteAllTasksForUserNG();
	}
	
	
	/**
	 * testGetAllTasksOKメソッド
	 * 既にデータベースに登録されているタスク情報について検索可能かテストする
	 */
	public static void testGetAllTasksOK() {
		
		TasksDAO dao = new TasksDAO();
		List<Task> resultList = dao.getAllTasks("Kurita");
		
		boolean resultCheckFlag1, resultCheckFlag2;	// 各タスクのチェック結果格納変数
		
		int resultList0TaskId = resultList.get(0).getTaskId();
		String resultList0UserId = resultList.get(0).getUserId();
		String resultList0Title = resultList.get(0).getTitle();
		String resultList0Contents = resultList.get(0).getContents();
		Date resultList0Deadline = resultList.get(0).getDeadline();
		String resultList0Priority = resultList.get(0).getPriority();
		
		int resultList1TaskId = resultList.get(1).getTaskId();
		String resultList1UserId = resultList.get(1).getUserId();
		String resultList1Title = resultList.get(1).getTitle();
		String resultList1Contents = resultList.get(1).getContents();
		Date resultList1Deadline = resultList.get(1).getDeadline();
		String resultList1Priority = resultList.get(1).getPriority();
		
		
		// 比較用日付
		Calendar cdate1 = Calendar.getInstance();
		cdate1.set(2023, 4, 18, 0, 0, 0);	// CalenderクラスのMonthは0(1月)～11(12月)になる
		cdate1.clear(Calendar.MILLISECOND);	// ミリ秒以下は削除
		Date ddate1 = cdate1.getTime();
		
		Calendar cdate2 = Calendar.getInstance();
		cdate2.set(2023, 4, 18, 0, 0, 0);
		cdate2.clear(Calendar.MILLISECOND);
		Date ddate2 = cdate2.getTime();
		
		
		if (resultList0TaskId == 1 &&
				resultList0UserId.equals("Kurita") &&
				resultList0Title.equals("Javaポートフォリオ作成") &&
				resultList0Contents.equals("就活向けにJavaを使用したWebアプリケーションを作成する") && 
				resultList0Deadline.compareTo(ddate1) == 0 &&
				resultList0Priority.equals("high")) {
			
			System.out.println("1つ目のタスクの取得:成功しました");
			resultCheckFlag1 = true;
			
		} else {
			System.out.println("1つ目のタスクの取得:失敗しました");
			resultCheckFlag1 = false;
		}
		
		
		if (resultList1TaskId == 2 &&
				resultList1UserId.equals("Kurita") &&
				resultList1Title.equals("応募書類作成") &&
				resultList1Contents.equals("履歴書と職務経歴書を作成する") && 
				resultList1Deadline.compareTo(ddate2) == 0 &&
				resultList1Priority.equals("high")) {
			
			
			System.out.println("2つ目のタスクの取得:成功しました");
			resultCheckFlag2 = true;
			
		} else {
			System.out.println("2つ目のタスクの取得:失敗しました");
			resultCheckFlag2 = false;
		}
		
		
		if (resultCheckFlag1 && resultCheckFlag2) {
			System.out.println("testGetAllTasksOK:成功しました");
		} else {
			System.out.println("testGetAllTasksOK:失敗しました");
		}
		
	}
	
	
	/**
	 * testGetAllTasksNGメソッド
	 * データベースに登録されていないタスク情報について検索結果をテストする
	 */
	public static void testGetAllTasksNG() {
		TasksDAO dao = new TasksDAO();
		List<Task> resultList = dao.getAllTasks("Kurata");
		
		if (resultList.size() == 0) {
			System.out.println("testGetAllTasksNG:成功しました");
		} else {
			System.out.println("testGetAllTasksNG:失敗しました");
		}
	}
	
	
	/**
	 * testGetTaskOKメソッド
	 * 既にデータベースに登録されている1件分のタスク情報について検索可能かテストする
	 */
	public static void testGetTaskOK() {
		TasksDAO dao = new TasksDAO();
		Task task = dao.getTask(1);
		
		// 比較用日付
		Calendar cdate1 = Calendar.getInstance();
		cdate1.set(2023, 4, 18, 0, 0, 0);	// CalenderクラスのMonthは0(1月)～11(12月)になる
		cdate1.clear(Calendar.MILLISECOND);	// ミリ秒以下は削除
		Date ddate1 = cdate1.getTime();
		
		if (task.getUserId().equals("Kurita") &&
				task.getTitle().equals("Javaポートフォリオ作成") &&
				task.getContents().equals("就活向けにJavaを使用したWebアプリケーションを作成する") && 
				task.getDeadline().compareTo(ddate1) == 0 &&
				task.getPriority().equals("high")) {
			
			System.out.println("testGetTaskOK:成功しました");
			
		} else {
			System.out.println("testGetTaskOK:失敗しました");
		}
	}
	
	
	/**
	 * testGetTaskNGメソッド
	 * データベースに登録されていないタスク情報の検索結果をテストする
	 */
	public static void testGetTaskNG() {
		TasksDAO dao = new TasksDAO();
		Task task = dao.getTask(50);
		
		if (task == null) {
			System.out.println("testGetTaskNG:成功しました");
		} else {
			System.out.println("testGetTaskNG:失敗しました");
		}
	}
	
	
	/**
	 * testAddTaskメソッド
	 * タスクの追加を実行する
	 */
	public static void testAddTask() {
		TasksDAO dao = new TasksDAO();
		
		Calendar cdate1 = Calendar.getInstance();
		cdate1.set(2023, 4, 31, 0, 0, 0);
		Date ddate1 = cdate1.getTime();
		
		Task task = new Task(8, "kurita", "申請書作成", "交通費申請書作成", ddate1, "middle");
		boolean result = dao.addTask(task);
		
		if (result) {
			System.out.println("testAddTask:成功しました");
		} else {
			System.out.println("testAddTask:失敗しました");
		}
	}
	
	
	/**
	 * testEditTaskOKメソッド
	 * タスクの修正を実行する
	 */
	public static void testEditTaskOK() {
		TasksDAO dao = new TasksDAO();
		
		Calendar cdate1 = Calendar.getInstance();
		cdate1.set(2023, 8, 30, 0, 0, 0);
		Date ddate1 = cdate1.getTime();
		
		Task task = new Task(5, "foo", "ワード作成", "打ち合わせで使用するデータ集計作業し文書作成", ddate1, "high");
		boolean result = dao.editTask(task);
		
		if (result) {
			System.out.println("testEditTaskOK:成功しました");
		} else {
			System.out.println("testEditTaskOK:失敗しました");
		}
	}
	
	
	/**
	 * testEditTaskNGメソッド
	 * データベースに登録されていないタスクの修正を実行した場合に、処理が異常終了するか確認する
	 */
	public static void testEditTaskNG() {
		TasksDAO dao = new TasksDAO();
		
		Calendar cdate1 = Calendar.getInstance();
		cdate1.set(2023, 8, 30, 0, 0, 0);
		Date ddate1 = cdate1.getTime();
		
		Task task = new Task(50, "foo", "表作成", "打ち合わせで使用するデータ集計作業", ddate1, "high");
		boolean result = dao.editTask(task);
		
		if (!result) {
			System.out.println("testEditTaskNG:成功しました");
		} else {
			System.out.println("testEditTaskNG:失敗しました");
		}
	}
	
	
	/**
	 * testDeleteTaskOKメソッド
	 * タスクの削除を実行する
	 */
	public static void testDeleteTaskOK() {
		TasksDAO dao = new TasksDAO();
		boolean result = dao.deleteTask(5);
		
		if (result) {
			System.out.println("testDelteTaskOK:成功しました");
		} else {
			System.out.println("testDelteTaskOK:失敗しました");
		}
	}
	
	
	/**
	 * testDeleteTaskNGメソッド
	 * データベースに登録されていないタスクの削除を実行した場合に、処理が異常終了するか確認する
	 */
	public static void testDeleteTaskNG() {
		TasksDAO dao = new TasksDAO();
		boolean result = dao.deleteTask(60);
		
		if (!result) {
			System.out.println("testDelteTaskNG:成功しました");
		} else {
			System.out.println("testDelteTaskNG:失敗しました");
		}
	}
	
	
	/**
	 * testDeleteAllTasksForUserOKメソッド
	 * ユーザの保有する全タスクの削除のテストを実行する
	 */
	public static void testDeleteAllTasksForUserOK() {
		TasksDAO dao = new TasksDAO();
		boolean result = dao.deleteAllTasksForUser("bar");
		
		if (result) {
			System.out.println("testDeleteAllTaskForUserOK:成功しました");
		} else {
			System.out.println("testDeleteAllTaskForUserOK:失敗しました");
		}
	}
	
	
	/**
	 * testDeleteAllTasksForUserNGメソッド
	 * タスクが未登録のユーザに対して、そのユーザの全タスクの削除のテストを実行する
	 */
	public static void testDeleteAllTasksForUserNG() {
		TasksDAO dao = new TasksDAO();
		boolean result = dao.deleteAllTasksForUser("foofoo");
		
		if (!result) {
			System.out.println("testDeleteAllTaskForUserNG:成功しました");
		} else {
			System.out.println("testDeleteAllTaskForUserNG:失敗しました");
		}
	}
}
