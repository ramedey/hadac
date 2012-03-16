package compiler.semantic.util;

import compiler.lexical.Token;
import compiler.semantic.symbol.SymbolVariable;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolTableIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;
import es.uned.lsi.compiler.semantic.type.TypeTableIF;

public class Util {

	/**
	 * Comprueba que el token no est� registrado en las tablas de s�mbolos y de tipos
	 *  del �mbito pasado como par�metro y las incluye.
	 * @param scope �mbito que contiene las tablas de s�mbolos y tipos a actualizar
	 * @param id Token identificador
	 * @param tipo Tipo del identificador
	 */
	public static void actualizarTablasSimbolosYTipos(ScopeIF scope, Token id, TypeIF tipo)
	{
		TypeTableIF typeTable = scope.getTypeTable();
        if(!typeTable.containsType(tipo))
        {
            typeTable.addType(tipo);
        }
        SymbolTableIF symbolTable = scope.getSymbolTable();
        if(!symbolTable.containsSymbol(id.getLexema()))
        {
           symbolTable.addSymbol(new SymbolVariable(scope, id, tipo));
        }
		
	}
	
}
