package br.com.vinicius.faculdade;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ContaBancaria {

	private final String nome;
	private final String tipo;

	private final int numero;
	private final int agencia;

	private double saldo;
	private String status;

	private boolean error;

	public ContaBancaria(String nome, String tipo, int numero, int agencia) {
		this.nome = nome;
		this.tipo = tipo;
		this.numero = numero;
		this.agencia = agencia;
		this.status = "Ativa";
		this.saldo = this.validarConta();
		if (this.saldo == -1.0D) {
			this.error = true;
			System.out.println("Ocorreu um erro ao validar sua conta.");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = null;

		try {
			scanner = new Scanner(System.in);

			System.out.println("Digite o nome da conta: ");
			String nome = scanner.nextLine();

			System.out.println("Digite o tipo da conta: ");
			String tipo = scanner.nextLine();

			System.out.println("Digite o número da conta: ");
			int numero = scanner.nextInt();

			System.out.println("Digite a agência: ");
			int agencia = scanner.nextInt();

			ContaBancaria conta = new ContaBancaria(nome, tipo, numero, agencia);
			if (conta.error)
				return;

			System.out.println("Comandos disponíveis: Sacar, Depositar, Transferir, Encerrar Conta e Status.");
			System.out.println("Para encerrar o programa, digite: Sair");
			boolean encerrar = false;
			do {
				String comando = scanner.nextLine();
				if (comando.equalsIgnoreCase("Sair")) {
					encerrar = true;
					System.out.println("Programa encerrado com sucesso!");
					break;
				}

				if (comando.equalsIgnoreCase("Comandos")) {
					System.out.println("Comandos disponíveis: Sacar, Depositar, Transferir e Encerrar Conta.");
					System.out.println("Para encerrar o programa, digite: Sair");
				} else if (comando.equalsIgnoreCase("Sacar")) {
					System.out.println("Seu saldo disponível é de: R$" + conta.validBalance(conta.getSaldo()));
					System.out.println("Digite a quantia que será sacada: ");
					double qnt = scanner.nextDouble();

					conta.sacar(qnt);
				} else if (comando.equalsIgnoreCase("Depositar")) {
					System.out.println("Digite a quantia que será depositada: ");
					double depositar = scanner.nextDouble();

					conta.depositar(depositar);
				} else if (comando.equalsIgnoreCase("Transferir")) {
					System.out.println("Digite o número da conta: ");
					int numero2 = scanner.nextInt();

					System.out.println("Digite o número da agência: ");
					int agencia2 = scanner.nextInt();

					System.out.println("Digite a quantia que será transferida: ");
					double qnt = scanner.nextDouble();

					conta.transferir(numero2, agencia2, qnt);
				} else if (comando.equalsIgnoreCase("Encerrar Conta")) {
					conta.encerrarConta();
				} else if (comando.equalsIgnoreCase("Status")) {
					conta.status();
				}
			} while (!encerrar);
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}

	public String getNome() {
		return this.nome;
	}

	public String getTipo() {
		return this.tipo.equalsIgnoreCase("cc") ? "Conta Corrente"
				: this.tipo.equalsIgnoreCase("pp") ? "Poupança" : this.tipo;
	}

	public int getNumero() {
		return this.numero;
	}

	public int getAgencia() {
		return this.agencia;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public final double validarConta() {
		if (this.tipo.equalsIgnoreCase("Conta Corrente") || this.tipo.equalsIgnoreCase("cc"))
			return 50.0D;
		else if (this.tipo.equalsIgnoreCase("Poupança") || this.tipo.equalsIgnoreCase("pp"))
			return 100.0D;
		else
			return -1.0d;
	}

	public final void sacar(double qnt) {
		if (this.saldo == 0.0d) {
			System.out.println("Você não possui quantia suficiente para sacar!");
			return;
		}

		if (this.saldo < qnt) {
			System.out.println("O valor informado é superior ao saldo de sua conta!");
			return;
		}

		System.out.println("Dinheiro sacado com sucesso! Quantia sacada: R$" + this.validBalance(qnt));
		this.saldo -= qnt;

		System.out.println("Novo saldo: R$" + this.validBalance(this.saldo));
	}

	public final void depositar(double depositar) {
		if (depositar == 0.0d) {
			System.out.println("Depósito inválido!");
			return;
		}

		this.saldo += depositar;
		System.out.println("Depósito efetuado com sucesso!");
		System.out.println("Novo saldo: R$" + this.validBalance(this.saldo));
	}

	public final void transferir(int numero, int agencia, double qnt) {
		if (valid(numero, agencia)) {
			System.out.println("Número e agência inválidas!");
			return;
		}

		if (this.saldo <= 0.0d) {
			System.out.println("Sua conta está negativada! Não será possível transferir a quantia.");
			return;
		}

		if (this.saldo < qnt) {
			System.out.println("Você não possui saldo para transferir a quantia desejada");
			System.out.println("Quantia desejada: R$" + this.validBalance(qnt) + ", saldo disponível: R$"
					+ this.validBalance(this.saldo));
			return;
		}

		ContaBancaria conta = new ContaBancaria("Seu José", "Conta Corrente", numero, agencia);
		conta.setSaldo(conta.getSaldo() + qnt);

		System.out.println("Transferência efetuada com sucesso!");
		this.saldo -= qnt;

		System.out.println("Seu novo saldo: R$" + this.validBalance(this.saldo));
		System.out.println(
				"Agora o " + conta.getNome() + " possui a quantia de R$" + this.validBalance(conta.getSaldo()));
	}

	public final void encerrarConta() {
		if (this.saldo >= 0.0d) {
			if (this.saldo > 0.0d) {
				System.out.println("Há uma quantia em sua conta para ser sacada.");
				System.out.println("Quantia que irá ser sacada: R$" + this.validBalance(this.saldo));
			}

			System.out.println("Conta encerrada com sucesso!");
			this.saldo = 0.0d;
			this.status = "Inativa";
		} else {
			System.out.println("Sua conta está negativa!");
			System.out.println("Será necessário depositar uma quantia para que ela seja encerrada.");
			System.out.println("Quantia que será necessária para depositar: R$" + this.validBalance(this.saldo));
		}
	}

	public final void status() {
		System.out.println(" ");
		System.out.println("Nome: " + this.getNome());
		System.out.println("Tipo da conta: " + this.getTipo());
		System.out.println("Número: " + this.getNumero());
		System.out.println("Agência: " + this.getAgencia());
		System.out.println("Saldo: R$" + this.validBalance(this.getSaldo()));
		System.out.println("Status: " + this.getStatus());
		System.out.println(" ");
	}

	public final boolean valid(int numero, int agencia) {
		return numero <= 0 | agencia <= 0 ? true : false;
	}

	public String toString() {
		return getNome() + ", " + getTipo() + ", " + getNumero() + ", " + getAgencia() + ", " + getSaldo() + ", "
				+ getStatus();
	}

	public String validBalance(double saldo) {
		DecimalFormat format = new DecimalFormat("#,###.##");
		format.setRoundingMode(RoundingMode.HALF_UP);

		return format.format(saldo);
	}
}
