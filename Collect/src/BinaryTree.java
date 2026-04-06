import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree implements Collection<Integer> {

    private Node root;
    private int size = 0;

    @Override
    public boolean add(Integer value) {
        if (root == null) {
            root = new Node(value);
            size++;
            return true;
        }
        return addRec(root, value);
    }

    private boolean addRec(Node node, int value) {
        if (value < node.getValue()) {
            if (node.getLeft() == null) {
                node.setLeft(new Node(value));
                size++;
                return true;
            } else {
                return addRec(node.getLeft(), value);
            }
        } else if (value > node.getValue()) {
            if (node.getRight() == null) {
                node.setRight(new Node(value));
                size++;
                return true;
            } else {
                return addRec(node.getRight(), value);
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        Integer value = (Integer) o;
        if (!contains(value)) return false;
        root = removeRec(root, value);
        size--;
        return true;
    }

    private Node removeRec(Node node, int value) {
        if (node == null) return null;

        if (value < node.getValue()) {
            node.setLeft(removeRec(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(removeRec(node.getRight(), value));
        } else {
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();

            Node min = findMin(node.getRight());
            node.setValue(min.getValue());
            node.setRight(removeRec(node.getRight(), min.getValue()));
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public boolean contains(Object o) {
        Integer value = (Integer) o;
        return containsRec(root, value);
    }

    private boolean containsRec(Node node, int value) {
        if (node == null) return false;
        if (value == node.getValue()) return true;
        if (value < node.getValue()) {
            return containsRec(node.getLeft(), value);
        } else {
            return containsRec(node.getRight(), value);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new BinaryTreeIterator();
    }

    private class BinaryTreeIterator implements Iterator<Integer> {
        private Stack<Node> stack = new Stack<>();

        public BinaryTreeIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node node = stack.pop();
            if (node.getRight() != null) {
                pushLeft(node.getRight());
            }
            return node.getValue();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        boolean changed = false;
        for (Integer val : c) {
            if (add(val)) changed = true;
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) {
            if (remove(o)) changed = true;
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("retainAll not implemented");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        for (Integer val : this) {
            arr[i++] = val;
        }
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("toArray with array not implemented");
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}