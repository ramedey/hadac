package compiler.code.translator;

import compiler.CompilerContext;
import compiler.code.ExecutionEnvironmentEns2001;
import compiler.intermediate.Temporal;
import compiler.intermediate.Value;
import compiler.intermediate.Variable;

import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public abstract class TranslatorBase implements TranslatorIF {

	StringBuilder translation;
	public final String SALTO_LINEA = "\n";
	public final String DISPLAY_ADDRESS = "#65001";
	/**
	 * Contador de ámbitos para calcular el desplazamiento del Display
	 */
	public static int scopeCount = 0;
	
	/**
	 * @return the scopeCount
	 */
	public int getScopeCount() {
		return scopeCount;
	}

	/**
	 * @param scopeCount the scopeCount to set
	 */
	public void setScopeCount(int scopeCount) {
		TranslatorBase.scopeCount = scopeCount;
	}

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
		getTranslation().append(";;;;;;;;;;;" + q + SALTO_LINEA);
		translate(q);
		getTranslation().append(SALTO_LINEA);// + ";;;;;;FIN;;;;;" + q + SALTO_LINEA);
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
			//Los parámetros se posicionan en las direcciones superiores al puntero de marco
			if(v.isParameter())
			{
				return "#" + (v.getAddress()+1) + "[.IX]";
			}
			if(isNoLocal(v))
			{
				return getNoLocalAddress(v);
			}
			return "#-" + v.getAddress() + "[.IX]";
		}else if(o instanceof Temporal)
		{
			Temporal t = (Temporal)o;
			return "#-" + t.getAddress() + "[.IX]";
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

	protected void createInstruction(String Instruction, String comments)
	{
		getTranslation().append(Instruction);
		getTranslation().append("	;" + comments + SALTO_LINEA);
	}
	
	protected void createInstruction(String Instruction)
	{
		getTranslation().append(Instruction + SALTO_LINEA);		
	}
	
	protected void createComment(String comment) {
		getTranslation().append("	;" + comment + SALTO_LINEA);
	}

	private String getNoLocalAddress(Variable var)
	{		
		//return "#-" + var.getAddress() + "[.R1]";
		return "[.R1]";
	}
	
	protected boolean isVariable(OperandIF op)
	{
		return (op instanceof Variable);
	}
	
	protected boolean isNoLocal(Variable var)
	{
//		createComment("La variable " + var.getName() + " es no-local?" + (var.getScope().getLevel() != scopeCount));
//		createComment("ScopeConut = " + getScopeCount() + ". Ambito de " + var.getName() + " = " + var.getScope().getLevel());
		return (var.getScope().getLevel() != getScopeCount());		
	}
}
