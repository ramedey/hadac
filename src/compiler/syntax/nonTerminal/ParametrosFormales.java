package compiler.syntax.nonTerminal;

import java.util.HashMap;

import compiler.lexical.Token;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ParametrosFormales extends NonTerminal {
	
	HashMap<String, TypeIF> parametros;

	/**
	 * @return the parametros
	 */
	public HashMap<String, TypeIF> getParametros() {
		return parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(HashMap<String, TypeIF> parametros) {
		this.parametros = parametros;
	}

	public void addParametros(ListaIdentificadores lista, TypeIF tipo)
	{	
		for(Token t : lista.getListaIdentificadores())
		{
			parametros.put(t.getLexema(), tipo);			
		}
	}
}
