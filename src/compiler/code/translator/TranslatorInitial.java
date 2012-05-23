package compiler.code.translator;

import compiler.code.MemoryManager;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorInitial extends TranslatorBase {

	/**********************************************
	 * 
	 * 1. Haz que la pila empiece en la 65000 y crezca hacia posiciones bajas
	 *	de memoria (hacia 0)
	 * 2. el código y las la memoria para las variables globales empiezan en 0
	 *	y crece hacia posiciones altas de memoria (hacia 65000)
	 * 3. Sitúa el display desde la 65001 en adelante hasta el final de la memoria
	 **********************************************/
	
	public final int STACK_ADDRESS = 65000;
	public final String FRAME_POINTER_ADDRESS = "#64999";//la posicion (#65000) es para el valor de retorno
	
	@Override
	public void translate(QuadrupleIF q) {
		
		getTranslation().append("ORG " + (MemoryManager.getgAddress()+1) + this.SALTO_LINEA);
		//Posicionar el puntero de pila de llamadas, donse se almacenan las direcciones de llamada y retorno
		createComment("la pila empieza en la 65000 y crece hacia posiciones bajas de memoria (hacia 0).");
		createComment("Se reserva espacio para " + MemoryManager.getSizeOfScope(0) + " temporales.\n");
		
		createInstruction("MOVE #" + (STACK_ADDRESS - (MemoryManager.getSizeOfScope(0)+2)) + ",.SP",
				"Se suma 1 a la reserva de espacio para que apunte a la primera posición libre.");
		//Posicionar el puntero del display
		getTranslation().append("MOVE " + DISPLAY_ADDRESS + ",.R0 		;dirección del display almacenada en el registro 0\n");
		//Guardar el Display[0] el FP del primer RA
		createInstruction("MOVE " + FRAME_POINTER_ADDRESS + ", [.R0]", 
				"Almaceno la direccion del puntero de marco (IX) en Display[0](en R0)");
		createInstruction("MOVE " + FRAME_POINTER_ADDRESS + ",.IX          ; Registro IX apuntando al RA del procedimiento principal");
		createInstruction("BR /" + q.getFirstOperand(), "Salto a la etiqueta del procedimiento principal");

	}
	

}
