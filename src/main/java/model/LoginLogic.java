/**
 * LoginLogic.java
 * ログインを実行するBOクラス
 */
package model;

import dao.UsersDAO;

public class LoginLogic {
	
	/**
	 * executeメソッド
	 * ログイン処理を行う
	 * @param ログイン情報
	 * @return ログイン結果
	 */
	public boolean execute(User user) {
		UsersDAO dao = new UsersDAO();
		boolean result = dao.authenticateUser(user);
		return result;
	}
}
