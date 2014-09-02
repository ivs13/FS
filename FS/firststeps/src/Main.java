import my.LinkedList.*;

public class Main {

    public static void main(String[] args) throws NodeNotFoundException
    {
        LinkedList<String> ls = new LinkedList<String>();
        ls.PrintYourself();
        ls.InsertAtBegin("ABC"); ls.PrintYourself();
        ls.InsertAtEnd("XYZ"); ls.PrintYourself();
        ls.InsertAtBegin("111"); ls.PrintYourself();
        ls.InsertAtEnd("***"); ls.PrintYourself();
        ls.InsertAfter("DDD", "ABC"); ls.PrintYourself();
        ls.InsertBefore("KKK", "XYZ"); ls.PrintYourself();
        ls.InsertBefore(null, "KKK"); ls.PrintYourself();
        ls.InsertBefore(null, null); ls.PrintYourself();
        ls.Delete("DDD"); ls.PrintYourself();
        ls.Delete("111"); ls.PrintYourself();
        ls.Delete("***"); ls.PrintYourself();
        ls.Delete(null); ls.PrintYourself();
        try {
            ls.Delete("GGG");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
