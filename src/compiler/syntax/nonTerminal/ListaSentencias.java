package compiler.syntax.nonTerminal;

import java.util.List;

public class ListaSentencias extends NonTerminal {

	private List<NonTerminal> lista;
	boolean tieneSentenciaReturn = false;
	
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
	 * "tieneSentenciaReturn"
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
			
			if(sent.isSentenciaReturn())
			{
				this.tieneSentenciaReturn = true;
			}
		}
		
		throw new Exception("Una lista de sentencias solo puede contener sentencias o expresiones");
	}
}
