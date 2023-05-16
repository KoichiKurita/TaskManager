/**
 * DeleteUserLogicTest.java
 * DeleteUserLogicの処理のテストを行う
 */
package test;

import model.DeleteUserLogic;

public class DeleteUserLogicTest {
	
	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		testDeleteUserLogicOK();
		testDeleteUserLogicNG();
	}
	
	
	/**
	 * testDeleteUserLogicOKメソッド
	 * ユーザを削除可能かテストする
	 */
	public static void testDeleteUserLogicOK() {
		DeleteUserLogic bo = new DeleteUserLogic();
		boolean result = bo.execute("hogehoge2");
		
		if (result) {
			System.out.println("testDeleteUserLogicOK:成功しました");
		} else {
			System.out.println("testDeleteUserLogicOK:失敗しました");
		}
	}
	
	
	/**
	 * testDeleteUserLogicNGメソッド
	 * データベースに登録されていないユーザに対して、ユーザ情報の削除を実行したときに、ユーザ情報が削除されないかテストする
	 */
	public static void testDeleteUserLogicNG() {
		DeleteUserLogic bo = new DeleteUserLogic();
		boolean result = bo.execute("hogehoge4");
		
		if (!result) {
			System.out.println("testDeleteUserLogicNG:成功しました");
		} else {
			System.out.println("testDeleteUserLogicNG:失敗しました");
		}
	}
}
