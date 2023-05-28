package br.com.serialexperimentscarina.arvoreint;

public class ArvoreInt implements IArvore{
	
	No raiz;
	
	public ArvoreInt() {
		raiz = null;
	}

	@Override
	public void insert(int valor) {
		No no = new No();
		no.dado = valor;
		no.esquerda = null;
		no.direita = null;
		insertLeaf(no, raiz);
	}

	private void insertLeaf(No no, No raizAtual) {
		if (raiz == null) {
			raiz = no;
		} else if (no.dado < raizAtual.dado) {
			if (raizAtual.esquerda == null) {
				raizAtual.esquerda = no;
			} else {
				insertLeaf(no, raizAtual.esquerda);
			}
		} else if (no.dado >= raizAtual.dado) {
			if (raizAtual.direita == null) {
				raizAtual.direita = no;
			} else {
				insertLeaf(no, raizAtual.direita);
			}
		}
	}

	@Override
	public void search(int valor) throws Exception {
		try {
			No no = nodeSearch(raiz, valor);
			int level = nodeLevel(raiz, valor);
			System.out.println(no + " - [Nível: " + level + " ]");
		} catch (Exception e) {
			throw new Exception("Valor não encontrado!");
		}
		
	}

	private No nodeSearch(No raizAtual, int valor) throws Exception {
		if (raizAtual == null) {
			throw new Exception("Árvore vazia!");
		} else if (valor < raizAtual.dado) {
			return nodeSearch(raizAtual.esquerda, valor);
		} else if (valor > raizAtual.dado) {
			return nodeSearch(raizAtual.direita, valor);
		} else {
			return raizAtual;
		}
	}

	private int nodeLevel(No raizAtual, int valor) throws Exception {
		if (raizAtual == null) {
			throw new Exception("Árvore vazia!");
		} else if (valor < raizAtual.dado) {
			return 1 + nodeLevel(raizAtual.esquerda, valor);
		} else if (valor > raizAtual.dado) {
			return 1 + nodeLevel(raizAtual.direita, valor);
		} else {
			return 0;
		}
	}

	@Override
	public void remove(int valor) throws Exception {
		try {
			removeChild(raiz, valor);
		} catch (Exception e) {
			throw new Exception("Valor não encontrado!");
		}
	}

	private No removeChild(No raizAtual, int valor) throws Exception {
		if (raizAtual == null) {
			throw new Exception("Árvore vazia!");
		} else if (valor < raizAtual.dado) {
			raizAtual.esquerda = removeChild(raizAtual.esquerda, valor);
		} else if (valor > raizAtual.dado) {
			raizAtual.direita = removeChild(raizAtual.direita, valor);
		} else {
			if (raizAtual.esquerda == null && raizAtual.direita == null) {
				raizAtual = null;
			} else if (raizAtual.esquerda == null) {
				raizAtual = raizAtual.direita;
			} else if (raizAtual.direita == null) {
				raizAtual = raizAtual.esquerda;
			} else {
				No no = raizAtual.direita;
				while (no.esquerda != null) {
					no = no.esquerda;
				}
				raizAtual.dado = no.dado;
				no.dado = valor;
				raizAtual.direita = removeChild(raizAtual.direita, valor);
			}
		}
		return raizAtual;
		
	}

	@Override
	public void prefixSearch() throws Exception {
		System.out.println("Busca préfixa: ");
		prefix(raiz);
	}

	private void prefix(No raizAtual) throws Exception{
		if (raizAtual == null) {
			throw new Exception("Árvore vazia!");
		} else {
			System.out.print(raizAtual.dado + " ");
			if (raizAtual.esquerda != null) {
				prefix(raizAtual.esquerda);
			}
			if (raizAtual.direita != null) {
				prefix(raizAtual.direita);
			}
		}
	}

	@Override
	public void infixSearch() throws Exception {
		System.out.println("Busca infixa: ");
		infix(raiz);
	}

	private void infix(No raizAtual) throws Exception {
		if (raizAtual == null) {
			throw new Exception("Árvore vazia!");
		} else {
			if (raizAtual.esquerda != null) {
				infix(raizAtual.esquerda);
			}
			System.out.print(raizAtual.dado + " ");
			if (raizAtual.direita != null) {
				infix(raizAtual.direita);
			}
		}
	}

	@Override
	public void postfixSearch() throws Exception {
		System.out.println("Busca pósfixa: ");
		postfix(raiz);		
	}

	private void postfix(No raizAtual) throws Exception {
		if (raizAtual == null) {
			throw new Exception("Árvore vazia!");
		} else {
			if (raizAtual.esquerda != null) {
				postfix(raizAtual.esquerda);
			}
			if (raizAtual.direita != null) {
				postfix(raizAtual.direita);
			}
			System.out.print(raizAtual.dado + " ");
		}
	}

}
