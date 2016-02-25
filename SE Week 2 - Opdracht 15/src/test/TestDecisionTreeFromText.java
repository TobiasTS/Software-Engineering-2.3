package test;

import classifier.DecisionTree;
import classifier.Feature;
import classifier.FeatureType;
import classifier.Item;
import classifier.TextToDesicionTree;
import junit.framework.TestCase;

public class TestDecisionTreeFromText extends TestCase {
	
	public TestDecisionTreeFromText(String arg0) {
		super(arg0);
	}
	
	public void testText() {
		TextToDesicionTree TextToDT = new TextToDesicionTree();
		DecisionTree dt = TextToDT.getDecisionTree();
		
		FeatureType yn = new FeatureType("YesNo",new String[]{"1","0"});
		
		Feature[] features = new Feature[]
		{ new Feature("Turbo","0",yn),
		  new Feature("EnginPower","0",yn),
		  new Feature("SportBumper","0",yn),
		  new Feature("SportRing","0",yn),
		  new Feature("CruisControll","0",yn),
		  new Feature("ABS","0",yn),
		  new Feature("AC","0",yn),
		  new Feature("Metalic","0",yn)
		};
		
		Item item = new Item("car", features);
		String category = dt.assignCategory(item);
		assertEquals("Super saver",category);
	}

}
