package compiler.code.translator;


import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorIO extends TranslatorBase {

	
	
	@Override
	public void translate(QuadrupleIF q) {
		
		getTranslation().append("WRSTR ");
		String label = LabelManager.getLabelText();
		getTranslation().append("/" + label + "\nWRCHAR #10\nWRCHAR #13");
		LabelManager.addLabel(label, "DATA " + q.getFirstOperand().toString());
		
	}

}
