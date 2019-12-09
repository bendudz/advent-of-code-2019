package aoc.day8;

import com.google.common.collect.Lists;
import lombok.val;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Day8 {
    private final String input;
    private final Integer imageSize;

    private Integer BLACK = 0;
    private Integer WHITE = 1;
    private Integer TRANSPARENT = 2;

    public Day8(String input, int imageSize) {
        this.input = input;
        this.imageSize = imageSize;
    }

    public Integer numberOfLayers() {
        List<List<Integer>> subSets = getImageLayers();
        return subSets.size();
    }

    public long findLeastZeroInLayer(List<Integer> layer) {
        return getLayerDigitCount(layer, "0");
    }

    public int imageChecksum() {
        List<List<Integer>> subSets = getImageLayers();
        List<Integer> leastZeroLayer = subSets.stream()
                .min(Comparator.comparing(this::findLeastZeroInLayer))
                .orElseThrow(NoSuchElementException::new);

        int numberOnes = getLayerDigitCount(leastZeroLayer, "1");
        int numberTwos = getLayerDigitCount(leastZeroLayer, "2");
        return numberOnes * numberTwos;
    }

    private List<List<Integer>> getImageLayers() {
        val intList = Arrays.stream(input.split("")).map(Integer::parseInt).collect(toList());
        return Lists.partition(intList, imageSize);
    }

    private int getLayerDigitCount(List<Integer> leastZeroLayer, String s) {
        return (int) leastZeroLayer.stream().map(String::valueOf).filter(c -> c.equals(s)).count();
    }


    public String finalImage() {
        List<List<Integer>> layers = getImageLayers();
        List<Integer> output = new ArrayList<>();
        for (int j=0; j < imageSize; j++) {
            output.add(3);
        }
            for (val layer : layers) {
                for (int i = 0; i < layer.size(); i++) {
                    val pixel = layer.get(i);
                    switch (pixel) {
                        case 0:
                        case 1:
                            if (output.get(i).equals(3)) {
                                output.set(i, pixel);
                            }
                            break;
                        case 2:
                            break;
                    }
                }
            }

        return output.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
