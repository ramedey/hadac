package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

import compiler.lexical.Token;
import compiler.semantic.symbol.SymbolVariable;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class DecVariable extends NonTerminal {

	TypeIF tipo;
	List<SymbolVariable> listaVariables;
	
	/**
	 * @return the listaVariables
	 */
	public List<SymbolVariable> getListaVariables() {
		return listaVariables;
	}

	public DecVariable()
	{
		super();
		listaVariables = new ArrayList<SymbolVariable>();
	}
	
	/**
	 * Construye un objeto DecVariable a partir de una lista de tokens.
	 * @param lista Lista de tokens, se supone que no hay tokens repetidos, ya que la 
	 * clase ListaIdentificadores no lo permite.
	 * @param tipo
	 * @param scope
	 */
	public DecVariable(ListaIdentificadores lista, TypeIF tipo, ScopeIF scope)
	{
		this.tipo = tipo; 
		for(Token token : lista.getListaIdentificadores())
		{
			listaVariables.add(new SymbolVariable(scope, token, tipo));
		}
	}
	
	/**
	 * @return the tipo
	 */
	public TypeIF getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TypeIF tipo) {
		this.tipo = tipo;
	}

	public void addSymbolVariable(SymbolVariable variable)
	{
		listaVariables.add(variable);
	}
	
}
