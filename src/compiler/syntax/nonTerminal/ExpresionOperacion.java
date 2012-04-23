package compiler.syntax.nonTerminal;

/**
 * Expresión que representa una operación aritmática o lógica.
 * @author amedey
 *
 */
public abstract class ExpresionOperacion extends Expresion {

	public ExpresionOperacion(){}
	
	public ExpresionOperacion(Expresion e1, Expresion e2) throws Exception{
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
	
	public abstract void doOperation(Expresion e1, Expresion e2);

}
