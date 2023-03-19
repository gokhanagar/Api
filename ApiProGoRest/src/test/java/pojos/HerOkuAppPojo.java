package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class HerOkuAppPojo {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private Map<String,Object> bookingDates;
    private String additionalneeds;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {this.firstname = firstname;}
    public String getLastname() {return lastname;}
    public void setLastname(String lastname) {this.lastname = lastname;}
    public int getTotalprice() {return totalprice;}
    public void setTotalprice(int totalprice) {this.totalprice = totalprice;}
    public boolean getDepositpaid() {return depositpaid;}
    public void setDepositpaid(boolean depositpaid) {this.depositpaid = depositpaid;}
    public Map<String, Object> getBookingDates() {return bookingDates;}
    public void setBookingDates(Map<String, Object> bookingData) {this.bookingDates = bookingData;}
    public String getAdditionalneeds() {return additionalneeds;}
    public void setAdditionalneeds(String additionalneeds) {this.additionalneeds = additionalneeds;}

    @Override
    public String toString() {
        return "HerOkuAppPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid='" + depositpaid + '\'' +
                ", bookingData=" + bookingDates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }


}
