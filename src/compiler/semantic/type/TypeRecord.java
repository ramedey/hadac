package compiler.semantic.type;

import java.util.HashMap;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for TypeRecord.
 */

// TODO: Student work
//       Include properties to characterize records

public class TypeRecord
    extends TypeBase
{   
	/**
	 * Campos del registro
	 */
	HashMap<String, TypeIF> campos;
    /**
     * Constructor for TypeRecord.
     * @param scope The declaration scope.
     */
    public TypeRecord (ScopeIF scope)
    {
        super (scope);
        campos = new HashMap<String, TypeIF>();
    }

    /**
     * Constructor for TypeRecord.
     * @param scope The declaration scope.
     * @param name The name of the type.
     */
    public TypeRecord (ScopeIF scope, String name)
    {   
        super (scope, name);
        campos = new HashMap<String, TypeIF>();
    }
   
    /**
     * Constructor for TypeRecord.
     * @param record The record to copy.
     */
    public TypeRecord (TypeRecord record)
    {
        super (record.getScope (), record.getName ());
        campos = new HashMap<String, TypeIF>(record.campos);
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
    
    public void addCampo(String nombre, TypeIF tipo)
    {
    	campos.put(nombre, tipo);
    }
    
    public boolean containsCampo(String nombre)
    {
    	return campos.containsKey(nombre);
    }
    
}
