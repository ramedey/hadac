package compiler.code.translator;


import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorIO extends TranslatorBase {

	
	
	@Override
	public void translate(QuadrupleIF q) {
		
		getTranslation().append("WRCHAR ");
		String label = LabelManager.getLabelText();
		getTranslation().append("/" + label);
		LabelManager.addLabel(label, "DATA " + q.getFirstOperand());
		
	}

}
