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
OR = or
PROCEDURE = procedure
PUT_LINE = Put_line
RECORD = record
RETURN = return
THEN = then
TRUE = True
TYPE = type
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
           			   
    " "|\t                  { /* Ignorar espacios en blanco y tabulaciones */ }
    {SALTODELINEA}          { /* Ignorar saltos de línea */ }
    {COMENTARIO}            { /* Ignorar comentarios */ }    
    "+"                     {  
                               Token token = new Token (sym.PLUS);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
               			       return token;
                            }
    "("                     {  
                               Token token = new Token (sym.ABRE_PARENTESIS);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    ")"                     {  
                               Token token = new Token (sym.CIERRA_PARENTESIS);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    ":"                     {  
                               Token token = new Token (sym.DECLARACION);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    ":="                    {  
                               Token token = new Token (sym.ASIGNACION);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    "="                     {  
                               Token token = new Token (sym.IGUAL);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    ">"                     {  
                               Token token = new Token (sym.MAYORQUE);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    ";"                     {  
                               Token token = new Token (sym.PUNTOYCOMA);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    "."                     {  
                               Token token = new Token (sym.PUNTO);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    ","                     {  
                               Token token = new Token (sym.COMA);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    ".."                    {  
                               Token token = new Token (sym.PUNTOPUNTO);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {BEGIN}                 {  
                               Token token = new Token (sym.BEGIN);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {BOOLEAN}               {  
                               Token token = new Token (sym.BOOLEAN);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {CONSTANT}              {  
                               Token token = new Token (sym.CONSTANT);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {CONSTANTE_CADENA}      {  
                               Token token = new Token (sym.CONSTANTE_CADENA);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {CONSTANTE_NUMERICA}    {  
                                Token token = new Token (sym.CONSTANTE_NUMERICA);
                                token.setLine (yyline + 1);
                                token.setColumn (yycolumn + 1);
                                token.setLexema (yytext ());
                                return token;
                            }
    {ELSE}                  {  
                               Token token = new Token (sym.ELSE);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {END}                   {  
                               Token token = new Token (sym.END);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {FALSE}                 {  
                               Token token = new Token (sym.FALSE);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {FOR}                   {  
                               Token token = new Token (sym.FOR);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {FUNCTION}              {  
                               Token token = new Token (sym.FUNCTION);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {IF}                    {  
                               Token token = new Token (sym.IF);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {IN}                    {  
                               Token token = new Token (sym.IN);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {IS}                    {  
                               Token token = new Token (sym.IS);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {PROCEDURE}             {  
                               Token token = new Token (sym.PROCEDURE);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {INTEGER}               {  
                               Token token = new Token (sym.INTEGER);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {FUNCTION}              {  
                               Token token = new Token (sym.FUNCTION);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {LOOP}                  {  
                               Token token = new Token (sym.LOOP);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {OR}                    {  
                               Token token = new Token (sym.OR);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {PROCEDURE}             {  
                               Token token = new Token (sym.PROCEDURE);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {PUT_LINE}              {  
                               Token token = new Token (sym.PUT_LINE);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {RECORD}                {  
                               Token token = new Token (sym.RECORD);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {RETURN}                {  
                               Token token = new Token (sym.RETURN);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {THEN}                  {  
                               Token token = new Token (sym.THEN);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {TRUE}                  {  
                               Token token = new Token (sym.TRUE);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
    {TYPE}                  {  
                               Token token = new Token (sym.TYPE);
                               token.setLine (yyline + 1);
                               token.setColumn (yycolumn + 1);
                               token.setLexema (yytext ());
                               return token;
                            }
   {IDENTIFICADOR}          {  
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


                         


