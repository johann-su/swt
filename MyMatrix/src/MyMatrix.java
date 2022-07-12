import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class MyMatrix<T> implements Matrix<T> {
    private Map<MatrixIndex,T> matrixEntries = new HashMap<MatrixIndex, T>();
    private Iterator<T> iterator = new DepthFirstIterator();

    MyMatrix() {};

    @Override
    public int getRowCount() {
        int rows = 0;

        for (MatrixIndex m : matrixEntries.keySet()) {
            if (m.getRow() >= rows && matrixEntries.get(m) != null) {
                rows = m.getRow()+1;
            } 
        }

        return rows;
    }

    @Override
    public int getColumnCount() {
        int columns = 0;

        for (MatrixIndex m : matrixEntries.keySet()) {
            if (m.getColumn() >= columns && matrixEntries.get(m) != null) {
                columns=m.getColumn()+1;
            }
        }
        return columns;
    }

    @Override
    public int getObjectCount() {
        int objects = 0;

        for (T m : matrixEntries.values()) {
            if (m != null) {
                objects++;
            }
        }

        return objects;
    }

    @Override
    public int getDistinctObjectCount() {
        Set<T> distictObjects = new HashSet<T>();

        for (T m : matrixEntries.values()) {
            distictObjects.add(m);
        }

        return distictObjects.size();
    }

    @Override
    public Iterator<T> iterator() {
        return iterator;
    }

    @Override
    public T get(int row, int column) {
        if (row < 0 || column < 0 || row >= getRowCount() || column >= getColumnCount()) {
            throw new IllegalArgumentException();
        }
        for (MatrixIndex m : matrixEntries.keySet()) {
            if (m.getRow() == row && m.getColumn() == column) {
                return matrixEntries.get(m);
            }
        }
        return null;
    }

    @Override
    public T put(int row, int column, T value) {
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException();
        }
        T prevVal = null;
        try {
            prevVal = get(row, column);
        } catch (IllegalArgumentException e) {

        }
        matrixEntries.put(new MatrixIndex(row, column), value);
        return prevVal;
    }

    @Override
    public boolean contains(T value) {
        for (T m : matrixEntries.values()) {
            if (m.equals(value)) {
                return true;
            }
        }
        return false;
    }

    class DepthFirstIterator implements Iterator<T> {
        private int currentCol = 0;
        private int currentRow = 0;

        DepthFirstIterator() {}

        private void increment() {
            currentRow++;
            if (currentRow > getRowCount()-1) {
                currentCol++;
                currentRow = 0;
            }
        }
        
        @Override
        public boolean hasNext() {
            // System.out.println("Max: " + getRowCount() + "," + getColumnCount() + " Pos: " + currentRow + "," + currentCol);
            int prevRow = currentRow;
            int prevCol = currentCol;

            try {
                while(get(currentRow, currentCol) == null) {
                    increment();
                }
                return true;
            } catch (IllegalArgumentException e) {
                //TODO: handle exception
            }

            currentRow = prevRow;
            currentCol = prevCol;

            return false;
        }

        @Override
        public T next() {
            if (hasNext() == false) {
                throw new NoSuchElementException();
            }
            try {
                while (get(currentRow, currentCol) == null) {
                    increment();
                };
            } catch (IllegalArgumentException e) {
                return null;
            }

            T res = get(currentRow, currentCol);
            increment();
            return res;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
    }
}
