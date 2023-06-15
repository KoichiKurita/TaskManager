/**
 * UsersDAOTest.java
 * UsersDAOの処理のテストを行う
 */
package test;

import dao.UsersDAO;
import model.User;

public class UsersDAOTest {

	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		testIsUserOK();			// ユーザが見つかった場合のテスト
		testIsUserNG();			// ユーザが見つからなかった場合のテスト
		testCreateUserOK();		// ユーザの新規登録を実行
		testCreateUserNG();		// 既に登録されているユーザの新規登録を実行
		testEditUserOK();		// ユーザ情報の編集を実行
		testEditUserNG();		// 存在しないユーザに対してユーザ情報の編集を実行
		testDeleteUserOK();		// ユーザ情報の削除を実行
		testDeleteUserNG();		// 存在しないユーザの削除を実行
	}
	
	
	/**
	 * testIsUserOKメソッド
	 * 既にデータベースに登録されているユーザの情報に対して、検索可能かテストする
	 */
	public static void testIsUserOK() {
		
		User user = new User("kurita", "abcd1234");
		UsersDAO dao = new UsersDAO();
		boolean result = dao.authenticateUser(user);
		
		if (result) {
			System.out.println("testIsUserOK:成功しました");
		} else {
			System.out.println("testIsUserOK:失敗しました");
		}
	}
	
	
	/**
	 * testIsUserNGメソッド
	 * データベースに登録されていないユーザの情報について正しい検索結果を得られるかテストする
	 */
	public static void testIsUserNG() {
		
		User user = new User("kurita", "abcd12345");
		UsersDAO dao = new UsersDAO();
		boolean result = dao.authenticateUser(user);
		
		if (!result) {
			System.out.println("testIsUserNG:成功しました");
		} else {
			System.out.println("testIsUserNG:失敗しました");
		}
	}
	
	
	/**
	 * testCreateUserOKメソッド
	 * ユーザの新規登録を実行する
	 */
	public static void testCreateUserOK() {
		
		User user = new User("kuritaKoichi7", "abc8dabiu4t5df");
		UsersDAO dao = new UsersDAO();
		boolean result = dao.createUser(user);
		
		if (result) {
			System.out.println("testCreateUserOK:成功しました");
		} else {
			System.out.println("testCreateUserOK:失敗しました");
		}
	}
	
	
	/**
	 * testCreateUserNGメソッド
	 * 既に登録されているユーザーの新規登録を実行
	 */
	public static void testCreateUserNG() {
		
		User user = new User("kurita", "abcd1234");
		UsersDAO dao = new UsersDAO();
		boolean result = dao.createUser(user);
		
		if (!result) {
			System.out.println("testCreateUserNG:成功しました");
		} else {
			System.out.println("testCreateUserNG:失敗しました");
		}
	}
	
	
	/**
	 * testEditUserOKメソッド
	 * ユーザ情報の編集に関するテストを実行する
	 */
	public static void testEditUserOK() {
		
		User user = new User("bar20", "abcd1234");
		UsersDAO dao = new UsersDAO();
		boolean result = dao.editUser("bar1", user);
		
		if (result) {
			System.out.println("testEditUserOK:成功しました");
		} else {
			System.out.println("testEditUserOK:失敗しました");
		}
	}
	
	
	/**
	 * testEditUserNGメソッド
	 * データベースに登録されていないユーザ情報の編集に関するテストを実行する
	 */
	public static void testEditUserNG() {
		
		User user = new User("bar20", "abcd1234");
		UsersDAO dao = new UsersDAO();
		boolean result = dao.editUser("barbar", user);
		
		if (!result) {
			System.out.println("testEditUserNG:成功しました");
		} else {
			System.out.println("testEditUserNG:失敗しました");
		}
	}
	
	
	/**
	 * testDeleteUserOKメソッド
	 * ユーザ情報の削除に関するテストを実行する
	 */
	public static void testDeleteUserOK() {
		
		UsersDAO dao = new UsersDAO();
		boolean result = dao.deleteUser("kuritaKoichi3");
		
		if (result) {
			System.out.println("testDeleteUserOK:成功しました");
		} else {
			System.out.println("testDeleteUserOK:失敗しました");
		}
	}
	
	
	/**
	 * testDeleteUserNGメソッド
	 * データベースに登録されていないユーザ情報の削除に関するテストを実行する
	 */
	public static void testDeleteUserNG() {
		
		UsersDAO dao = new UsersDAO();
		boolean result = dao.deleteUser("kuritaKoichi10");
		
		if (!result) {
			System.out.println("testDeleteUserNG:成功しました");
		} else {
			System.out.println("testDeleteUserNG:失敗しました");
		}
	}
}
