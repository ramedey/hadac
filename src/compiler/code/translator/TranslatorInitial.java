package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorInitial extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		
		getTranslation().append("ORG 0\n");
		getTranslation().append("MOVE #65535,.SP\n");
		getTranslation().append("MOVE #36864,.R0\n");
		getTranslation().append("MOVE #36897,[.R0]\n");
		//getTranslation().append("");

	}

}
