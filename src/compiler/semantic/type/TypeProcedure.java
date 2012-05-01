package compiler.semantic.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import compiler.CompilerContext;
import compiler.syntax.nonTerminal.Expresion;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for TypeProcedure.
 */

// TODO: Student work
//       Include properties to characterize procedure declarations

public class TypeProcedure
    extends TypeBase
{   
   protected HashMap<String, TypeIF> parametros;

/**
     * Constructor for TypeProcedure.
     * @param scope The declaration scope.
     */
    public TypeProcedure (ScopeIF scope)
    {
        super (scope);
    }

    /**
     * Constructor for TypeProcedure.
     * @param scope The declaration scope
     * @param name The name of the procedure.
     */
    public TypeProcedure (ScopeIF scope, String name)
    {
        super (scope, name);
    }
    
    /**
     * Constructor for TypeProcedure.
     * @param scope The declaration scope
     * @param name The name of the procedure.
     */
    public TypeProcedure (ScopeIF scope, String name, HashMap<String, TypeIF> parametros)
    {
        super (scope, name);
        setParametros(parametros);
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
	 * @return the parametros
	 */
	public HashMap<String, TypeIF> getParametros() {
		return parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(HashMap<String, TypeIF> parametros) {
		CompilerContext.getSemanticErrorManager().semanticDebug("Numero de parametros: " + parametros.size());
		this.parametros = new HashMap<String, TypeIF>(parametros);
//		for(String key : parametros.keySet())
//		{
//			this.parametros.put(key, parametros.get(key));
//		}
	}

	public void setParametro(String nombre, TypeIF tipo) {
		this.parametros.put(nombre, tipo);
	}
	
	/**
	 * Comprueba el número, orden y tipo de los parámetros actuales pasados como
	 * parámetro de acuerdo con la definición del tipo de subprograma.
	 * @param parametrosActuales Listado de parámetros de una llamada a función o procedimiento.
	 * @return devuelve True si coincide.
	 */
	public boolean validarParametros(List<Expresion> parametrosActuales)
	{
		if(parametrosActuales.size() != parametros.size())
		{
			CompilerContext.getSemanticErrorManager().semanticDebug("No coincide el numero de parametros: " + parametrosActuales.size() + " y " + parametros.size());
			return false;
		}
		
		List<TypeIF> tipos = new ArrayList<TypeIF>(parametros.values());
		
		for(int i = 0; i < parametrosActuales.size(); i++)
		{
			CompilerContext.getSemanticErrorManager().semanticDebug("Comparando " + parametrosActuales.get(i).getTipoInstruccion() + " y " + tipos.get(i));
			if(!parametrosActuales.get(i).getTipoInstruccion().getName().equals(tipos.get(i).getName()))
			{
				return false;
			}
		}
		
		return true;
	}
}
