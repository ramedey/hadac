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

	

}
