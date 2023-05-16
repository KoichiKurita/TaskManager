/**
 * InputIdPwLogic.java
 * ユーザID、パスワードの入力チェックを実行するBOクラス
 */
package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputIdPwCheckLogic {
	
	/**
	 * executeクラス
	 * ユーザID、パスワードの入力チェックを行う
	 * @param userId ユーザID
	 * @param passWord　パスワード
	 * @return エラーメッセージのArrayList
	 */
	public ArrayList<String> execute(String userId, String passWord) {
		
		// ユーザIDとパスワードの入力チェックを行う
		ArrayList<String> errorMessages = new ArrayList<>();	// エラーメッセージのリスト
		
		// ユーザIDとパスワードの文字数チェックを実行
		// ユーザIDの文字数をチェックする。要件を満たさない場合は、エラーメッセージを生成する。		
		if (userId.equals("") || userId.length() < 8) {
			errorMessages.add("※ユーザIDは8文字以上で入力してください");
		}
		
		if (userId.length() > 50) {
			errorMessages.add("※ユーザIDは50文字以下で入力してください");
		}
		
		// パスワードの文字数をチェックする。要件を満たさない場合は、エラーメッセージを生成する。
		if (passWord.equals("") || passWord.length() < 8) {
			errorMessages.add("※パスワードは8文字以上で入力してください");
		}
		
		if (passWord.length() > 20) {
			errorMessages.add("※パスワードは20文字以下で入力してください");
		}
		
		
		// ユーザIDとパスワードに半角カナ、全角カナ、ひらがなを含むかチェックする
		// ユーザIDに半角カナ・全角カナ・ひらがなを含むか
		boolean includeUserIdHankakuKana = userId.chars().anyMatch((cp) -> Character.toString(cp).matches("[ｦ-ﾟ]+")); // 半角カナ
		boolean includeUserIdZenkakuKana = userId.chars().anyMatch((cp) -> Character.toString(cp).matches("[ァ-ヴー]+")); // 全角カナ
		boolean includeUserIdHiragana = userId.chars().anyMatch((cp) -> Character.toString(cp).matches("[ぁ-ん]+")); // ひらがな
		
		if (includeUserIdHankakuKana || includeUserIdZenkakuKana || includeUserIdHiragana) {
			errorMessages.add("※ユーザIDは半角英数記号で入力してください");
		}
		
		// パスワードに半角カナ・全角カナ・ひらがなを含むか
		boolean includePassWordHankakuKana = passWord.chars().anyMatch((cp) -> Character.toString(cp).matches("[ｦ-ﾟ]+")); // 半角カナ
		boolean includePassWordZenkakuKana = passWord.chars().anyMatch((cp) -> Character.toString(cp).matches("[ァ-ヴー]+")); // 全角カナ
		boolean includePassWordHiragana = passWord.chars().anyMatch((cp) -> Character.toString(cp).matches("[ぁ-ん]+")); // ひらがな
		
		if (includePassWordHankakuKana || includePassWordZenkakuKana || includePassWordHiragana) {
			errorMessages.add("※パスワードは半角英数記号で入力してください");
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
			errorMessages.add("※パスワードは英字、数字、記号を各最低1回以上使用してください");
		}
		
		return errorMessages;
	}
}
