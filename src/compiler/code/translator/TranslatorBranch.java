package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorBranch extends TranslatorBase{

	@Override
	public void translate(QuadrupleIF q) {
		getTranslation().append("BR /" + q.getResult());
		
	}

}
