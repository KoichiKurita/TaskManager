/**
 * AuthenticationInfoDummy.java
 * 認証情報を格納するクラス（ダミー）
 */

/**
 * 定数クラスについて
 * https://www.xmisao.com/2013/12/07/java-constant-class.html
 */

package contents;

public class AuthenticationInfoDummy {
	
	// コンストラクタをprivateにし、外部からインスタンスを作成させないようにする
	private AuthenticationInfoDummy() {}
	
	// DB認証情報
	public static final String JDBC_URL = null;		// 実際に接続するDBのURL
	public static final String DB_USER = null;		// 実際に接続するDBのユーザID
	public static final String DB_PASS = null;		// 実際に接続するDBのパスワード
	
	// パスワードハッシュ化情報
	// ハッシュアルゴリズム
	public static final String ALGORITHM = null;
	
	// ソルト用固定文字列
	public static final String FIXEDSOLT = null;
}
