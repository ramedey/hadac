package compiler.semantic.type;

import java.util.HashMap;

import compiler.syntax.nonTerminal.SentenciaReturn;

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
    private TypeIF tipoRetorno;
    private SentenciaReturn sentenciaReturn;
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
    
    public TypeFunction (ScopeIF scope, String name, HashMap<String, TypeIF> parametros)
    {
        super (scope, name, parametros);
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

	public void setSentenciaReturn(SentenciaReturn sentenciaReturn) {
		this.sentenciaReturn = sentenciaReturn;
	}

	public SentenciaReturn getSentenciaReturn() {
		return sentenciaReturn;
	}

}
