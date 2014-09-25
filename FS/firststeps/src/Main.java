import org.slf4j.*;
import my.LinkedList.*;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkedList.class);

    public static void main(String[] args) {
        LinkedList<String> ls = new LinkedList<String>();
        ls.printYourself();
        ls.insertAtBegin("ABC");
        ls.printYourself();
        ls.insertAtEnd("XYZ");
        ls.printYourself();
        ls.insertAtBegin("111");
        ls.printYourself();
        ls.insertAtEnd("***");
        ls.printYourself();
        ls.insertAfter("DDD", "ABC");
        ls.printYourself();
        ls.insertBefore("KKK", "XYZ");
        ls.printYourself();
        ls.insertBefore(null, "KKK");
        ls.printYourself();
        ls.insertBefore(null, null);
        ls.printYourself();
        ls.delete("DDD");
        ls.printYourself();
        ls.delete("111");
        ls.printYourself();
        ls.delete("***");
        ls.printYourself();
        ls.delete(null);

        ls.printYourself();

        try {
            ls.delete("GGG");
        } catch (Exception ex) {
            LOGGER.info("error: " + ex.getMessage());
        }

        for (String s : ls) {
            LOGGER.info(s);
        }
    }
}
