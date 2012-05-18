package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.InstructionSetArchitecture;

import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelFactoryIF;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class SentenciaIf extends Sentencia {

	private Expresion exp;
	private ListaSentencias parteIf;
	private ListaSentencias parteElse = null;
	
	public SentenciaIf(Expresion exp, ListaSentencias parteIf)
	{
		this.exp = exp;
		this.parteIf = parteIf;
	}
	
	public SentenciaIf(Expresion exp, ListaSentencias parteIf, ListaSentencias parteElse)
	{
		this(exp, parteIf);
		this.parteElse = parteElse;
	}
	
	public void generarCodigoIntermedio()
	{		
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope ();
        LabelFactoryIF lF = new LabelFactory ();
        LabelIF l1 = lF.create ();
        TemporalIF eTemp = exp.getTemporal ();
        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
    	if(!tieneParteElse())
        {        	
        	cb.addQuadruples (exp.getIntermediateCode());
            cb.addQuadruple (InstructionSetArchitecture.BRANCH_FALSE, eTemp, l1);
            cb.addQuadruples (parteIf.getIntermediateCode());
            cb.addQuadruple (InstructionSetArchitecture.LABEL, l1);            
        }else
        {                
	        LabelIF l2 = lF.create();
	        cb.addQuadruples (exp.getIntermediateCode());
	        cb.addQuadruple (InstructionSetArchitecture.BRANCH_FALSE, eTemp, l1);
	        cb.addQuadruples (parteIf.getIntermediateCode());
	        cb.addQuadruple (InstructionSetArchitecture.BRANCH, l2);
	        cb.addQuadruple (InstructionSetArchitecture.LABEL, l1);
	        cb.addQuadruples (parteElse.getIntermediateCode());
	        cb.addQuadruple (InstructionSetArchitecture.LABEL, l2);	        
        }
        this.setIntermediateCode(cb.create ());
        CompilerContext.getSemanticErrorManager().semanticDebug(this.getIntermediateCode());
	}
	
	private boolean tieneParteElse()
	{
		return (parteElse != null);
	}
}
