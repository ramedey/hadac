package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SymbolIntegerConstant extends SymbolConstant {
	
	public SymbolIntegerConstant(ScopeIF scope, String name, TypeIF type, int value) {
		super(scope, name, type);
		this.setValue(value);
	}

}
