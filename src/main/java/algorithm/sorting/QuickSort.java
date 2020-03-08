package algorithm.sorting;

import algorithm.graph.obj.LoadFileUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSort {

    private enum PivotRuleType{FIRST_ELEMENT, LAST_ELEMENT, MEDIAN_ELEMENT};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        List<Integer> inputArray = LoadFileUtil.loadInversions("resource/quicksort.txt");

        ArrayList<Integer> sortedArrayFirstElement = new ArrayList<Integer>();
        ArrayList<Integer> sortedArrayLastElement = new ArrayList<Integer>();
        ArrayList<Integer> sortedArrayMedianElement = new ArrayList<Integer>();

        int numberOfComparisonsFirstElement = quickSort(inputArray, sortedArrayFirstElement, PivotRuleType.FIRST_ELEMENT);
        int numberOfComparisonsLastElement = quickSort(inputArray, sortedArrayLastElement, PivotRuleType.LAST_ELEMENT);
        int numberOfComparisonsMedianElement = quickSort(inputArray, sortedArrayMedianElement, PivotRuleType.MEDIAN_ELEMENT);

        System.out.println("********************************************");
        System.out.println("Number of Comparisons Pivot First Element = " + numberOfComparisonsFirstElement);
//    System.out.println("Ordered Array =>");
//    for(Integer i : sortedArrayFirstElement){
//      System.out.println(i);
//    }
//    System.out.println("");

        System.out.println("********************************************");
        System.out.println("Number of Comparisons Pivot Last Element = " + numberOfComparisonsLastElement);
//    System.out.println("Ordered Array =>");
//    for(Integer i : sortedArrayLastElement){
//      System.out.println(i);
//    }
//    System.out.println("");

        System.out.println("********************************************");
        System.out.println("Number of Comparisons Pivot Median Element = " + numberOfComparisonsMedianElement);
//    System.out.println("Ordered Array =>");
//    for(Integer i : sortedArrayMedianElement){
//      System.out.println(i);
//    }
        System.out.println("********************************************");
    }

    /**
     * Process quick sort algorithm
     * @param inputArray Input parameter that represents the array to be sorted
     * @param sortedArray Output parameter that represents the sorted array after the process
     * @param type Type of rule to choose the pivot
     * @return The number of comparisons done
     */
    private static int quickSort(List<Integer> inputArray, ArrayList<Integer> sortedArray, PivotRuleType type)
    {
        int numberOfComparisons = 0;

        if(inputArray != null && inputArray.size() > 0)
        {
            if(inputArray.size() == 1){
                sortedArray.addAll(inputArray);
            }
            else
            {
                //NUMBER OF COMPARISONS IS M - 1
                numberOfComparisons = inputArray.size() - 1;

                //CHOOSE THE PIVOT
                int pivotIndex = choosePivot(inputArray, type);

                //PARTITION THE ARRAY AROUND THE CALCULATED PIVOT
                ArrayList<Integer> firstPart = new ArrayList<Integer>();
                ArrayList<Integer> secondPart = new ArrayList<Integer>();

                //SWAP PIVOT ELEMENT WITH FIRST ELEMENT
                ArrayList<Integer> auxInputArray = new ArrayList<Integer>(inputArray);

                if(type != PivotRuleType.FIRST_ELEMENT)
                {
                    Integer pivot = auxInputArray.get(pivotIndex);
                    auxInputArray.set(pivotIndex, auxInputArray.get(0));
                    auxInputArray.set(0, pivot);
                }

                partitionArrayAroundPivot(auxInputArray, firstPart, secondPart);

                //RECURSIVELY SORT 1st PART AND 2nd PART
                ArrayList<Integer> firstPartSorted = new ArrayList<>();
                ArrayList<Integer> secondPartSorted = new ArrayList<>();

                int numberOfComparisonsFirstPart = quickSort(firstPart, firstPartSorted, type);
                int numberOfComparisonsSecondPart = quickSort(secondPart, secondPartSorted, type);

                numberOfComparisons += numberOfComparisonsFirstPart;
                numberOfComparisons += numberOfComparisonsSecondPart;

                //ADD TO THE ORDERED ARRAY THE RESULT
                sortedArray.addAll(firstPartSorted);
                sortedArray.add(auxInputArray.get(0));
                sortedArray.addAll(secondPartSorted);
            }
        }

        return numberOfComparisons;
    }

    /**
     * Choose a pivot for a given array
     * @param array The list of items in which is choosen the pivot
     * @return The pivot index
     */
    private static int choosePivot(List<Integer> array, PivotRuleType type)
    {
        int pivotIndex = 0;

        if(type == PivotRuleType.FIRST_ELEMENT){
            pivotIndex = 0;
        }
        else if(type == PivotRuleType.LAST_ELEMENT){
            pivotIndex = array.size() - 1;
        }
        else if(type == PivotRuleType.MEDIAN_ELEMENT)
        {
            int middleElementPivotIndex = array.size() % 2 == 0 ? (array.size() / 2) - 1 : array.size() / 2;

            Integer initialElement = array.get(0);
            Integer middleElement = array.get(middleElementPivotIndex);
            Integer lastElement = array.get(array.size() - 1);

            ArrayList<Integer> elements = new ArrayList<Integer>();
            elements.add(initialElement);
            elements.add(middleElement);
            elements.add(lastElement);

            Collections.sort(elements);

            if(elements.get(1).intValue() == initialElement.intValue()){
                pivotIndex = 0;
            }
            else if(elements.get(1).intValue() == middleElement.intValue()){
                pivotIndex = middleElementPivotIndex;
            }
            else if(elements.get(1).intValue() == lastElement.intValue()){
                pivotIndex = array.size() - 1;
            }
        }

        return pivotIndex;
    }

    /**
     * Partition of the array with a given pivot.
     * @param array The input array
     * @param firstPart The first part of the array partitioned (output parameter)
     * @param secondPart The second of the array partitioned (output parameter)
     */
    private static void partitionArrayAroundPivot(List<Integer> array, ArrayList<Integer> firstPart, ArrayList<Integer> secondPart)
    {
        ArrayList<Integer> auxArray = new ArrayList<Integer>(array);

        Integer pivot = auxArray.get(0);

        int i = 1;

        for(int j = 1; j < auxArray.size(); j++)
        {
            if(auxArray.get(j).intValue() < pivot.intValue())
            {
                //SWAP A[j] AND A[i]
                Integer valJ = auxArray.get(j);
                Integer valI = auxArray.get(i);

                auxArray.set(i, valJ);
                auxArray.set(j, valI);

                i++;
            }
        }

        //SWAP A[l] AND A[i - 1]
        Integer valI = auxArray.get(i - 1);

        auxArray.set(i - 1, pivot);
        auxArray.set(0, valI);

        //PARTITION THE FINAL ARRAY
        firstPart.addAll(auxArray.subList(0, i - 1));

        if(i < auxArray.size()){
            secondPart.addAll(auxArray.subList(i, auxArray.size()));
        }
    }
}
