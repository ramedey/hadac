package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorWriteExp extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		getTranslation().append("WRINT ");
		getTranslation().append(translate(q.getResult()));

	}

}
