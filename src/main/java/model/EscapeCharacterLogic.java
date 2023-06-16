/**
 * EscapeCharacterLogic.java
 * HTML特殊文字をエスケープするBOクラス
 */

/*
 * HTML特殊文字のエスケープについて
 * 参考サイト：https://terasolunaorg.github.io/guideline/current/ja/Security/XSS.html
 * http://www.code-magagine.com/?p=577
 */

package model;

public class EscapeCharacterLogic {
	
	/**
	 * executeメソッド
	 * HTML特殊文字を全てエスケープする
	 * @param エスケープさせたい文字列
	 * @return エスケープされた文字列
	 */
	public String execute(String str) {
		if (str == null) return "";
		
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("'", "&#39;");
		
		return str;
	}
}
