package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

import compiler.CompilerContext;
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
	
	/**
	 * A�ade un token a la lista de identificadores comprobando que no haya repeticiones
	 * @param token
	 */
	public void addIdentificador(Token token)
	{
		//TODO: comprobar que no se inserte dos veces el mism s�mbolo	
		if(contiene(token))
		{
			CompilerContext.getSemanticErrorManager().semanticFatalError("El s�mbolo " + token.getLexema() + " est� repetido");
		}
		
		listaIdentificadores.add(token);		
	}
	
	public boolean contiene(Token token)
	{
		return listaIdentificadores.contains(token);
	}
}
