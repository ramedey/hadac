package compiler.semantic.symbol;

import compiler.semantic.type.TypeRecord;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SymbolVariableRecord extends SymbolVariable {

	/**
	 * indica el campo de la variable registro que se está referenciando. Un null significa
	 * 	que no hay ningun campo referenciado.
	 */
	SymbolVariable campoReferenciado = null;
	
	public SymbolVariableRecord(ScopeIF scope, String name, TypeIF type) {
		super(scope, name, type);
		// TODO Auto-generated constructor stub
	}
	
	public SymbolVariableRecord(SymbolVariable simbolo, String nombrecampoReferenciado) {
		super(simbolo.getScope(), simbolo.getName(), simbolo.getType());
		referenciarCampo(nombrecampoReferenciado);
	}
	
	public void referenciarCampo(String nombreCampo)
	{
		TypeRecord tipo = (TypeRecord)this.getType();
		
		tipo.containsCampo(nombreCampo);
		
		campoReferenciado = new SymbolVariable(this.getScope(), nombreCampo, tipo.getCampos().get(nombreCampo));
	}
	
	public TypeIF getTipoDeCampoReferenciado() throws Exception
	{
		if(campoReferenciado == null)
		{
			throw new Exception("El campo referenciado no existe.");
		}
		
		return campoReferenciado.getType();
	}
	
	public SymbolVariable getCampoReferenciado()
	{
		return campoReferenciado;
	}

}
