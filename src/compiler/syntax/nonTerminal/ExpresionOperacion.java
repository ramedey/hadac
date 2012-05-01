package compiler.syntax.nonTerminal;

import compiler.CompilerContext;

/**
 * Expresión que representa una operación aritmática o lógica.
 * @author amedey
 *
 */
public abstract class ExpresionOperacion extends Expresion {

	public ExpresionOperacion(){}
	
	public ExpresionOperacion(Expresion e1, Expresion e2){
		CompilerContext.getSyntaxErrorManager().syntaxInfo("Expresion operacion");
		if(e1 == null)
			CompilerContext.getSemanticErrorManager().semanticFatalError("La primera expresión es nula");
		if(e2 == null)
			CompilerContext.getSemanticErrorManager().semanticFatalError("La segunda expresión es nula");
		CompilerContext.getSyntaxErrorManager().syntaxInfo("Tipos: " + e1.getTipoInstruccion() + " y " + e2.getTipoInstruccion());
		if(!e1.getTipoInstruccion().getName().equals(e2.getTipoInstruccion().getName()))
        {
			CompilerContext.getSemanticErrorManager().semanticFatalError("Los tipos no coinciden: " + e1.getTipoInstruccion() + " distinto de " + e2.getTipoInstruccion());
        }
		this.setTipoInstruccion(e1.getTipoInstruccion());
		// Realizar la operación de expresiones.
        //this.doOperation(e1, e2);
	}
	
	public abstract void doOperation(Expresion e1, Expresion e2);

}
