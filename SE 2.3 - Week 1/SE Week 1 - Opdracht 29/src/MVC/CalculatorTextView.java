package MVC;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Text view to show the calculation line in postfix format.
 * 
 * @author Tobias Schlichter
 * @version 1.0
 */
public class CalculatorTextView extends JPanel implements ActionListener {
	private JTextField calculationLineField = new JTextField();
	private CalculatorModel calc;
	private String calculationLine = "";
	
	/**
	 * Constructor of the text view.
	 * Sets layout to GridLayout and adds the variable calculationLineField to this.
	 */
	public CalculatorTextView(CalculatorModel calc) {
		calculationLineField.setEditable(false);
		this.setLayout(new GridLayout());
		this.add(calculationLineField);
	}

	/**
	 * Called when an action event is happens.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		calc = (CalculatorModel) e.getSource();
		calculationLine = calc.getCalculationLine();
		calculationLineField.setText(calculationLine);
	}

}
