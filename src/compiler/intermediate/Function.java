package compiler.intermediate;

import es.uned.lsi.compiler.semantic.ScopeIF;

public class Function extends Procedure {

	private Object value;
	
	
	public Function(String name, ScopeIF scope) {
		super(name, scope);
		// TODO Auto-generated constructor stub
	}


	public void setValue(Object value) {
		this.value = value;
	}


	public Object getValue() {
		return value;
	}

}
