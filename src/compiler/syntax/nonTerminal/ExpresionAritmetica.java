package compiler.syntax.nonTerminal;

public class ExpresionAritmetica extends Expresion {

	// valor entero del contexto de la expresión
	private int value;
	
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	public ExpresionAritmetica(){
		super();
	}
	
	public ExpresionAritmetica(int value){
		super();
		this.value = value;
	}
}
