/**
 * DeleteTaskLogic.java
 * タスクを削除するBOクラス
 */
package model;

import dao.TasksDAO;

public class DeleteTaskLogic {

	/**
	 * executeメソッド
	 * タスクを削除する
	 * @param タスク情報
	 * @return 処理結果
	 */
	public boolean execute(int taskId) {
		TasksDAO dao = new TasksDAO();
		boolean result = dao.deleteTask(taskId);
		return result;
	}
}
