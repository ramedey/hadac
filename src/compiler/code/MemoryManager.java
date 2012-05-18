package compiler.code;

import java.util.List;

import compiler.CompilerContext;
import compiler.intermediate.Temporal;
import compiler.semantic.symbol.SymbolVariable;

import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;

public class MemoryManager {

	private static int gAddress = 0;
	
	/**
	 * @return the gAddress
	 */
	public static int getgAddress() {
		return gAddress;
	}

	/**
	 * @param gAddress the gAddress to set
	 */
	public static void setgAddress(int gAddress) {
		MemoryManager.gAddress = gAddress;
	}

	public static void assignAddresses()
    {
    	int lOffset = 0;
  		
 		 
 		 List<ScopeIF> scopes = CompilerContext.getScopeManager().getAllScopes ();
 		 for (ScopeIF scope: scopes) {
           List<SymbolIF> symbols = scope.getSymbolTable ().getSymbols();
           for (SymbolIF s: symbols) {
               if (s instanceof SymbolVariable){
                   SymbolVariable symbol = (SymbolVariable)s;
                   if (scope.getLevel () == 0)
                   {
                       symbol.setAddress(gAddress + symbol.getType().getSize());
                       gAddress += symbol.getType().getSize();
                   }else {
                       symbol.setAddress(lOffset + symbol.getType().getSize());
                       lOffset += symbol.getType().getSize();
                   }
               }
           }
           List<TemporalIF> temporals = scope.getTemporalTable ().getTemporals();
           for (TemporalIF t: temporals)
           {
        	   Temporal temp = (Temporal)t;
               t.setAddress (lOffset + temp.getSize ());
               lOffset += temp.getSize ();
                   
           } 
 		 }
    }
}