package MVC;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Text view to show the calculation line in postfix format.
 * 
 * @author Tobias Schlichter
 * @version 1.0
 */
public class CalculatorPreviousCalculationsView extends JPanel implements ActionListener {
	private JTextArea calculationText = new JTextArea(5, 20);
	private CalculatorModel calc;

	/**
	 * Constructor of the text view.
	 * Sets layout to GridLayout and adds the variable calculationLineField to this.
	 */
	public CalculatorPreviousCalculationsView(CalculatorModel calc) {	
		calculationText.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(calculationText);
		
		
		this.setLayout(new GridLayout());
		this.add(scrollPane);
	}

	/**
	 * Called when an action event is happens.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		calc = (CalculatorModel) e.getSource();
		writeCalculations();
	}
	
	private void writeCalculations() {
		calculationText.setText("");
		for(String cal: calc.getPreviousCalculations()) {
			if(calculationText.getText().isEmpty()) {
				calculationText.setText(cal);
			}
			else {
				calculationText.setText(calculationText.getText() + "\n" + cal);
			}
		}
	}

}
