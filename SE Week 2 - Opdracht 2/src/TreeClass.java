import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

public class TreeClass {

	public static void main(String[] args) {
		// root
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("person");

		// children of person 
		DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode("employee");
		DefaultMutableTreeNode customerNode = new DefaultMutableTreeNode("customer");
		
		root.add(employeeNode);
		root.add(customerNode);
		
		// children of employee
		DefaultMutableTreeNode salesRepNode = new DefaultMutableTreeNode("sales_rep");
		DefaultMutableTreeNode engineerNode = new DefaultMutableTreeNode("engineer");
		
		employeeNode.add(salesRepNode);
		employeeNode.add(engineerNode);
		
		// children of customer		
		DefaultMutableTreeNode usCustomerNode = new DefaultMutableTreeNode("us_customer");
		DefaultMutableTreeNode nonUsCustomerNode = new DefaultMutableTreeNode("non_us_customer");
		
		customerNode.add(usCustomerNode);
		customerNode.add(nonUsCustomerNode);
		
		// children of us_customer		
		DefaultMutableTreeNode localCustomersNode = new DefaultMutableTreeNode("local_customers");
		DefaultMutableTreeNode regionalCustomersNode = new DefaultMutableTreeNode("regional_customers");
		
		usCustomerNode.add(localCustomersNode);
		usCustomerNode.add(regionalCustomersNode);

		// level-order traversing
		System.out.println("Lever-order traversing:");
		traverseEnumeration(root.breadthFirstEnumeration());
		System.out.println("");
		
		// pre-order traversing
		System.out.println("Pre-order traversing:");
		traverseEnumeration(root.preorderEnumeration());
		System.out.println("");
		
		// post-order traversing
		System.out.println("Post-order traversing:");
		traverseEnumeration(root.postorderEnumeration());
		System.out.println("");
	}
	
	private static void traverseEnumeration(Enumeration<DefaultMutableTreeNode> en) {
		while (en.hasMoreElements()) {
			DefaultMutableTreeNode node = en.nextElement();
			System.out.println("   " + node);
		}
	}
	

}
