package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorRetValue extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		createInstruction("MOVE #1[.IX], " + translate(q.getResult()), "Guardo el valor de retorno");

	}

}
