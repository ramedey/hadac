package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

public class DecVariables extends NonTerminal {

	private List<DecVariable> declaraciones;
	
	public DecVariables()
	{
		super();
		declaraciones = new ArrayList<DecVariable>();
	}
	
	public DecVariables(DecVariable declaracion)
	{
		this();
		declaraciones.add(declaracion);
	}
	
	public void addDeclaracion(DecVariable declaracion)
	{
		declaraciones.add(declaracion);
	}

	/**
	 * @return the declaraciones
	 */
	public List<DecVariable> getDeclaraciones() {
		return declaraciones;
	}
	
	public void registrarDeclaraciones()
	{
		for(DecVariable dec : declaraciones)
		{
			dec.registrarVariables();
		}
	}
	
}
