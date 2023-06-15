/**
 * EditTaskLogicTest.java
 * EditTaskLogicの処理のテストを行う
 */
package test;

import java.util.Calendar;
import java.util.Date;

import model.EditTaskLogic;
import model.Task;

public class EditTaskLogicTest {
	
	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		testEditTaskLogicOK();
		testEditTaskLogicNG();
	}
	
	
	/**
	 * testEditTaskLogicOKメソッド
	 * タスクの編集が可能かテストする
	 */
	public static void testEditTaskLogicOK() {
		
		// テスト用Date生成
		Calendar cdate1 = Calendar.getInstance();
		cdate1.set(2023, 7, 31, 0, 0, 0);
		Date ddate1 = cdate1.getTime();
		
		Task task = new Task(12, "bar", "システム結合テスト", "開発中のシステムの結合テスト", ddate1, "middle");
		EditTaskLogic bo = new EditTaskLogic();
		boolean result = bo.execute(task);
		
		if (result) {
			System.out.println("testEditTaskLogicOK:成功しました");
		} else {
			System.out.println("testEditTaskLogicOK:失敗しました");
		}
	}
	
	
	/**
	 * testEditTaskLogicNGメソッド
	 * データベースに登録されていないタスクの編集に対して、タスクの編集が実行されないことを確認する
	 */
	public static void testEditTaskLogicNG() {
		
		// テスト用Date生成
		Calendar cdate1 = Calendar.getInstance();
		cdate1.set(2023, 7, 31, 0, 0, 0);
		Date ddate1 = cdate1.getTime();
		
		Task task = new Task(20, "bar", "システム結合テスト", "開発中のシステムの結合テスト", ddate1, "middle");
		EditTaskLogic bo = new EditTaskLogic();
		boolean result = bo.execute(task);
		
		if (!result) {
			System.out.println("testEditTaskLogicNG:成功しました");
		} else {
			System.out.println("testEditTaskLogicNG:失敗しました");
		}
	}
}
