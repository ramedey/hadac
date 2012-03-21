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

	public void addParametro(ParametroFormal p)
	{
		ListaIdentificadores lista = p.listaIdentificadores;
		
		for(Token t : lista.getListaIdentificadores())
		{
			parametros.put(t.getLexema(), p.tipo);			
		}
	}
}
