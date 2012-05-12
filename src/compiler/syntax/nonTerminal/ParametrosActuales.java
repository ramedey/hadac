package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

import compiler.CompilerContext;

import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class ParametrosActuales extends NonTerminal {

	private List<Expresion> parametros;
	
	public ParametrosActuales()
	{
		super();
		parametros = new ArrayList<Expresion>();
	}
	
	public ParametrosActuales(Expresion exp)
	{
		super();
		parametros = new ArrayList<Expresion>();
		
		parametros.add(exp);		
	}
	
	public ParametrosActuales(Expresion exp, ParametrosActuales par)
	{
		super();
		parametros = new ArrayList<Expresion>();
		
		parametros.add(exp);
		
		for(Expresion expresion : par.getParametros())
		{
			parametros.add(expresion);
		}
	}

	/**
	 * @return the parametros
	 */
	public List<Expresion> getParametros() {
		return parametros;
	}
	
	public void generarCodigoIntermedio()
	{
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
		
		for(Expresion parametro : parametros)
		{
			cb.addQuadruples(parametro.getIntermediateCode());
			cb.addQuadruple ("PARAM", parametro.getTemporal ());
		}
		this.setIntermediateCode(cb.create());
	}
	
}
