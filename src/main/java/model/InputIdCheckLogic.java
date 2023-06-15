/**
 * InputIdLogic.java
 * ユーザIDの入力チェックを実行するBOクラス
 */
package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputIdCheckLogic {
	
	/**
	 * executeクラス
	 * ユーザIDの入力チェックを行う
	 * @param userId ユーザID
	 * @return エラーメッセージのArrayList
	 */
	public ArrayList<String> execute(String userId) {
		
		// ユーザIDに関するエラーメッセージを格納するArrayList
		ArrayList<String> userIdErrorMessages = new ArrayList<>();
		
		// ユーザIDの入力チェックを行う
		
		// ユーザIDの文字数チェックを実行
		// ユーザIDの文字数をチェックする。要件を満たさない場合は、エラーメッセージを生成する。		
		if (userId.equals("") || userId.length() < 8) {
			userIdErrorMessages.add("※ユーザIDは8文字以上で入力してください");
		}
		
		if (userId.length() > 50) {
			userIdErrorMessages.add("※ユーザIDは50文字以下で入力してください");
		}
		
		
		// ユーザIDに半角カナ、全角カナ、ひらがなを含むかチェックする
		// ユーザIDに半角カナ・全角カナ・ひらがなを含むか
		boolean includeUserIdHankakuKana = userId.chars().anyMatch((cp) -> Character.toString(cp).matches("[ｦ-ﾟ]+")); // 半角カナ
		boolean includeUserIdZenkakuKana = userId.chars().anyMatch((cp) -> Character.toString(cp).matches("[ァ-ヴー]+")); // 全角カナ
		boolean includeUserIdHiragana = userId.chars().anyMatch((cp) -> Character.toString(cp).matches("[ぁ-ん]+")); // ひらがな
		
		if (includeUserIdHankakuKana || includeUserIdZenkakuKana || includeUserIdHiragana) {
			userIdErrorMessages.add("※ユーザIDは半角英数で入力してください");
		}
		
		
		// 空白を含むかチェックする
		// 判定パターン文字列
		Pattern pSpace = Pattern.compile("\s+");
		
		// ユーザIDに空白を含むか判定
		Matcher mSpaceUserId = pSpace.matcher(userId);
		
		if (mSpaceUserId.find()) {
			userIdErrorMessages.add("※ユーザIDに空白を含めないでください。");
		}
		
		return userIdErrorMessages;
	}
}
