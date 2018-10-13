package br.com.vinicius.faculdade;

import java.util.Calendar;
import java.util.Scanner;

public class DataClass {
	
	/*
	 * Primeiro exercício de P.O.O. do professor Jonathas Silva dos Santos
	 * na faculdade CENTRO UNIVERSITÁRIO DO NORTE-  UNINORTE
	 * 
	 * Criado por Marcus Vinícius
	 * 12 de outubro de 2018
	 */

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

	public static void main(String[] args) {
		Scanner scanner = null;

		try {
			scanner = new Scanner(System.in);

			System.out.println("Por favor, informe o dia da data");
			int day = scanner.nextInt();

			System.out.println("Agora, por favor, informe o mês da data");
			int month = scanner.nextInt();

			System.out.println("Novamente, por favor, informe o ano da data");
			int year = scanner.nextInt();

			DataClass data = new DataClass(day, month, year);
			if (data.error)
				return;

			System.out.println("Data atual: " + data.toString());

			data.advanceOneDayAndCheckValues();
			System.out.println("Próxima data: " + data.toString());

			data.insertValuesTheOS();
			System.out.println("Data do sistema: " + data.toString());
		} finally {
			if (scanner != null)
				scanner.close();
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
		if (this.validFebruary(this.day, this.month, this.year) | this.validDay(this.day)
				| this.validMonth(this.day, this.month)) {
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

	public final boolean validMonth(int day, int month) {
		return month == 4 | month == 6 | month == 9 | month == 11 && day > 30 ? true : false;
	}

	public final boolean validDay(int day) {
		return day <= 0 | day > 31 ? true : false;
	}

	public final boolean validFebruary(int day, int month, int year) {
		return (month == 2 && day > 28 && year % 4 != 0) | (month == 2 && day > 29 && year % 4 == 0) ? true : false;
	}

	public final int validDay(int day, int month, int year) {
		return this.validFebruary(day, month, year) | this.validDay(day) | this.validMonth(day, month) ? -1 : day;
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
