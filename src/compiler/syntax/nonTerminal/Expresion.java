package compiler.syntax.nonTerminal;

import java.util.List;

import compiler.CompilerContext;
import compiler.intermediate.Temporal;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public abstract class Expresion extends NonTerminal {
    
	private TypeIF tipoInstruccion;
	
	private TemporalIF temporal;
	
    /**
	 * @return the tipoInstruccion
	 */
	public TypeIF getTipoInstruccion() {
		return tipoInstruccion;
	}

	/**
	 * @param tipoInstruccion the tipoInstruccion to set
	 */
	public void setTipoInstruccion(TypeIF tipoInstruccion) {
		this.tipoInstruccion = tipoInstruccion;
	}
	
	public Expresion(){}
	
	public Expresion(TypeIF tipoInstruccion) {
		if(tipoInstruccion == null)
		{
			CompilerContext.getSemanticErrorManager().semanticFatalError("el tipo es nulo. Constructor de expresion.");
		}
		this.tipoInstruccion = tipoInstruccion;
	}
	
	public void setTemporal(TemporalIF temp) {
		this.temporal = temp;
	}

	public TemporalIF getTemporal() {
		return temporal;
	}

	public abstract void generarCodigoIntermedio();
}
