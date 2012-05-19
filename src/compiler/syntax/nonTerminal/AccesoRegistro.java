package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.intermediate.InstructionSetArchitecture;
import compiler.intermediate.Variable;
import compiler.semantic.symbol.SymbolVariable;
import compiler.semantic.type.TypeRecord;

import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class AccesoRegistro extends Elemento {

	/**
	 * indica el campo de la variableRegistro registro que se está referenciando. Un null significa
	 * 	que no hay ningun campo referenciado.
	 */
	private SymbolVariable campoReferenciado;
	private SymbolVariable variableRegistro;
	/**
	 * @return the variableRegistro
	 */
	public SymbolVariable getVariableRegistro() {
		return variableRegistro;
	}

	/**
	 * @param variableRegistro the variableRegistro to set
	 */
	public void setVariableRegistro(SymbolVariable variableRegistro) {
		this.variableRegistro = variableRegistro;
	}


	
	public AccesoRegistro(SymbolVariable variable , String nombreCampo)
	{
		super();
		this.setTipoInstruccion(variable.getType());
		this.variableRegistro = variable;
		this.referenciarCampo(nombreCampo);
	}
	
	public void generarCodigoIntermedio()
	{
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
        TemporalFactory tF = new TemporalFactory(scope);
        TemporalIF temp = tF.create ();
        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
        //cb.addQuadruples (r.getCode ());
        TypeRecord tipo = (TypeRecord)variableRegistro.getType();
        int idOffset = tipo.getOffset(campoReferenciado.getName());
        Variable var = new Variable(variableRegistro);
        //MOVE_REG tiene que sumar la direccion de var y el offset y almacenarla en un registro.
        //despues alojar la variable temp en la diraccion dada por dicho registro.
        cb.addQuadruple(InstructionSetArchitecture.MOVE_REG, temp, idOffset, var);
        this.setTemporal(temp);
        this.setIntermediateCode(cb.create());
	}
	
	public void referenciarCampo(String nombreCampo)
	{
		TypeRecord tipo = (TypeRecord)variableRegistro.getType();
		
		if(tipo.containsCampo(nombreCampo))
		{
			CompilerContext.getSemanticErrorManager().semanticDebug("El campo " 
					+ nombreCampo + "no pertenece al tipo " + this.getTipoInstruccion());
		}
		
		campoReferenciado = new SymbolVariable(variableRegistro.getScope(), nombreCampo, tipo.getCampos().get(nombreCampo));
	}
	
	public TypeIF getTipoDeCampoReferenciado()
	{
		return campoReferenciado.getType();
	}
	
	public SymbolVariable getCampoReferenciado()
	{
		return campoReferenciado;
	}
	

}
