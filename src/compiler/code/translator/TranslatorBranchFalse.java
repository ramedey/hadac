package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorBranchFalse extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		
		getTranslation().append("CMP #1, " + translate(q.getResult()) + "\n");
		getTranslation().append("BNZ /" + q.getFirstOperand());

	}

}
