
import java.time.LocalDate;

class Lending {
    String bcode;  // Book code
    String rcode;  // Reader code
    int state;     // 1: Book is lent, 2: Returned
    LocalDate ldate;  // Lending date
    LocalDate rdate;  // Return date

    public Lending(String bcode, String rcode, int state, LocalDate ldate, LocalDate rdate) {
        this.bcode = bcode;
        this.rcode = rcode;
        this.state = state;
        this.ldate = ldate;
        this.rdate = rdate;
    }

    @Override
    public String toString() {
        return bcode + ", " + rcode + ", " + state + ", " + ldate + ", " + (rdate != null ? rdate : "null");
    }
}
