package compiler.code.translator;


import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorMove extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		getTranslation().append("MOVE ");
		getTranslation().append(translate(q.getFirstOperand()));
		getTranslation().append(", ");
		getTranslation().append(translate(q.getResult()));
	}
}
