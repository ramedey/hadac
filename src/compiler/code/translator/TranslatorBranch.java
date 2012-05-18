package compiler.code.translator;

import compiler.intermediate.InstructionSetArchitecture;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorBranch extends TranslatorBase{

	@Override
	public void translate(QuadrupleIF q) {
		if(q.getOperation().equals(InstructionSetArchitecture.BRANCH))
		{
			getTranslation().append("BR /" + q.getResult());
		}else if(q.getOperation().equals(InstructionSetArchitecture.BRANCH_FALSE))
		{
			getTranslation().append("CMP #1, " + translate(q.getResult()) + 
					"\t;Resta el contenido del operando 1 y el operando 2 (pero " +
					"no almacena el resultado de la operación en ningún sitio)." +
					"Modifica los biestables de estado.\n");
			getTranslation().append("BNZ /" + q.getFirstOperand() + 
					"\t;Bifurcación si resultado distinto de cero.");
		}else if(q.getOperation().equals(InstructionSetArchitecture.BRANCH_TRUE))
		{
			getTranslation().append("CMP #1, " + translate(q.getResult()) + 
					"	;Resta el contenido del operando 1 y el operando 2 (pero " +
					"no almacena el resultado de la operación en ningún sitio)." +
					"Modifica los biestables de estado.\n");
			getTranslation().append("BZ /" + q.getFirstOperand() + 
					"	;Bifurcación si resultado igual a cero.");
		}
	}

}
