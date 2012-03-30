package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SentenciaReturn extends Sentencia {

	private Expresion expresion;
	
	public SentenciaReturn(){
		super();
	}
	
	public SentenciaReturn(Expresion exp){
		super();
		expresion = exp;
	}
	
	public TypeIF getTipoDevolucion()
	{
		if(expresion != null)
			return expresion.getTipoInstruccion();
		return null;
	}
}
