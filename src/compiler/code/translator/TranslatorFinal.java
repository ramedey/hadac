package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorFinal extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		getTranslation().append("HALT\n");
		getTranslation().append("ORG 32768\n");
		getTranslation().append(LabelManager.getLabels());
		getTranslation().append("END");
		
	}

}
