package lab04;

import java.util.Scanner;

public class Program {

	private static Controle c = new Controle();
	
	private static Scanner sc = new Scanner(System.in);
	
	private static void menu() {
		System.out.println("(C)adastrar Aluno");
		System.out.println("(E)xibir Aluno");
		System.out.println("(N)ovo Grupo");
		System.out.println("(A)locar Aluno no Grupo e Imprimir Grupos");
		System.out.println("(R)egistrar Aluno que Respondeu");
		System.out.println("(I)mprimir Alunos que Responderam");
		System.out.println("(O)ra, vamos fechar o programa!");
		System.out.print("Opcao> ");
	}
	
	private static void cadastraAluno() {
		System.out.println();
		System.out.print("Matricula: ");
		String matricula = sc.nextLine();
		System.out.print("Nome: ");
		String nome = sc.nextLine();
		System.out.print("Curso: ");
		String curso = sc.nextLine();
		if (!c.cadastraAluno(matricula, nome, curso)) {
			System.out.println("Matricula ja cadastrada!" + System.lineSeparator());
		}
		
		else {
			c.cadastraAluno(matricula, nome, curso);
			System.out.println("Cadastro realizado com sucesso!" + System.lineSeparator());
		}
	}
	
	private static void exibeAluno() {
		System.out.println();
		System.out.print("Matricula: ");
		String matricula = sc.nextLine();
		
		if (c.exibeAluno(matricula) == null) {
			System.out.println("Aluno nao cadastrado." + System.lineSeparator());
		}else System.out.println("Aluno: " + c.exibeAluno(matricula) + System.lineSeparator());
	}
	
	private static void registraResposta() {
		System.out.println();
		System.out.print("Matricula: ");
		String matricula = sc.nextLine();
		if (c.cadastraAlunoRespondeu(matricula)) {
			System.out.println("Aluno Registrado!" + System.lineSeparator());
		}else System.out.println("Aluno nao cadastrado!" + System.lineSeparator());
	}
	
	private static void imprimeResposta() {
		if (c.exibeAlunosRespondeu().equals("Alunos: " + System.lineSeparator())) {
			System.out.println("Nenhum aluno respondeu!");
		}
		else {
			System.out.println(c.exibeAlunosRespondeu());
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		menu();
		do {
			String opcao = sc.nextLine();
			if (opcao.toUpperCase().equals("C")) {
				cadastraAluno();
			}
			
			else if (opcao.toUpperCase().equals("E")) {
				exibeAluno();
			}
			
			else if (opcao.equals("N")) {
		
			}
			
			else if (opcao.equals("A")) {
				
			}
			
			else if (opcao.toUpperCase().equals("R")) {
				registraResposta();
			}
			
			else if (opcao.toUpperCase().equals("I")) {
				imprimeResposta();
			}
			
			else if (opcao.equals("O")) {
				break;
			}
			else {
				System.out.println("OPCAO INVALIDA!" + System.lineSeparator());
			}
			menu();
		} while (true);
		
	}
		
}
