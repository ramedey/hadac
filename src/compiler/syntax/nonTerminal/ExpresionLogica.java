package compiler.syntax.nonTerminal;

import compiler.semantic.type.TypeSimpleBoolean;

import es.uned.lsi.compiler.semantic.ScopeIF;

public class ExpresionLogica extends Expresion {

	private boolean value;
	private Operation operacion;
	
	public enum Operation{Eq, Neq, Gt, Lt};
	
	public ExpresionLogica(){
		super();
	}
	
	public ExpresionLogica(boolean value, ScopeIF scope) {
		this.value = value;
		this.setTipoInstruccion(new TypeSimpleBoolean(scope));
	}
	
	public ExpresionLogica(boolean value, ScopeIF scope, Operation operacion) {
		this.value = value;
		this.setTipoInstruccion(new TypeSimpleBoolean(scope));
		this.operacion = operacion;
	}
	
	public ExpresionLogica(Expresion e1, Expresion e2) throws Exception
	{
		super(e1, e2);
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void doOperation(Expresion e1, Expresion e2) {
		this.value = Boolean.parseBoolean(e1.getValue().toString());		
	}
}
