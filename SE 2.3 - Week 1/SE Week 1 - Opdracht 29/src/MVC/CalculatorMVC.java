package MVC;

import java.awt.BorderLayout;

import javax.swing.JApplet;

public class CalculatorMVC extends JApplet {
	private CalculatorModel model;
	private CalculatorController controller;
	private CalculatorTextView textView;
	private CalculatorPreviousCalculationsView calculationsView;
	
	public void init() {
		this.setSize(600, 300);
		model = new CalculatorModel();
		
		textView = new CalculatorTextView(model);
		this.getContentPane().add(textView, BorderLayout.NORTH);
		
		controller = new CalculatorController(model);
		this.getContentPane().add(controller,BorderLayout.CENTER);

		calculationsView = new CalculatorPreviousCalculationsView(model);
		this.getContentPane().add(calculationsView, BorderLayout.EAST);
		
		model.addActionListener(textView);
        model.addActionListener(controller);
        model.addActionListener(calculationsView);

		
	}
}
