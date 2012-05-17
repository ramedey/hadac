package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorLabel extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		getTranslation().append(q.getResult() + " : ");

	}

}
