package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorParam extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
//		createInstruction("MOVE " + translate(q.getResult()) + ", #-" + q.getFirstOperand() + "[.SP]",
//				"Muevo el parametro a su posición del nuevo RA");
		createInstruction("PUSH " + translate(q.getResult()),
		"Muevo el parametro a su posición del nuevo RA");

	}

}
