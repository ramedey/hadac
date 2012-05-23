package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.InstructionSetArchitecture;
import compiler.intermediate.Variable;
import compiler.semantic.type.TypeRecord;

import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class SentenciaAsignacion extends Sentencia {

	private Expresion exp;
	/**
	 * Puede ser una variable, un parametro un campo de registro
	 */
	private NonTerminal variable;
	
	public SentenciaAsignacion(NonTerminal nt, Expresion exp)
	{
		super();
		this.exp = exp;
		variable = nt;
	}
	
	public void generarCodigoIntermedio()
	{		
		AccesoRegistro reg = (AccesoRegistro)variable;
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
        TemporalIF eTemp = exp.getTemporal ();
        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
        Variable var = getVariable(reg);
        int offset = getOffset(reg);
        cb.addQuadruples(exp.getIntermediateCode());
        //Mueve el valor del temporal a la variable-registro var que se calcula con el offset
        cb.addQuadruple(InstructionSetArchitecture.MOVE_REG, var, offset, eTemp);
        this.setIntermediateCode(cb.create()); 
	}

	private int getOffset(AccesoRegistro reg) {
		// TODO Auto-generated method stub
		return ((TypeRecord)reg.getVariableRegistro().getType()).getOffset(reg.getCampoReferenciado().getName());
	}
	
	private Variable getVariable(AccesoRegistro reg)
	{
		return new Variable(reg.getVariableRegistro());
	}
}
