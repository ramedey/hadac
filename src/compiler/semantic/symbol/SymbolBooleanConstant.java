package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SymbolBooleanConstant extends SymbolConstant {

	public SymbolBooleanConstant(ScopeIF scope, String name, TypeIF type, Object value)
	{
		super(scope, name, type);
		this.setValue(value);
	}
}
