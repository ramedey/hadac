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
		size++; // dejo una posici�n extra para el valor de retorno de la funci�n. El valor de retorno estar� en #-1[IX]
		
		ScopeIF scope = sub.getScope();
		int espacioVaryTemp = MemoryManager.getSizeOfScope(scope.getLevel()+1);
				
		getTranslation().append(";--Creacion RA--" + SALTO_LINEA);
		//getTranslation().append("MOVE .IX, #" + scopeCount + "[.R0]	;1- Mover puntero de marco(IX) a Display[" + scopeCount + "]" + SALTO_LINEA);
		this.createInstruction("MOVE .SP,.IY", "posiciono el puntero IY en la primera posicion libre de la pila");
		//Avanzo el puntero una posici�n paara dejar espacio para el valor de retorno.
		//Los parametros ya est�n insertados mediante instruciones PUSH, por lo cual el puntero de pila ya
		// ha avanzado el n� de par�metros.
		this.createInstruction("SUB .IY, #" + 1, 
				"avanzo el puntero IY con - 1 espacio del valor de retorno(sentido decreciente)");
		this.createInstruction("MOVE .A,.IY", "ahora IY apunta a la posici�n que va a contener vinculo de control del RA");
		this.createInstruction("MOVE .IX,[.R0]", "Se guarda la direccion del RA anterior en el display");
		this.createInstruction("INC .R0", "incremento el display a la siguiente posici�n libre");
//		addParameters(sub);
		this.createInstruction("MOVE .IY,.IX", "Ahora el puntero de marco (FP) apunta al RA actual");
		this.createInstruction("SUB .IX, #" + (espacioVaryTemp + 2), "Muevo el putero de pila a la primera posici�n libre, contando las variables y temporales.");
		this.createInstruction("MOVE .A,.SP");
		this.createInstruction("CALL /" + sub.getCodeLabel(), "Salto al c�digo del procedimiento, agregando la direccion de retorno al RA");
		getTranslation().append(";--Fin Creacion RA--" + SALTO_LINEA);
		this.createInstruction("ADD .SP, #" + (size + espacioVaryTemp + 2), "Devuelvo el puntero de pila a la direcci�n inicial del RA padre");
		this.createInstruction("MOVE .A,.SP");
		this.createInstruction("DEC .R0", "decremento el display para que apunte al ambito padre ");
		this.createInstruction("MOVE [.R0],.IX");
		
		
		
		//this.createComment("Ambito " + getScopeCount());
		//Incremento el contador de �mbitos.
		//setScopeCount(scope.getLevel() + 1);
		
//		this.createComment("Ambito " + getScopeCount());
	}

	public boolean isFunction(OperandIF sub)
	{
		return (sub instanceof Function);
	}
	
	private int addParameters(Procedure sub)
	{
		int i = 2; //la primera posici�n es para el valor de retorno.
		for(SymbolParameter param : sub.getParametros())
		{
				this.createInstruction("MOVE " + translate(param.getTemporal()) + ", #" + i + "[.IY]",
						"Copio parametro " + param.getTemporal() + 
						" a su zona dentro del RA(en el puntero de marco provisional IY");
				//param.getTemporal().setAddress(i);
				i++;
			
		}
		return i;
	}
}
