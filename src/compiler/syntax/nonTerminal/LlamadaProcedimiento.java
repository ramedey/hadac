package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.Variable;

import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class LlamadaProcedimiento extends NonTerminal {

	private ParametrosActuales parametrosActuales;
	private TypeIF tipo;
	
	/**
	 * @return the simbolo
	 */
	public TypeIF getTipo() {
		return tipo;
	}

	/**
	 * @param simbolo the simbolo to set
	 */
	public void setTipo(TypeIF simbolo) {
		this.tipo = simbolo;
	}

	public LlamadaProcedimiento(TypeIF tipo, ParametrosActuales parametros)
	{
		super();
		parametrosActuales = parametros;
		this.tipo = tipo;
	}
	
	public void generarCodigoIntermedio()
	{
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
        cb.addQuadruples(parametrosActuales.getIntermediateCode());
        Variable f = new Variable (tipo.getName(), scope);
        cb.addQuadruple ("CALL", f);
        this.setIntermediateCode(cb.create());
        CompilerContext.getSemanticErrorManager().semanticDebug("CI llamada: " + this.getIntermediateCode());
	}
}
