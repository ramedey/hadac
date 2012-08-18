package compiler.code.translator;


import compiler.intermediate.Temporal;
import compiler.intermediate.Variable;
import compiler.semantic.symbol.SymbolParameter;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorMove extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		if(isVariable(q.getFirstOperand()))
		{
			Variable v = (Variable)q.getFirstOperand();
			if(isNoLocal(v)){
				int display = 65001 + ((Variable) q.getFirstOperand()).getScope().getLevel();
				createInstruction("MOVE #" + display + ", .R1");
				createInstruction("SUB [.R1], #" + ((Variable) q.getFirstOperand()).getAddress());
				createInstruction("MOVE .A, .R1");
			}
		}
		getTranslation().append("MOVE ");
		getTranslation().append(translate(q.getFirstOperand()));
		getTranslation().append(", ");
		getTranslation().append(translate(q.getResult()));
	}
}
