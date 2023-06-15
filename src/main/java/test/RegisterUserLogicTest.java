/**
 * RegisterUserLogicTest.java
 * RegisterUserLogicの処理のテストを行う
 */
package test;

import model.RegisterUserLogic;
import model.User;

public class RegisterUserLogicTest {

	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		testRegisterUserLogicOK();
		testRegisterUserLogicNG();
	}
	
	/**
	 * testRegisterUserLogicOKメソッド
	 * 新規登録処理のテストを行う
	 */
	public static void testRegisterUserLogicOK() {
		User user = new User("foofoofoo", "erkd4657r856ghda");
		RegisterUserLogic bo = new RegisterUserLogic();
		boolean result = bo.execute(user);
		
		if (result) {
			System.out.println("testRegisterUserLogicOK:成功しました");
		} else {
			System.out.println("testRegisterUserLogicOK:失敗しました");
		}
	}
	
	
	/**
	 * testRegisterUserLogicNGメソッド
	 * 既に登録されているアカウントに対して、新規登録処理のテストを行う
	 */
	public static void testRegisterUserLogicNG() {
		User user = new User("kurita", "abcd1234");
		RegisterUserLogic bo = new RegisterUserLogic();
		boolean result = bo.execute(user);
		
		if (!result) {
			System.out.println("testRegisterUserLogicNG:成功しました");
		} else {
			System.out.println("testRegisterUserLogicNG:失敗しました");
		}
	
	}

}
