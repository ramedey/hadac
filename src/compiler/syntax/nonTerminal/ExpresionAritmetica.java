package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.InstructionSetArchitecture;
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
		CompilerContext.getSyntaxErrorManager().syntaxInfo("Expresion aritmetica creada");
	}
	
	@Override
	public String getCodigoOperacion() {		
		return InstructionSetArchitecture.ADD;
	}	

	
}
