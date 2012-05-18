package compiler.code.translator;

import compiler.CompilerContext;
import compiler.intermediate.Temporal;
import compiler.intermediate.Value;
import compiler.intermediate.Variable;

import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public abstract class TranslatorBase implements TranslatorIF {

	StringBuilder translation;
	public final String SALTO_LINEA = "\n";
	
	/**
	 * @return the translation
	 */
	public StringBuilder getTranslation() {
		return translation;
	}

	/**
	 * @param translation the translation to set
	 */
	public void setTranslation(StringBuilder translation) {
		this.translation = translation;
	}

	public TranslatorBase()	{
		translation = new StringBuilder();
	}
	
	public String createTranslation(QuadrupleIF q)
	{
//		CompilerContext.getSemanticErrorManager().semanticDebug("Primer operando: " + q.getFirstOperand() == null);
//		CompilerContext.getSemanticErrorManager().semanticDebug("Resultado: " + q.getResult() == null);
		translate(q);
		return getTranslation().toString();
	}
	

	public abstract void translate(QuadrupleIF q);

	protected String translate(OperandIF o) {
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
			if(v.getValue().toString().equals("true"))
			{
				return "#1";
			}else if(v.getValue().toString().equals("false")){
				return "#0";
			}else if (v.getValue() instanceof String){
				return v.getValue().toString();
			}
				
			return "#" + v.getValue();
		}
		return "NO IMPLEMENTADO: " + o;
	}

}
