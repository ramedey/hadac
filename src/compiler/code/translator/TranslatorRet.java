package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorRet extends TranslatorBase {

	/**
	 * 1- Recuperar la direccion de retorno de la pila.
	 */
	
	@Override
	public void translate(QuadrupleIF q) {
		//Esta parte solo es válida para las funciones
		if(q.getResult() != null)
		{
			this.createInstruction("DEC .R0", "decremento el display para que R0 apunte al ambito padre ");
			this.createInstruction("ADD #1, [.R0]");
			createInstruction("MOVE "+ translate(q.getResult()) + 
				", [.A]", "Muevo el valor de retorno a su posición del RA (#1[.IX]) para tenerlo accesible desde el llamador");
			this.createInstruction("INC .R0", "restauro el valor del display");
		}
		createInstruction("RET");
		//Se vuelve al ambito de nivel superior
//		createComment("Scope: " + getScopeCount());
	}

}
