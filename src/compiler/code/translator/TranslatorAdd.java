package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorAdd extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		getTranslation().append("ADD " + translate(q.getFirstOperand()) 
				+ ", " + translate(q.getSecondOperand()) + SALTO_LINEA);
		getTranslation().append("MOVE .A, " + translate(q.getResult()));
	}

}
