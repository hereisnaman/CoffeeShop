import java.util.Scanner;
/**
 * Created by Naman on 02/04/17.
 */
public class main {
    private static Scanner scn=new Scanner(System.in);
    private static coffeeshop shop=new coffeeshop();
    public static void main(String[] agrs)
    {
        home();
    }

    public static void home()
    {
        System.out.println();
        System.out.println("###################### THE COFFEE SHOP ######################");
        System.out.println("1) Customer\n2) Shopkeeper\n3) Exit");
       option: switch (scn.nextInt())
        {
            case 1:
                customer();
                home();
                break;
            case 2:
                shopkeeper();
                home();
                break;
            case 3:
                break;
            default:
                break option;
        }
    }

    public static void customer()
    {
        displaymenu();
        System.out.println("We have the following offers for you:");
        System.out.println("1) Rs50 off on Rs500+ purchase.\n2) Rs100 off on Rs750+ purchase.\n3) Lucky Draw: 50% off.");
        System.out.println("Want to buy something? (yes/no)");
        if(scn.next().equals("yes"))
        {
            System.out.println("");
            System.out.print("Enter your name: ");
            shop.bill(isLucky(scn.next()));
        }
    }



    public static boolean isLucky(String name)
    {
        int count=0;
        for (int i = 0; i < name.length(); i++)
            if (name.charAt(i) == 'a'||name.charAt(i) == 'A')
                count++;
        return count>=3;
    }

    public static void shopkeeper()
    {
        System.out.println();
        System.out.println("###################### THE COFFEE SHOP ######################");
        System.out.println("1) Add Coffee\n2) Delete Coffee\n3) Display Menu\n4) Display Taxes\n5) Update Item\n6) Update Taxes\n7) Back");
        option: switch (scn.nextInt())
        {
            case 1:
                addcoffee();
                shopkeeper();
                break;
            case 2:
                deletecoffee();
                shopkeeper();
                break;
            case 3:
                displaymenu();
                shopkeeper();
                break;
            case 4:
                displaytaxes();
                shopkeeper();
                break;
            case 5:
                updateitem();
                shopkeeper();
                break;
            case 6:
                updatetaxes();
                shopkeeper();
                break;
            case 7:
                break;
            default:
                break option;
        }
    }

    public static void addcoffee()
    {
        coffee newcoffee=new coffee();
        System.out.println();
        System.out.println("###################### THE COFFEE SHOP ######################");
        try {
            System.out.print("Enter Coffee Name: ");
            newcoffee.setName(scn.next());
        }
        catch (Exception e)
        {
            System.out.println(e);
            return;
        }
        try {
            System.out.print("Enter Coffee Price: ");
            newcoffee.setPrice(scn.nextInt());
        }
        catch (Exception e)
        {
            System.out.println(e);
            return;
        }
        try {
            System.out.print("Enter Coffee Icode: ");
            newcoffee.setIcode(scn.nextInt(),shop);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return;
        }
        shop.add(newcoffee);
        System.out.print("Coffee Added to Menu.");
    }

    public static void deletecoffee()
    {
        System.out.println();
        System.out.println("###################### THE COFFEE SHOP ######################");
        try{
            System.out.print("Enter Coffee Icode: ");
            shop.delete(scn.nextInt());
        }
        catch (Exception e)
        {
            System.out.print(e);
            return;
        }
        System.out.println("Coffee Deleted.");
    }

    public static void displaymenu()
    {
        shop.displaymenu();
    }

    public static void displaytaxes()
    {
        shop.displaytaxes();
    }
    public static void updateitem()
    {
        System.out.println();
        System.out.println("###################### THE COFFEE SHOP ######################");
        coffee temp;
        try{
            System.out.print("Enter Coffee Icode: ");
            temp=shop.coffeewithIcode(scn.nextInt());
        }
        catch (Exception e)
        {
            System.out.println(e);
            return;
        }
        System.out.println("1) Update Name\n2) Update Price\n3) Update Icode\n4) Back");
        switch (scn.nextInt())
        {
            case 1:
                try {
                    System.out.print("Enter Coffee Name: ");
                    temp.setName(scn.next());
                }
                catch (Exception e)
                {
                    System.out.println(e);
                    return;
                }
                System.out.println("Coffee Updated");
                break;
            case 2:
                try {
                    System.out.print("Enter Coffee Price: ");
                    temp.setPrice(scn.nextInt());
                }
                catch (Exception e)
                {
                    System.out.println(e);
                    return;
                }
                System.out.println("Coffee Updated");
                break;
            case 3:
                try {
                    System.out.print("Enter Coffee Icode: ");
                    temp.setIcode(scn.nextInt(),shop);
                }
                catch (Exception e)
                {
                    System.out.println(e);
                    return;
                }
                System.out.println("Coffee Updated");
                break;
            case 4:
                return;
            default:
                return;
        }
    }

    public static void updatetaxes()
    {
        System.out.println();
        System.out.println("###################### THE COFFEE SHOP ######################");
        System.out.println("1) Update Vat\n2) Update Service Tax\n3) Update Cess\n4) Back");
        switch (scn.nextInt())
        {
            case 1:
                try {
                    System.out.print("Enter Vat: ");
                    shop.setVat(scn.nextDouble());
                }
                catch (Exception e)
                {
                    System.out.println(e);
                    return;
                }
                System.out.println("Vat Updated");
                break;
            case 2:
                try {
                    System.out.print("Enter Service Tax: ");
                    shop.setServiceTax(scn.nextDouble());
                }
                catch (Exception e)
                {
                    System.out.println(e);
                    return;
                }
                System.out.println("Service Tax Updated");
                break;
            case 3:
                try {
                    System.out.print("Enter Cess: ");
                    shop.setCess(scn.nextDouble());
                }
                catch (Exception e)
                {
                    System.out.println(e);
                    return;
                }
                System.out.println("Cess Updated");
                break;
            case 4:
                return;
            default:
                return;
        }
    }
}
