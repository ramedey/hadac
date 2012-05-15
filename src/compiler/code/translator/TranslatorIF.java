package compiler.code.translator;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

public interface TranslatorIF {

	String createTranslation(QuadrupleIF q);
}
