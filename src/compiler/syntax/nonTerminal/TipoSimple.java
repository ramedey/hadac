package compiler.syntax.nonTerminal;

import compiler.semantic.type.TypeSimple;

public class TipoSimple extends NonTerminal {
	
	private TypeSimple tipo;
	
	/**
	 * @return the tipo
	 */
	public TypeSimple getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TypeSimple tipo) {
		this.tipo = tipo;
	}

	public TipoSimple(TypeSimple tSimple)
	{
		tipo = tSimple;
	}

}
