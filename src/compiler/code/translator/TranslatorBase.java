package compiler.code.translator;

import compiler.CompilerContext;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public abstract class TranslatorBase implements TranslatorIF {

	StringBuilder translation;
	
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
		CompilerContext.getSemanticErrorManager().semanticDebug("Primer operando: " + q.getFirstOperand() == null);
		CompilerContext.getSemanticErrorManager().semanticDebug("Resultado: " + q.getResult() == null);
		translate(q);
		return getTranslation().toString();
	}
	

	public abstract void translate(QuadrupleIF q);

}
