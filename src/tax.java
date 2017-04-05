/**
 * Created by Naman on 05/04/17.
 */
public class tax {
    private double vat;
    private double servicetax;
    private double cess;

    public tax() throws Exception
    {
        this(12.5,5.0,3.0);
    }

    public tax(double vat, double servicetax, double cess) throws Exception
    {
        this.setVat(vat);
        this.setServiceTax(servicetax);
        this.setCess(cess);
    }
    public void setVat(double vat) throws Exception
    {
        if(vat>=0)
            this.vat=vat;
        else
            throw new Exception("Invalid vat");
    }

    public double getVat()
    {
        return this.vat;
    }

    public void setServiceTax(double servicetax) throws Exception
    {
        if(servicetax>=0)
            this.servicetax=servicetax;
        else
            throw new Exception("Invalid servicetax");
    }

    public double getServiceTax()
    {
        return this.servicetax;
    }

    public void setCess(double cess) throws Exception
    {
        if(cess>=0)
            this.cess=cess;
        else
            throw new Exception("Invalid cess");
    }

    public double getCess()
    {
        return this.cess;
    }
}
