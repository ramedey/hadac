package compiler.syntax.nonTerminal;

import compiler.semantic.type.TypeFunction;

import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Elemento extends Expresion {

	
	private OperandIF operand;
	
	
	public Elemento()
	{
		super();
	}
	
	public Elemento(TypeIF tipo)
	{
		super(tipo);		
	}
	
	public Elemento(AccesoRegistro acreg)
	{
		super(acreg.getVariableRegistro().getType());
		this.setTemporal(acreg.getTemporal());
		this.setIntermediateCode(acreg.getIntermediateCode());
	}
	
	public Elemento(SymbolIF simbolo)
	{
		super(simbolo.getType());
		
	}
	
	/**
	 * Construye un elemento a partir de una llamada a función.
	 * Propaga el valor de retorno de la llamada (como un temporal)
	 * @param llamada
	 */
	public Elemento (LlamadaFuncion llamada)
	{
		super(llamada.getTipoRetorno());
//		TypeFunction function = (TypeFunction)llamada.getTipo();
//		this.setTemporal(function.getSentenciaReturn().getExpresion().getTemporal());
//		this.setIntermediateCode(llamada.getIntermediateCode());
	}
	
	@Override
	public void generarCodigoIntermedio() {
//		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
//        TemporalFactory tF = new TemporalFactory (scope);
//        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
//        TemporalIF temp = tF.create ();
//        CompilerContext.getSemanticErrorManager().semanticDebug("codigo intermedio elemento: " + this.getResultado());
//		
//        cb.addQuadruple ("MV", temp, new Value(this.getResultado()));
//        this.setTemporal(temp);
//        if(this.getIntermediateCode() == null)
//        {
//        	this.setIntermediateCode(cb.create());
//        }else{
//        	this.getIntermediateCode().addAll(cb.create());
//        }
//        CompilerContext.getSemanticErrorManager().semanticDebug("El codigo intermedio es nulo: " + this.getIntermediateCode() == null);
//        CompilerContext.getSemanticErrorManager().semanticDebug(this.getIntermediateCode());
	}

	public void setOperand(OperandIF operand) {
		this.operand = operand;
	}

	public OperandIF getOperand() {
		return operand;
	}

	

}
