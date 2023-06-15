/**
 * AddTaskLogicTest.java
 * AddTaskLogicの処理のテストを行う
 */
package test;

import java.util.Calendar;
import java.util.Date;

import model.AddTaskLogic;
import model.Task;

public class AddTaskLogicTest {
	
	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		testAddTaskLogic();
	}
	
	
	/**
	 * testAddTaskLogicメソッド
	 * タスクを追加可能かテストする
	 */
	public static void testAddTaskLogic() {
		
		// テスト用Date生成
		Calendar cdate1 = Calendar.getInstance();
		cdate1.set(2023, 4, 31, 0, 0, 0);
		Date ddate1 = cdate1.getTime();
		
		Task task = new Task(100, "bar", "会議", "事業会議", ddate1, "high");
		AddTaskLogic bo = new AddTaskLogic();
		boolean result = bo.execute(task);
		
		if (result) {
			System.out.println("testAddTaskLogic:成功しました");
		} else {
			System.out.println("testAddTaskLogic:失敗しました");
		}
	}
}
