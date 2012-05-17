package compiler.code.translator;

import compiler.code.MemoryManager;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorInitial extends TranslatorBase {

	/**********************************************
	 * 
	 * 1. Haz que la pila empiece en la 65000 y crezca hacia posiciones bajas
de memoria (hacia 0)
2. el código y las la memoria para las variables globales empiezan en 0
y crece hacia posiciones altas de memoria (hacia 65000)
3. Sitúa el display desde la 65001 en adelante hasta el final de la memoria
	 **********************************************/
	
	public final String STACK_ADDRESS = "#65000";
	public final String DISPLAY_ADDRESS = "#65001";
	public final String FRAME_POINTER_ADDRESS = "#36897";
	
	@Override
	public void translate(QuadrupleIF q) {
		
		getTranslation().append("ORG " + (MemoryManager.getgAddress()+1) + this.SALTO_LINEA);
		//Posicionar el puntero de pila de llamadas, donse se almacenan las direcciones de llamada y retorno
		getTranslation().append("MOVE " + STACK_ADDRESS + ",.SP ; la pila empieza en la 65000 y crezca hacia posiciones bajas de memoria (hacia 0)\n");
		//Posicionar el puntero del display
		getTranslation().append("MOVE " + DISPLAY_ADDRESS + ",.R0 ; dirección del display almacenada en el registro 0\n");
		//getTranslation().append("MOVE #36897,[.R0]\n");
//		getTranslation().append("MOVE .SP, .IY");
		getTranslation().append("MOVE " + FRAME_POINTER_ADDRESS + ",.IX          ; Registro IX apuntando al RA del procedimiento principal");

	}

}
