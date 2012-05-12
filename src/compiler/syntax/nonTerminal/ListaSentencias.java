package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

import compiler.CompilerContext;
import compiler.intermediate.Value;
import es.uned.lsi.compiler.intermediate.IntermediateCodeBuilder;
import es.uned.lsi.compiler.intermediate.TemporalFactory;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class ListaSentencias extends NonTerminal {

	private List<NonTerminal> lista;
	/**
	 * Indice de la primera sentencia return en la lista de sentencias
	 */
	int indiceSentenciaReturn = -1;
	
	/**
	 * @return the tieneSentenciaReturn
	 */
	public boolean tieneSentenciaReturn() {
		return indiceSentenciaReturn != -1;
	}

	/**
	 * Obtiene la sentencia return de la lista de sentencias
	 * @return La sentencia return o null si no tiene ninguna sentencia return
	 */
	public SentenciaReturn getSentenciaReturn()
	{
		if(this.tieneSentenciaReturn())
			return (SentenciaReturn) lista.get(indiceSentenciaReturn);
		return null;
	}
	public ListaSentencias()
	{
		super();
		lista = new ArrayList<NonTerminal>();
	}

	public void setLista(List<NonTerminal> lista) {
		this.lista = lista;
	}

	public List<NonTerminal> getLista() {
		return lista;
	}
	
	/**
	 * Agrega una sentencia o expresion a la lista. Si es una sentencia
	 * se comprueba si es una sentencia return para actualizar la variable
	 * "indiceSentenciaReturn"
	 * @param noterminal
	 * @throws Exception
	 */
	public void agregarSentenciaOExpresion(NonTerminal noterminal) throws Exception
	{
		//CompilerContext.getSyntaxErrorManager().syntaxInfo("ListaSentencias, agregando sentencias a la lista. " + lista + noterminal);
		if(noterminal instanceof Expresion)
		{
			lista.add(noterminal);
		}else if(noterminal instanceof Sentencia)
		{
			lista.add(noterminal);
			Sentencia sent = (Sentencia)noterminal;
			// Si es una sentencia Return y no hay otra sentencia return previa,
			// se almacena su indice en la lista de sentencias.
			if(sent instanceof SentenciaReturn && !this.tieneSentenciaReturn())
			{
				this.indiceSentenciaReturn = lista.size() - 1;
				CompilerContext.getSyntaxErrorManager().syntaxInfo("indice de la sent return " + indiceSentenciaReturn);
			}
			
		}else{
		
			CompilerContext.getSemanticErrorManager().semanticFatalError("Una lista de sentencias solo puede contener sentencias o expresiones: " + noterminal.toString());
		}
	}
	
	public void generarCodigoIntermedio()
	{
		ScopeIF scope = CompilerContext.getScopeManager().getCurrentScope();

        IntermediateCodeBuilder cb = new IntermediateCodeBuilder(scope);
        
        for(NonTerminal noterminal : lista)
        {
        	cb.addQuadruples(noterminal.getIntermediateCode());
        }
        
        this.setIntermediateCode(cb.create());
        //CompilerContext.getSemanticErrorManager().semanticDebug(this.getIntermediateCode());
	}
}
