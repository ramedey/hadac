package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.semantic.symbol.SymbolVariable;

public class Declaraciones extends  NonTerminal{

	public Declaraciones()
	{
		super();
	}
	
	public Declaraciones(Declaraciones otro)
	{
		this();
		this.setIntermediateCode(otro.getIntermediateCode());
	}
	
	public Declaraciones addDeclaracion(Declaraciones otro)
	{
		CompilerContext.getSemanticErrorManager().semanticDebug("A�adiendo delaraciones de " + otro);
		this.getIntermediateCode().addAll(0, otro.getIntermediateCode());
		return this;
	}
	
	/**
	 * Agrega la declaraci�n de variables a la lista y registra cada variable en el �mbito actual.
	 */
	public Declaraciones addDeclaracion(DecVariables otro)
	{		
		for(DecVariable dVar : otro.getDeclaraciones())
		{
			for(SymbolVariable symbol : dVar.getListaVariables())
			{
				if(!CompilerContext.getScopeManager().getCurrentScope().getSymbolTable().containsSymbol(symbol))
				{
					CompilerContext.getScopeManager().getCurrentScope().getSymbolTable().addSymbol(symbol);
				}
			}
		}
		if(otro.getIntermediateCode() != null)
		{
			this.getIntermediateCode().addAll(0, otro.getIntermediateCode());
		}
		return this;
	}
}
