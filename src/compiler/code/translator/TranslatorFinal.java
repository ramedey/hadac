package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorFinal extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		getTranslation().append(LabelManager.getLabels());
		getTranslation().append("HALT");
		
	}

}
