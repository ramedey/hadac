package compiler.syntax.nonTerminal;

import java.util.ArrayList;

import compiler.semantic.symbol.SymbolVariable;

public class ListaIdentificadores extends NonTerminal {

	private ArrayList<SymbolVariable> listaIdentificadores = new ArrayList<SymbolVariable>(); //Lista de identificadores
	
	/**
	 * @return the listaIdentificadores
	 */
	public ArrayList<SymbolVariable> getListaIdentificadores() {
		return listaIdentificadores;
	}

	/**
	 * @param listaIdentificadores the listaIdentificadores to set
	 */
	public void setListaIdentificadores(
			ArrayList<SymbolVariable> listaIdentificadores) {
		this.listaIdentificadores = listaIdentificadores;
	}

	public ListaIdentificadores(){
		super();
	}
	
	public void addIdentificador(SymbolVariable symbol)
	{
		//TODO: comprobar que no se inserte dos veces el mism símbolo
		listaIdentificadores.add(symbol);		
	}
}
