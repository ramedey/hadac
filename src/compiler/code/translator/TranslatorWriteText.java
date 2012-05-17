package compiler.code.translator;


import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorWriteText extends TranslatorBase {

	
	
	@Override
	public void translate(QuadrupleIF q) {
		
		getTranslation().append("WRSTR ");
		String label = LabelManager.getLabelText();
		getTranslation().append("/" + label + "\nWRCHAR #10\nWRCHAR #13");
		OperandIF o = q.getFirstOperand();
		LabelManager.addLabel(label, "DATA " + q.getResult());
		
	}

}
