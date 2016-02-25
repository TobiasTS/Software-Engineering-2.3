package classifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Creates a decisiontree from a textfile.
 * @author Tobias Schlichter
 * @version 1.1
 */
public class TextToDesicionTree {
	// Path to the text files.
	private static final String TRAININGSETPATH = "src/test/TrainingSet.txt";
	private static final String OPTIONSPATH = "src/test/OptiesText.txt";
	
	// FeatureType for all features.
	private static final FeatureType ONEORZERO = new FeatureType("OneZero", new String[]{"1", "0"});
	
	// Files to read.
	private static final File TRAININGSETFILE = new File(TRAININGSETPATH);
	private static final File OPTIONSFILE = new File(OPTIONSPATH);
	
	// HashMaps to save the values.
	private Map<Item, String> trainingsSetMap = new HashMap<Item, String>();
	private Map<String, FeatureType> featuresMap = new HashMap<String, FeatureType>();
	private ArrayList<String> featuresArrayList = new ArrayList<>();
	
	// DecisionTree.
	private DecisionTree dt;
	
	/**
	 * Constructor for a new TexToDecisionTree object.
	 * 		Creates the features.
	 * 		Creates the trainingset object.
	 * 		Creates the desicion tree.
	 */
	public TextToDesicionTree() {    	
		try {
			createFeatures();
			createTrainingSet();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		
		this.dt = new DecisionTree(trainingsSetMap,featuresMap);
	}
	
	/**
	 * Creates a new Item object.
	 * @param itemValues String[] with the values of the item.
	 * @return Item object.
	 */
	private Item createItem(String[] itemValues) {
		Feature[] featureValues = new Feature[featuresArrayList.size()];
		
		for(int i = 0; i < featuresArrayList.size(); i++) {
			featureValues[i] = new Feature(featuresArrayList.get(i), 
					itemValues[i], 
					ONEORZERO);
		}
		
		return new Item("Car", featureValues);
	}
	
	/**
	 * Fills the HashMap with the TRAININGSETFILE file.
	 * @throws FileNotFoundException when the input file is not found.
	 */
	private void createTrainingSet() throws FileNotFoundException {
		Scanner fileScanner = new Scanner(TRAININGSETFILE);
		Scanner lineScanner;
		fileScanner.nextLine();
		fileScanner.nextLine();
		
		String[] itemFeatures = new String[featuresArrayList.size() + 1];
		
		while(fileScanner.hasNextLine()) {
			lineScanner = new Scanner(fileScanner.nextLine());
			lineScanner.useDelimiter(";");
			lineScanner.next();
			
			int counter = 0;
			while(lineScanner.hasNext()) {
				itemFeatures[counter] = lineScanner.next();
				counter++;
			}
			String itemClass = itemFeatures[featuresArrayList.size()];
			itemFeatures[featuresArrayList.size()] = null;
			Item item = createItem(itemFeatures);
			trainingsSetMap.put(item, itemClass);
		}
	}
	
	/**
	 * Loops trough the OprionsFile and fills the array and hashmap.
	 * @throws FileNotFoundException when the input file is not found.
	 */
	private void createFeatures() throws FileNotFoundException {
		Scanner n = new Scanner(OPTIONSFILE);
		String tempLine;
		while(n.hasNext()) {
			tempLine = n.nextLine();
			featuresMap.put(tempLine, ONEORZERO);
			featuresArrayList.add(tempLine);
		}
	}
	
	/**
	 * Getter for the DecisionTree
	 * @return DecistionTree created tree.
	 */
	public DecisionTree getDecisionTree() {
		return dt;
	}
	
}