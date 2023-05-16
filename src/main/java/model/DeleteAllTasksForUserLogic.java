/**
 * DeleteAllTasksForUserLogic.java
 * ユーザ情報の削除を実行するBOクラス
 */
package model;

import dao.TasksDAO;

public class DeleteAllTasksForUserLogic {
	
	/**
	 * executeメソッド
	 * ユーザが保有する全タスクを削除する
	 * @param userId ユーザID
	 * @return 削除結果
	 */
	public boolean execute(String userId) {
		TasksDAO dao = new TasksDAO();
		boolean result = dao.deleteAllTasksForUser(userId);
		return result;
	}
}
