package compiler.syntax.nonTerminal;

import java.util.List;

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
		if(noterminal instanceof Expresion)
		{
			lista.add(noterminal);
		}else if(noterminal instanceof Sentencia)
		{
			Sentencia sent = (Sentencia)noterminal;
			
			// Si es una sentencia Return y no hay otra sentencia return previa,
			// se almacena su indice en la lista de sentencias.
			if(sent instanceof SentenciaReturn && !this.tieneSentenciaReturn())
			{
				this.indiceSentenciaReturn = lista.size() - 1;
			}
		}
		
		throw new Exception("Una lista de sentencias solo puede contener sentencias o expresiones");
	}
}
