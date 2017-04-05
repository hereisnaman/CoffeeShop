import java.util.Scanner;

/**
 * Created by Naman on 02/04/17.
 */
public class coffeeshop {
    private int top;
    private coffee drink[];
    private tax tax;

    public coffeeshop()
    {
        try {
            tax = new tax();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        top=4;
        drink=new coffee[10];
        drink[0]=new coffee("Cafe Americano",75,1);
        drink[1]=new coffee("Cafe Latte",78,2);
        drink[2]=new coffee("Cafe Mocha",85,3);
        drink[3]=new coffee("Cappuccino",110,4);
        drink[4]=new coffee("Espresso",90,5);
    }

    public tax getTax()
    {
        return this.tax;
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
        System.out.println("Vat           : "+this.tax.getVat()+"%");
        System.out.println("Service Tax   : "+this.tax.getServiceTax()+"%");
        System.out.println("Cess          : "+this.tax.getCess()+"%");
    }
    public double getAmount(double amount)
    {
        return (this.getTax().getVat()+this.getTax().getServiceTax()+this.getTax().getCess()+100)*amount/100;
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
        System.out.println("Vat           "+this.getTax().getVat()+"%: Rs"+this.getTax().getVat()*amount/100);
        System.out.println("Service Tax    "+this.getTax().getServiceTax()+"%: Rs"+this.getTax().getServiceTax()*amount/100);
        System.out.println("Cess           "+this.getTax().getCess()+"%: Rs"+this.getTax().getCess()*amount/100);
        System.out.println("Total Price        : Rs"+this.getAmount(amount));
        System.out.println("######################    THANK YOU    ######################");
    }
}
