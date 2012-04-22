package compiler.syntax.nonTerminal;

import compiler.semantic.type.TypeSimpleBoolean;

import es.uned.lsi.compiler.semantic.ScopeIF;

public class ExpresionLogica extends ExpresionOperacion {

	private boolean value;
	private Operation operacion;
	
	public enum Operation{Eq, Gt, Or};
	
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
		this.setOperacion(operacion);
	}
	
	public ExpresionLogica(Expresion e1, Expresion e2, Operation operation) throws Exception
	{
		super(e1, e2);
		this.setOperacion(operation);
	}

	@Override
	public Object getValue() {
		return value;
	}

	public void setOperacion(Operation operacion) {
		this.operacion = operacion;
	}

	public Operation getOperacion() {
		return operacion;
	}

	@Override
	public void doOperation(Expresion e1, Expresion e2) throws Exception {
		switch(operacion){
		case Eq:
			this.value = Boolean.parseBoolean(e1.getValue().toString()) == Boolean.parseBoolean(e2.getValue().toString());
			break;
		case Gt:
			this.value = Integer.parseInt(e1.getValue().toString()) > Integer.parseInt(e2.getValue().toString());
			break;
		case Or:
			this.value = Boolean.parseBoolean(e1.getValue().toString()) || Boolean.parseBoolean(e2.getValue().toString());
			break;
		default:
			throw new Exception("Operacion lógica no definida");
		}		
	}
}
