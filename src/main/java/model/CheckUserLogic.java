/**
 * CheckUserLogic.java
 * ユーザが既に登録済みか確認するBOクラス
 */
package model;

import dao.UsersDAO;

public class CheckUserLogic {
	
	/**
	 * executeメソッド
	 * ユーザが既に登録済みか確認する
	 * @param userId ユーザID
	 * @return　確認結果
	 */
	public boolean execute(String userId) {
		UsersDAO dao = new UsersDAO();
		boolean result = dao.checkUser(userId);
		return result;
	}
}
