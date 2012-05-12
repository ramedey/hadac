package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.symbol.SymbolIF;

public class LlamadaFuncion extends LlamadaProcedimiento {

	private Object retorno;
	
	public LlamadaFuncion(SymbolIF simbolo, ParametrosActuales parametros) {
		super(simbolo, parametros);
		// TODO Auto-generated constructor stub
	}

	public void generarCodigoIntermedio()
	{
		super.generarCodigoIntermedio();
		
	}
}
