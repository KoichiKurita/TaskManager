/**
 * DeleteUserLogic.java
 * ユーザ情報の削除を実行するBOクラス
 */
package model;

import dao.UsersDAO;

public class DeleteUserLogic {
	
	/**
	 * executeメソッド
	 * ユーザ情報の削除を行う
	 * @param userId 削除対象のユーザのユーザID
	 * @return　削除結果
	 */
	public boolean execute(String userId) {
		UsersDAO dao = new UsersDAO();
		boolean result = dao.deleteUser(userId);
		return result;
	}
}
