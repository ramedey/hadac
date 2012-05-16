package compiler.code.translator;

import java.util.ArrayList;
import java.util.List;

public class LabelManager {

	private static List<String> labels = new ArrayList<String>();
	private static int i = 0;
	private static final String saltoLinea = "\n";
	
	public static void addLabel(String label, String intruction)
	{
		labels.add(label + ": " + intruction + saltoLinea);
	}
	
	public static String getLabelText()
	{
		String label = "txt" + i;
		i++;
		return label;
	}
	
	public static String getLabels()
	{
		StringBuilder sb = new StringBuilder();
		for(String label: labels)
		{
			sb.append(label);
		}
		return sb.toString();
	}
	
}
