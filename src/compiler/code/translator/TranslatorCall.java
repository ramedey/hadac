package compiler.code.translator;

import compiler.code.MemoryManager;
import compiler.intermediate.Function;
import compiler.intermediate.Procedure;
import compiler.semantic.symbol.SymbolParameter;
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
		if(isFunction(sub))
		{
			size++; // dejo una posición extra para el valor de retorno de la función. El valor de retorno estará en #-1[IX]
		}
		ScopeIF scope = sub.getScope();
		int espacioVaryTemp = MemoryManager.getSizeOfScope(scope.getLevel()+1);
				
		getTranslation().append(";--Creacion RA--" + SALTO_LINEA);
		//getTranslation().append("MOVE .IX, #" + scopeCount + "[.R0]	;1- Mover puntero de marco(IX) a Display[" + scopeCount + "]" + SALTO_LINEA);
		this.createInstruction("MOVE .SP,.IY", "posiciono el puntero IY en la primera posicion libre de la pila");
		this.createInstruction("SUB .IY, #" + size, 
				"avanzo el puntero IY con el tamaño de los parametros para dejar espacio (en sentido decreciente)");
		this.createInstruction("MOVE .A,.IY", "ahora IY apunta a la posición que va a contener vinculo de control del RA");
		this.createInstruction("MOVE .IX,[.R0]", "Se guarda la direccion del RA anterior en el display");
		this.createInstruction("INC .R0", "incremento el display a la siguiente posición libre");
		addParameters(sub);
		this.createInstruction("MOVE .IY,.IX", "Ahora el puntero de marco (FP) apunta al RA actual");
		this.createInstruction("SUB .IX, #" + (espacioVaryTemp + 1), "Muevo el putero de pila a la primera posición libre, contando las variables y temporales.");
		this.createInstruction("MOVE .A,.SP");
		this.createInstruction("CALL /" + sub.getCodeLabel(), "Salto al código del procedimiento, agregando la direccion de retorno al RA");
		getTranslation().append(";--Fin Creacion RA--" + SALTO_LINEA);
		this.createInstruction("ADD .SP, #" + (size + espacioVaryTemp + 1), "Devuelvo el puntero de pila a la dirección inicial del RA padre");
		this.createInstruction("MOVE .A,.SP");
		this.createInstruction("DEC .R0", "decremento el display para que apunte al ambito padre ");
		this.createInstruction("MOVE [.R0],.IX");
		//this.createComment("Ambito " + getScopeCount());
		//Incremento el contador de ámbitos.
		//setScopeCount(scope.getLevel() + 1);
		
//		this.createComment("Ambito " + getScopeCount());
	}

	public boolean isFunction(OperandIF sub)
	{
		return (sub instanceof Function);
	}
	
	private int addParameters(Procedure sub)
	{
		int i = 1;
		for(SymbolParameter param : sub.getParametros())
		{
				this.createInstruction("MOVE " + translate(param.getTemporal()) + ", #" + i + "[.IY]",
						"Copio parametro a su zona dentro del RA(en el puntero de marco provisional IY");
				param.getTemporal().setAddress(i);
				i++;
			
		}
		return i;
	}
}
