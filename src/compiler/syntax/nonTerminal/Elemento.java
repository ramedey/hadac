package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.Value;
import compiler.semantic.symbol.SymbolBooleanConstant;
import compiler.semantic.symbol.SymbolFunction;
import compiler.semantic.symbol.SymbolIntegerConstant;
import compiler.semantic.symbol.SymbolParameter;
import compiler.semantic.symbol.SymbolVariable;

import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Elemento extends Expresion {

	private Object resultado;
	
	/**
	 * @return the resultado
	 */
	public Object getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(Object resultado) {
		this.resultado = resultado;
	}

	public Elemento(Object valor, TypeIF tipo)
	{
		super(tipo);
		resultado = valor;
	}
	
	public Elemento(AccesoRegistro acreg)
	{
		super(acreg.getTipoDeCampoReferenciado());
		resultado = acreg.getCampoReferenciado().getValor();
		this.setIntermediateCode(acreg.getIntermediateCode());
	}
	
//	public Elemento(SymbolVariable variable)
//	{
//		super(variable.getType());
//		resultado = variable.getValor();
//	}
	
	public Elemento(SymbolIF simbolo)
	{
		super(simbolo.getType());
		if(simbolo instanceof SymbolVariable)
        {
			resultado = ((SymbolVariable)simbolo).getValor();
        }else if(simbolo instanceof SymbolBooleanConstant){
        	resultado = ((SymbolBooleanConstant)simbolo).getValue();
        }else if(simbolo instanceof SymbolIntegerConstant){
        	resultado = ((SymbolIntegerConstant)simbolo).getValue();
        }else if(simbolo instanceof SymbolParameter){
        	resultado = ((SymbolParameter)simbolo).getValue();
        }else if(simbolo instanceof SymbolFunction){
        	resultado = 0; // TODO: Student work
        }else{
        	CompilerContext.getSemanticErrorManager().semanticFatalError("El identificador debe ser una variable o constante");
        }
	}
	
	@Override
	public Object getValue() {
		
		return resultado;
	}

	@Override
	public void generarCodigoIntermedio() {
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
        TemporalFactory tF = new TemporalFactory (scope);
        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
        TemporalIF temp = tF.create ();
        CompilerContext.getSemanticErrorManager().semanticDebug("codigo intermedio elemento: " + this.getResultado());
		
        cb.addQuadruple ("MV", temp, new Value(this.getResultado()));
        this.setTemporal(temp);
        if(this.getIntermediateCode() == null)
        {
        	this.setIntermediateCode(cb.create());
        }else{
        	this.getIntermediateCode().addAll(cb.create());
        }
        CompilerContext.getSemanticErrorManager().semanticDebug(this.getIntermediateCode());
	}

	

}
