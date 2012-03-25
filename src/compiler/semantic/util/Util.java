package compiler.semantic.util;

import java.util.List;

import compiler.lexical.Token;
import compiler.semantic.symbol.SymbolBooleanConstant;
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
	
	public static void agregarConstanteBooleanaATablasSimbolosYTipos(ScopeIF scope, List<Token> identificadores, TypeIF tipo, Token constante) throws Exception
	{
        // A�adir el tipo a la tabla de tipos
        TypeTableIF typeTable = scope.getTypeTable();
        if(!typeTable.containsType(tipo))
        {
            typeTable.addType(tipo);
        }
        
        // A�adir los identificadores a la tabla de s�mbolos
        SymbolTableIF symbolTable = scope.getSymbolTable();
        for(int i = 0; i < identificadores.size(); i++)
        {
            if(!symbolTable.containsSymbol(identificadores.get(i).getLexema()))
            {
                symbolTable.addSymbol(new SymbolBooleanConstant(scope, 
                                            identificadores.get(i).getLexema(), 
                                            tipo,
                                            Util.convertirStringABoolean(constante.getLexema())));
            }
        }
	}
	
	
	public static boolean convertirStringABoolean(String cadena) throws Exception
	{
		if(cadena.toLowerCase() == "true")
		{
			return true;
		}else if(cadena.toLowerCase() == "false")
		{
			return false;
		}
		throw new Exception("La cadena de entrada no se puede convertir: " + cadena);
	}
}
