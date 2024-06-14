import library.BinaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An adapter class that adapts a list of integers into a binary tree
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * You CAN add new private constructors, methods, attributes, and nested classes to this class.
 * You MUST NOT edit the parameters, return types, and checked exceptions of the existing methods and constructors.
 * You MUST NOT edit the names of the existing methods and constructors.
 * You MUST NOT rename this file.
 * You MUST NOT delete this file.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public final class ListAdapter implements BinaryTree {
    private final List<Integer> list;
    /**
     * Constructs a ListAdapter with the specified list.
     * @param list Representation of a binary tree.
     * @throws IllegalArgumentException when the list is null or empty.
     */
    public ListAdapter(List<Integer> list) {
        if (list == null)
            throw new IllegalArgumentException("List should not be null");
        
        this.list = List.copyOf(list);
    }

    @Override
    public Integer getRoot() {
        return this.list.get(0);
    }

    @Override
    public ListAdapter getLeft() {
        if (this.list.size() < 2)
            return null;

        List<Integer> newTree = new ArrayList<>();
        return new ListAdapter(addRecur(newTree, 2));
    }

    @Override
    public ListAdapter getRight() {
        if (this.list.size() < 3)
            return null;

        List<Integer> newTree = new ArrayList<>();
        return new ListAdapter(addRecur(newTree, 3));
    }

    /**
     * Helper method to recursively create the new list for the Binary tree to return
     * @param l The list into which we write
     * @param idx The index to add to the list
     * @return The list we added in
     */
    private List<Integer> addRecur(List<Integer> l, int idx) {
        if ((idx-1) >= this.list.size())
            return l;

        l.add(this.list.get(idx-1));
        addRecur(l, (idx*2));
        addRecur(l, (idx*2+1));
        return l;
    }
}
