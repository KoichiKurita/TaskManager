/**
 * EditPassWordLogic.java
 * パスワードを変更するBOクラス
 */
package model;

import dao.UsersDAO;

public class EditPassWordLogic {
	
	/**
	 * executeメソッド
	 * パスワードを変更する
	 * @param 変更後のパスワードを含むユーザ情報
	 * @return 処理結果
	 */
	public boolean execute(User user) {
		UsersDAO dao = new UsersDAO();
		boolean result = dao.editPassWord(user);
		return result;
	}
}
