import listaligada.ListaLigada;

public class HashVetor {

	private int tamanho;
	private TabelaHash tabelaHash;

	public HashVetor(int tamanho) {
		if (tamanho <= 0) {
			throw new IllegalArgumentException("Tamanho invalido. Deve ser maior que zero");
		}
		tabelaHash = new TabelaHash(tamanho);
		this.tamanho = tamanho;
	}

	private int calculaHash(int chave) {

		String chaveConvertida = String.valueOf(chave);
		int h = 0;
		for (int i = 0; i < chaveConvertida.length(); i++) {
			h = 31 * h + chaveConvertida.charAt(i);
		}
		return h % tamanho;
	}

	public int adicionar(Cliente novoCliente) {

		int enderecoHash = calculaHash(novoCliente.getCodigo());

		if (tabelaHash.getElementos()[enderecoHash] != null) {
			tabelaHash.getColisoes()[enderecoHash].adicionarNoFinal(novoCliente);

		} else {
			tabelaHash.getElementos()[enderecoHash] = novoCliente;
		}
		return enderecoHash;

	}

	public void imprimir() {
		for (Cliente cliente : tabelaHash.getElementos()) {
			if (cliente != null) {
				System.out.println("Hashing: " + calculaHash(cliente.getCodigo()) + " - " + cliente.toString());
				imprimirListaColisoes(cliente);
			}
		}
	}

	private void imprimirListaColisoes(Cliente cliente) {
		int enderecoHash = calculaHash(cliente.getCodigo());
		if (tabelaHash.getColisoes()[enderecoHash].tamanho() > 0) {

			for (int i = 0; i < tabelaHash.getColisoes()[enderecoHash].tamanho(); i++) {
				Cliente clienteColisao = (Cliente) tabelaHash.getColisoes()[enderecoHash].pegar(i);
				System.out.println(
						"Hashing: " + calculaHash(clienteColisao.getCodigo()) + " - " + clienteColisao.toString());

			}
		}
	}

	public Cliente buscarClientePorCodigo(int codigoClienteBuscado) {
		int enderecoHash = calculaHash(codigoClienteBuscado);

		if (tabelaHash.getElementos()[enderecoHash] != null) {
			Cliente cliente = tabelaHash.getElementos()[enderecoHash];
			if (cliente.getCodigo() == codigoClienteBuscado) {
				return cliente;
			} else {
				if (tabelaHash.getColisoes()[enderecoHash].tamanho() > 0) {

					for (int i = 0; i < tabelaHash.getColisoes()[enderecoHash].tamanho(); i++) {
						Cliente clienteColisao = (Cliente) tabelaHash.getColisoes()[enderecoHash].pegar(i);
						if (clienteColisao.getCodigo() == codigoClienteBuscado) {
							return clienteColisao;
						}
					}
				}

			}
		}

		return null;
	}

	public boolean contem(int codigoElemento) {
		int enderecoHash = calculaHash(codigoElemento);

		if (tabelaHash.getElementos()[enderecoHash] != null) {
			Cliente cliente = tabelaHash.getElementos()[enderecoHash];
			if (cliente.getCodigo() == codigoElemento) {
				return true;
			} else {
				if (tabelaHash.getColisoes()[enderecoHash].tamanho() > 0) {
					for (int i = 0; i < tabelaHash.getColisoes()[enderecoHash].tamanho(); i++) {
						Cliente clienteColisao = (Cliente) tabelaHash.getColisoes()[enderecoHash].pegar(i);
						if (clienteColisao.getCodigo() == codigoElemento) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}
	//ve se ele tem uma lista. Se tiver, pega o primeiro e seta ele no vetor. Se não, deixa a posição nula.	
	public void removerElemento(int codigoElemento) {
		int enderecoHash = calculaHash(codigoElemento);

		if (tabelaHash.getElementos()[enderecoHash] != null) {
			Cliente cliente = tabelaHash.getElementos()[enderecoHash];
			if (cliente.getCodigo() == codigoElemento) {
				if (tabelaHash.getColisoes()[enderecoHash].tamanho() > 0) {
					tabelaHash.getElementos()[enderecoHash] = buscarClientePorCodigo((int) tabelaHash.getColisoes()[enderecoHash].pegaPrimeiro());
				}else {
					
				}
			} else {
				if (tabelaHash.getColisoes()[enderecoHash].tamanho() > 0) {
					for (int i = 0; i < tabelaHash.getColisoes()[enderecoHash].tamanho(); i++) {
						Cliente clienteColisao = (Cliente) tabelaHash.getColisoes()[enderecoHash].pegar(i);
						if (clienteColisao.getCodigo() == codigoElemento) {
							return true;
						}
					}
				}
			}
		}
	}

}
