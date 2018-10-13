package br.com.vinicius.faculdade;

import java.util.Calendar;

public class DataClass {

	protected int day;
	protected int month;
	protected int year;

	protected boolean error = false;

	protected static final String ERROR_DATE = "Ocorreu um erro ao verificar os valores "
			+ "fornecidos. Certifique-se que os mesmos estão corretos!";

	public DataClass(int day, int month, int year) {
		this.day = this.validDay(day, month, year);
		if (this.day == -1) {
			this.sendError();
			return;
		}

		this.month = this.validMonth(month);
		if (this.month == -1) {
			this.sendError();
			return;
		}

		this.year = this.validYear(year);
		if (this.month == -1) {
			this.sendError();
			return;
		}
	}

	public final void insertValuesTheOS() {
		Calendar calendar = Calendar.getInstance();

		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.month = calendar.get(Calendar.MONTH);
		this.month += 1;
		if (this.month > 12)
			this.month = 1;

		this.year = calendar.get(Calendar.YEAR);
	}

	public final void advanceOneDayAndCheckValues() {
		this.day = this.day += 1;
		if ((this.month == 2 && this.day > 28 && this.year % 4 != 0)
				| (this.month == 2 && this.day > 29 && this.year % 4 == 0)
				| (this.validMonthWith30Days(this.day, this.month))) {
			this.day = 1;
			this.month += 1;
		}

		if (this.month > 12) {
			this.month = 1;
			this.year += 1;
		}
	}

	public final void sendError() {
		System.out.println(ERROR_DATE);
		this.error = true;
	}

	public final boolean error() {
		return error;
	}

	public final int getDay() {
		return this.day;
	}

	public final void setDay(int day) {
		this.day = day;
	}

	public final int getMonth() {
		return this.month;
	}

	public final void setMonth(int month) {
		this.month = month;
	}

	public final int getYear() {
		return this.year;
	}

	public final void setYear(int year) {
		this.year = year;
	}

	public final boolean validMonthWith30Days(int day, int month) {
		return month == 4 | month == 6 | month == 9 | month == 11 && day > 30 ? true : false;
	}

	public final int validDay(int day, int month, int year) {
		return (month == 2 && day > 28 && year % 4 != 0) | (month == 2 && day > 29 && year % 4 == 0)
				| this.validMonthWith30Days(day, month) ? -1 : day;
	}

	public final int validMonth(int month) {
		return month <= 0 || month > 12 ? -1 : month;
	}

	public final int validYear(int year) {
		return year <= 0 ? -1 : year;
	}

	public final String toString() {
		return String.format("%s/%s/%s", this.day, this.month, this.year);
	}
}