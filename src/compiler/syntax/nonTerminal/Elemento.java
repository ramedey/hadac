package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.semantic.symbol.SymbolBooleanConstant;
import compiler.semantic.symbol.SymbolIntegerConstant;
import compiler.semantic.symbol.SymbolVariable;

import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
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
	
	public Elemento(SymbolIF simbolo)
	{
		super(simbolo.getType());
		if(simbolo instanceof SymbolVariable)
        {
			resultado = ((SymbolVariable)simbolo).getValor();
        }else if(simbolo instanceof SymbolBooleanConstant){
        	resultado = ((SymbolBooleanConstant)simbolo).getValue();
        }else if(simbolo instanceof SymbolIntegerConstant){
        	resultado = ((SymbolIntegerConstant)simbolo).getValue();
        }else{
        	CompilerContext.getSemanticErrorManager().semanticFatalError("El identificador debe ser una variable o constante");
        }
	}
	
	@Override
	public Object getValue() {
		
		return resultado;
	}

	

}
