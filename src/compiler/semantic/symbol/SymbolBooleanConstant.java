package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SymbolBooleanConstant extends SymbolConstant {

	private boolean value;
	
	/**
	 * @param value the value to set
	 */
	public void setValue(boolean value) {
		this.value = value;
	}
	
	public boolean getValue(){
		return value;
	}

	public SymbolBooleanConstant(ScopeIF scope, String name, TypeIF type, boolean value)
	{
		super(scope, name, type);
		this.setValue(value);
	}
}
