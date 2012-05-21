package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.intermediate.LabelIF;


/**
 * Abstract Class for Axiom non terminal.
 */
public class Axiom
    extends NonTerminal
{
	private LabelIF mainLabel;
    /**
     * Constructor for Axiom.
     */
    public Axiom ()
    {
        super (); 
    }
	public void setMainLabel(LabelIF mainLabel) {
		this.mainLabel = mainLabel;
	}
	public LabelIF getMainLabel() {
		return mainLabel;
	}
}
