/**
 * User.java
 * ユーザー情報を格納するEntityモデルクラス(JavaBeans)
 */

/*
 * パスワードのハッシュ化について　
 * https://gist.github.com/seyan/915057/fa23295ae63c782fcdd2c1c604c023f4df1ec0ea
 */

package model;

import static contents.AuthenticationInfo.*;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements Serializable {
	
	private String userId;			// ユーザーID
	private String passWord;		// パスワード
	private String hashedPassWord;	// ハッシュ化されたパスワード
	
	public User() {}
	
	public User(String userId, String passWord) {
		this.userId = userId;
		this.passWord = passWord;
		this.hashedPassWord = this.hashPassWord();
	}

	public String getUserId() {
		return userId;
	}

	public String getPassWord() {
		return passWord;
	}
	
	public String getHashedPassWord() {
		return hashedPassWord;
	}

	/**
	 * hashPassWordメソッド
	 * パスワードをハッシュ化する
	 * @return ハッシュ化されたパスワード
	 */
	private String hashPassWord() {
		String hash = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			md.update((this.passWord + this.userId + FIXEDSOLT).getBytes());
			byte[] digest = md.digest();
			// byte[]からStringに変換する
			hash = new String(digest, "UTF-8");
			
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmExceptionが発生しました");
			e.printStackTrace();
			
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingExceptionが発生しました");
			e.printStackTrace();
		}
		
		// パスワードをハッシュ化した文字列で置き換える
		return hash;
	}
	
}
