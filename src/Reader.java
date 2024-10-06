import java.io.Serializable;
import java.util.Objects;

public class Reader implements Serializable, Comparable<Reader> {
    private String rcode;
    private String name;
    private int byear;

    public Reader(String rcode, String name, int byear) {
        if (rcode == null || rcode.isEmpty()) {
            throw new IllegalArgumentException("Reader code cannot be null or empty.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (byear <= 0) {
            throw new IllegalArgumentException("Invalid birth year.");
        }
        this.rcode = rcode;
        this.name = name;
        this.byear = byear;
    }

    public String getRcode() {
        return rcode;
    }

    public String getName() {
        return name;
    }

    public int getByear() {
        return byear;
    }

    @Override
    public String toString() {
        return "Reader [rcode=" + rcode + ", name=" + name + ", byear=" + byear + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Reader other = (Reader) obj;
        return rcode.equals(other.rcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rcode);
    }

    @Override
    public int compareTo(Reader other) {
        return this.rcode.compareTo(other.rcode);
    }
}
