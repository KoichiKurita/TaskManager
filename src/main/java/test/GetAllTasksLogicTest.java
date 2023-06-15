/**
 * GetAllTasksLogicTest.java
 * SearchTaskLogicの処理のテストを行う
 */
package test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.GetAllTasksLogic;
import model.Task;

public class GetAllTasksLogicTest {
	
	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		testGetAllTasksLogicOK();
		testGetAllTasksLogicNG();
	}
	
	
	/**
	 * testGetAllTasksLogicOKメソッド
	 * 既にデータベースに登録されているタスク情報について取得可能かテストする
	 */
	public static void testGetAllTasksLogicOK() {
		GetAllTasksLogic bo = new GetAllTasksLogic();
		List<Task> resultList = bo.execute("hoge");
		
		int resultList0TaskId = resultList.get(0).getTaskId();
		String resultList0UserId = resultList.get(0).getUserId();
		String resultList0Title = resultList.get(0).getTitle();
		String resultList0Contents = resultList.get(0).getContents();
		Date resultList0Deadline = resultList.get(0).getDeadline();
		String resultList0Priority = resultList.get(0).getPriority();
		
		int resultList1TaskId = resultList.get(1).getTaskId();
		String resultList1UserId = resultList.get(1).getUserId();
		String resultList1Title = resultList.get(1).getTitle();
		String resultList1Contents = resultList.get(1).getContents();
		Date resultList1Deadline = resultList.get(1).getDeadline();
		String resultList1Priority = resultList.get(1).getPriority();
		
		
		// 比較用日付
		Calendar cdate1 = Calendar.getInstance();
		cdate1.set(2023, 8, 30, 0, 0, 0);	// CalenderクラスのMonthは0(1月)～11(12月)になる
		cdate1.clear(Calendar.MILLISECOND);	// ミリ秒以下は削除
		Date ddate1 = cdate1.getTime();
		
		Calendar cdate2 = Calendar.getInstance();
		cdate2.set(2023, 8, 30, 0, 0, 0);
		cdate2.clear(Calendar.MILLISECOND);
		Date ddate2 = cdate2.getTime();
		
		
		if (resultList0TaskId == 9 &&
				resultList0UserId.equals("hoge") &&
				resultList0Title.equals("ワード作成") &&
				resultList0Contents.equals("打ち合わせで使用するデータ集計作業し文書作成") && 
				resultList0Deadline.compareTo(ddate1) == 0 &&
				resultList0Priority.equals("high") &&
				resultList1TaskId == 10 &&
				resultList1UserId.equals("hoge") &&
				resultList1Title.equals("表作成") &&
				resultList1Contents.equals("打ち合わせで使用するデータ集計作業") && 
				resultList1Deadline.compareTo(ddate2) == 0 &&
				resultList1Priority.equals("high")) {
			
			System.out.println("testGetAllTasksLogicOK:成功しました");
			
		} else {
			System.out.println("testGetAllTasksLogicOK:失敗しました");
		}

	}
	
	
	/**
	 * testGetAllTasksLogicNGメソッド
	 * データベースに登録されていないタスク情報について取得し、データ件数が0件であることを確認する
	 */
	public static void testGetAllTasksLogicNG() {
		GetAllTasksLogic bo = new GetAllTasksLogic();
		List<Task> resultList = bo.execute("barbar");
		
		if (resultList.size() == 0) {
			System.out.println("testGetAllTasksLogicNG:成功しました");
		} else {
			System.out.println("testGetAllTasksLogicNG:失敗しました");
		}
	}
}
