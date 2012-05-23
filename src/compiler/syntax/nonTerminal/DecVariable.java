package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

import compiler.CompilerContext;
import compiler.lexical.Token;
import compiler.semantic.symbol.SymbolVariable;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class DecVariable extends NonTerminal {

	private TypeIF tipo;
	private List<SymbolVariable> listaVariables;
	
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
	 * Comprueba si hay en el mismo ambito otros identificadores con el mismo nombre.
	 * Registra cada símbolo en el ámbito actual.
	 * @param lista Lista de tokens, se supone que no hay tokens repetidos, ya que la 
	 * clase ListaIdentificadores no lo permite.
	 * @param tipo
	 * @param scope
	 */
	public DecVariable(ListaIdentificadores lista, TypeIF tipo, ScopeIF scope)
	{
		this.tipo = tipo; 
		listaVariables = new ArrayList<SymbolVariable>();
		for(Token token : lista.getListaIdentificadores())
		{
			if(!this.existeIdentificadorEnAmbito(scope, token.getLexema()))
			{
				//Registra cada símbolo en el ámbito pasado como parámetro
//				CompilerContext.getSyntaxErrorManager().syntaxInfo("Registrando simbolo " + token.getLexema() + " en ambito " + scope.getName());
//				scope.getSymbolTable().addSymbol(new SymbolVariable(scope, token, tipo));
				listaVariables.add(new SymbolVariable(scope, token, tipo));
//				CompilerContext.getSyntaxErrorManager().syntaxInfo("Fin Registro simbolo");
			}else{
				CompilerContext.getSemanticErrorManager().semanticError("El identificador " + token.getLexema() + " ya existe.");
			}
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
	
	public boolean existeIdentificadorEnAmbito(ScopeIF scope, String simbolo)
	{
		return scope.getSymbolTable().containsSymbol(simbolo);
	}
	
	public void registrarVariables()
	{
		for(SymbolVariable sVar: listaVariables)
		{
			sVar.getScope().getSymbolTable().addSymbol(sVar);
		}
	}
}
