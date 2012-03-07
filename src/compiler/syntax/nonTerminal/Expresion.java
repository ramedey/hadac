package compiler.syntax.nonTerminal;

import java.util.List;

import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Expresion extends NonTerminal {

    private TypeIF tipoInstruccion;
    private List codigoIntermedio;
    private OperandIF resultado;
    
    public Expresion(){}
    
    public Expresion(TypeIF tipoInstruccion)
    {
    	this.tipoInstruccion = tipoInstruccion; 
    }
    
    public Expresion(Expresion expresion)
    {
    	this.tipoInstruccion = expresion.getTipoInstruccion(); 
    	this.resultado = expresion.getResultado();
    }
    
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
	/**
	 * @return the codigoIntermedio
	 */
	public List getCodigoIntermedio() {
		return codigoIntermedio;
	}
	/**
	 * @param codigoIntermedio the codigoIntermedio to set
	 */
	public void setCodigoIntermedio(List codigoIntermedio) {
		this.codigoIntermedio = codigoIntermedio;
	}
	/**
	 * @return the resultado
	 */
	public OperandIF getResultado() {
		return resultado;
	}
	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(OperandIF resultado) {
		this.resultado = resultado;
	}
    
}
