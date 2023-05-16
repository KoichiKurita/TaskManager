/**
 * GetTaskLogicTest.java
 * GetTaskLogicの処理のテストを行う
 */
package test;

import java.util.Calendar;
import java.util.Date;

import model.GetTaskLogic;
import model.Task;

public class GetTaskLogicTest {
	
	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		testGetTaskLogicOK();
		testGetTaskLogicNG();
	}
	
	
	/**
	 * testGetTaskLogicOKメソッド
	 * 1件分のタスク情報を取得可能かテストする
	 */
	public static void testGetTaskLogicOK() {
		GetTaskLogic bo = new GetTaskLogic();
		Task resultTask = bo.execute(2);
		
		// 比較用日付
		Calendar cdate1 = Calendar.getInstance();
		cdate1.set(2023, 4, 18, 0, 0, 0);	// CalenderクラスのMonthは0(1月)～11(12月)になる
		cdate1.clear(Calendar.MILLISECOND);	// ミリ秒以下は削除
		Date ddate1 = cdate1.getTime();
		
		if (resultTask.getUserId().equals("Kurita") &&
				resultTask.getTitle().equals("応募書類作成") &&
				resultTask.getContents().equals("履歴書と職務経歴書を作成する") && 
				resultTask.getDeadline().compareTo(ddate1) == 0 &&
						resultTask.getPriority().equals("high")) {
			
			System.out.println("testGetTaskLogicOK:成功しました");
			
		} else {
			System.out.println("testGetTaskLogicOK:失敗しました");
		}
	}
	
	
	public static void testGetTaskLogicNG() {
		GetTaskLogic bo = new GetTaskLogic();
		Task resultTask = bo.execute(50);
		
		if (resultTask == null) {
			System.out.println("testGetTaskLogicNG:成功しました");
		} else {
			System.out.println("testGetTaskLogicNG:失敗しました");
		}
	}
}
