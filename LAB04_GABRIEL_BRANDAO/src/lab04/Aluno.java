package lab04;

public class Aluno {
	
	private String matricula;
	private String nome;
	private String curso;
	
	public Aluno(String matricula, String nome, String curso) {
		this.matricula = matricula;
		this.nome = nome;
		this.curso = curso;
	}
	
	public String getMatricula() {
		return this.matricula;
	}
	
	public String toString() {
		return this.matricula + " - " + this.nome + " - " + this.curso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
	
	
	
}
