package task6a;

//Implement the MinMax interface in a generic class
class MinMaxFinder<T extends Comparable<T>> implements MinMax<T> {

 @Override
 public T findMax(T[] array) {
     T max = array[0]; // Assume the first element is the max
     for (T element : array) {
         if (element.compareTo(max) > 0) {
             max = element; // Update max if a larger element is found
         }
     }
     return max;
 }

 @Override
 public T findMin(T[] array) {
     T min = array[0]; // Assume the first element is the min
     for (T element : array) {
         if (element.compareTo(min) < 0) {
             min = element; // Update min if a smaller element is found
         }
     }
     return min;
 }
}

