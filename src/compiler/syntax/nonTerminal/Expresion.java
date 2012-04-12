package compiler.syntax.nonTerminal;

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
	
	public Expresion(Expresion e1, Expresion e2) throws Exception{
		if(e1 == null)
			throw new Exception("La primera expresión es nula");
		if(e2 == null)
			throw new Exception("La segunda expresión es nula");
		
		if(!e1.getTipoInstruccion().equals(e2.getTipoInstruccion()))
        {
			throw new Exception("Los tipos no coinciden");
        }
		
		// Realizar la operación de expresiones.
        this.doOperation(e1, e2);
	}
	
	public abstract Object getValue();
	
	public abstract void doOperation(Expresion e1, Expresion e2) throws Exception;
}
