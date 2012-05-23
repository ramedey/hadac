package compiler.code.translator;

import compiler.intermediate.Variable;
import compiler.semantic.symbol.SymbolVariable;
import compiler.semantic.type.TypeRecord;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorMoveReg extends TranslatorBase {

	//MOVE_REG tiene que sumar la direccion de var y el offset y almacenarla en un registro.
    //despues alojar la variable temp en la diraccion dada por dicho registro.
	@Override
	public void translate(QuadrupleIF q) {
		
		Variable var;
		String offset = translate(q.getFirstOperand());
		if(q.getSecondOperand() instanceof Variable)
		{
			var = (Variable)q.getSecondOperand(); 
			createComment("Acceso a campo " + var.getName() + ". Direccion: " + var.getAddress());
			SymbolVariable s = (SymbolVariable)var.getSimbolo();
			TypeRecord t = (TypeRecord)s.getType(); 
			createComment("Registro: " + t);
			//Sumo la direccion de la variable mas el desplazamiento para obtener la dir del campo
			createInstruction("ADD " + offset 
				+ ", #" + var.getAddress(), "Sumo la direccion de la variable mas el desplazamiento para obtener la dir del campo");
			//Guardo el temporal en la direccion del campo
			createInstruction("MOVE [.A], " + translate(q.getResult()), "Guardo el campo en el temporal");
		}else {
			var = (Variable)q.getResult(); 
			createComment("Acceso a campo " + var.getName() + ". Direccion: " + var.getAddress());
			SymbolVariable s = (SymbolVariable)var.getSimbolo();
			TypeRecord t = (TypeRecord)s.getType(); 
			createComment("Registro: " + t);
			
			//Sumo la direccion de la variable mas el desplazamiento para obtener la dir del campo
			createInstruction("ADD " + offset 
				+ ", #" + var.getAddress(), "Sumo la direccion de la variable mas el desplazamiento para obtener la dir del campo");
			//Guardo el temporal en la direccion del campo
			createInstruction("MOVE " + translate(q.getSecondOperand()) + ", [.A]", "Guardo el temporal en la direccion del campo");
		}
	}

}
