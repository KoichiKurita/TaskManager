/**
 * AuthenticationInfo.java
 * 認証情報を格納するクラス
 *
 * 認証情報（DB接続情報・パスワードハッシュ化情報）はソースコードに直接書かず、
 * サーバー上の設定ファイル（taskmanager.properties）から起動時に読み込む。
 * これにより、ソースコード・WARファイル・Git・クラウドストレージに秘密情報が含まれない。
 *
 * 設定ファイルの場所（上から順に探す）
 *   1. 環境変数 TASKMANAGER_CONFIG で指定したパス
 *   2. システムプロパティ taskmanager.config で指定したパス
 *   3. ${catalina.base}/conf/taskmanager.properties（Tomcat上で動かす場合の標準の場所）
 *
 * 設定ファイルの書式は taskmanager.properties.sample を参照
 */

/**
 * 定数クラスについて
 * https://www.xmisao.com/2013/12/07/java-constant-class.html
 */

package contents;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class AuthenticationInfo {

	// コンストラクタをprivateにし、外部からインスタンスを作成させないようにする
	private AuthenticationInfo() {}

	// DB認証情報
	public static final String JDBC_URL;	// 実際に接続するDBのURL
	public static final String DB_USER;		// 実際に接続するDBのユーザID
	public static final String DB_PASS;		// 実際に接続するDBのパスワード

	// パスワードハッシュ化情報
	// ハッシュアルゴリズム
	public static final String ALGORITHM;

	// ソルト用固定文字列
	public static final String FIXEDSOLT;

	// クラスの読み込み時に、設定ファイルから認証情報を読み込む
	static {
		Properties props = load(resolveConfigPath());
		JDBC_URL  = required(props, "db.url");
		DB_USER   = required(props, "db.user");
		DB_PASS   = required(props, "db.password");
		ALGORITHM = required(props, "hash.algorithm");
		FIXEDSOLT = required(props, "hash.fixedsalt");
	}

	/**
	 * 設定ファイルのパスを決定する
	 * @return 設定ファイルのパス
	 */
	private static Path resolveConfigPath() {
		String env = System.getenv("TASKMANAGER_CONFIG");
		if (env != null && !env.isBlank()) {
			return Paths.get(env);
		}
		String prop = System.getProperty("taskmanager.config");
		if (prop != null && !prop.isBlank()) {
			return Paths.get(prop);
		}
		String catalinaBase = System.getProperty("catalina.base");
		if (catalinaBase != null && !catalinaBase.isBlank()) {
			return Paths.get(catalinaBase, "conf", "taskmanager.properties");
		}
		throw new IllegalStateException(
				"認証情報の設定ファイルの場所が分かりません。環境変数 TASKMANAGER_CONFIG を設定してください");
	}

	/**
	 * 設定ファイルを読み込む（UTF-8）
	 * @param path 設定ファイルのパス
	 * @return 読み込んだ設定
	 */
	private static Properties load(Path path) {
		Properties props = new Properties();
		try (InputStream in = Files.newInputStream(path)) {
			props.load(new java.io.InputStreamReader(in, java.nio.charset.StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new IllegalStateException("認証情報の設定ファイルを読み込めませんでした: " + path, e);
		}
		return props;
	}

	/**
	 * 必須の設定値を取得する（値の前後の空白は取り除かない）
	 * @param props 設定
	 * @param key キー
	 * @return 設定値
	 */
	private static String required(Properties props, String key) {
		String value = props.getProperty(key);
		if (value == null || value.isEmpty()) {
			throw new IllegalStateException("認証情報の設定ファイルに " + key + " がありません");
		}
		return value;
	}
}
