package compiler.syntax.nonTerminal;

/**
 * Expresi�n que representa una operaci�n aritm�tica o l�gica.
 * @author amedey
 *
 */
public abstract class ExpresionOperacion extends Expresion {

	public ExpresionOperacion(){}
	
	public ExpresionOperacion(Expresion e1, Expresion e2) throws Exception{
		if(e1 == null)
			throw new Exception("La primera expresi�n es nula");
		if(e2 == null)
			throw new Exception("La segunda expresi�n es nula");
		
		if(!e1.getTipoInstruccion().equals(e2.getTipoInstruccion()))
        {
			throw new Exception("Los tipos no coinciden");
        }
		
		// Realizar la operaci�n de expresiones.
        this.doOperation(e1, e2);
	}
	
	public abstract void doOperation(Expresion e1, Expresion e2);

}
