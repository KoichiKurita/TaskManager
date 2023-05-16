/**
 * LoginLogicTest.java
 * LoginLogicの処理のテストを行う
 */
package test;

import model.LoginLogic;
import model.User;

public class LoginLogicTest {
	
	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		testLoginLogicOK();
		testLoginLogicNG();
	}
	
	
	/**
	 * testLoginLogicOKメソッド
	 * 登録されているアカウントに対して、ログイン処理のテストを行う
	 */
	public static void testLoginLogicOK() {
		User user = new User("hoge", "wxyz0987");
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(user);
		
		if (result) {
			System.out.println("testLoginLogicOK:成功しました");
		} else {
			System.out.println("testLoginLogicOK:失敗しました");
		}
	}
	
	
	/**
	 * testLoginLogicNGメソッド
	 * 登録されていないアカウントに対して、ログイン処理のテストを行う
	 */
	public static void testLoginLogicNG() {
		User user = new User("hoge", "wxyz09876");
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(user);
		
		if (!result) {
			System.out.println("testLoginLogicNG:成功しました");
		} else {
			System.out.println("testLoginLogicNG:失敗しました");
		}
	}
}
