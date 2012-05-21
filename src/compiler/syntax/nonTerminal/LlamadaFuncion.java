package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.Function;
import compiler.semantic.type.TypeFunction;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class LlamadaFuncion extends LlamadaProcedimiento {

	public LlamadaFuncion(TypeIF tipo, ParametrosActuales parametros) {
		super(tipo, parametros, new Function(tipo.getName(), 
				CompilerContext.getScopeManager().getCurrentScope()));
		
	}

	public void generarCodigoIntermedio()
	{
		super.generarCodigoIntermedio();
		
	}
	
	public TypeIF getTipoRetorno()
	{
		return ((TypeFunction)getTipo()).getTipoRetorno();
	}
}
