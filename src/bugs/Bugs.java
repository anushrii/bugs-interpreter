package bugs;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import tree.Tree;
import bugs.Token.Type;

/**
 * Interpreter for Bugs-Language. 
 * 
 * @author Anushree Singh
 * @version March 2015
 */
public class Bugs {

	double x,y,angle;
	Color bugColor;
	String bugName; 
	public Map<String,Double> variables;
	public Map<String, Tree<Token>> functions;
	
	
	private Stack<Boolean> loopStack = new Stack<>(); 
	
	 /**
     * Constructs a Bug.
     */
	public Bugs(){

		x =0;
		y=0;
		angle=0;
		bugColor = Color.black;
		variables = new HashMap<>();
		functions = new HashMap<String,Tree<Token>>();
	}
	
	 /**
     * Stores the value specified into the desired variable in the hashMap
     * @param variable 
     *  * @param value 
     */
	public void store(String variable, double value){
	    if(variable.equals("x")){
			x = value;
		}
		else if(variable.equals("y")){
			y = value;		
		}
		else if(variable.equals("angle")){
			angle = value;
			if (angle >= 360) angle = angle%360;
		}
		else {
		variables.put(variable,value);
		}
	}
	
	 /**
     * Fetches the value of the specified  variable, that needs to be 
     * there in the hashMap
     * A <code>RunTimeException</code> will be thrown if the variable(key) 
     * is not in the map.
     * @param  variable
     * @return <code>true</code> value of the the variable.
     */
	public double fetch(String variable) {
		if(variable.equals("x")){
			return x;
		}
		if(variable.equals("y")){
			return y;		
		}
		if(variable.equals("angle")){
			return angle;
		}
		if ( variables.containsKey(variable)) {
			return variables.get(variable);
		}
		else throw new  RuntimeException();
	}
	
	
	 /**
     * Evaluates the tree input
     * @param tree to be evaluated
     * @return <code>true</code> evaluated value.
     */
	public double evaluate(Tree<Token> tree){
		Token s = tree.getValue();
		if (tree.getNumberOfChildren() == 0) {
			if (s.type== Type.NUMBER){
				return Double.parseDouble(s.toStringHelper()) ;
			}
			else {
				return fetch(s.toStringHelper());
			}
		}
		
		if ("+".equals(s.value)){          
			if (tree.getNumberOfChildren() == 1){
				
				if(tree.getChild(0).getValue().type == Type.NUMBER)
					return Double.parseDouble(tree.getChild(0).getValue().toStringHelper());
				
				return fetch(tree.getChild(0).getValue().toStringHelper());
			}
			else if (tree.getNumberOfChildren() == 2){
				double a;
				double b;
				if(tree.getChild(0).getValue().type == Type.NUMBER)
					a = Double.parseDouble(tree.getChild(0).getValue().toStringHelper());
				else
				    a = fetch(tree.getChild(0).getValue().toStringHelper());
				
				if(tree.getChild(1).getValue().type == Type.NUMBER)
					b = Double.parseDouble(tree.getChild(1).getValue().toStringHelper());
				else
				    b = fetch(tree.getChild(1).getValue().toStringHelper());
				
				//double c = a + b ;
				return a + b;
				
			}
		}
		
		if ("-".equals(s.value)){            
			if (tree.getNumberOfChildren() == 1){
				
				if(tree.getChild(0).getValue().type == Type.NUMBER)
					return -(Double.parseDouble(tree.getChild(0).getValue().toStringHelper()));
				
				return -(fetch(tree.getChild(0).getValue().toStringHelper()));
			}
			else if (tree.getNumberOfChildren() == 2){
				double a;
				double b;
				if(tree.getChild(0).getValue().type == Type.NUMBER)
					a = Double.parseDouble(tree.getChild(0).getValue().toStringHelper());
				else
				    a = fetch(tree.getChild(0).getValue().toStringHelper());
				
				if(tree.getChild(1).getValue().type == Type.NUMBER)
					b = Double.parseDouble(tree.getChild(1).getValue().toStringHelper());
				else
				    b = fetch(tree.getChild(1).getValue().toStringHelper());
				
				//double c = a + b ;
				return a - b;
				
			}
		}
		
		
		if ("*".equals(s.value)){            
			 if (tree.getNumberOfChildren() == 2){
				double a;
				double b;
				if(tree.getChild(0).getValue().type == Type.NUMBER)
					a = Double.parseDouble(tree.getChild(0).getValue().toStringHelper());
				else
				    a = fetch(tree.getChild(0).getValue().toStringHelper());
				
				if(tree.getChild(1).getValue().type == Type.NUMBER)
					b = Double.parseDouble(tree.getChild(1).getValue().toStringHelper());
				else
				    b = fetch(tree.getChild(1).getValue().toStringHelper());
				
				//double c = a + b ;
				return a*b;
				
			}
		}
		
		if ("/".equals(s.value)){            
		     if (tree.getNumberOfChildren() == 2){
				double a;
				double b;
				if(tree.getChild(0).getValue().type == Type.NUMBER)
					a = Double.parseDouble(tree.getChild(0).getValue().toStringHelper());
				else
				    a = fetch(tree.getChild(0).getValue().toStringHelper());
				
				if(tree.getChild(1).getValue().type == Type.NUMBER)
					b = Double.parseDouble(tree.getChild(1).getValue().toStringHelper());
				else
				    b = fetch(tree.getChild(1).getValue().toStringHelper());
				
				//double c = a + b ;
				return a/b;
				
			}
		}
		
		if (">".equals(s.value)){           
		     if (tree.getNumberOfChildren() == 2){
				double a;
				double b;
				if(tree.getChild(0).getValue().type == Type.NUMBER)
					a = Double.parseDouble(tree.getChild(0).getValue().toStringHelper());
				else
				    a = fetch(tree.getChild(0).getValue().toStringHelper());
				
				if(tree.getChild(1).getValue().type == Type.NUMBER)
					b = Double.parseDouble(tree.getChild(1).getValue().toStringHelper());
				else
				    b = fetch(tree.getChild(1).getValue().toStringHelper());
				
				if (a>b) return 1;
				else return 0;
			}
		}
		
		if ("<".equals(s.value)){           
		     if (tree.getNumberOfChildren() == 2){
				double a;
				double b;
				if(tree.getChild(0).getValue().type == Type.NUMBER)
					a = Double.parseDouble(tree.getChild(0).getValue().toStringHelper());
				else
				    a = fetch(tree.getChild(0).getValue().toStringHelper());
				
				if(tree.getChild(1).getValue().type == Type.NUMBER)
					b = Double.parseDouble(tree.getChild(1).getValue().toStringHelper());
				else
				    b = fetch(tree.getChild(1).getValue().toStringHelper());
				
				if (a<b) return 1;
				else return 0;
				
			}
		}
		
		if ("=".equals(s.value)){            
		     if (tree.getNumberOfChildren() == 2){
				double a;
				double b;
				if(tree.getChild(0).getValue().type == Type.NUMBER)
					a = Double.parseDouble(tree.getChild(0).getValue().toStringHelper());
				else
				    a = fetch(tree.getChild(0).getValue().toStringHelper());
				
				if(tree.getChild(1).getValue().type == Type.NUMBER)
					b = Double.parseDouble(tree.getChild(1).getValue().toStringHelper());
				else
				    b = fetch(tree.getChild(1).getValue().toStringHelper());
				
				if (a==b) return 1;
				else return 0;
				
				
			}
		}
		
		if ("!=".equals(s.value)){            
		     if (tree.getNumberOfChildren() == 2){
				double a;
				double b;
				if(tree.getChild(0).getValue().type == Type.NUMBER)
					a = Double.parseDouble(tree.getChild(0).getValue().toStringHelper());
				else
				    a = fetch(tree.getChild(0).getValue().toStringHelper());
				
				if(tree.getChild(1).getValue().type == Type.NUMBER)
					b = Double.parseDouble(tree.getChild(1).getValue().toStringHelper());
				else
				    b = fetch(tree.getChild(1).getValue().toStringHelper());
				
				if (a==b) return 0;
				else return 1;
				
				
			}
		}
		
		if (">=".equals(s.value)){          
		     if (tree.getNumberOfChildren() == 2){
				double a;
				double b;
				if(tree.getChild(0).getValue().type == Type.NUMBER)
					a = Double.parseDouble(tree.getChild(0).getValue().toStringHelper());
				else
				    a = fetch(tree.getChild(0).getValue().toStringHelper());
				
				if(tree.getChild(1).getValue().type == Type.NUMBER)
					b = Double.parseDouble(tree.getChild(1).getValue().toStringHelper());
				else
				    b = fetch(tree.getChild(1).getValue().toStringHelper());
				
				if (a==b || a>b) return 1;
				else return 0;
				
				
			}
		}
		if ("<=".equals(s.value)){            
		     if (tree.getNumberOfChildren() == 2){
				double a;
				double b;
				if(tree.getChild(0).getValue().type == Type.NUMBER)
					a = Double.parseDouble(tree.getChild(0).getValue().toStringHelper());
				else
				    a = fetch(tree.getChild(0).getValue().toStringHelper());
				
				if(tree.getChild(1).getValue().type == Type.NUMBER)
					b = Double.parseDouble(tree.getChild(1).getValue().toStringHelper());
				else
				    b = fetch(tree.getChild(1).getValue().toStringHelper());
				
				if (a==b || a<b) return 1;
				else return 0;
				
				
			}
		}
		
		if ("case".equals(s.value)){            
		     if (tree.getNumberOfChildren() == 2){

			double exp = evaluate(tree.getChild(0));
			if (!(exp == 0)){
			interpret(tree.getChild(1)) ;
			}
			return exp;

			}
		}
		return 0;
		
		
	}
	
