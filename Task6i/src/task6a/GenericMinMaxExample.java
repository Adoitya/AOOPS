package task6a;

//Main class to test the program
public class GenericMinMaxExample {
 public static void main(String[] args) {
     // Integer array
     Integer[] intArray = {3, 5, 7, 2, 8};
     MinMax<Integer> intFinder = new MinMaxFinder<>();
     System.out.println("Integer Array: ");
     System.out.println("Max: " + intFinder.findMax(intArray));
     System.out.println("Min: " + intFinder.findMin(intArray));

     // String array
     String[] strArray = {"apple", "orange", "banana", "grape"};
     MinMax<String> strFinder = new MinMaxFinder<>();
     System.out.println("\nString Array: ");
     System.out.println("Max: " + strFinder.findMax(strArray));
     System.out.println("Min: " + strFinder.findMin(strArray));

     // Character array
     Character[] charArray = {'a', 'z', 'm', 'k'};
     MinMax<Character> charFinder = new MinMaxFinder<>();
     System.out.println("\nCharacter Array: ");
     System.out.println("Max: " + charFinder.findMax(charArray));
     System.out.println("Min: " + charFinder.findMin(charArray));

     // Float array
     Float[] floatArray = {3.5f, 2.1f, 7.8f, 1.9f};
     MinMax<Float> floatFinder = new MinMaxFinder<>();
     System.out.println("\nFloat Array: ");
     System.out.println("Max: " + floatFinder.findMax(floatArray));
     System.out.println("Min: " + floatFinder.findMin(floatArray));
 }
}