package task6a;
//Generic Interface
interface MinMax<T extends Comparable<T>> {
T findMax(T[] array);
T findMin(T[] array);
}