	 /**
     * Interprets the tree input.
     * @param tree to be interpreted.
     */
	public void interpret(Tree<Token> tree){
		Token s = tree.getValue();
		
		// move
		if ("move".equals(s.value)){ 
			double distance = 	evaluate(tree.getChild(0));
			x = x + distance*Math.cos(Math.toRadians(angle));
		
			y = y + distance*Math.sin(Math.toRadians(angle));
		
		}
		
		// move to
		if ("moveto".equals(s.value)){ 
			double a = 	evaluate(tree.getChild(0));
			double b = 	evaluate(tree.getChild(1));
			x = a;
			y = b;
			
		}
		
		// turn		
		if ("turn".equals(s.value)){ 
			double a = 	evaluate(tree.getChild(0));
			angle = angle + a;
			if (angle >= 360) angle = angle%360;
			
		}
		
		// turn to		
		if ("turnto".equals(s.value)){ 
			double a = 	evaluate(tree.getChild(0));
		    angle = a;
		    if (angle >= 360) angle = angle%360;
		}
		
		// switch
		
		if ("switch".equals(s.value)){
			int number = tree.getNumberOfChildren();
			if(number!=0){
			for(int i=0; i < number ; i++){
				double a = 	evaluate(tree.getChild(i));
				if (a == 1 ) break;
				else continue;
			}
			}
		}
		
		// color detection		
		if ("color".equals(s.value)){ 
			String a = 	tree.getChild(0).getValue().toStringHelper();
			if(a.equals("black")){
			bugColor = Color.black;
			}
			else if(a.equals("blue")){
			bugColor = Color.black;	
		    }
			else if(a.equals("cyan")){
			bugColor = Color.cyan;
		}
			else if(a.equals("darkGray")){
			bugColor = Color.darkGray;
		}
			else if(a.equals("gray")){
			bugColor = Color.gray;
		}
			else if(a.equals("green")){
			bugColor = Color.green;
		}
			else if(a.equals("yellow")){
			bugColor = Color.yellow;
		}
			else if(a.equals("lightGray")){
			bugColor = Color.lightGray;
		}
			else if(a.equals("magenta")){
			bugColor = Color.magenta;
		}
			else if(a.equals("orange")){
			bugColor = Color.orange;
		}
			else if(a.equals("pink")){
			bugColor = Color.pink;
		}
			else if(a.equals("red")){
			bugColor = Color.red;		
		}
			else if(a.equals("white")){
			bugColor = Color.white;
		}
			else if(a.equals("brown")){
			
			bugColor = new Color(165,42,42);
		}
			else if(a.equals("purple")){
			bugColor = new Color(128,0,128);
		}
			else if(a.equals("none")){
			bugColor = null;
		}
			else throw new  RuntimeException();
				
		}
		
		if("function".equals(s.value)){
			functions.put(tree.getChild(0).getValue().toStringHelper(), tree);
		}
		
		if ("Bug".equals(s.value)){ 
		String name = tree.getChild(0).getValue().toStringHelper();
		bugName  = name;
		
		interpret(tree.getChild(1));
		interpret(tree.getChild(2));
		interpret(tree.getChild(3));
		interpret(tree.getChild(4));
	
		}

		if ("list".equals(s.value)){ 
			int number = tree.getNumberOfChildren();
			if(number!=0){
			for(int i=0; i < number ; i++){
				interpret(tree.getChild(i));
			}
			}
		}

		if ("initially".equals(s.value)){ 
			int number = tree.getNumberOfChildren();
			if(number!=0){
			interpret(tree.getChild(0));
			}
		}

		if ("var".equals(s.value)){ 
			
			int number = tree.getNumberOfChildren();
			if(number!=0){
			for(int i=0; i < number ; i++){
			String name =	(tree.getChild(i).getValue().toStringHelper());
			store(name, 0);
			}
		}
		}

		if ("assign".equals(s.value)){ 
			String a = 	tree.getChild(0).getValue().toStringHelper();
			if ( !variables.containsKey(a))  throw new  RuntimeException();
			double val = evaluate(tree.getChild(1));
			store(a, val);
		}

		if ("exit".equals(s.value)){ 
			double condition = evaluate(tree.getChild(0));
			if (condition == 1){
			loopStack.pop();
			loopStack.push(false);
			}
		
		}

		if ("block".equals(s.value)){ 
			int number = tree.getNumberOfChildren();
			if(number!=0){
			for(int i=0; i < number ; i++){
				interpret(tree.getChild(i));
			}
		}
		}

		if ("loop".equals(s.value)){ 
			loopStack.push(true);
			
				while (loopStack.peek()) {
				interpret(tree.getChild(0))	;
				System.out.println (x);
				
				}
				loopStack.pop();
		}

		
		
	}
}
