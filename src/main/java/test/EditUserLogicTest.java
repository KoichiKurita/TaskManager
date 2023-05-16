/**
 * EditUserLogicTest.java
 * EditUserLogicの処理のテストを行う
 */
package test;

import model.EditUserLogic;
import model.User;

public class EditUserLogicTest {

	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		testEditUserLogicOK();
		testEditUserLogicNG();
	}
	
	
	/**
	 * testEditUserLogicOKメソッド
	 * ユーザを編集可能かテストする
	 */
	public static void testEditUserLogicOK() {
		User user = new User("hoge2", "aaa111***");
		EditUserLogic bo = new EditUserLogic();
		boolean result = bo.execute("hoge", user);
		
		if (result) {
			System.out.println("testEditUserLogicOK:成功しました");
		} else {
			System.out.println("testEditUserLogicOK:失敗しました");
		}
	}
	
	
	/**
	 * testEditUserLogicNGメソッド
	 * データベースに登録されていないユーザに対して、ユーザ情報の変更を実行したときに、ユーザ情報が変更されないかテストする
	 */
	public static void testEditUserLogicNG() {
		User user = new User("test4", "aaa111***");
		EditUserLogic bo = new EditUserLogic();
		boolean result = bo.execute("test3", user);
		
		if (!result) {
			System.out.println("testEditUserLogicNG:成功しました");
		} else {
			System.out.println("testEditUserLogicNG:失敗しました");
		}
	}
}
