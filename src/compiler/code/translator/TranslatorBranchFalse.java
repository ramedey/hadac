package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorBranchFalse extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		
		getTranslation().append("CMP #1, " + translate(q.getResult()) + 
				" ;Resta el contenido del operando 1 y el operando 2 (pero " +
				"no almacena el resultado de la operación en ningún sitio)." +
				"Modifica los biestables de estado.\n");
		getTranslation().append("BNZ /" + q.getFirstOperand() + 
				" Bifurcación si resultado distinto de cero.");

	}

}
