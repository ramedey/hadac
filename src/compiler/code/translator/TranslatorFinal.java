package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorFinal extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		getTranslation().append("HALT\n");
		getTranslation().append("ORG 32768\n");
		String labels = LabelManager.getLabels();
		if(labels != null)
		{
			getTranslation().append(labels);
		}
		getTranslation().append("END");
		
	}

}
