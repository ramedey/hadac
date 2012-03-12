package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

import compiler.lexical.Token;

/**
 * 
 * Lista de identificadores de clase Token
 * @author amedey
 *
 */
public class ListaIdentificadores extends NonTerminal {

	private List<Token> listaIdentificadores = new ArrayList<Token>(); //Lista de identificadores
	
	/**
	 * @return the listaIdentificadores
	 */
	public List<Token> getListaIdentificadores() {
		return listaIdentificadores;
	}

	/**
	 * @param listaIdentificadores the listaIdentificadores to set
	 */
	public void setListaIdentificadores(
			List<Token> listaIdentificadores) {
		this.listaIdentificadores = listaIdentificadores;
	}

	public ListaIdentificadores(){
		super();
	}
	
	public void addIdentificador(Token token)
	{
		//TODO: comprobar que no se inserte dos veces el mism símbolo		
		listaIdentificadores.add(token);		
	}
	
	public boolean contiene(Token token)
	{
		return listaIdentificadores.contains(token);
	}
}
