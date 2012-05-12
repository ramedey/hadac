package compiler.syntax.nonTerminal;

public class Sentencia extends NonTerminal {

	
	public Sentencia()
	{
		super();
	}

	public Sentencia(LlamadaProcedimiento proc)
	{
		super();
		this.setIntermediateCode(proc.getIntermediateCode());
	}
}
