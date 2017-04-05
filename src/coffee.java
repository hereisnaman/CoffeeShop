/**
 * Created by Naman on 02/04/17.
 */
public class coffee {
    private String name;
    private int price;
    private int icode;

    public coffee()
    {

    }

    public coffee(String name, int price, int icode)
    {
        this.name=name;
        this.price=price;
        this.icode=icode;
    }

    public void setName(String name) throws Exception
    {
        if(name.length()>0&&name.length()<=40)
            this.name = name;
        else
            throw new Exception("Invalid Name");
    }

    public String getName()
    {
        return this.name;
    }

    public void setPrice(int price) throws Exception
    {
        if(price>0)
            this.price = price;
        else
            throw new Exception("Invalid Price. Can't be Zero.");
    }

    public int getPrice()
    {
        return this.price;
    }

    public void setIcode(int code,coffeeshop shop) throws Exception
    {
        if(shop.isIcode(code))
            throw new Exception("Invalid Icode. Already Taken.");
        this.icode=code;
    }

    public int getIcode()
    {
        return this.icode;
    }
}
