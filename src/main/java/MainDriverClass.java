
/*
* @Author: Harald EDERER
* @Date: 16.10.2022
 */
public class MainDriverClass {
    public static void main(String[] args) {
        try {
            UserInterface ui = new UserInterface();
        } catch (Exception e) {
            System.err.println("Error occured!");
            e.printStackTrace();
        }
    }
}
