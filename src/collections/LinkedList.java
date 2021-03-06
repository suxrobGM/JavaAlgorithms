package collections;

/**
 * LinkedList represents a sinlge linked list data structure.
 */
public class LinkedList<T> extends AbstractLinkedList<T> {

    /**
     * Creates new instance of LinkedList<>
     */
    public LinkedList() {
        super();
    }

    /**
     * Inserts data at front of the list.
     * @param data to insert.
     * @implNote Time complexity: O(1)
     */
    @Override
    public void addFront(T data) {
        if (isEmpty()) {
            _headNode = new Node<T>(data);
            _lastNode = _headNode;
            _items++;
            return;
        }

        var node = new Node<T>(data, _headNode);
        _headNode = node;
        _items++;
    }

    /**
     * Inserts data at rear of the list.
     * @param data to insert.
     * @implNote Time complexity: O(1)
     */
    @Override
    public void addBack(T data) {
        if (isEmpty()) {
            addFront(data);
            return;
        }

        var lastNode = new Node<T>(data);
        _lastNode.next = lastNode;
        _lastNode = lastNode;
        _items++;
    }

    /**
     * Inserts data at specified position in the list.
     * @param data to insert.
     * @param index of the nth position to insert.
     * @exception IndexOutOfBoundsException Throws if given index is higher than length of the list.
     * @implNote Time complexity: O(n)
     */
    @Override
    public void insertAt(T data, int index) {
        if (index < 0 || index >= _items) {
            throw new IndexOutOfBoundsException();
        }
        else if (index == 0) {
            addFront(data);
            return;
        }
        else if (index == _items - 1) {
            addBack(data);
            return;
        }

        var currentNode = _headNode;

        for (int i = 1; i < index; i++) {
            if (currentNode == null) {
                throw new IndexOutOfBoundsException();
            }

            currentNode = currentNode.next;
        }

        var nextNode = currentNode.next;
        currentNode.next = new Node<T>(data, nextNode);
        _items++;
    }

    /**
     * Removes the first occured item from the list, if it contains in the list.
     * @param item to delete.
     * @implNote Time complexity O(n)
     */
    @Override
    public void remove(T item) {
        var index = find(item);

        if (index == -1) {
            return;
        }
        else if (index == 0) {
            removeFront();
            return;
        }
        else if (index == _items - 1) {
            removeBack();
            return;
        }

        var currentNode = _headNode;

        for (int i = 1; i < index; i++) {
            if (currentNode == null) {
                return;
            }

            currentNode = currentNode.next;
        }

        var nextNode = currentNode.next.next;
        currentNode.next = nextNode;
        _items--;
    }

    /**
     * Deletes node from front of the list.
     * @return data of the removed node. If list is empty then returns null.
     * @implNote Time complexity: O(1)
     */
    @Override
    public T removeFront() {
        if (isEmpty()) {
            return null;
        }

        var removedNode = _headNode;
        _headNode = _headNode.next;
        _items--;
        return removedNode.data;
    }

    /**
     * Deletes node from rear of the list.
     * @return data of the removed node. If list is empty then returns null.
     * @implNote Time complexity: O(n)
     */
    @Override
    public T removeBack() {
        if (isEmpty()) {
            return null;
        }

        var currentNode = _headNode;
        while (currentNode.next != _lastNode) {
            currentNode = currentNode.next;
        }

        var removedNode = _lastNode;
        _lastNode = currentNode;
        _lastNode.next = null;
        _items--;
        return removedNode.data;
    }
}