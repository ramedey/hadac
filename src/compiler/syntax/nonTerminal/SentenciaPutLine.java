package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.InstructionSetArchitecture;
import compiler.intermediate.Value;
import compiler.semantic.type.TypeSimpleBoolean;

import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelFactoryIF;
import es.uned.lsi.compiler.intermediate.LabelIF;

public class SentenciaPutLine extends Sentencia {

	public void generarCodigoIntermedio(Expresion exp)
	{
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder (CompilerContext.getScopeManager().getCurrentScope());
        cb.addQuadruples (exp.getIntermediateCode());
        //cb.addQuadruple(InstructionSetArchitecture.ESCRIBE_VALOR, exp.getTemporal());
        if(exp.getTipoInstruccion() instanceof TypeSimpleBoolean)
        {
	        LabelFactoryIF lF = new LabelFactory ();
	        LabelIF l1 = lF.create ();
	        LabelIF l2 = lF.create ();
	        cb.addQuadruple (InstructionSetArchitecture.BRANCH_FALSE, exp.getTemporal(), l1);
	        cb.addQuadruple (InstructionSetArchitecture.ESCRIBE, new Value("\"true\""));
	        cb.addQuadruple (InstructionSetArchitecture.BRANCH, l2);
	        cb.addQuadruple (InstructionSetArchitecture.LABEL, l1);
	        cb.addQuadruple (InstructionSetArchitecture.ESCRIBE, new Value("\"false\""));
	        cb.addQuadruple (InstructionSetArchitecture.LABEL, l2);
        }else{
        	//CompilerContext.getSemanticErrorManager().semanticDebug("Tipo PUT_LINE: " + exp.getTipoInstruccion());
        	cb.addQuadruple(InstructionSetArchitecture.ESCRIBE_VALOR, exp.getTemporal());
        }
        this.setIntermediateCode(cb.create());
	}
	
	public void generarCodigoIntermedio(String cadena)
	{
		IntermediateCodeBuilder cb = new IntermediateCodeBuilder (CompilerContext.getScopeManager().getCurrentScope());
        cb.addQuadruple(InstructionSetArchitecture.ESCRIBE, new Value(cadena));
        this.setIntermediateCode(cb.create());
	}
}
