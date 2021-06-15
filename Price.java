 

import java.io.Serializable;
import java.util.Date;

public class Price implements Serializable {
    private Date date;
    private double open;
    private double close;
    private double low;
    private double high;
    private double volume;

    public Price(Date date,  double open, double close, double low, double high, double volume){
        this.date = date;
        this.open = open;
        this.close = close;
        this.low = low;
        this.high = high;
        this.volume = volume;
    }

    public double getClose() {
        return close;
    }

    public double getLow() {
        return low;
    }

    public Date getDate() {
        return date;
    }
}
