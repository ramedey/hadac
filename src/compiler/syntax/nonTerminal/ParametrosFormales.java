package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

import compiler.CompilerContext;
import compiler.lexical.Token;
import compiler.semantic.symbol.SymbolParameter;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ParametrosFormales extends NonTerminal {
	
	List<SymbolParameter> parametros;

	/**
	 * Ambito donde se registrarán los identificadores
	 */
	private ScopeIF scope;
	
	public ParametrosFormales(ListaIdentificadores lista, TypeIF tipo)
	{
		parametros = new ArrayList<SymbolParameter>();
		scope = CompilerContext.getScopeManager().getCurrentScope();
		addParametros(lista, tipo);
		
	}
	
	/**
	 * @return the parametros
	 */
	public List<SymbolParameter> getParametros() {
		return parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(List<SymbolParameter> parametros) {
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
			parametros.add(parametro);			
		}
	}
}
