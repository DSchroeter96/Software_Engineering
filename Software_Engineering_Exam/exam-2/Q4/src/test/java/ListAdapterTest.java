import library.BinaryTree;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * Class for the ListAdapter tests.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * You MUST use this file to test ListAdapter
 * You CAN add new constructors, methods, and nested classes to this class.
 * You MUST NOT rename this file.
 * You MUST NOT delete this file.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public class ListAdapterTest {
    @Test
    public void onePlusOneIsTwo() {
        assertThat(1 + 1, is(2));
    }

    @Test
    public void throwExceptionWhenLisNull() {
        assertThrows(IllegalArgumentException.class, () -> new ListAdapter(null));
    }
    
    @Test
    public void returnNullEmptyLeft() {
        BinaryTree tree = new ListAdapter(new ArrayList<>());
        assertThat(tree.getLeft(), is(nullValue()));
    }
    
    @Test
    public void returnNullEmptyRight() {
        BinaryTree tree = new ListAdapter(new ArrayList<>());
        assertThat(tree.getRight(), is(nullValue()));
    }

    @Test
    public void rootShouldBeFstElemList() {
        BinaryTree tree = new ListAdapter(List.of(1));
        assertThat(tree.getRoot(), is(1));
    }

    @Test
    public void leftShouldReturnLeftSubTRee() {
        BinaryTree tree = new ListAdapter(List.of(1, 3, 6, 5, 9, 8));
        BinaryTree left = tree.getLeft();

        assertThat(left.getRoot(), is(3));
        assertThat(left.getLeft().getRoot(), is(5));
        assertThat(left.getRight().getRoot(), is(9));
    }

    @Test
    public void rightShouldReturnRightSubTRee() {
        BinaryTree tree = new ListAdapter(List.of(1, 3, 6, 5, 9, 8));
        BinaryTree right = tree.getRight();

        assertThat(right.getRoot(), is(6));
        assertThat(right.getLeft().getRoot(), is(8));
    }
    
    @Test
    public void treeShouldMatchList() {
        BinaryTree tree = new ListAdapter(List.of(1, 3, 6, 5, 9, 8));
        BinaryTree left = tree.getLeft();
        BinaryTree right = tree.getRight();
        assertThat(tree.getRoot(), is(1));
        assertThat(left.getRoot(), is(3));
        assertThat(right.getRoot(), is(6));
        assertThat(left.getLeft().getRoot(), is(5));
        assertThat(left.getRight().getRoot(), is(9));
        assertThat(right.getLeft().getRoot(), is(8));
    }
}
