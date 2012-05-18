package compiler.code.translator;

import compiler.intermediate.InstructionSetArchitecture;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorLogical extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		if(q.getOperation().equals(InstructionSetArchitecture.OR))
		{
			getTranslation().append("OR " + translate(q.getFirstOperand())+ 
					", " + translate(q.getSecondOperand()) + SALTO_LINEA);
			getTranslation().append("MOVE .A, " + translate(q.getResult()));
		}else if(q.getOperation().equals(InstructionSetArchitecture.EQUAL))
		{
			getTranslation().append("CMP " + translate(q.getFirstOperand())+ 
					", " + translate(q.getSecondOperand()) + SALTO_LINEA);
			String label = LabelManager.getLabelText();
			String label2 = LabelManager.getLabelText();
			getTranslation().append("BNZ /" + label + SALTO_LINEA);
			getTranslation().append("MOVE #1, " + translate(q.getResult()) + SALTO_LINEA);
			getTranslation().append("BR /" + label2 + SALTO_LINEA);
			getTranslation().append(label + " : " + SALTO_LINEA);
			getTranslation().append("MOVE #0, " + translate(q.getResult()) + SALTO_LINEA);
			getTranslation().append(label2 + " : " + SALTO_LINEA);
		}else if(q.getOperation().equals(InstructionSetArchitecture.GREATER_THAN))
		{
			getTranslation().append("SUB " + translate(q.getFirstOperand())+ 
					", " + translate(q.getSecondOperand()) + SALTO_LINEA);
			String label = LabelManager.getLabelText();
			String label2 = LabelManager.getLabelText();
			getTranslation().append("BN /" + label + "	;Salto si el resultado es negativo"  + SALTO_LINEA);
			getTranslation().append("MOVE #1, " + translate(q.getResult()) + SALTO_LINEA);
			getTranslation().append("BR /" + label2 + SALTO_LINEA);
			getTranslation().append(label + " : " + SALTO_LINEA);
			getTranslation().append("MOVE #0, " + translate(q.getResult()) + SALTO_LINEA);
			getTranslation().append(label2 + " : " + SALTO_LINEA);
		}

	}

}
