package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

import compiler.CompilerContext;
import compiler.intermediate.InstructionSetArchitecture;

import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class ParametrosActuales extends NonTerminal {

	private List<Expresion> parametros;
	
	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(List<Expresion> parametros) {
		this.parametros = parametros;
	}

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
	
	/**
	 * 
	 * @param exp
	 * @param par
	 */
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
			if(!parametroPreviamenteGenerado(parametro))
			{
//				CompilerContext.getSemanticErrorManager().semanticDebug("Generando parametro: " + parametro.toString());
				cb.addQuadruples(parametro.getIntermediateCode());
//				//La instrucción define el temporal que contiene el parametro y su desplazamiento
//				// para localizarlo en el area de datos del subprograma
//				cb.addQuadruple(InstructionSetArchitecture.PARAM, parametro.getTemporal(), parametros.indexOf(parametro)+1);
			}
		}
		this.setIntermediateCode(cb.create());
		
	}
	
	//TODO: controlar que no se genere codigo intermedio dos veces para los parametros
	// que provienen de la otra clase (parametro par);
	private boolean parametroPreviamenteGenerado(Expresion parametro)
	{
		for(QuadrupleIF quadruple : parametro.getIntermediateCode())
		{
			if(quadruple.getOperation().equals("PARAM"))
			{
				return true;
			}
		}
		return false;
	}
}
