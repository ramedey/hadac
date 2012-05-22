package compiler.semantic.type;

import java.util.ArrayList;
import java.util.List;

import compiler.CompilerContext;
import compiler.semantic.symbol.SymbolParameter;
import compiler.syntax.nonTerminal.Expresion;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

/**
 * Class for TypeProcedure.
 */

// TODO: Student work
//       Include properties to characterize procedure declarations

public class TypeProcedure
    extends TypeBase
{   
   protected List<SymbolParameter> parametros;

/**
     * Constructor for TypeProcedure.
     * @param scope The declaration scope.
     */
    public TypeProcedure (ScopeIF scope)
    {
        super (scope);
        parametros = new ArrayList<SymbolParameter>();
    }

    /**
     * Constructor for TypeProcedure.
     * @param scope The declaration scope
     * @param name The name of the procedure.
     */
    public TypeProcedure (ScopeIF scope, String name)
    {
        super(scope, name);
        parametros = new ArrayList<SymbolParameter>();
    }
    
    /**
     * Constructor for TypeProcedure.
     * @param scope The declaration scope
     * @param name The name of the procedure.
     */
    public TypeProcedure (ScopeIF scope, String name, List<SymbolParameter> parametros)
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
	public List<SymbolParameter> getParametros() {
		return parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(List<SymbolParameter> parametros) {
//		CompilerContext.getSemanticErrorManager().semanticDebug("Numero de parametros: " + parametros.size());
		this.parametros = new ArrayList<SymbolParameter>(parametros);
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
		
		
		for(int i = 0; i < parametrosActuales.size(); i++)
		{
			CompilerContext.getSemanticErrorManager().semanticDebug("Comparando " + parametrosActuales.get(i).getTipoInstruccion() + " y " + parametros.get(i).getType().getName());
			if(!parametrosActuales.get(i).getTipoInstruccion().getName().equals(parametros.get(i).getType().getName()))
			{
				return false;
			}
			
		}
		
		return true;
	}
}
