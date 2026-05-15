import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> implements Collection<T> {

    private Node<T> root;
    private int size = 0;

    @Override
    public boolean add(T value) {
        if (root == null) {
            root = new Node<>(value);
            size++;
            return true;
        }
        return addRec(root, value);
    }

    private boolean addRec(Node<T> node, T value) {
        if (value.compareTo(node.getValue()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new Node<>(value));
                size++;
                return true;
            } else {
                return addRec(node.getLeft(), value);
            }
        } else if (value.compareTo(node.getValue()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new Node<>(value));
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
        T value = (T) o;
        if (!contains(value)) return false;
        root = removeRec(root, value);
        size--;
        return true;
    }

    private Node<T> removeRec(Node<T> node, T value) {
        if (node == null) return null;

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeft(removeRec(node.getLeft(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRight(removeRec(node.getRight(), value));
        } else {
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();

            Node<T> min = findMin(node.getRight());
            node.setValue(min.getValue());
            node.setRight(removeRec(node.getRight(), min.getValue()));
        }
        return node;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public boolean contains(Object o) {
        T value = (T) o;
        return containsRec(root, value);
    }

    private boolean containsRec(Node<T> node, T value) {
        if (node == null) return false;
        if (value.compareTo(node.getValue()) == 0) return true;
        if (value.compareTo(node.getValue()) < 0) {
            return containsRec(node.getLeft(), value);
        } else {
            return containsRec(node.getRight(), value);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    private class BinaryTreeIterator implements Iterator<T> {
        private Stack<Node<T>> stack = new Stack<>();

        public BinaryTreeIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node<T> node) {
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
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node<T> node = stack.pop();
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
    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;
        for (T val : c) {
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
        for (T val : this) {
            arr[i++] = val;
        }
        return arr;
    }

    @Override
    public <U> U[] toArray(U[] a) {
        throw new UnsupportedOperationException("toArray with array not implemented");
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }
}