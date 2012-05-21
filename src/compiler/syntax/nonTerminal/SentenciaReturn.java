package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.InstructionSetArchitecture;

import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SentenciaReturn extends Sentencia {

	private Expresion expresion;
	
	/**
	 * @return the expresion
	 */
	public Expresion getExpresion() {
		return expresion;
	}

	/**
	 * @param expresion the expresion to set
	 */
	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	public SentenciaReturn(){
		super();
	}
	
	public SentenciaReturn(Expresion exp){
		super();
		expresion = exp;
	}
	
	public TypeIF getTipoDevolucion()
	{
		if(expresion != null)
			return expresion.getTipoInstruccion();
		return null;
	}
	
	public void generarCodigoIntermedio()
	{
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
        TemporalFactory tF = new TemporalFactory (scope);
        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
        //TemporalIF temp = tF.create ();
        cb.addQuadruples(expresion.getIntermediateCode());
        cb.addQuadruple (InstructionSetArchitecture.RET, expresion.getTemporal());
        //expresion.setTemporal(temp);
        this.setIntermediateCode(cb.create());
        CompilerContext.getSemanticErrorManager().semanticDebug(this.getIntermediateCode());
	}
}
