package compiler.code.translator;

import compiler.intermediate.Variable;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorMoveReg extends TranslatorBase {

	//MOVE_REG tiene que sumar la direccion de var y el offset y almacenarla en un registro.
    //despues alojar la variable temp en la diraccion dada por dicho registro.
	@Override
	public void translate(QuadrupleIF q) {
		
		Variable var = (Variable)q.getSecondOperand(); 
		
		//Sumo la direccion de la variable mas el desplazamiento para obtener la dir del campo
		getTranslation().append("ADD " + translate(q.getFirstOperand()) 
				+ ", #" + var.getAddress() + SALTO_LINEA);
		//Guardo el temporal en la direccion del campo
		getTranslation().append("MOVE [.A], " + translate(q.getResult()) + SALTO_LINEA);
		
	}

}
