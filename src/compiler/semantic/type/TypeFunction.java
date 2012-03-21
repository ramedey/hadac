package compiler.semantic.type;

import java.util.HashMap;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for TypeFunction.
 */

// TODO: Student work
//       Include properties to characterize function declarations

public class TypeFunction
    extends TypeProcedure
{   
    TypeIF tipoRetorno;
	HashMap<String, TypeIF> parametros;
	
    /**
     * Constructor for TypeFunction.
     * @param scope The declaration scope.
     */
    public TypeFunction (ScopeIF scope)
    {
        super (scope);
        parametros = new HashMap<String, TypeIF>();
    }

    /**
     * Constructor for TypeFunction.
     * @param scope The declaration scope
     * @param name The name of the function.
     */
    public TypeFunction (ScopeIF scope, String name)
    {
        super (scope, name);
        parametros = new HashMap<String, TypeIF>();
    }
    
    /**
     * Returns the size of the type.
     * @return the size of the type.
     */
    @Override
    public int getSize ()
    {
        // TODO: Student work
        return 1;
    }
    
    /**
	 * @return the tipoRetorno
	 */
	public TypeIF getTipoRetorno() {
		return tipoRetorno;
	}

	/**
	 * @param tipoRetorno the tipoRetorno to set
	 */
	public void setTipoRetorno(TypeIF tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	/**
	 * @return the parametros
	 */
	public HashMap<String, TypeIF> getParametros() {
		return parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(HashMap<String, TypeIF> parametros) {
		this.parametros = parametros;
	}
	
	public void setParametro(String nombre, TypeIF tipo)
	{
		this.parametros.put(nombre, tipo);
	}

}
