package compiler.semantic.type;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import compiler.CompilerContext;
import compiler.semantic.symbol.SymbolVariable;
import compiler.syntax.nonTerminal.DecVariable;
import compiler.syntax.nonTerminal.DecVariables;

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
	 * @return the campos
	 */
	public HashMap<String, TypeIF> getCampos() {
		return campos;
	}

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
     * Constructor que crea un objeto TypeRecord con la lista de campos.
     * @param scope
     * @param name
     * @param declaraciones
     */
    public TypeRecord(ScopeIF scope, String name, DecVariables declaraciones)
    {
    	super (scope, name);
        campos = new HashMap<String, TypeIF>();
        
        for(DecVariable declaracion : declaraciones.getDeclaraciones())
        {
        	for(SymbolVariable symbol : declaracion.getListaVariables())
        	{
        		campos.put(symbol.getName(), symbol.getType());
        	}        	
        }
    }
    
    /**
     * Returns the size of the type.
     * @return the size of the type.
     */
    @Override
    public int getSize ()
    {
    	int size = 0;
    	for(TypeIF tipo : getCampos().values())
    	{
    		size += tipo.getSize();
    	}
    	//CompilerContext.getSemanticErrorManager().semanticDebug("Tamaño de variable registro: " + size);
        return size;
    }
    
    public void addCampo(String nombre, TypeIF tipo)
    {
    	campos.put(nombre, tipo);
    }
    
    public boolean containsCampo(String nombre)
    {
    	return campos.containsKey(nombre);
    }
    
    public int getOffset(String nombre)
    {
    	Iterator<Entry<String, TypeIF>> it = campos.entrySet().iterator();
    	int i = 0;
    	while (it.hasNext()) {
    		Entry<String, TypeIF> entry = it.next();
    		if(entry.getKey().equals(nombre))
    		{
    			return i;
    		}
    		i++;    		
    	}
    	return -1;
    }
    
}
