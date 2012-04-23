package compiler.semantic.util;

import java.util.List;

import compiler.CompilerContext;
import compiler.lexical.Token;
import compiler.semantic.symbol.SymbolBooleanConstant;
import compiler.semantic.symbol.SymbolVariable;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.ScopeManagerIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolTableIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;
import es.uned.lsi.compiler.semantic.type.TypeTableIF;

public class Util {

	/**
	 * Comprueba que el token no está registrado en las tablas de símbolos y de tipos
	 *  del ámbito pasado como parámetro y las incluye.
	 * @param scope Ámbito que contiene las tablas de símbolos y tipos a actualizar
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
        // Añadir el tipo a la tabla de tipos
        TypeTableIF typeTable = scope.getTypeTable();
        if(!typeTable.containsType(tipo))
        {
            typeTable.addType(tipo);
        }
        
        // Añadir los identificadores a la tabla de símbolos
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
	
	/**
	 * Comprueba si se ha registrado la tabla de símbolos del ámbito
	 * pasado como parámetro (y superiores) el símbolo pasado como parámetro.
	 * @param textoSimbolo Cadena de caracteres con el nombre del símbolo
	 * @param scope Ámbito donde se comprueba la existencia del símbolo.
	 */
	public static SymbolIF comprobarExisteSimboloEnAmbito(String textoSimbolo, ScopeIF scope)
	{
		if(!scope.getSymbolTable().containsSymbol(textoSimbolo))
		{
			ScopeIF parentScope = scope.getParentScope();
			boolean encontrado = false;
			while(parentScope != null && encontrado == false)
			{
				if(parentScope.getSymbolTable().containsSymbol(textoSimbolo))
				{
					encontrado = true;
				}
				parentScope = parentScope.getParentScope();
			}
			if(!encontrado)
			{
				CompilerContext.getSemanticErrorManager().semanticFatalError("El simbolo " + textoSimbolo + " no existe. Ambito " + scope.getName());
			}
		}
		
		return scope.getSymbolTable().getSymbol(textoSimbolo);
//		
//		if(!scope.getTypeTable().containsType(simbolo.getType()))
//		{
//			CompilerContext.getSemanticErrorManager().semanticFatalError("El símbolo " + textoSimbolo + " no existe en el ámbito actual");
//		}
	}
	
	/**
	 * Busca un tipo en las tablas de tipos. En la actual y las de ámbitos de niveles superiores.
	 * @param scopeManager
	 * @param typeTable
	 * @param name
	 * @return
	 */
    public static TypeIF buscarTipoPreviamenteDeclarado(ScopeManagerIF scopeManager, String name){
        
        boolean encontrado=false;
        ScopeIF scope = scopeManager.getCurrentScope();
        TypeIF tipo=null;
        while(scope != null){
            if (encontrado) 
            {
            	break;
            }
            else {
                tipo= scope.getTypeTable().getType(name);
                if (tipo != null) {encontrado=true;}
            }
        	scope = scope.getParentScope();
            
        }
       return tipo;         
    }
}
