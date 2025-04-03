package pcd.ass01;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ListUtils {
    public static <E> List<List<E>> partitionByNumber(List<E> elems, int numberOfPartitions) {
        List<List<E>> partitions = new ArrayList<>();
        for (int i = 0; i < numberOfPartitions; i++) {
            partitions.add(new ArrayList<E>());
        }
        for (int i = 0; i < elems.size(); i++) {
            partitions.get(i % numberOfPartitions).add(elems.get(i));
        }
        return partitions;
    }
}
