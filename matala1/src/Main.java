import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println();
        int userChoice=0;
        boolean menuCrash=true;
        Scanner scanner= new Scanner(System.in);
        Website website=new Website();
        System.out.println("Welcome to our shop! ");
        do {
            while (menuCrash){
                try {
                System.out.println("""  
                For register->press 1
                to log in   ->press 2
                for exit    ->press 3
                \s""");
                    userChoice=scanner.nextInt();
                    if (userChoice>=Constants.MENU_ONE && userChoice<=Constants.MENU_THREE){
                        menuCrash=false;
                    }else {
                        System.out.println("Please select a number between 1 to 3 depending on your needs in the menu");
                    }
                }catch (InputMismatchException e){
                    scanner.next();
                    System.out.println("Please select only a number between 1 to 3 depending on your needs in the menu");
                }
            }
            menuCrash=true;
            int userTypeSelect=0;
            switch (userChoice){
                case Constants.MENU_ONE:
                    do {
                        try {
                            System.out.println("For regular account press 1 ");
                            System.out.println("For employee account press 2 ");
                            userTypeSelect=scanner.nextInt();
                            if (userTypeSelect==1||userTypeSelect==2){
                                website.registerToSite(userTypeSelect);
                            }else {
                                System.out.println("Pleas enter 1 or 2");
                            }
                        }catch (InputMismatchException e){
                            scanner.next();
                            System.out.println("Enter only number");
                        }

                    }while ((userTypeSelect<1)||(userTypeSelect>2));
                    break;

                case Constants.MENU_TWO:
                    do {
                        try {
                            System.out.println("Welcome back! ");
                            System.out.println("For regular account press 1 ");
                            System.out.println("For employee account press 2 ");
                            userTypeSelect=scanner.nextInt();
                            if (userTypeSelect==1||userTypeSelect==2){
                                website.login(userTypeSelect);
                            }else {
                                System.out.println("Pleas enter 1 or 2");
                            }
                        }catch (InputMismatchException e){
                            scanner.next();
                            System.out.println("Enter only number");
                        }

                    }while ((userTypeSelect<1)||(userTypeSelect>2));
                    break;
                case Constants.MENU_THREE:
                    System.out.println("Goodbye hope to see you again!");
                    break;
            }

        }while (userChoice!=Constants.MENU_THREE);

    }

}
