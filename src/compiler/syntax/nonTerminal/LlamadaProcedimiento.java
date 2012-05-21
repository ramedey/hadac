package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.InstructionSetArchitecture;
import compiler.intermediate.Procedure;

import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class LlamadaProcedimiento extends NonTerminal {

	private ParametrosActuales parametrosActuales;
	private TypeIF tipo;
	private OperandIF operacion;


	public LlamadaProcedimiento(TypeIF tipo, ParametrosActuales parametros)
	{
		super();
		parametrosActuales = parametros;
		this.tipo = tipo;
		operacion = new Procedure(tipo.getName(), CompilerContext.getScopeManager().getCurrentScope());
	}
	
	protected LlamadaProcedimiento(TypeIF tipo, ParametrosActuales parametros, OperandIF operacion)
	{
		super();
		parametrosActuales = parametros;
		this.tipo = tipo;
		this.operacion = operacion;
	}
	
	public void generarCodigoIntermedio()
	{
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
        //cb.addQuadruples(parametrosActuales.getIntermediateCode());
        
        cb.addQuadruple (InstructionSetArchitecture.CALL, operacion);
        this.setIntermediateCode(cb.create());
        CompilerContext.getSemanticErrorManager().semanticDebug("CI llamada: " + this.getIntermediateCode());
	}
	
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
}
