package test;
import classifier.*;
import junit.framework.TestCase;



public class TestClassifier extends TestCase {

	public TestClassifier(String arg0) {
		super(arg0);
	}

    private DecisionTree buildTree(){
		Node root = new Node("AC");
		
		Node n1=new Node("ABS");
		Node n2=new Node("ABS");
		root.addChild("yes",n1);
		root.addChild("no",n2);
		
		Node n3=new Node("CC");
		Node n4=new Node("CC");
		Node n5=new Node("CC");
		Node n6=new Node("CC");
		n1.addChild("yes", n3);
		n1.addChild("no", n4);
		n2.addChild("yes", n5);
		n2.addChild("no", n6);
		
		// leaves
		Node l1 = new Node("high");
		Node l2 = new Node("high");
		Node l3 = new Node("medium");
		Node l4 = new Node("medium");
		Node l5 = new Node("medium");
		Node l6 = new Node("medium");
		Node l7 = new Node("low");
		Node l8 = new Node("low");
		
		n3.addChild("yes",l1);
		n3.addChild("no",l2);

		n4.addChild("yes",l3);
		n4.addChild("no",l4);
		
		n5.addChild("yes",l5);
		n5.addChild("no",l6);
		
		n6.addChild("yes",l7);
		n6.addChild("no",l8);
		return new DecisionTree(root);
    }
	public void testCategory(){
		
		DecisionTree dt = buildTree();

		FeatureType yn = new FeatureType("YesNo"
						,new String[]{"yes","no"});

		Feature[] features = new Feature[]
		{ new Feature("AC","yes",yn),
		  new Feature("ABS","yes",yn)
		  new Feature("CC", "yes",yn)
		};
		
		Item item = new Item("car",features);
		
		String category = dt.assignCategory(item);
		assertEquals("high",category);
		
		
		item.setFeatureValue("AC","no");
		category = dt.assignCategory(item);
		assertEquals("medium",category);

		item.setFeatureValue("ABS","no");
		category = dt.assignCategory(item);
		assertEquals("low",category);
		
		item.setFeatureValue("CC","no");
		category = dt.assignCategory(item);
		assertEquals("medium",category);
	}
}
