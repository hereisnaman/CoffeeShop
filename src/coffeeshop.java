import java.util.Scanner;

/**
 * Created by Naman on 02/04/17.
 */
public class coffeeshop {
    private int top;
    private coffee drink[];
    private double vat;
    private double servicetax;
    private double cess;

    public coffeeshop()
    {
        top=4;
        vat=12.5;
        servicetax=5.0;
        cess=3.0;
        drink=new coffee[10];
        drink[0]=new coffee("Cafe Americano",75,1);
        drink[1]=new coffee("Cafe Latte",78,2);
        drink[2]=new coffee("Cafe Mocha",85,3);
        drink[3]=new coffee("Cappuccino",110,4);
        drink[4]=new coffee("Espresso",90,5);
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

    public double getAmount(double amount)
    {
        return (getVat()+getServiceTax()+getCess()+100)*amount/100;
    }
    public boolean isEmpty()
    {
        if(top==-1)
            return true;
        else
            return false;
    }

    public void add(coffee item)
    {
        if(top>=drink.length-1)
        {
            coffee[] temp =new coffee[drink.length*2];
            for (int i=0;i<drink.length;i++)
            {
                temp[i]=drink[i];
            }
            drink=temp;
        }
        top++;
        drink[top]=item;
    }

    public void delete(int code) throws Exception
    {
        if(isEmpty())
            throw new Exception("Menu is Already Empty. No Drinks to Delete.");
        if(!isIcode(code))
            throw new Exception("No Coffee Found to Delete.");
        int i;
        for(i=0;i<=this.top;i++)
            if(this.drink[i].getIcode()==code)
                break;
        for (;i<this.top+1&&i<this.drink.length;i++)
            this.drink[i]=this.drink[i+1];
        this.top--;
    }

    public boolean isIcode(int code)
    {
        for (int i=0;i<=top;i++)
        {
            int temp=this.drink[i].getIcode();
            if(temp!=code)
                continue;
            else
            return true;
        }
        return false;
    }

    public coffee coffeewithIcode(int icode) throws Exception
    {
        if(!this.isIcode(icode))
            throw new Exception("No Coffee Found.");
        int i;
        for (i=0;i<=this.top;i++)
        {
            if(this.drink[i].getIcode()!=icode)
                continue;
            else
                break;
        }
        return this.drink[i];
    }

    public void displaymenu()
    {
        System.out.println();
        System.out.println("###################### THE COFFEE SHOP ######################");
        System.out.println("SNO                    NAME                    PRICE    ICODE");
        for (int i=0;i<=this.top;i++) {
            System.out.print((i + 1)+"   ");
            System.out.print(drink[i].getName());
            for(int j=0;j<(44-drink[i].getName().length()-((i>9)?1:0)/2);j++)
                System.out.print(" ");
            System.out.print(drink[i].getPrice());
            for(int j=0;j<10-(drink[i].getPrice()+" ").length();j++)
                System.out.print(" ");
            System.out.println(drink[i].getIcode());
        }
    }

    public void displaytaxes()
    {
        System.out.println();
        System.out.println("###################### THE COFFEE SHOP ######################");
        System.out.println("Vat           : "+this.getVat()+"%");
        System.out.println("Service Tax   : "+this.getServiceTax()+"%");
        System.out.println("Cess          : "+this.getCess()+"%");
    }
    public void bill(boolean isLucky)
    {
        Scanner scn=new Scanner(System.in);
        if(isLucky)
            System.out.println("Congratulations you are our lucky customer.\nYou can start placing your order now:");
        String ans;
        coffee drink;
        int quantity,icode;
        Double amount=0.0,discount=0.0;
        do {
            try {
                System.out.print("Enter Coffee Icode: ");
                icode=scn.nextInt();
                drink=this.coffeewithIcode(icode);
            }
            catch (Exception e)
            {
                System.out.println(e);
                return;
            }
            try {
                System.out.print("Enter "+this.coffeewithIcode(icode).getName()+" Quanity: ");
                quantity=scn.nextInt();
                if(quantity<=0)
                    throw new Exception("Invalid Quantity");
            }
            catch (Exception e)
            {
                System.out.println(e);
                return;
            }
            amount+=drink.getPrice()*quantity;
            System.out.println("Want to add more items to your order? (yes/no)");
            ans = scn.next();
        } while (ans.equals("yes"));
        System.out.println();
        System.out.println("######################       BILL      ######################");
        System.out.println("Base Price         : Rs"+amount);
        if(isLucky) {
            System.out.println("Lucky Draw         : -Rs" + amount / 2);
            discount=amount/2;
        }
        else if(amount>750) {
            System.out.println("Discount           : -Rs100");
            discount=100.0;
        }
        else if (amount>500) {
            System.out.println("Discount           : -Rs50");
            discount=50.0;
        }
        amount-=discount;
        System.out.println("Discounted Price   : Rs"+amount);
        System.out.println("Vat           "+this.getVat()+"%: Rs"+this.getVat()*amount/100);
        System.out.println("Service Tax    "+this.getServiceTax()+"%: Rs"+this.getServiceTax()*amount/100);
        System.out.println("Cess           "+this.getCess()+"%: Rs"+this.getCess()*amount/100);
        System.out.println("Total Price        : Rs"+this.getAmount(amount));
        System.out.println("######################    THANK YOU    ######################");
    }
}
