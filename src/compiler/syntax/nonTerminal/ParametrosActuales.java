package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

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
	
}
