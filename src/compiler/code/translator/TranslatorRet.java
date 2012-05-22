package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorRet extends TranslatorBase {

	/**
	 * 1- Recuperar la direccion de retorno de la pila.
	 */
	
	@Override
	public void translate(QuadrupleIF q) {
		//getTranslation().append("POP .R1	;1- Recuperar direccion de retorno del tope de la pila" + SALTO_LINEA);
		//getTranslation().append("MOVE #-1[IX] .R1	;Mueve dirección de retorno a R1" + SALTO_LINEA);
		//Esta instrucción solo es válida para las funciones
		if(q.getResult() != null)
		{
			createInstruction("MOVE "+ translate(q.getResult()) + 
				", .R2", "Muevo el valor de retorno al registro 2 para tenerlo accesible desde el llamador");
		}
		createInstruction("RET");
		//Se vuelve al ambito de nivel superior
//		setScopeCount(getScopeCount() - 1);
		createComment("Scope: " + getScopeCount());
	}

}
