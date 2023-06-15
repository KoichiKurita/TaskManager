/**
 * InputPwLogic.java
 * パスワードの入力チェックを実行するBOクラス
 */
package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputPwCheckLogic {
	
	/**
	 * executeクラス
	 * パスワードの入力チェックを行う
	 * @param passWord　パスワード
	 * @return エラーメッセージのArrayList
	 */
	public ArrayList<String> execute(String passWord) {
		
		// パスワードに関するエラーメッセージを格納するArrayList
		ArrayList<String> passWordErrorMessages = new ArrayList<>();
		
		
		// パスワードの入力チェックを行う
		
		// パスワードの文字数チェックを実行
		// パスワードの文字数をチェックする。要件を満たさない場合は、エラーメッセージを生成する。
		if (passWord.equals("") || passWord.length() < 8) {
			passWordErrorMessages.add("※パスワードは8文字以上で入力してください");
		}
		
		if (passWord.length() > 20) {
			passWordErrorMessages.add("※パスワードは20文字以下で入力してください");
		}
		
		
		// パスワードに半角カナ、全角カナ、ひらがなを含むかチェックする
		// パスワードに半角カナ・全角カナ・ひらがなを含むか
		boolean includePassWordHankakuKana = passWord.chars().anyMatch((cp) -> Character.toString(cp).matches("[ｦ-ﾟ]+")); // 半角カナ
		boolean includePassWordZenkakuKana = passWord.chars().anyMatch((cp) -> Character.toString(cp).matches("[ァ-ヴー]+")); // 全角カナ
		boolean includePassWordHiragana = passWord.chars().anyMatch((cp) -> Character.toString(cp).matches("[ぁ-ん]+")); // ひらがな
		
		if (includePassWordHankakuKana || includePassWordZenkakuKana || includePassWordHiragana) {
			passWordErrorMessages.add("※パスワードは半角英数記号で入力してください");
		}
		
		
		// パスワードに英字、数字、記号をそれぞれ1回ずつ使用しているかチェックする
		// 判定パターン文字列
		// 英字
		Pattern pAlphabet = Pattern.compile("[a-zA-Z]+");
		// 数字
		Pattern pNumber = Pattern.compile("[0-9]+");
		// 記号
		Pattern pSymbol = Pattern.compile("[\\!\\\"\\#\\$\\%\\&\\'\\(\\)\\=\\-\\~\\^\\|\\\\`\\@\\{\\[\\+\\;\\*\\:\\}\\]\\<\\,\\>\\.\\?\\/\\_]+");
		
		// 判定
		Matcher mAlphabet = pAlphabet.matcher(passWord); // 英字
		Matcher mNumber = pNumber.matcher(passWord); // 数字
		Matcher mSymbol = pSymbol.matcher(passWord); // 記号
		
		if (!(mAlphabet.find() && mNumber.find() && mSymbol.find())) {
			passWordErrorMessages.add("※パスワードは英字、数字、記号を各最低1回以上使用してください");
			passWordErrorMessages.add("※使用可能な記号は !&quot;#$%&amp;&#39;()=-~^|&yen;`@{}[]+;*:&lt;&gt;,.?/_ です。");
		}
		
		
		// 空白を含むかチェックする
		// 判定パターン文字列
		Pattern pSpace = Pattern.compile("\s+");
		
		// パスワードに空白を含むか判定
		Matcher mSpacePassWord = pSpace.matcher(passWord);
		
		if (mSpacePassWord.find()) {
			passWordErrorMessages.add("※パスワードに空白を含めないでください。");
		}

		
		return passWordErrorMessages;
	}
}
