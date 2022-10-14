package ch.epfl.sweng;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class QuicksortTest {

  private Integer[] i;
  @BeforeEach
  public void initializeArray() {
    i = IntStream.range(0, 100).boxed().toArray(Integer[]::new);
    //Integer[] i2 = IntStream.range(0, 100).boxed().toArray((in) -> new Integer[100]);
    //Integer[] i3 = IntStream.range(0, 10).boxed().toArray(new IntFunction<Integer[]>() {
    //  @Override
    //  public Integer[] apply(int value) {
    //    System.out.printf("value %d\n", value); // only called once for last value
    //    return new Integer[10];
    //  }
    //});
    //System.out.println(Arrays.toString(i));
  }

  @Test
  public void testAddition() {
    assertThat(1 + 1, is(2));
  }

  @Test
  public void quickSortShouldNotAccessIndexOverArraySize() {
    Integer[] copy = List.of(i).toArray(Integer[]::new);
    Collections.shuffle(Arrays.asList(copy));
    Quicksort.sort(copy, Comparator.naturalOrder());
    assertThat(copy, is(i));
  }

  @Test
  public void quickSortOnFiniteArrayShouldNotResultInInfinitLoop() {
    Integer[] copy = List.of(i).toArray(Integer[]::new);
    Collections.shuffle(Arrays.asList(copy));
    Quicksort.sort(copy, Comparator.naturalOrder());
    assertThat(copy, is(i));
  }
  @Test
  public void quickSortShouldNotAccessIndexBelowArraySize() {
    Integer[] copy = List.of(i).toArray(Integer[]::new);
    Collections.shuffle(Arrays.asList(copy));
    Quicksort.sort(copy, Comparator.naturalOrder());
    assertThat(copy, is(i));
  }

}
