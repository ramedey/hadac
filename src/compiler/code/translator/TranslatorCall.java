package compiler.code.translator;

import compiler.code.MemoryManager;
import compiler.intermediate.Procedure;
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class TranslatorCall extends TranslatorBase {

	/*
	 *	1- Mover puntero de marco(IX) a Display[ambito]
	 *	2- Mover puntero de pila(SP) a puntero de marco(IX)
	 *  3- Colocar la direccion de retorno en el tope de la pila
	 * (non-Javadoc)
	 * @see compiler.code.translator.TranslatorBase#translate(es.uned.lsi.compiler.intermediate.QuadrupleIF)
	 */
	
	@Override
	public void translate(QuadrupleIF q) {
		
		OperandIF subprograma = q.getResult();
		Procedure sub = (Procedure)subprograma;
		
		int size = sub.getSizeOfParameters();
		size++; // dejo una posición extra para el valor de retorno de la función. El valor de retorno estará en #-1[IX]
		
		ScopeIF scope = sub.getScope();
		int espacioVaryTemp = MemoryManager.getSizeOfScope(scope.getLevel()+1);
				
		getTranslation().append(";--Creacion RA--" + SALTO_LINEA);
		//getTranslation().append("MOVE .IX, #" + scopeCount + "[.R0]	;1- Mover puntero de marco(IX) a Display[" + scopeCount + "]" + SALTO_LINEA);
		this.createInstruction("MOVE .SP,.IY", "posiciono el puntero IY en la primera posicion libre de la pila");
		//Avanzo el puntero una posición paara dejar espacio para el valor de retorno.
		//Los parametros ya están insertados mediante instruciones PUSH, por lo cual el puntero de pila ya
		// ha avanzado el nº de parámetros.
		this.createInstruction("SUB .IY, #" + 1, 
				"avanzo el puntero IY con - 1 espacio del valor de retorno(sentido decreciente)");
		this.createInstruction("MOVE .A,.IY", "ahora IY apunta a la posición que va a contener vinculo de control del RA");
		this.createInstruction("MOVE .IX,[.R0]", "Se guarda la direccion del RA anterior en el display");
		this.createInstruction("INC .R0", "incremento el display a la siguiente posición libre");
		this.createInstruction("MOVE .IY,.IX", "Ahora el puntero de marco (FP) apunta al RA actual");
		this.createInstruction("SUB .IX, #" + (espacioVaryTemp + 2), "Muevo el putero de pila a la primera posición libre, contando las variables y temporales.");
		this.createInstruction("MOVE .A,.SP");
		this.createInstruction("CALL /" + sub.getCodeLabel(), "Salto al código del procedimiento, agregando la direccion de retorno al RA");
		getTranslation().append(";--Fin Creacion RA--" + SALTO_LINEA);
		//Espacio del RA: tamaño parámetros (size)
		// +1 (valor de retorno)
		// +1 (vínculo de control)
		// tamaño de variables y temporales (espacioVaryTemp)
		// El RA también contiene la dirección de retorno en la posición mas baja del registro
		// de activación, pero al devolver la llamada (instrucción RET) se hace POP del puntero de pila
		// y se adelanta el puntero .SP una posición, por lo que ahora no hay que sumarla.
		this.createInstruction("ADD .SP, #" + (size + espacioVaryTemp + 2), "Devuelvo el puntero de pila a la dirección inicial del RA padre");
		this.createInstruction("MOVE .A,.SP");
		this.createInstruction("DEC .R0", "decremento el display para que apunte al ambito padre ");
		this.createInstruction("MOVE [.R0],.IX");
	}
}
