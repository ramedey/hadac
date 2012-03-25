package compiler.syntax.nonTerminal;

public class Sentencia extends NonTerminal {

	
	private boolean esSentenciaReturn = false;
	
	public Sentencia()
	{
		super();
	}

	public void setSentenciaReturn(boolean esSentenciaReturn) {
		this.esSentenciaReturn = esSentenciaReturn;
	}

	public boolean isSentenciaReturn() {
		return esSentenciaReturn;
	}
	
}
