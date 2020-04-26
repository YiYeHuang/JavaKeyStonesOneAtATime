package algorithm.sorting.mergeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class FJMergeSort {

    public static void main(String[] args) {
        Long[] ar = privideArray(100);
        List<Long> longs = asList(ar);
        Arrays.stream(ar).forEach(x -> System.out.print(x + " "));
        System.out.println(isSorted(longs));

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<List<Long>> task = new MergeTask(longs);
        List<Long> result = pool.invoke(task);
        pool.shutdown();

        result.forEach(x -> System.out.print(x + " "));
        System.out.println(isSorted(result));

        //Arrays.stream(ar).sorted().forEach(x -> System.out.print(x + " "));
    }

    public static class MergeTask extends RecursiveTask<List<Long>> {
        private static final int THRESHOLD = 4;
        private final List<Long> list;

        public MergeTask(List<Long> list) {
            this.list = list;
        }

        @Override
        protected List<Long> compute() {
            if (list.size() < THRESHOLD) {
                return list.stream().sorted().collect(toList());
            }

            MergeTask left = new MergeTask(list.stream().limit(list.size()/2).collect(toList()));
            MergeTask right = new MergeTask(list.stream().skip(list.size()/2).collect(toList()));
            // make task split to other thread to do
            invokeAll(left, right);

            return merge(left.join(), right.join());
        }
    }

    public static List<Long> merge(List<Long> a, List<Long> b) {
        int i=0, j=0;
        List<Long> result = new ArrayList<>(a.size() + b.size());
        while(i < a.size() && j < b.size())
            result.add(a.get(i) < b.get(j) ? a.get(i++): b.get(j++));

        while(i < a.size())
            result.add(a.get(i++));

        while(j < b.size())
            result.add(b.get(j++));

        return result;
    }

    public static long[] merge(long[] a, long[] b) {

        long[] answer = new long[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while(i < a.length && j < b.length)
            answer[k++] = a[i] < b[j] ? a[i++] : b[j++];

        while(i < a.length)
            answer[k++] = a[i++];

        while(j < b.length)
            answer[k++] = b[j++];

        return answer;
    }

    public static Long[] privideArray(int length) {
        assert length > 0;
        Random random = new Random(length);
        Long[] array = new Long[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = (long) random.nextInt(10);
        }
        return array;
    }

    static boolean isSorted(List<Long> array) {
        for(int i = 1; i < array.size(); i++) {
            if(array.get(i-1) > array.get(i)) return false;
        }
        return true;
    }
}
