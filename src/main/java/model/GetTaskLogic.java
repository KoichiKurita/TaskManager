/**
 * GetTaskLogic.java
 * 1件のタスクを取得するBOクラス
 */
package model;

import dao.TasksDAO;

public class GetTaskLogic {
	
	/**
	 * executeメソッド
	 * 1件タスクの取得を行う
	 * @param タスクID
	 * @return 1件分のタスク
	 */
	public Task execute(int taskId) {
		TasksDAO dao = new TasksDAO();
		Task task = dao.getTask(taskId);
		return task;
	}
}
