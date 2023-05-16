/**
 * EditTaskLogic.java
 * タスクを編集するBOクラス
 */
package model;

import dao.TasksDAO;

public class EditTaskLogic {

	/**
	 * executeメソッド
	 * タスクを編集する
	 * @param タスク情報
	 * @return 処理結果
	 */
	public boolean execute(Task task) {
		TasksDAO dao = new TasksDAO();
		boolean result = dao.editTask(task);
		return result;
	}
}
