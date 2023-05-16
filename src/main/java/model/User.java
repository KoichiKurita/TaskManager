/**
 * User.java
 * ユーザー情報を格納するEntityモデルクラス(JavaBeans)
 */
package model;

import java.io.Serializable;

public class User implements Serializable {
	
	private String userId;		// ユーザーID
	private String passWord;	// パスワード
	
	public User() {}
	
	public User(String userId, String passWord) {
		this.userId = userId;
		this.passWord = passWord;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassWord() {
		return passWord;
	}
	
}
