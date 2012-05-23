package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.InstructionSetArchitecture;
import compiler.intermediate.Variable;

import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelFactoryIF;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class SentenciaFor extends Sentencia {

	private Expresion iterador, inicio, fin;
	private ListaSentencias lista;
	
	public SentenciaFor(Expresion iterador, Expresion inicio, Expresion fin, ListaSentencias lista)
	{
		this.iterador = iterador;
		this.inicio = inicio;
		this.fin = fin;
		this.lista = lista;
	}
	
	public void generarCodigoIntermedio()
	{
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope ();
        LabelFactoryIF lF = new LabelFactory();
        TemporalFactory tF = new TemporalFactory(scope);
        TemporalIF tempCondicion = tF.create();
        LabelIF l1 = lF.create();
        LabelIF l2 = lF.create();
        Variable varIterator = getVariableIterator();
        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
        cb.addQuadruples(iterador.getIntermediateCode());
        cb.addQuadruples(inicio.getIntermediateCode());
        cb.addQuadruple(InstructionSetArchitecture.MOVE, varIterator, inicio.getTemporal());
        cb.addQuadruples(fin.getIntermediateCode());
        cb.addQuadruple(InstructionSetArchitecture.LABEL, l1);
        cb.addQuadruple(InstructionSetArchitecture.GREATER_EQUAL, tempCondicion, fin.getTemporal(), varIterator);
        cb.addQuadruple(InstructionSetArchitecture.BRANCH_FALSE, tempCondicion, l2);
        cb.addQuadruples(lista.getIntermediateCode());
        cb.addQuadruple(InstructionSetArchitecture.INCREMENT, varIterator);
        cb.addQuadruple(InstructionSetArchitecture.BRANCH, l1);
        cb.addQuadruple(InstructionSetArchitecture.LABEL, l2);
        this.setIntermediateCode(cb.create());
	}
	
	private Variable getVariableIterator()
	{
		if(iterador instanceof Elemento)
		{
			OperandIF o = ((Elemento)iterador).getOperand();
			if(o instanceof Variable)
			{
				return (Variable) o;
			}
			CompilerContext.getSemanticErrorManager().semanticError(
					"El iterador del bucle for (" + o + ") debe ser una variable");
		}
		CompilerContext.getSemanticErrorManager().semanticError("El iterador del bucle for debe ser una variable");
		return null;
	}
}
