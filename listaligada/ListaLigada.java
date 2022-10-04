package listaligada;

public class ListaLigada {

	private Celula primeira = null;
	private Celula ultima = null;
	private int totalDeElementos = 0;

	public Object pegaPrimeiro() {
		if (totalDeElementos == 0) {
			throw new IllegalArgumentException("lista vazia");
		}
		return primeira.getElemento();
	}

	public Object pegaUltimo() {
		if (totalDeElementos == 0) {
			throw new IllegalArgumentException("lista vazia");
		}
		return ultima.getElemento();
	}

	public void adicionarNoComeco(Object novoElemento) {

		if (totalDeElementos == 0) {
			Celula nova = new Celula(novoElemento);
			this.primeira = nova;
			this.ultima = nova;
		} else {
			Celula nova = new Celula(novoElemento, this.primeira);
			this.primeira.setAnterior(nova);
			this.primeira = nova;
		}
		totalDeElementos++;
	}

	public void adicionarNoFinal(Object novoElemento) {

		if (totalDeElementos == 0) {
			adicionarNoComeco(novoElemento);
		} else {
			Celula nova = new Celula(novoElemento);
			this.ultima.setProximo(nova);
			nova.setAnterior(this.ultima);
			this.ultima = nova;
			totalDeElementos++;
		}

	}

	public boolean contem(Object elementoBuscado) {

		Celula atual = this.primeira;

		while (atual != null) {
			if (atual.getElemento().equals(elementoBuscado)) {
				return true;
			}
			atual = atual.getProximo();
		}
		return false;
	}

	public int tamanho() {
		return this.totalDeElementos;
	}

	private Celula pegarCelula(int posicao) {

		Celula atual = this.primeira;
		for (int i = 0; i < posicao; i++) {
			atual = atual.getProximo();
		}
		return atual;
	}

	private boolean posicaoOcupada(int posicao) {
		return posicao >= 0 && posicao < this.totalDeElementos;

	}

	public Object pegar(int posicao) {
		if (!posicaoOcupada(posicao)) {
			throw new IllegalArgumentException("posicao invalida");
		}
		return this.pegarCelula(posicao).getElemento();
	}

	private boolean posicaoValida(int posicao) {
		return posicao >= 0 && posicao <= this.totalDeElementos;

	}

	public void adicionarNaPosicao(int posicao, Object novoElemento) {
		if (!posicaoValida(posicao)) {
			throw new IllegalArgumentException("posicao invalida");
		}
		if (posicao == 0) {
			adicionarNoComeco(novoElemento);
		} else if (posicao == this.totalDeElementos) {
			adicionarNoFinal(novoElemento);
		} else {
			Celula anterior = this.pegarCelula(posicao - 1);
			Celula proximo = anterior.getProximo();

			Celula nova = new Celula(novoElemento, proximo);
			nova.setAnterior(anterior);
			anterior.setProximo(nova);
			proximo.setAnterior(nova);

			totalDeElementos++;
		}
	}

	public void removerDoComeco() {

		if (this.totalDeElementos == 0) {
			throw new IllegalArgumentException("lista vazia");
		}
		this.primeira = this.primeira.getProximo();

		if (this.totalDeElementos > 1) {
			this.primeira.setAnterior(null);
		}
		this.totalDeElementos--;

		if (this.totalDeElementos == 0) {
			this.ultima = null;
		}
	}

	public void removerDoFinal() {

		if (this.totalDeElementos == 0) {
			throw new IllegalArgumentException("lista vazia");
		}
		if (this.totalDeElementos == 1) {
			this.removerDoComeco();
		} else {
			Celula penultima = this.ultima.getAnterior();
			penultima.setProximo(null);
			this.ultima = penultima;
			totalDeElementos--;
		}
	}

	public void remover(int posicao) {

		if (totalDeElementos == 0) {
			throw new IllegalArgumentException("A lista está vazia!");
		}
		if (!posicaoOcupada(posicao)) {
			throw new IllegalArgumentException("A posicao informada é inválida!");
		}
		if (posicao == 0) {
			removerDoComeco();
		} else if (posicao == this.totalDeElementos) {
			removerDoFinal();
		} else {
			Celula anterior = this.pegarCelula(posicao - 1);
			Celula atual = anterior.getProximo();
			Celula proxima = atual.getProximo();

			anterior.setProximo(proxima);
			proxima.setAnterior(anterior);

			this.totalDeElementos--;
		}
	}
}
