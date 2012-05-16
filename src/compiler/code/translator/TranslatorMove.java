package compiler.code.translator;

import compiler.CompilerContext;
import compiler.intermediate.Temporal;
import compiler.intermediate.Value;
import compiler.intermediate.Variable;

import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public class TranslatorMove extends TranslatorBase {

	@Override
	public void translate(QuadrupleIF q) {
		getTranslation().append("MOVE ");
		getTranslation().append(translate(q.getFirstOperand()));
		getTranslation().append(", ");
		getTranslation().append(translate(q.getResult()));
	}

	
	private String translate(OperandIF o){
		CompilerContext.getSemanticErrorManager().semanticDebug(o.getClass());
		if(o instanceof Variable){
			Variable v =(Variable)o;
			if(v.isGlobal())
			{
				return "/" + v.getAddress();
			}
			return "#" + v.getAddress() + "[.IX]";
		}else if(o instanceof Temporal)
		{
			Temporal t = (Temporal)o;
			return "#" + t.getAddress() + "[.IX]";
		}else if(o instanceof Value)
		{
			Value v = (Value)o;
			return "#" + v.getValue();
		}
		return "NO IMPLEMENTADO";
	}
}
