package compiler.syntax.nonTerminal;

import compiler.semantic.type.TypeSimpleInteger;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class ExpresionAritmetica extends ExpresionOperacion {

	// valor entero del contexto de la expresión
	private int value;
	
	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	public ExpresionAritmetica(int value, ScopeIF scope) {
		this.value = value;
		this.setTipoInstruccion(new TypeSimpleInteger(scope));
	}

	public ExpresionAritmetica(Expresion e1, Expresion e2) throws Exception
	{
		super(e1, e2);		
	}
	
	@Override
	public void doOperation(Expresion e1, Expresion e2){
		value = Integer.parseInt(e1.getValue().toString()) + Integer.parseInt(e2.getValue().toString());
	}	

	
}
