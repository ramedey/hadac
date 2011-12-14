package compiler.lexical;

import compiler.syntax.sym;
import compiler.lexical.Token;
import es.uned.lsi.compiler.lexical.ScannerIF;
import es.uned.lsi.compiler.lexical.LexicalError;
import es.uned.lsi.compiler.lexical.LexicalErrorManager;

// incluir aqui, si es necesario otras importaciones

%%
 
%public
%class Scanner
%char
%line
%column
%cup
%ignorecase
%full
%notunix

%implements ScannerIF
%scanerror LexicalError

AND = and   
ARRAY = array
BEGIN = begin
BOOLEAN = Boolean
CONSTANT = constant
ELSE = else
END = end
FALSE = False
FOR = for
FUNCTION = function
IF = if
IN = in
INTEGER = Integer
IS = is
LOOP = loop
OF = of
OR = or
OUT = out
PROCEDURE = procedure
PUT_LINE = Put_line
RECORD = record
RETURN = return
THEN = then
TRUE = True
TYPE = type
WHILE = while
SALTODELINEA = \r\n
COMENTARIO = --.*\r\n
DIGITO = [0-9]
CARACTER = [a-zA-Z]
CONSTANTE_NUMERICA = [1-9]{DIGITO}*
CONSTANTE_CADENA = \".*\"
IDENTIFICADOR = {CARACTER}({CARACTER}|{DIGITO})*

// incluir aqui, si es necesario otras directivas

%{
  LexicalErrorManager lexicalErrorManager = new LexicalErrorManager ();
  private int commentCount = 0;
%}  
  
%%

<YYINITIAL> 
{
           			       
    "+"                {  
                           Token token = new Token (sym.PLUS);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
           			       return token;
                        }
    " "|\t              { /* Ignorar espacios en blanco y tabulaciones */ }
    \r\n                { /* Ignorar saltos de línea */ }
    {COMENTARIO}        { /* Ignorar comentarios */ }
    {CONSTANTE_CADENA}    {  
                           Token token = new Token (sym.CONSTANTE_CADENA);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
    {CONSTANTE_NUMERICA}  {  
                           Token token = new Token (sym.CONSTANTE_NUMERICA);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }                                          
    {CONSTANT}            {  
                           Token token = new Token (sym.CONSTANT);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }            
    {IS}                  {  
                           Token token = new Token (sym.IS);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
    {PROCEDURE}           {  
                           Token token = new Token (sym.PROCEDURE);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
    "("                 {  
                           Token token = new Token (sym.ABRE_PARENTESIS);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
    ")"                 {  
                           Token token = new Token (sym.CIERRA_PARENTESIS);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
    ":"                 {  
                           Token token = new Token (sym.DECLARACION);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
    ":="                {  
                           Token token = new Token (sym.ASIGNACION);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
    ";"                 {  
                           Token token = new Token (sym.PUNTOYCOMA);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
   {INTEGER}              {  
                           Token token = new Token (sym.INTEGER);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
   {FUNCTION}             {  
                           Token token = new Token (sym.FUNCTION);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
   {OUT}                  {  
                           Token token = new Token (sym.OUT);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
    {RETURN}              {  
                           Token token = new Token (sym.RETURN);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
    {BEGIN}               {  
                           Token token = new Token (sym.BEGIN);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
    ","                {  
                           Token token = new Token (sym.COMA);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
    {PUT_LINE}            {  
                           Token token = new Token (sym.PUT_LINE);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
    {END}                 {  
                           Token token = new Token (sym.END);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        }
    {IDENTIFICADOR}     {  
                           Token token = new Token (sym.IDENTIFICADOR);
                           token.setLine (yyline + 1);
                           token.setColumn (yycolumn + 1);
                           token.setLexema (yytext ());
                           return token;
                        } 
    
    // incluir aqui el resto de las reglas patron - accion
    
    
    // error en caso de coincidir con ningún patrón
	[^]     
                        {                                               
                           LexicalError error = new LexicalError ();
                           error.setLine (yyline + 1);
                           error.setColumn (yycolumn + 1);
                           error.setLexema (yytext ());
                           lexicalErrorManager.lexicalError (error);
                        }
    
}


                         


