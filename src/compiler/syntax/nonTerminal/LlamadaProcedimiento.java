package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.Variable;

import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;

public class LlamadaProcedimiento extends NonTerminal {

	private ParametrosActuales parametrosActuales;
	private SymbolIF simbolo;
	
	/**
	 * @return the simbolo
	 */
	public SymbolIF getSimbolo() {
		return simbolo;
	}

	/**
	 * @param simbolo the simbolo to set
	 */
	public void setSimbolo(SymbolIF simbolo) {
		this.simbolo = simbolo;
	}

	public LlamadaProcedimiento(SymbolIF simbolo, ParametrosActuales parametros)
	{
		super();
		parametrosActuales = parametros;
		this.simbolo = simbolo;
	}
	
	public void generarCodigoIntermedio()
	{
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
        cb.addQuadruples(parametrosActuales.getIntermediateCode());
        Variable f = new Variable (simbolo.getName(), scope);
        cb.addQuadruple ("CALL", f);
        this.setIntermediateCode(cb.create());
        CompilerContext.getSemanticErrorManager().semanticDebug("CI llamada: " + this.getIntermediateCode());
	}
}
