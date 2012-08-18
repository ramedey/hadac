package compiler.code;

import java.util.Arrays;
import java.util.List;

import compiler.code.translator.*;
import compiler.intermediate.InstructionSetArchitecture;
import compiler.semantic.type.TypeSimple;

import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.code.MemoryDescriptorIF;
import es.uned.lsi.compiler.code.RegisterDescriptorIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

/**
 * Class for the ENS2001 Execution environment.
 */

public class ExecutionEnvironmentEns2001 
    implements ExecutionEnvironmentIF
{    
    private final static int      MAX_ADDRESS = 65535; 
    private final static String[] REGISTERS   = {
       ".PC", ".SP", ".SR", ".IX", ".IY", ".A", 
       ".R0", ".R1", ".R2", ".R3", ".R4", 
       ".R5", ".R6", ".R7", ".R8", ".R9"
    };
    
    private RegisterDescriptorIF registerDescriptor;
    private MemoryDescriptorIF   memoryDescriptor;
    private boolean debugMode = false;
    
    
    /**
     * Constructor for ENS2001Environment.
     */
    public ExecutionEnvironmentEns2001 ()
    {       
        super ();
    }
    
    /**
     * Returns the size of the type within the architecture.
     * @return the size of the type within the architecture.
     */
    @Override
    public final int getTypeSize (TypeSimple type)
    {      
        return 1;  
    }
    
    /**
     * Returns the registers.
     * @return the registers.
     */
    @Override
    public final List<String> getRegisters ()
    {
        return Arrays.asList (REGISTERS);
    }
    
    /**
     * Returns the memory size.
     * @return the memory size.
     */
    @Override
    public final int getMemorySize ()
    {
        return MAX_ADDRESS;
    }
           
    /**
     * Returns the registerDescriptor.
     * @return Returns the registerDescriptor.
     */
    @Override
    public final RegisterDescriptorIF getRegisterDescriptor ()
    {
        return registerDescriptor;
    }

    /**
     * Returns the memoryDescriptor.
     * @return Returns the memoryDescriptor.
     */
    @Override
    public final MemoryDescriptorIF getMemoryDescriptor ()
    {
        return memoryDescriptor;
    }

    /**
     * Translate a quadruple into a set of final code instructions. 
     * @param cuadruple The quadruple to be translated.
     * @return a quadruple into a set of final code instructions. 
     */
    @Override
    public final String translate (QuadrupleIF quadruple)
    {      
    	TranslatorIF trans;
    	String op = quadruple.getOperation();
    	if(op.equals(InstructionSetArchitecture.ADD))
    	{
    		trans = new TranslatorAdd();    		
    	}else if(op.equals(InstructionSetArchitecture.BRANCH))
    	{
    		trans = new TranslatorBranch();    		
    	}else if(op.equals(InstructionSetArchitecture.BRANCH_FALSE))
    	{
    		trans = new TranslatorBranch();    		
    	}else if(op.equals(InstructionSetArchitecture.BRANCH_TRUE))
    	{
    		trans = new TranslatorBranch();    		
    	}else if(op.equals(InstructionSetArchitecture.CALL))
    	{
    		trans = new TranslatorCall();   		
    	}else if(op.equals(InstructionSetArchitecture.EQUAL))
    	{
    		trans = new TranslatorLogical();   		
    	}else if(op.equals(InstructionSetArchitecture.ESCRIBE))
    	{
    		trans = new TranslatorWriteText();    		
    	}else if(op.equals(InstructionSetArchitecture.ESCRIBE_VALOR))
    	{
    		trans = new TranslatorWriteExp();		
    	}else if(op.equals(InstructionSetArchitecture.INCREMENT)){
    		
    		trans = new TranslatorIncrement();
    	}else if(op.equals(InstructionSetArchitecture.INICIO)){
    		
    		trans = new TranslatorInitial();
    	}else if(op.equals(InstructionSetArchitecture.FINAL)){
    		
    		trans = new TranslatorFinal();
    	}else if(op.equals(InstructionSetArchitecture.GREATER_THAN)){
    		
    		trans = new TranslatorLogical();
    	}else if(op.equals(InstructionSetArchitecture.GREATER_EQUAL)){
    		
    		trans = new TranslatorLogical();
    	}else if(op.equals(InstructionSetArchitecture.LABEL)){
    		
    		trans = new TranslatorLabel();
    	}else if(op.equals(InstructionSetArchitecture.MOVE)){
    		
    		trans = new TranslatorMove();
    	}else if(op.equals(InstructionSetArchitecture.MOVE_REG)){
    		
    		trans = new TranslatorMoveReg();
    	}else if(op.equals(InstructionSetArchitecture.OR)){
    		
    		trans = new TranslatorLogical();
    	}else if(op.equals(InstructionSetArchitecture.PARAM)){
    		
    		trans = new TranslatorParam();
    	}else if(op.equals(InstructionSetArchitecture.RET)){
    		
    		trans = new TranslatorRet();
    	}else if(op.equals(InstructionSetArchitecture.RET_VALUE)){
    		
    		trans = new TranslatorRetValue();
    	}else{
    	
    		return quadruple.toString(); 
    	}
    	if(debugMode)
    	{
    		return quadruple.toString();
    	}
    	//return  quadruple.toString() + "\n" + trans.createTranslation(quadruple);
    	return  trans.createTranslation(quadruple);
    }
}
