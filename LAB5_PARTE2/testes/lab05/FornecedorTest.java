package lab05;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Cliente;
import entities.Fornecedor;

class FornecedorTest {

	private Fornecedor f1;
	private Fornecedor f2;
	@SuppressWarnings("unused")
	private Cliente c1;

	@BeforeEach
	void setUp() {
		this.f1 = new Fornecedor("Gabriel", "gabriel@ccc", "4002-8922");
		this.f2 = new Fornecedor("Jujubinha", "jujuba@ccc", "9191-2323");
		this.c1 = new Cliente("12345678911", "Max", "maxzinho@ccc", "casa");
		f1.cadastraProduto("Abacate", "Abacate de uva", 5.1);
		f1.cadastraProduto("Pizza", "Pizza de frango", 15.50);
		f2.cadastraProduto("Leite", "Leite com pera", 15.12);
		String[] produtinhos = { "Abacate - Abacate de uva", "Pizza - Pizza de frango"};
		f1.cadastraCombo("Promo 1", "Vale a pena", 0.63, 20.60, produtinhos);
		f1.criaConta("12345678911"); 
	}

	@Test
	void testSaida() {
		assertEquals("Gabriel - gabriel@ccc - 4002-8922", f1.toString());
		assertEquals("Jujubinha - jujuba@ccc - 9191-2323", f2.toString());
	}

	@Test
	void testAlteraEmail() {
		this.f1.setEmail("gabriel.brandao@ccc");
		;
		this.f2.setEmail("juju@ccc");
		assertEquals("Gabriel - gabriel.brandao@ccc - 4002-8922", f1.toString());
		assertEquals("Jujubinha - juju@ccc - 9191-2323", f2.toString());
	}

	@Test
	void testAlteraTelefone() {
		this.f1.setTelefone("5555-5555");
		this.f2.setTelefone("1234-5678");
		assertEquals("Gabriel - gabriel@ccc - 5555-5555", f1.toString());
		assertEquals("Jujubinha - jujuba@ccc - 1234-5678", f2.toString());
	}

	@Test
	void testExibeProduto() {
		assertEquals("Abacate - Abacate de uva - R$5,10", f1.exibeUmProduto("Abacate", "Abacate de uva"));
		assertEquals("Leite - Leite com pera - R$15,12", f2.exibeUmProduto("Leite", "Leite com pera"));
		assertEquals("Produto nao cadastrado", f1.exibeUmProduto("Banana", "Banana com leite"));
	}

	@Test
	void testEProdutosIguais() {
		assertTrue(f2.verificaIgual("Leite", "Leite com pera"));
		assertFalse(f2.verificaIgual("Banana", "Banana com abacaxi"));
	}

	@Test
	void testExibeTodosProdutosUmFornecedor() {
		f1.cadastraProduto("Jujuba", "Jujuba e leite", 2.50);
		assertEquals(
				"Gabriel - Abacate - Abacate de uva - R$5,10 | Gabriel - Jujuba - Jujuba e leite - R$2,50 | Gabriel - Pizza - Pizza de frango - R$15,50 | Gabriel - Promo 1 - Vale a pena - R$7,62",
				f1.exibeTodosProdutosUmFornecedor());
		assertEquals("Jujubinha - Leite - Leite com pera - R$15,12", f2.exibeTodosProdutosUmFornecedor());
	}

	@Test
	void testAlteraPreco() {
		assertTrue(f1.alteraPreco("Abacate", "Abacate de uva", 2.30));
		assertTrue(f2.alteraPreco("Leite", "Leite com pera", 2.50));
	}

	@Test
	void testRemoveProduto() {
		assertTrue(f1.removeProduto("Abacate", "Abacate de uva"));
		assertTrue(f2.removeProduto("Leite", "Leite com pera"));
		assertFalse(f1.removeProduto("Bananinha", "Baninha e maca"));
		assertFalse(f2.removeProduto("X frango", "X frango com bacon"));
	}

	@Test
	void testFornecedoresIguais() {
		Fornecedor f3 = new Fornecedor("Gabriel", "gab@ccc.ufcg.edu.br", "9090-5050");
		assertEquals(f3.hashCode(), f1.hashCode());
		assertTrue(f1.equals(f3));
		assertFalse(f1.equals(f2));
	}

	@Test
	void testVerificaComboExiste() {
		assertTrue(f1.verificaComboExiste("Promo 1 Vale a pena"));
		assertFalse(f1.verificaComboExiste("Jujuba com doce de leite"));
	}
		
	@Test
	void testGetDebitoCliente() {
		f1.insereProdutoNaConta("12345678911", "20/05/2010", "Promo 1", "Vale a pena");
		assertEquals("7.62",f1.pegaValorDaConta("12345678911"));
	}
	
	@Test
	void testVerifiaSeTemConta() {
		assertTrue(f1.verificaSeTemConta("12345678911"));
		assertFalse(f1.verificaSeTemConta("98765432101"));
	}
	
	@Test
	void testRealizaPagamento() {
		f1.realizaPagamento("12345678911");
	}
	
	@Test
	void testExibeContaCliente() {
		f1.insereProdutoNaConta("12345678911", "20/05/2010", "Promo 1", "Vale a pena");
		assertEquals("Gabriel | Promo 1 - 20-05-2010", f1.exibeContas("12345678911"));
	}
	
	@Test
	void testEntradaVazia() {
		try {
			new Fornecedor("", "gabriel@ambev", "4002-8922");
		} catch (IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			new Fornecedor("Gabriel", "", "4002-8922");
		} catch (IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			new Fornecedor("Gabriel", "gabriel@ambev", "");
		} catch (IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			f1.cadastraProduto("", "Leite com pera", 10.20);
		} catch (IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			f1.cadastraProduto("Leite", "", 10.20);
		} catch (IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.", iae.getMessage());
		}
		try {
			f1.cadastraProduto("Leite", "Leite com pera", -1);
		} catch (IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de produto: preco invalido.", iae.getMessage());
		}
	}

	@Test
	void testEntradaNula() {
		try {
			new Fornecedor(null, "gabriel@ambev", "4002-8922");
		} catch (IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			new Fornecedor("Gabriel", null, "4002-8922");
		} catch (IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			new Fornecedor("Gabriel", "gabriel@ambev", null);
		} catch (IllegalArgumentException iae) {
			assertEquals("Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			f1.cadastraProduto(null, "Leite com pera", 10.20);
		} catch (IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.", iae.getMessage());
		}
		try {
			f1.cadastraProduto("Leite", null, 10.20);
		} catch (IllegalArgumentException iae) {
			assertEquals("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.", iae.getMessage());
		}
	}
}
