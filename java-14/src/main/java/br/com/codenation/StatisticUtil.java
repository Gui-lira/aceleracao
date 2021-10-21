package br.com.codenation;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StatisticUtil {

	public static int average(int[] elements) {
		int total = 0;
		for (int element: elements) {
			total += element;
		}
		return Math.round(total / elements.length);
	}

	public static int mode(int[] elements) {
		int returnValue = 0;
		int ocurrences = 0;
		for (int element: elements) {
			int subOcurrence = 0;
			for (int element1: elements) {
				if (element == element1) {
					subOcurrence += 1;
				}
			}
			if (subOcurrence > ocurrences) {
				returnValue = element;
				ocurrences = subOcurrence;
			}
		}
		return returnValue;
	}

	public static int median(int[] elements) {
		int size = elements.length;
		int[] newElements = Arrays.stream(elements).sorted().toArray();
		if (size % 2 != 0) return newElements[(elements.length - 1) / 2];
		int first = newElements[elements.length / 2];
		int second = newElements[(elements.length / 2) - 1];
		return Math.round((first + second) / 2);
	}
}