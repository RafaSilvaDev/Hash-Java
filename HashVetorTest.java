import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashVetorTest {

	private HashVetor hash;
	
	@BeforeEach
	void inicializarHash() {
		hash = new HashVetor(10);
	}
	
	@Test
	void criarHashTamanhosInvalidos() {
		assertThrows(IllegalArgumentException.class, ()-> new HashVetor(0));
		assertThrows(IllegalArgumentException.class, ()-> new HashVetor(-1));
	}

	@Test
	void adicionarSemColisao() {
		Cliente cliente1 = new Cliente(22, "Fernando", "Avenida Brasil, 2000"); //0
		Cliente cliente2 = new Cliente(7, "Maria", "Rua das Flores, 777"); //5
		
		assertEquals(0, hash.adicionar(cliente1));
		assertEquals(5, hash.adicionar(cliente2));
		
		assertEquals(22, hash.buscarClientePorCodigo(22).getCodigo());
		assertEquals(7, hash.buscarClientePorCodigo(7).getCodigo());
		
	}
	
	@Test
	void adicionarComColisao() {
		Cliente cliente1 = new Cliente(22, "Fernando", "Avenida Brasil, 2000"); //0
		Cliente cliente2 = new Cliente(7, "Maria", "Rua das Flores, 777"); //5
		
		Cliente cliente3 = new Cliente(3645, "Andre", "Rua XV, 234"); //0
		Cliente cliente4 = new Cliente(511, "Rose", "Rua Floriano Peixoto, 12"); //1
		
		Cliente cliente5 = new Cliente(123, "Marcos", "Rua das Coves, 1442"); //0
		
		
		
		assertEquals(0, hash.adicionar(cliente1));
		assertEquals(5, hash.adicionar(cliente2));
		assertEquals(0, hash.adicionar(cliente3));
		assertEquals(1, hash.adicionar(cliente4));
		assertEquals(0, hash.adicionar(cliente5));
		
		assertEquals(22, hash.buscarClientePorCodigo(22).getCodigo());
		assertEquals(7, hash.buscarClientePorCodigo(7).getCodigo());
		assertEquals(3645, hash.buscarClientePorCodigo(3645).getCodigo());
		assertEquals(511, hash.buscarClientePorCodigo(511).getCodigo());
		assertEquals(123, hash.buscarClientePorCodigo(123).getCodigo());
		
		hash.imprimir();
		
	}
	
	@Test
	void contemSemColisao() {
		Cliente cliente1 = new Cliente(22, "Fernando", "Avenida Brasil, 2000"); //0
		Cliente cliente2 = new Cliente(7, "Maria", "Rua das Flores, 777"); //5
		
		assertEquals(0, hash.adicionar(cliente1));
		assertEquals(5, hash.adicionar(cliente2));
		
		assertTrue(hash.contem(22));
		assertTrue(hash.contem(7));
	}
	
	@Test
	void contemComColisao() {
		Cliente cliente1 = new Cliente(22, "Fernando", "Avenida Brasil, 2000"); //0
		Cliente cliente2 = new Cliente(7, "Maria", "Rua das Flores, 777"); //5
		
		Cliente cliente3 = new Cliente(3645, "Andre", "Rua XV, 234"); //0
		Cliente cliente4 = new Cliente(511, "Rose", "Rua Floriano Peixoto, 12"); //1
		
		Cliente cliente5 = new Cliente(123, "Marcos", "Rua das Coves, 1442"); //0
		
		
		
		assertEquals(0, hash.adicionar(cliente1));
		assertEquals(5, hash.adicionar(cliente2));
		assertEquals(0, hash.adicionar(cliente3));
		assertEquals(1, hash.adicionar(cliente4));
		assertEquals(0, hash.adicionar(cliente5));
		
		assertTrue(hash.contem(22));
		assertTrue(hash.contem(7));
		assertTrue(hash.contem(3645));
		assertTrue(hash.contem(511));
		assertTrue(hash.contem(123));
	}
	
	
	
}
