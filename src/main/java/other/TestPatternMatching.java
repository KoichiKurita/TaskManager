/**
 * TestPatternMatching.java
 * 正規表現の実験
 * 
 * 参考サイト：
 * 正規表現について
 * https://qiita.com/suema0331/items/5dde9f91671100a83905
 * https://www.mlab.im.dendai.ac.jp/~yamada/java/regex/
 * 
 * StreamAPIについて
 * https://qiita.com/takumi-n/items/369dd3fcb9ccb8fcfa44
 * https://www.tairaengineer-note.com/java-stream-api-nonematch/
 * 
 * 全角と半角カナの検出について
 * https://4engineer.net/java/character-type-check/#i-7
 */

package other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPatternMatching {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		// 判定する文字列
//		String str = "DJNhfjt51あカナカキクケコﾁﾞｶｷｸいかき2478nj*+?>";
//		String str = "hASJGFjndhajgh";
//		String str = "521415825554465";
//		String str = "!#$%&*+><?";
//		String str = "あいうえお";
		String str = "\"";
		
		// 判定パターン
		// 英字
		Pattern pAlphabet = Pattern.compile("[a-zA-Z]+");
		
		// 数字
		Pattern pNumber = Pattern.compile("[0-9]+");
		
		// 記号
		Pattern pSymbol = Pattern.compile("[\\\"\\!\\#\\$\\%\\&\\'\\(\\)\\=\\-\\~\\^\\|\\\\`\\@\\{\\[\\+\\;\\*\\:\\}\\]\\<\\,\\>\\.\\?\\/\\_]+");
		
		// 判定
		Matcher mAlphabet = pAlphabet.matcher(str);
		Matcher mNumber = pNumber.matcher(str);
		Matcher mSymbol = pSymbol.matcher(str);
		
		// 半角カナを含むか
		boolean resultHankakuKana = str.chars().anyMatch((cp) -> Character.toString(cp).matches("[ｦ-ﾟ]+"));
		
		// 全角カナを含むか
		boolean resultZenkaku = str.chars().anyMatch((cp) -> Character.toString(cp).matches("[ァ-ヴー]+"));
		
		// ひらがなを含むか
		boolean resultHiragana = str.chars().anyMatch((cp) -> Character.toString(cp).matches("[ぁ-ん]+"));
		
		// 結果表示
		if (mAlphabet.find()) {
			System.out.println("英字あり");
		} else {
			System.out.println("英字なし");
		}
		
		if (mNumber.find()) {
			System.out.println("数字あり");
		} else {
			System.out.println("数字なし");
		}
		
		if (mSymbol.find()) {
			System.out.println("記号あり");
		} else {
			System.out.println("記号なし");
		}
		
		if (resultHankakuKana) {
			System.out.println("半角カナあり");
		} else {
			System.out.println("半角カナなし");
		}
		
		if (resultZenkaku) {
			System.out.println("全角カナあり");
		} else {
			System.out.println("全角カナなし");
		}
		
		if (resultHiragana) {
			System.out.println("ひらがなあり");
		} else {
			System.out.println("ひらがななし");
		}
		
		// 空白を含むかチェックする
		// 判定パターン文字列
		Pattern pSpace = Pattern.compile("\s+");
		
		// 文字列に空白を含むか判定
		Matcher mSpaceUserId = pSpace.matcher(" 0Pt$Ngv4WNpuz@ePgHXx");
		
		if (mSpaceUserId.find()) {
			System.out.println("空白あり");
		} else {
			System.out.println("空白なし");
		}
	}

}
