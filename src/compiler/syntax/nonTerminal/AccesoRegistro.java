package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import compiler.semantic.symbol.SymbolVariable;
import compiler.semantic.type.TypeRecord;

import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class AccesoRegistro extends NonTerminal {

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

	private TemporalIF temporal;
	
	public AccesoRegistro(SymbolVariable variable , String nombreCampo)
	{
		super();
		this.variableRegistro = variable;
		this.referenciarCampo(nombreCampo);
	}
	
	public void generarCodigoIntermedio()
	{
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();
        TemporalFactory tF = new TemporalFactory(scope);
        //TemporalIF rTemp = r.getTemporal();
        //TemporalIF rTempI = r.getTemporalIndex();
        TemporalIF rTempO = tF.create ();
        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
        //cb.addQuadruples (r.getCode ());
        TypeRecord tipo = (TypeRecord)variableRegistro.getType();
        int idOffset = tipo.getOffset(campoReferenciado.getName());
        cb.addQuadruple ("MV", rTempO, idOffset);
//        rn.setTemporal (rTemp);
//        rn.setTemporalIndex (rTempI);
//        rn.setTemporalOffset (rTempO) ;
        this.setIntermediateCode(cb.create());
	}
	
	public void referenciarCampo(String nombreCampo)
	{
		TypeRecord tipo = (TypeRecord)variableRegistro.getType();
		
		tipo.containsCampo(nombreCampo);
		
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
