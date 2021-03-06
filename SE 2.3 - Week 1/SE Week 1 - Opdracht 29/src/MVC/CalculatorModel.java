/*
 * (C) Copyright 2005 Davide Brugali, Marco Torchiano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307  USA
 */
package MVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

import multiformat.Base;
import multiformat.BinaryBase;
import multiformat.DecimalBase;
import multiformat.FixedPointFormat;
import multiformat.Format;
import multiformat.FormatException;
import multiformat.HexBase;
import multiformat.NumberBaseException;
import multiformat.OctalBase;
import multiformat.Rational;
import multiformat.RationalFormat;

/**
 * The multiformat calculator
 */
public class CalculatorModel {
  private Rational operand_0 = new Rational();
  private Rational operand_1 = new Rational();
  
  private String tempOperand1 = "";
  private String tempOperand2 = "";
  private boolean writingOperand1 = true;
  
  private ArrayList<ActionListener> actionListenerList = new ArrayList<ActionListener>();
  
  // Opdracht 28: Array for previous calculations.
  private ArrayList<String> previousCalculations = new ArrayList<>();

  // Opdracht 27: The line of operands and the operator. 
  private String calculationLine = "";
  private String currentOperator = "";
  
  // The current format of the calculator
  private Format format = new FixedPointFormat();
  // The current numberbase of the calculator
  private Base base = new DecimalBase();
  
  // Opdracht 29: Stack for calculation.
  private Stack<String> calculationStack = new Stack<String>();
  private String tempOperand = "";
  private int readyToCalculate = 0;

  private boolean newLine = true;
  
  private boolean errorLine = false;
  
  public CalculatorModel() {
  }
  
  public void addOperand(String newOperand) throws FormatException {
	  try{
		  // Opdracht 26: CheckUserInput is called
		  CheckUserInput(newOperand);
		  
		  operand_1 = operand_0;
	      operand_0 = format.parse(newOperand, base);
		  //updateCalculationLine();	      
	  }
	  catch(NumberBaseException e) {
		  errorLine = true;
		  System.out.println("error!");
		  System.out.println(e);
	  }
  }

  public void add(){
    operand_0 = operand_1.plus(operand_0);
    operand_1 = new Rational();
  }
  public void subtract() {
    operand_0 = operand_1.minus(operand_0);
    operand_1 = new Rational();
  }
  public void multiply() {
    operand_0 = operand_1.mul(operand_0);
    operand_1 = new Rational();
  }
  public void divide() {
	  operand_0 = operand_1.div(operand_0);
	  operand_1 = new Rational();
  }
  public void delete() {
    operand_0 = operand_1;
    operand_1 = new Rational();
  }

  public String firstOperand(){
    return format.toString(operand_1,base);
  }
  public String secondOperand(){
    return format.toString(operand_0,base);
  }

  public void setBase(Base newBase){
    base = newBase;
  }
  public Base getBase(){
    return base;
  }
  public void setFormat(Format newFormat){
    format = newFormat;
  }
  public Format getFormat(){
    return format;
  }
  
  /**
   * Opdracht 26
   * Method to check if numbers of a String fit in the current numeral system.
   * 
   * @param rawUserInput The String that needs to be checked.
   * @throws NumberBaseException Is thrown when String contains numbers from another numeral system.
   */
  public void CheckUserInput(String rawUserInput) throws NumberBaseException {
	  String userInput = rawUserInput;    
	  userInput = userInput.replaceAll("\\.","");
  	  userInput = userInput.replaceAll("\\-","");
  	  
  	  char[] digits = userInput.toCharArray();    	    	
	  for (char waarde: digits) {
		  int p = base.getBaseDigits().indexOf(waarde);
		  if (p < 0) {
			  throw new NumberBaseException("Het getal " + waarde + " bestaat niet in het " + base.getName() + " talstelsel!");
		  }    		
	  }
  }  
  	
  	/**
  	 * Opdracht 27:
  	 * 
  	 * Adds a new operand with the addOperand method.
  	 * Then processes the event.
  	 * @param value String thet needs to be added.
  	 */
  	public void writeValue2(String value) {
  		if(writingOperand1) {
  			tempOperand1 = tempOperand1 + value;
  		}
  		else {
  			tempOperand2 = tempOperand2 + value;
  		}
  		//updateCalculationLine();
  		processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
  	}
  	
  	
  	/**
  	 * Opdracht 27:
  	 * 
  	 * Changes the base.
  	 * Then processes the event.
  	 * @param value Base that needs to be added.
  	 */
  	public void sendBase(String value) {
  		switch(value) {
  		case "bin":
  			setBase(new BinaryBase());
  			break;
  		case "oct":
  			setBase(new OctalBase());
  			break;
  		case "dec":
  			setBase(new DecimalBase());
  			break;
  		case "hex":
  			setBase(new HexBase());
  			break;
  		}
  		updateCalculationLine("");
  		processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
  	}
  	
