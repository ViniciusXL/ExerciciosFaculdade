package br.com.vinicius.faculdade;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = null;

		try {
			scanner = new Scanner(System.in);
			
			print("Por favor, informe o dia da data");
			int day = scanner.nextInt();
			
			print("Agora, por favor, informe o mês da data");
			int month = scanner.nextInt();
			
			print("Novamente, por favor, informe o ano da data");
			int year = scanner.nextInt();
			
			DataClass data = new DataClass(day, month, year);
			if (data.error)
				return;
			
			print("Data atual: " + data.toString());
			
			data.advanceOneDayAndCheckValues();
			print("Próxima data: " + data.toString());
			
			data.insertValuesTheOS();
			print("Data do sistema: " + data.toString());
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}
	
	protected static void print(String print) {
		System.out.println(print);
	}

}
