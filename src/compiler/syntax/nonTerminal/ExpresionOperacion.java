package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

/**
 * Expresi�n que representa una operaci�n aritm�tica o l�gica.
 * @author amedey
 *
 */
public abstract class ExpresionOperacion extends Expresion {

	private Expresion e1, e2;
	
	public ExpresionOperacion(){}
	
	public ExpresionOperacion(Expresion e1, Expresion e2){
		//CompilerContext.getSyntaxErrorManager().syntaxInfo("Expresion operacion");
		if(e1 == null)
			CompilerContext.getSemanticErrorManager().semanticFatalError("La primera expresi�n es nula");
		if(e2 == null)
			CompilerContext.getSemanticErrorManager().semanticFatalError("La segunda expresi�n es nula");
		
		this.e1 = e1;
		this.e2 = e2;
		
		//CompilerContext.getSyntaxErrorManager().syntaxInfo("Tipos: " + e1.getTipoInstruccion() + " y " + e2.getTipoInstruccion());
		if(!e1.getTipoInstruccion().getName().equals(e2.getTipoInstruccion().getName()))
        {
			CompilerContext.getSemanticErrorManager().semanticFatalError("Los tipos no coinciden: " + e1.getTipoInstruccion() + " distinto de " + e2.getTipoInstruccion());
        }
		this.setTipoInstruccion(e1.getTipoInstruccion());
		// Realizar la operaci�n de expresiones.
        //this.doOperation(e1, e2);
	}
	
	public void generarCodigoIntermedio()
	{
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
        TemporalFactory tF = new TemporalFactory (scope);
        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
        TemporalIF temp1 = e1.getTemporal ();
        TemporalIF temp2 = e2.getTemporal ();
        TemporalIF temp = tF.create ();
        cb.addQuadruples (e1.getIntermediateCode());
        cb.addQuadruples (e2.getIntermediateCode());
        cb.addQuadruple (getCodigoOperacion(), temp, temp1, temp2);
        this.setTemporal (temp);
        this.setIntermediateCode(cb.create());
	}
	
	public abstract void doOperation(Expresion e1, Expresion e2);
	
	/**
	 * Obtiene el c�digo de operaci�n
	 * @return
	 */
	public abstract String getCodigoOperacion();

}
