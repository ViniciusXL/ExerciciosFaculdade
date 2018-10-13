package br.com.vinicius.faculdade;

public class ElevadorClass {
	
	/*
	 * Segundo exercício de P.O.O. do professor Jonathas Silva dos Santos
	 * na faculdade CENTRO UNIVERSITÁRIO DO NORTE-  UNINORTE
	 * 
	 * Criado por Marcus Vinícius
	 * 13 de outubro de 2018
	 */

	protected int andar_Atual = 0;

	protected int andares;
	protected int capacidade;
	protected int pessoas_Presentes;

	public ElevadorClass(int andares, int capacidade, int pessoas_Presentes) {
		this.andares = andares;
		this.capacidade = capacidade;
		this.pessoas_Presentes = pessoas_Presentes;
	}

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

	public final void entrar() {
		if (this.pessoas_Presentes + 1 > this.capacidade) {
			System.out.println("Não há espaço para mais pessoas no elevador!");
			return;
		}

		this.pessoas_Presentes += 1;
	}
	
	public final void sair() {
		if (this.pessoas_Presentes - 1 < 0) {
			System.out.println("Não há pessoas para sair do elevador!");
			return;
		}
		
		this.pessoas_Presentes -= 1;
	}
	
	public final void sobe() {
		if (this.andar_Atual + 1 > this.andares) {
			System.out.println("Não há mais andares para subir!");
			return;
		}
		
		this.andar_Atual += 1;
	}
	
	public final void desce() {
		if (this.andar_Atual - 1 <= 0) {
			System.out.println("Você já está no térreo!");
			return;
		}
		
		this.andar_Atual -= 1;
	}
}
