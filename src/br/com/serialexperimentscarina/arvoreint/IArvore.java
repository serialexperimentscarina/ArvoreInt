package br.com.serialexperimentscarina.arvoreint;

public interface IArvore {
	
	public void insert(int valor);
	public void search(int valor) throws Exception;
	public void remove(int valor) throws Exception;
	public void prefixSearch() throws Exception;
	public void infixSearch() throws Exception;
	public void postfixSearch() throws Exception;

}
