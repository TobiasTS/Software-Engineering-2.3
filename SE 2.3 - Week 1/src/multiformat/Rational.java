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
package multiformat;

/**
 * Class representing a rational ('breuk').
 * 
 * @author J.Baljé
 * @author Tobias Schlichter
 * @version 1.1
 */
public class Rational {
	static final double PRECISION=10;
	static final double EPSILON = Math.pow(10,-PRECISION);
	
	double numerator = 0.0; // teller
	double denominator = 1.0; // noemer

	/**
	 * Create a new Rational.
	 * @param num Numerator.
	 * @param den Denominator.
	 */
	public Rational(double num, double den) {
		numerator = num;
		denominator = den;
		simplify();
	}

	/**
	 * Create a new Rational.
	 */
	public Rational() {
	}
	
	/**
	 * Create a new Rational.
	 * @param number A number that will be the Rational.
	 */
	public Rational(double number) {
		numerator = number;
		denominator = 1.0;
		canonical();
		simplify();
	}

	/**
	 * Get rid of any decimals in the numerator. E.g. 12.5/1.0 becomes 125.0/10.0
	 * (Note that any decimals in the denominator aren't handled, eg 10/0.5.
	 *  This seems an omission.)
	 *  Seen also unittest TestRational.java
	 */
	public void canonical() {
		double num = Math.abs(numerator);
		double decimal = num - Math.floor(num);
		int num_digits = 0;

		while (decimal > EPSILON && num_digits < PRECISION) {
			num = num * 10;
			decimal = num - Math.floor(num);
			num_digits++;
		}

		numerator = numerator * Math.pow(10.0, num_digits);
		denominator = denominator * Math.pow(10.0, num_digits);
	}

	/**
	 * Simplify the Rational. 125/10 becomes 25/2.
	 *  Seen also unittest TestRational.java
	 */
	public void simplify() {
		// Take the smallest from the two (10.0)
		double divisor = Math.min(Math.abs(numerator), denominator);
		// Step from 10.0 to 9.0 to ... 1.0
		for (; divisor > 1.0; divisor -= 1.0) {
			double rn =	Math.abs(
					Math.IEEEremainder(Math.abs(numerator), divisor));
			double rd = Math.abs(
					Math.IEEEremainder(denominator, divisor));
			// If both the numerator and denominator have a very small remainder
			// then they can both be divided by devisor (in our example 5).
			if (rn < EPSILON && rd < EPSILON) {
				numerator /= divisor;
				denominator /= divisor;
				divisor = Math.min(Math.abs(numerator), denominator);
			}
		}
	}

	/**
	 * Add two Rationals
	 * @param other Another Rational to add to this.
	 * @return A new Rational representing the sum.
	 */
	public Rational plus(Rational other) {
		if (denominator == other.denominator)
			return new Rational(numerator + other.numerator
								,other.denominator);
		else
			// a/x + b/y = 
			// (breuken gelijknamig maken)
			// a*y/x*y + b*x/x*y = (a*y + b*x)/x*y
			return new Rational(numerator * other.denominator + 
										denominator * other.numerator
								,denominator * other.denominator);
	}
	
	/**
	 * Subtract 2 Rationals.
	 * @param other Another Rational to subtract from this.
	 * @return A new Rational representing the subtraction.
	 */
	public Rational minus(Rational other) {
		if (denominator == other.denominator)
			return new Rational(numerator - other.numerator, denominator);
		else
			return new Rational(numerator * other.denominator - 
									denominator * other.numerator
								,denominator * other.denominator);
	}
	
	/**
	 * Multiply 2 Rationals.
	 * @param other Another Rational to multiply with this.
	 * @return A new Rational representing the multiplication.
	 */
	public Rational mul(Rational other) {
		return new Rational(
			numerator * other.numerator,
			denominator * other.denominator);
	}

	/**
	 * Devide 2 Rationals.
	 * @param other Another Rational to devide this.
	 * @return A new Rational representing the dividation.
	 */
	public Rational div(Rational other) {
		Rational rat;
		try{
			if(other.numerator == 0) {
				throw new ArithmeticException("Cannot divide by zero");
			}
			rat =  new Rational(
					numerator * other.denominator,
					denominator * other.numerator);
		}
		catch(ArithmeticException ex) {
			System.out.println("An integer cannot be divided by zero");
			rat = new Rational();
		}
		
		return rat;
	}
	
	/**
	 * Creates a copy of a Rational.
	 * @param other Another Rational to copy.
	 */
	public void copyOf(Rational other) {
		this.numerator = other.numerator;
		this.denominator = other.denominator;
	}
	
	/**
	 * Returns the numerator.
	 * @return Numerator.
	 */
	public double getNumerator(){
		return numerator;
	}
	
	/**
	 * Returns the denominator.
	 * @return Denominator.
	 */
	public double getDenominator() {
		return denominator;
	}
	
	/**
	 * Sets numerator.
	 * @param num numerator.
	 */
	public void setNumerator(double num){
		numerator = num;
	}
	
	/**
	 * Set denominator.
	 * @param den denominator.
	 */
	public void setDenominator(double den) {
		denominator = den;
	}
}