package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorInitial extends TranslatorBase {

	/**********************************************
	 * 
	 * 1. Haz que la pila empiece en la 65000 y crezca hacia posiciones bajas
de memoria (hacia 0)
2. el c�digo y las la memoria para las variables globales empiezan en 0
y crece hacia posiciones altas de memoria (hacia 65000)
3. Sit�a el display desde la 65001 en adelante hasta el final de la memoria
	 **********************************************/
	
	@Override
	public void translate(QuadrupleIF q) {
		
		getTranslation().append("ORG 0\n");
		getTranslation().append("MOVE #65000,.SP ; la pila empieza en la 65000 y crezca hacia posiciones bajas de memoria (hacia 0)\n");
		getTranslation().append("MOVE #65001,.R0 ; direcci�n del display almacenada en el registro 0\n");
		//getTranslation().append("MOVE #36897,[.R0]\n");
		getTranslation().append("MOVE #36897,.IX          ; Registro IX apuntando al RA del procedimiento principal");

	}

}
