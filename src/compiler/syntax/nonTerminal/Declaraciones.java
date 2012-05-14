package compiler.syntax.nonTerminal;

import compiler.CompilerContext;

public class Declaraciones extends  NonTerminal{

	public Declaraciones()
	{
		super();
	}
	
	public Declaraciones(Declaraciones otro)
	{
		super();
		this.setIntermediateCode(otro.getIntermediateCode());
	}
	
	public Declaraciones addDeclaracion(Declaraciones otro)
	{
		this.getIntermediateCode().addAll(0, otro.getIntermediateCode());
		return this;
	}
	
	public Declaraciones addDeclaracion(DecVariables otro)
	{
		CompilerContext.getSemanticErrorManager().semanticDebug(otro);
		this.getIntermediateCode().addAll(0, otro.getIntermediateCode());
		return this;
	}
}
