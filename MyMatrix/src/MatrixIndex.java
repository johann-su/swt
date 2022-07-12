public class MatrixIndex {
    private int row;
    private int column;

    MatrixIndex(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public boolean equals(Object o) {
        return o.hashCode()==this.hashCode();
    }

    public int hashCode() {
        return hash(row, column);
    }

    public int hash(int a, int b) {
        // 53, 47 are prime
        int hash = 53; 
        hash = hash * 47 + a;
        hash = hash * 47 + b;
        return hash;
    }
}
