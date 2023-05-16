/**
 * Task.java
 * タスク情報を格納するEntityモデルクラス(JavaBeans)
 */

/*
 * 日付の差分について
 * 参考サイト：
 * https://su-kun1899.hatenablog.com/entry/2017/04/28/230000
 * https://www.ne.jp/asahi/hishidama/home/tech/java/temporalAmount.html
 * https://qiita.com/tora_kouno/items/d230f904a2b768ccb319
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Task implements Serializable {
	
	private int taskId = 0;		// タスクID
	private String userId;		// タスクを登録したユーザのユーザID
	private String title;		// タスクタイトル
	private String contents;	// タスクの詳細
	private Date deadline;		// タスクの期限
	private String priority;	// タスクの優先度（高・中・低）
	private int daysLeft;		// 残りの日数
	
	public Task() {}
	
	public Task(int taskId, String userId, String title, String contents, Date deadline, String priority) {
		this.taskId = taskId;
		this.userId = userId;
		this.title = title;
		this.contents = contents;
		this.deadline = deadline;
		this.priority = priority;
		this.daysLeft = this.calcDaysLeft();
	}
	
	public Task(String userId, String title, String contents, Date deadline, String priority) {
		this.userId = userId;
		this.title = title;
		this.contents = contents;
		this.deadline = deadline;
		this.priority = priority;
		this.daysLeft = this.calcDaysLeft();
	}

	public int getTaskId() {
		return taskId;
	}

	public String getUserId() {
		return userId;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public Date getDeadline() {
		return deadline;
	}

	public String getPriority() {
		return priority;
	}
	
	public int getDaysLeft() {
		return daysLeft;
	}

	
	/**
	 * calcDaysLeftメソッド
	 * 今日の日付と期限との差分を取得する
	 * @return 今日の日付と期限の間の日数
	 */
	private int calcDaysLeft() {
		Date today = new Date();
		LocalDate ldToday =  LocalDate.ofInstant(today.toInstant(), ZoneId.systemDefault());
		LocalDate ldDeadline = LocalDate.ofInstant(this.deadline.toInstant(), ZoneId.systemDefault());
		Period pDaysLeft = Period.between(ldToday, ldDeadline);
		return pDaysLeft.getYears()*365 + pDaysLeft.getMonths()*12 + pDaysLeft.getDays();
	}
	
}
