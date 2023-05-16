/**
 * UsersDAO.java
 * ユーザー情報のデータベースへの登録・取得・編集・削除を行うDAOモデルクラス
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UsersDAO {
	
	// データベース情報
	// URL
	private final String JDBC_URL = null;		// データベースのURL(ここではnullにしている)
	private final String DB_USER = null;		// データベースアカウント(ここではnullにしている)
	private final String DB_PASS = null;		// データベースのパスワード(ここではnullにしている)
	
	/**
	 * isUserメソッド
	 * ユーザ情報をデータベースから検索する
	 * @param　ユーザ情報
	 * @return 登録されているかどうか（true or false）
	 */
	public boolean isUser(User user) {
		
		// JDBCドライバ読み込み
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// データベースに接続（データベースの切断はプログラム終了時に自動で実行される）
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// SELECT文を準備
			String sql = "SELECT * FROM users WHERE user_id = ? AND pass_word = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// プレースホルダーに入れる値を挿入してSELECT文を完成させる(SQLインジェクション対策)
			pStmt.setString(1, user.getUserId());
			pStmt.setString(2, user.getPassWord());
			
			// SELECT文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				// ユーザ情報の検索結果がある場合
				return true;
			} 
			
		} catch (SQLException e) { // データベース処理中に例外が発生した場合、処理終了
			System.out.println("SQLExceptionが発生しました");
			System.out.println("処理を終了します");
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	
	/**
	 * createUserメソッド
	 * 1件分のユーザーをデータベースに登録する
	 * @param 1件分のユーザ情報
	 * @return 登録結果
	 */
	public boolean createUser(User user) {
		
		// JDBCドライバ読み込み
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// データベースに接続（データベースの切断はプログラム終了時に自動で実行される）
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				
			// ユーザー情報の検索結果がない場合、新規ユーザー登録を行う
			// INSERT文を準備
			String sql = "INSERT INTO users(user_id, pass_word) VALUES(?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// プレースホルダーに入れる値を挿入してINSERT文を完成させる
			pStmt.setString(1, user.getUserId());
			pStmt.setString(2, user.getPassWord());
			
			// INSERT文を実行
			int result = pStmt.executeUpdate();
			
			if(result != 1) {	// 追加したデータが1件でない場合、登録処理終了
				System.out.println("データベースへの登録件数が1件ではありませんでした");
				return false;
			}
			
		} catch (SQLException e) {	// データベース処理中に例外が発生した場合、処理終了
			System.out.println("SQLExceptionが発生しました");
			System.out.println("処理を終了します");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * editUserメソッド
	 * 既に登録されているユーザのユーザID, パスワードを修正する
	 * @param beforeEditUserId 変更対象のユーザのユーザID
	 * @param AfterEditUser　変更後のユーザ情報（ユーザID, パスワード）
	 * @return 修正結果
	 */
	public boolean editUser(String beforeEditUserId, User AfterEditUser) {
		
		// JDBCドライバ読み込み
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// データベースに接続（データベースの切断はプログラム終了時に自動で実行される）
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// UPDATE文を準備
			String sql = "UPDATE users SET user_id = ?, pass_word = ? WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// プレースホルダーに入れる値を挿入してUPDATE文を完成させる
			pStmt.setString(1, AfterEditUser.getUserId());
			pStmt.setString(2, AfterEditUser.getPassWord());
			pStmt.setString(3, beforeEditUserId);
			
			// UPDATE文を実行
			int result = pStmt.executeUpdate();
			
			if(result != 1) {	// 追加したデータが1件でない場合、登録処理終了
				System.out.println("データベースへの変更件数が1件ではありませんでした");
				System.out.println("処理を終了します");
				return false;
			}
			
		} catch (SQLException e) {	// データベース処理中に例外が発生した場合、処理終了
			System.out.println("SQLExceptionが発生しました");
			System.out.println("処理を終了します");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * deleteUserメソッド
	 * データベースから1件分のユーザ情報を削除する
	 * @param userId 削除対象のユーザのユーザID
	 * @return 削除結果
	 */
	public boolean deleteUser(String userId) {
		
		// JDBCドライバ読み込み
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// データベースに接続（データベースの切断はプログラム終了時に自動で実行される）
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// DELETE文を準備
			String sql = "DELETE FROM users WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// プレースホルダーに入れる値を挿入してDELETE文を完成させる
			pStmt.setString(1, userId);
			
			// DELETE文を実行
			int result = pStmt.executeUpdate();
			
			if(result != 1) {	// 削除したデータが1件でない場合、登録処理終了
				System.out.println("データベースへの削除件数が1件ではありませんでした");
				System.out.println("処理を終了します");
				return false;
			}
			
		} catch (SQLException e) {	// データベース処理中に例外が発生した場合、処理終了
			System.out.println("SQLExceptionが発生しました");
			System.out.println("処理を終了します");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
