/**
 * GetAllTasksLogic.java
 * タスク一覧を取得するBOクラス
 */
package model;

import java.util.List;

import dao.TasksDAO;

public class GetAllTasksLogic {
	
	/**
	 * executeメソッド
	 * タスク一覧の取得を行う
	 * @param ユーザID
	 * @return タスク一覧
	 */
	public List<Task> execute(String userId) {
		TasksDAO dao = new TasksDAO();
		List<Task> taskList = dao.getAllTasks(userId);
		return taskList;
	}
}
