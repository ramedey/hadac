package compiler.syntax.nonTerminal;

import compiler.CompilerContext;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public abstract class Expresion extends NonTerminal {
    
	private TypeIF tipoInstruccion;
	
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
	
	/**
	 * Obtiene el valor resultado de la expresion
	 * @return
	 */
	public abstract Object getValue();
}
