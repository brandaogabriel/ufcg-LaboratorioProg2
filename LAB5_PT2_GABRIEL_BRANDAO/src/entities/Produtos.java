package entities;

/**
 * Representacao do produto de um fornecedor, este por sua vez possui nome,
 * descricao e um preco.
 */

public class Produtos implements Comparable<Produtos> {

	/**
	 * Representa o nome de um produto
	 */
	private String nome;

	/**
	 * Representa a descricao de um produto
	 */
	private String descricao;

	/**
	 * Representa o preco de um produto
	 */
	protected double preco;

	/**
	 * Representa um conjunto de metodos do tipo Excecoes para realizar validacao de
	 * dados de entrada
	 */
	private Excecoes valida;

	/**
	 * Constroi um produto a partir de seu nome, descricao e preco
	 * 
	 * @param nome      recebe o nome do produto
	 * @param descricao recebe a descricao do produto
	 * @param preco     recebe o preco do produto
	 */
	public Produtos(String nome, String descricao, double preco) {
		this.valida = new Excecoes();
		valida.validaCadastroProduto(nome, descricao, preco);
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}

	/**
	 * O metodo retorna o nome de um produto
	 * 
	 * @return nome do produto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * O metodo altera o preco de um produto
	 * 
	 * @param preco recebe o novo preco
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * O metodo retorna o preco de um produto
	 * 
	 * @return preco do produto
	 */
	public double getPreco() {
		return this.preco;
	}

	/**
	 * O metodo formata o valor de um produto acrescentano uma cada decimal e
	 * trocando o '.' (ponto) pela ',' (virgula).
	 * 
	 * @return o preco formatado.
	 */
	public String toStringPreco() {
		String s = String.format("%.2f", this.preco);
		s.replaceAll(".", ",");
		return s;
	}

	/**
	 * Retorna a String que representa o produto de um fornecedor. No formato NNNN -
	 * DDDD - R$PP,PP onde 'N' representa o nome do produto, 'D' representa a
	 * descricao do produto e 'P' representa o valor do produto.
	 */
	@Override
	public String toString() {
		return this.nome + " - " + this.descricao + " - " + "R$" + toStringPreco();
	}

	/**
	 * Retorna um numero inteiro que representa um codigo que eh gerado a partir das
	 * informacoes do objeto Produto.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * O metodo compara se um objeto eh igual ao outro, retornando true ou false. No
	 * caso em questao ele ira verificar se o nome e a descricao de um produto sao
	 * iguais ao nome e descricao de outro produto. Caso seja, retornara true, caso
	 * contrario, retornara false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produtos other = (Produtos) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/**
	 * O metodo compara se o nome de um produto eh maior que o outro alfabeticamente
	 * 
	 * @return um numero inteiro
	 */
	@Override
	public int compareTo(Produtos o) {
		return this.nome.compareTo(o.getNome());
	}

}