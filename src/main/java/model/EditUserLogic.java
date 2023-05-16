/**
 * EditUserLogic.java
 * ユーザ情報の変更を実行するBOクラス
 */
package model;

import dao.UsersDAO;

public class EditUserLogic {
	
	/**
	 * executeメソッド
	 * ユーザ情報の変更を行う
	 * @param beforeEditUserId 変更対象のユーザのユーザID
	 * @param AfterEditUser 変更後のユーザ情報
	 * @return 変更結果
	 */
	public boolean execute(String beforeEditUserId, User AfterEditUser) {
		UsersDAO dao = new UsersDAO();
		boolean result = dao.editUser(beforeEditUserId, AfterEditUser);
		return result;
	}
}
