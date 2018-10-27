package br.com.vinicius.faculdade;

public class Elevador {
	
	/*
	 * Segundo exercício de P.O.O. do professor Jonathas Silva dos Santos
	 * na faculdade CENTRO UNIVERSITÁRIO DO NORTE-  UNINORTE
	 * 
	 * Criado por Marcus Vinícius
	 * 13 de outubro de 2018
	 */
	
	protected int andar_Atual = 0;
	protected int pessoas_Presentes = 0;

	protected int andares;
	protected int capacidade;

	public Elevador(int andares, int capacidade) {
		this.andares = andares;
		this.capacidade = capacidade;
	}
	
	// Métodos de get e set //

	public final int getAndarAtual() {
		return this.andar_Atual;
	}

	public final void setAndarAtual(int andar_Atual) {
		this.andar_Atual = andar_Atual;
	}

	public final int getAndares() {
		return this.andares;
	}

	public final void setAndares(int andares) {
		this.andares = andares;
	}

	public final int getCapacidade() {
		return this.capacidade;
	}

	public final void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public final int getPessoasPresentes() {
		return this.pessoas_Presentes;
	}

	public final void setPessoasPresentes(int pessoas_Presentes) {
		this.pessoas_Presentes = pessoas_Presentes;
	}
	
	// Método de entrar no elevador, verificando se há espaço no mesmo //

	public final void entrar() {
		if (this.pessoas_Presentes + 1 > this.capacidade) {
			System.out.println("Não há espaço para mais pessoas no elevador!");
			return;
		}

		this.setPessoasPresentes(this.getPessoasPresentes() + 1);
	}
	
	// Método de sair do elevador, verificando se há mais de 0 pessoas nele //
	
	public final void sair() {
		if (this.pessoas_Presentes - 1 < 0) {
			System.out.println("Não há pessoas para sair do elevador!");
			return;
		}
		
		this.setPessoasPresentes(this.getPessoasPresentes() - 1);
	}
	
	// Método de subir o andar do elevador, verificando se há andar superior //
	
	public final void sobe() {
		if (this.andar_Atual + 1 > this.andares) {
			System.out.println("Não há mais andares para subir!");
			return;
		}
		
		this.setAndarAtual(this.getAndarAtual() + 1);
	}
	
	// Método de descer o elevador, verificando se há andares abaixo //
	
	public final void desce() {
		if (this.andar_Atual - 1 < 0) {
			System.out.println("Você já está no térreo!");
			return;
		}
		
		this.setAndarAtual(this.getAndarAtual() - 1);
	}
}
