public class PiecePart implements Cloneable {
    int i;
    int j;
    int identity;

    public PiecePart(int i, int j, int identity) {
        this.i = i;
        this.j = j;
        this.identity = identity;
    }

    public String toString() {
        return identity + "";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        PiecePart other = (PiecePart)o;
        if (other == null) {
            return false;
        }
        return this.i == other.i && this.j == other.j && this.identity == other.identity;
    }

}
