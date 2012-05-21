package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.Function;
import compiler.intermediate.InstructionSetArchitecture;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class DecSubprograma extends NonTerminal {

	ListaSentencias sentencias;
	private String name;
	Declaraciones decSub;
	
	public DecSubprograma(String name, Declaraciones dec,ListaSentencias lista)
	{
		super();
		this.name = name;
		sentencias = lista;
		decSub = dec;
	}
	
	public void generarCodigoIntermedio()
	{
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
        Function funcion = new Function(name, scope);
        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
        cb.addQuadruple(InstructionSetArchitecture.LABEL, funcion.getCodeLabel());
        cb.addQuadruples(sentencias.getIntermediateCode());
        this.setIntermediateCode(cb.create());
		
	}
}
