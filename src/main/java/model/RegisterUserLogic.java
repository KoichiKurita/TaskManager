/**
 * RegisterUserLogic.java
 * 新規アカウント登録を実行するBOクラス
 */
package model;

import dao.UsersDAO;

public class RegisterUserLogic {
	
	/**
	 * executeメソッド
	 * 新規アカウント登録を行う
	 * @param ログイン情報
	 * @return 登録結果
	 */
	public boolean execute(User user) {
		UsersDAO dao = new UsersDAO();
		boolean result = dao.createUser(user);
		return result;
	}
}
