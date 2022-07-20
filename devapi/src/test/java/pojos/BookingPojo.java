package pojos;

public class BookingPojo {
      /*
    {
                                "firstname": "Selim",
                                "lastname": "Ak",
                                "totalprice": 15000,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2020-09-09",
                                    "checkout": "2020-09-21"
                                }
                            }
     */
    private int bookingid;
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesPojo bookingdates;
    private String booking;

    public BookingPojo(){}
    public BookingPojo(String firstname, String lastname, int totalprice,Boolean depositpaid, BookingDatesPojo bookingdates){
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
    }



    public String getFirstname() {return firstname;}
    public void setFirstname(String firstname) {this.firstname = firstname;}
    public String getLastname() {return lastname;}
    public void setLastname(String lastname) {this.lastname = lastname;}
    public int getTotalprice() {return totalprice;}
    public void setTotalprice(int totalprice) {this.totalprice = totalprice;}
    public boolean isDepositpaid() {return depositpaid;}
    public void setDepositpaid(boolean depositpaid) {this.depositpaid = depositpaid;}
    public BookingDatesPojo getBookingdates() {return bookingdates;}
    public void setBookingdates(BookingDatesPojo bookingdates) {this.bookingdates = bookingdates;}
    public int getBookingid() {return bookingid;}
    public void setBookingid(int bookingid) {this.bookingid = bookingid;}
    public String getBooking() {return booking;}
    public void setBooking(String booking) {this.booking = booking;}

    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                '}';
    }
}
