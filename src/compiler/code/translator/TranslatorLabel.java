package compiler.code.translator;

import compiler.intermediate.Value;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorLabel extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		getTranslation().append(q.getResult() + " : ");
		if(q.getFirstOperand() != null)
		{
			//EN las etiquetas que marcan el inicio de un subprograma, se incluye el nivel de
			// ámbito como primer operando.
			Value v = (Value)q.getFirstOperand();
			setScopeCount((Integer) v.getValue());
			this.createComment("Ambito " + getScopeCount());
		}
	}

}
