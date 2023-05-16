package other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMySQL {
	
	/**
	 * MySQLへの接続確認
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		final String JDBC_URL = "jdbc:mysql://localhost:3306/links_prog_exercise?useUnicode=true&characterEncoding=utf8";
		final String DB_USER = "root";
		final String DB_PASS = "";
		
		// JDBCドライバ読み込み
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		// データベースに接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// SELECT文を準備
			String sql = "SELECT * FROM post_codes WHERE id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			// プレースホルダーに入れる値を挿入してSELECT文を完成させる
			pStmt.setString(1, "1");
			
			// SELECT文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				// 結果がある場合、表示する
				System.out.println("ID:" + rs.getString("id"));
				System.out.println("郵便番号:" + rs.getString("post_code"));
				System.out.println("都道府県:" + rs.getString("prefecture"));
				System.out.println("市区町村:" + rs.getString("municipality"));
				System.out.println("町名:" + rs.getString("town_area"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
