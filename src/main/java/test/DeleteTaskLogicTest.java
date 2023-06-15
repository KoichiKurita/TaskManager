/**
 * DeleteTaskLogicTest.java
 * DeleteTaskLogicの処理のテストを行う
 */
package test;

import model.DeleteTaskLogic;

public class DeleteTaskLogicTest {
	
	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		testDeleteTaskLogicOK();
		testDeleteTaskLogicNG();
	}
	
	
	/**
	 * testDeleteTaskLogicOKメソッド
	 * タスクを削除可能かテストする
	 */
	public static void testDeleteTaskLogicOK() {
		DeleteTaskLogic bo = new DeleteTaskLogic();
		boolean result = bo.execute(13);
		
		if (result) {
			System.out.println("testDeleteTaskLogicOK:成功しました");
		} else {
			System.out.println("testDeleteTaskLogicOK:失敗しました");
		}
	}
	
	
	/**
	 * testDeleteTaskLogicNGメソッド
	 * データベースに登録されていないタスクの削除を実行した場合に、タスクの削除が実行されないことを確認する
	 */
	public static void testDeleteTaskLogicNG() {
		DeleteTaskLogic bo = new DeleteTaskLogic();
		boolean result = bo.execute(20);
		
		if (!result) {
			System.out.println("testDeleteTaskLogicNG:成功しました");
		} else {
			System.out.println("testDeleteTaskLogicNG:失敗しました");
		}
	}
}
