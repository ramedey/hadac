package compiler.syntax.nonTerminal;

import java.util.HashMap;

import compiler.CompilerContext;
import compiler.lexical.Token;
import compiler.semantic.symbol.SymbolParameter;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ParametrosFormales extends NonTerminal {
	
	HashMap<String, TypeIF> parametros;

	/**
	 * Ambito donde se registrarán los identificadores
	 */
	private ScopeIF scope;
	
	public ParametrosFormales(ListaIdentificadores lista, TypeIF tipo)
	{
		parametros = new HashMap<String, TypeIF>();
		scope = CompilerContext.getScopeManager().getCurrentScope();
		addParametros(lista, tipo);
		
	}
	
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

	/**
	 * Agrega los parametros de la lista a la clase ParametrosFormales.
	 * Registra los identificadores en el ambito actual.
	 * @param lista
	 * @param tipo
	 */
	public void addParametros(ListaIdentificadores lista, TypeIF tipo)
	{	
		for(Token t : lista.getListaIdentificadores())
		{
			SymbolParameter parametro = new SymbolParameter(scope, t.getLexema(), tipo);
			scope.getSymbolTable().addSymbol(parametro);
			parametros.put(t.getLexema(), tipo);			
		}
	}
}