  	/**
  	 * Opdracht 27:
  	 * 
  	 * Changes the current operator.
  	 * When the operator "=" is called the current operands and the current operand will be used to calculate.
  	 * @param operator Operator that needs to be the current one.
  	 */
  	public void sendOperator (String operator) {
  		if(!tempOperand.equals("")) {
  			switch(operator) {
  	  		case "+":
  	  			flushOperand(operator);
  	  			currentOperator = "+";
  	  	  		processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
  	  			break;
  	  		case "-":
  	  			flushOperand(operator);
  	  			currentOperator = "-";
  	  	  		processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
  	  			break;
  	  		case "*":
  	  			flushOperand(operator);
  	  			currentOperator = "*";
  	  	  		processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
  	  			break;
  	  		case "/":
  	  			flushOperand(operator);
  	  			currentOperator = "/";
  	  	  		processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
  	  			break;
  	  		case "=":
  	  			readyToCalculate++;
  				if(readyToCalculate > 1) {
  					flushOperand(operator);
  					calculate();
  	  	  			
  	  	  			if(!errorLine) {
  	  	  	  			updateCalculationLine(secondOperand() + "");
  	  	  			}
	  	  	  		errorLine = false;

	  	  	  		previousCalculations.add(0, calculationLine);

  	  	  	  		processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
  	  	  	  		calculationLine = "";
  	  	  	  		readyToCalculate = 1;
  	  	  	  		newLine = true;
  				}
  				readyToCalculate--;
  				
  	  			
  	  			break;
  	  		}
  		}  		
  	}
  	
  	public void writeValue(String value) {
  		tempOperand = tempOperand + value;
  		updateCalculationLine(value);
  		processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
  	}
  	
  	private void flushOperand(String operator) {
  		readyToCalculate++;
		calculationStack.push(tempOperand);
		calculationStack.push(operator);
		
		updateCalculationLine(" " + operator + " ");
		
		tempOperand = "";
  	}
  	
  	private void calculate() {
  		boolean started = false;
  		while(!calculationStack.isEmpty()) {
  			if(!started) {
  				calculationStack.pop();
  	  			String tempString = calculationStack.pop();
  	  			String tempOperand = calculationStack.pop();

  	  	  		try {
  					addOperand(calculationStack.pop());
  					addOperand(tempString);
  					if(errorLine) {
  						setCalculationLine("Contains characters from another numeral system");
  					}
  					else {
  						switch(tempOperand) {
  	  					case "+": 
  	  						add();
  	  						break;
  	  					case "-": 
  	  						subtract();
  	  						break;
  	  					case "*": 
  	  						multiply();
  	  						break;
  	  					case "/": 
  	  						divide();
  	  						break;
  	  					}
  	  					started = true;
  					}
  					
  				} catch (FormatException e) {
  					e.printStackTrace();
  				}
  			}
  			else {
  				String tempOperand = calculationStack.pop();
  				String tempString = calculationStack.pop();
  	  			
  	  			
  				try {
					addOperand(tempString);
					
					switch(tempOperand) {
  					case "+": 
  						add();
  						break;
  					case "-": 
  						subtract();
  						break;
  					case "*": 
  						multiply();
  						break;
  					case "/": 
  						divide();
  						break;
  					}
				} catch (FormatException e) {
					e.printStackTrace();
				}
  			}
  			
  		}
  	}
  	
  	/**
  	 * Opdracht 27:
  	 * 
  	 * Getter for the calculationLine.
  	 * @return String calculationLine.
  	 */
  	public String getCalculationLine() {
  		return calculationLine;
  	}
  	
  	/**
  	 * Opdracht 28:
  	 * 
  	 * @return ArrayList of Strings of the previous calculations.
  	 */
  	public ArrayList<String> getPreviousCalculations() {
  		return previousCalculations;
  	}
  	
  	/**
  	 * Opdracht 27:
  	 * 
  	 * Method that updates the calculation line with newest values.
  	 */
  	private void updateCalculationLine(String value) {
  		if(newLine) {
  			calculationLine = "base: " + base.getName() + "      " + value;
  			newLine = false;
  		}
  		else {
  			calculationLine = calculationLine + value;
  		}
  	}
  	
  	private void setCalculationLine(String value) {
  		calculationLine = value;
	  	processEvent( new ActionEvent( this, ActionEvent.ACTION_PERFORMED, null));
  	}

  	/**
  	 * Adds an ActionListner object to the ActionListner list.
  	 * @param l The ActionListner object that needs to be added.
  	 */
  	public void addActionListener( ActionListener l){
		actionListenerList.add( l );
	}

  	/**
  	 * Removes an ActionListners from the ActionListnerList.
  	 * @param l The ActionListner that needs to be removed.
  	 */
	public void removeActionListener( ActionListener l){
		if ( actionListenerList.contains( l ) )
			actionListenerList.remove( l );
	}
	
	/**
	 * Processes ActionListners.
	 * @param e
	 */
	private void processEvent(ActionEvent e){
		for( ActionListener l : actionListenerList)
			l.actionPerformed( e );
	}

}