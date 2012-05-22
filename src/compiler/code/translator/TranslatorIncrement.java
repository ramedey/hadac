package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorIncrement extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		createInstruction("INC " + translate(q.getResult()));

	}

}
