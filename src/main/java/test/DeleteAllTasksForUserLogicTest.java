/**
 * DeleteAllTasksForUserLogicTest.java
 * DeleteAllTasksForUserLogicの処理のテストを行う
 */
package test;

import model.DeleteAllTasksForUserLogic;

public class DeleteAllTasksForUserLogicTest {

	/**
	 *　メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
//		testDeleteAllTasksForUserLogicOK();
		testDeleteAllTasksForUserLogicNG();
	}
	
	
	/**
	 * testDeleteAllTasksForUserLogicOKメソッド
	 * ユーザの保有する全タスクを削除可能かテストする
	 */
	public static void testDeleteAllTasksForUserLogicOK() {
		DeleteAllTasksForUserLogic bo = new DeleteAllTasksForUserLogic();
		boolean result = bo.execute("foo");
		
		if (result) {
			System.out.println("testDeleteAllTasksForUserLogicOK:成功しました");
		} else {
			System.out.println("testDeleteAllTasksForUserLogicOK:失敗しました");
		}
	}
	
	
	/**
	 * testDeleteAllTasksForUserLogicNGメソッド
	 * タスクが未登録のユーザや未登録のユーザに対して、タスクの削除を実行し、タスクが削除されないことを確認する
	 */
	public static void testDeleteAllTasksForUserLogicNG() {
		DeleteAllTasksForUserLogic bo = new DeleteAllTasksForUserLogic();
		boolean result = bo.execute("bar30");
		
		if (!result) {
			System.out.println("testDeleteAllTasksForUserLogicNG:成功しました");
		} else {
			System.out.println("testDeleteAllTasksForUserLogicNG:失敗しました");
		}
	}
}
