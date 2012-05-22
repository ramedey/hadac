package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.Function;
import compiler.intermediate.InstructionSetArchitecture;
import compiler.intermediate.Procedure;
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
        Procedure sub = new Procedure(name, scope);
        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
        cb.addQuadruples(decSub.getIntermediateCode());
        cb.addQuadruple(InstructionSetArchitecture.LABEL, sub.getCodeLabel(), scope.getLevel());
        cb.addQuadruples(sentencias.getIntermediateCode());
        //Si es un procedimiento añado la instrucción de retorno
        if(!sentencias.tieneSentenciaReturn())
        {
        	cb.addQuadruple(InstructionSetArchitecture.RET);
        }
        this.setIntermediateCode(cb.create());
		
	}
}
