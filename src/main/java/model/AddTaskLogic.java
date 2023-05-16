/**
 * AddTaskLogic.java
 * タスクを追加するBOクラス
 */
package model;

import dao.TasksDAO;

public class AddTaskLogic {

	/**
	 * executeメソッド
	 * タスクをデータベースに登録する
	 * @param タスク情報
	 * @return 処理結果
	 */
	public boolean execute(Task task) {
		TasksDAO dao = new TasksDAO();
		boolean result = dao.addTask(task);
		return result;
	}
}
