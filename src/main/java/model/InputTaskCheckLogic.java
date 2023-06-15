/**
 * InputTaskCheckLogic.java
 * タスク名、タスク内容の入力チェックを実行するBOクラス
 */
package model;

import java.util.ArrayList;

public class InputTaskCheckLogic {
	
	/**
	 * executeメソッド
	 * 入力されたタスクの入力チェックを行う
	 * @param task 入力されたタスク情報
	 * @return エラーメッセージのArrayList
	 */
	public ArrayList<String> execute(Task task) {
		
		// エラーメッセージのリスト
		ArrayList<String> errorMessages = new ArrayList<>();
		
		// タスク名の文字数をチェックする。要件を満たさない場合は、エラーメッセージを生成する。
		if (task.getTitle().length() > 50) {
			errorMessages.add("※タスク名は50文字以下で入力してください");
		}
		
		return errorMessages;
	}
}
