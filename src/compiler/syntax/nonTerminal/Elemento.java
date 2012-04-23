package compiler.syntax.nonTerminal;

import compiler.semantic.symbol.SymbolVariable;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Elemento extends Expresion {

	private Object resultado;
	
	/**
	 * @return the resultado
	 */
	public Object getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(Object resultado) {
		this.resultado = resultado;
	}

	public Elemento(Object valor, TypeIF tipo)
	{
		super(tipo);
		resultado = valor;
	}
	
	public Elemento(SymbolVariable variable)
	{
		super(variable.getType());
		resultado = variable.getValor();
	}
	
	@Override
	public Object getValue() {
		
		return resultado;
	}

	

}
