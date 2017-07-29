package nic.gst;

/**
 * Created by Ha on 24/07/2017.
 */

public class GstDatatype {
    protected float amt;
    protected float t1;
    protected float t2;
    protected float total;

    public GstDatatype() {
        amt = t1 =t2 = total= 0;
    }
    public GstDatatype( float amt, float t1,float t2,float total ) {
        this.amt = amt;
        this.t1 = t1;
        this.t2 =t2;
        this.total=total;
    }

    public GstDatatype( float amt, float t1,float total ) {
        this.amt = amt;
        this.t1 = t1;
        this.total=total;
    }



    public GstDatatype add( GstDatatype g1,boolean intrastate ) {
        if(intrastate)
        {
            GstDatatype v1 = new GstDatatype(this.amt + g1.amt, this.t1 + g1.t1,this.total+g1.total);
            return v1;
        }
        else {
            GstDatatype v2 = new GstDatatype(this.amt + g1.amt, this.t1 + g1.t1, this.t2 + g1.t2,this.total+g1.total);
            return v2;
        }
    }

    public GstDatatype sub( GstDatatype g1,boolean intrastate )
    {
        if(intrastate)
        {GstDatatype v1 = new GstDatatype( this.amt - g1.amt, this.t1 - g1.t1,this.total-g1.total );
            return v1;}
        else
        {
        GstDatatype v2 = new GstDatatype( this.amt - g1.amt, this.t1 - g1.t1,this.t2-g1.t2,this.total-g1.total );
        return v2;
    }
    }


    public void clear() {
        this.amt = this.t1 =this.t2 = this.total= 0;


    }
}

