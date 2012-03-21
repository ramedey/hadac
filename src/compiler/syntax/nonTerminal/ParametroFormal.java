package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ParametroFormal extends NonTerminal {

	ListaIdentificadores listaIdentificadores;
	TypeIF tipo;
	
	public ParametroFormal(ListaIdentificadores lista, TypeIF tipo)
	{
		listaIdentificadores = lista;
		this.tipo = tipo;
	}
	
}
