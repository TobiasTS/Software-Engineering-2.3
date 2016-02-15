package MVC;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import multiformat.FormatException;

/**
 * Controller of the Calculator.
 * 
 * @author Tobias Schlichter
 * @version 1.0
 */
public class CalculatorController extends JPanel implements ActionListener {

	private CalculatorModel calcModel;
	
	public JButton number0 = new JButton("0");
	public JButton number1 = new JButton("1");
	public JButton number2 = new JButton("2");
	public JButton number3 = new JButton("3");
	public JButton number4 = new JButton("4");
	public JButton number5 = new JButton("5");
	public JButton number6 = new JButton("6");
	public JButton number7 = new JButton("7");
	public JButton number8 = new JButton("8");
    public JButton number9 = new JButton("9");
    public JButton numberA = new JButton("A");
    public JButton numberB = new JButton("B");
    public JButton numberC = new JButton("C");
    public JButton numberD = new JButton("D");
    public JButton numberE = new JButton("E");
    public JButton numberF = new JButton("F");
    
    
    public JButton dot = new JButton(".");
    
    public JButton actionAdd = new JButton("+");
    public JButton actionSubtract = new JButton("-");
    public JButton actionMultiply = new JButton("*");
    public JButton actionDivide = new JButton("/");
    public JButton actionCalculate = new JButton("=");
    
    public JButton baseBin = new JButton("BIN");
    public JButton baseOct = new JButton("OCT");
    public JButton baseDec = new JButton("DEC");
    public JButton baseHex = new JButton("HEX");
    
    /**
     * Constructor for a new CalculatorController object.
     * 
     * @param calcModel The CalculatorModel where this object is the controller for.
     */
    public CalculatorController(CalculatorModel calcModel) {
		setLayout(new GridLayout(6, 3));

    	this.calcModel = calcModel;
    	
    	this.add(number0);
    	number0.addActionListener(this);
    	
    	this.add(number1);
    	number1.addActionListener(this);
    	
    	this.add(number2);
    	number2.addActionListener(this);
    	
    	this.add(number3);
    	number3.addActionListener(this);
    	
    	this.add(actionAdd);
    	actionAdd.addActionListener(this);
    	
    	this.add(number4);
    	number4.addActionListener(this);
    	
    	this.add(number5);
    	number5.addActionListener(this);
    	
    	this.add(number6);
    	number6.addActionListener(this);
    	
    	this.add(number7);
    	number7.addActionListener(this);
    	

    	this.add(actionSubtract);
    	actionSubtract.addActionListener(this);
    	
    	this.add(number8);
    	number8.addActionListener(this);
    	
    	this.add(number9);
    	number9.addActionListener(this);
    	
    	this.add(numberA);
    	numberA.addActionListener(this);
    	
    	this.add(numberB);
    	numberB.addActionListener(this);
    	
    	this.add(actionMultiply);
    	actionMultiply.addActionListener(this);
    	
    	this.add(numberC);
    	numberC.addActionListener(this);
    	
    	this.add(numberD);
    	numberD.addActionListener(this);
    	
    	this.add(numberE);
    	numberE.addActionListener(this);
    	
    	this.add(numberF);
    	numberF.addActionListener(this);
    	

    	this.add(actionDivide);
    	actionDivide.addActionListener(this);
    	
    	this.add(baseBin);
    	baseBin.addActionListener(this);
    	
    	this.add(baseOct);
    	baseOct.addActionListener(this);
    	
    	this.add(baseDec);
    	baseDec.addActionListener(this);
    	
    	this.add(baseHex);
    	baseHex.addActionListener(this);
    	
    	this.add(dot);
    	dot.addActionListener(this);
    	
    	this.add(actionCalculate);
    	actionCalculate.addActionListener(this);
    	
    	
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == number0) {
			calcModel.writeValue("0");
		}
		if(e.getSource() == number1) {
			calcModel.writeValue("1");
		}
		if(e.getSource() == number2) {
			calcModel.writeValue("2");
		}
		if(e.getSource() == number3) {
			calcModel.writeValue("3");
		}
		if(e.getSource() == number4) {
			calcModel.writeValue("4");
		}
		if(e.getSource() == number5) {
			calcModel.writeValue("5");
		}
		if(e.getSource() == number6) {
			calcModel.writeValue("6");
		}
		if(e.getSource() == number7) {
			calcModel.writeValue("7");
		}
		if(e.getSource() == number8) {
			calcModel.writeValue("8");
		}
		if(e.getSource() == number9) {
			calcModel.writeValue("9");
		}
		if(e.getSource() == numberA) {
			calcModel.writeValue("A");
		}
		if(e.getSource() == numberB) {
			calcModel.writeValue("B");
		}
		if(e.getSource() == numberC) {
			calcModel.writeValue("C");
		}
		if(e.getSource() == numberD) {
			calcModel.writeValue("D");
		}
		if(e.getSource() == numberE) {
			calcModel.writeValue("E");
		}
		if(e.getSource() == numberF) {
			calcModel.writeValue("F");
		}
		if(e.getSource() == dot) {
			calcModel.writeValue(".");
		}
		if(e.getSource() == actionAdd) {
			calcModel.sendOperator("+");
		}
		if(e.getSource() == actionSubtract) {
			calcModel.sendOperator("-");
		}
		if(e.getSource() == actionMultiply) {
			calcModel.sendOperator("*");
		}
		if(e.getSource() == actionDivide) {
			calcModel.sendOperator("/");
		}
		if(e.getSource() == actionCalculate) {
			calcModel.sendOperator("=");
		}
		if(e.getSource() == baseBin) {
			calcModel.sendBase("bin");
		}
		if(e.getSource() == baseOct) {
			calcModel.sendBase("oct");
		}
		if(e.getSource() == baseDec) {
			calcModel.sendBase("dec");
		}
		if(e.getSource() == baseHex) {
			calcModel.sendBase("hex");
		}
		
	}
	
}
