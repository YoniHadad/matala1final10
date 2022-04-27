import java.time.LocalDate;
import java.util.*;

public class Website {
    private List<Cart> cartList;
    private List<User> memberAccounts;
    private List<Item> itemList;


    public Website() {
        this.cartList = new ArrayList<>();
        this.memberAccounts = new ArrayList<>();
        this.itemList=new ArrayList<>();
    }

    public void registerToSite(int typeOfAccount){
        Scanner scanner=new Scanner(System.in);
        String firstName;
        String lastName;
        boolean numbersInclude=false;
        do {
            System.out.println("Pleas enter your first name ");
            firstName=scanner.nextLine();
            char[] chars=firstName.toCharArray();
            for (char c:chars){
                if (Character.isDigit(c)){
                    System.out.println("First name cannot contain numbers");
                    numbersInclude=true;
                    break;
                }else {
                    numbersInclude=false;
                }
            }
        }while (numbersInclude);

        do {
            System.out.println("Pleas enter your last name ");
            lastName=scanner.nextLine();
            char[] chars=lastName.toCharArray();
            for (char c:chars){
                if (Character.isDigit(c)){
                    System.out.println("Last name cannot contain numbers");
                    numbersInclude=true;
                    break;
                }else {
                    numbersInclude=false;
                }
            }
        }while (numbersInclude);

        String nickName;
        do {
            System.out.println("Please select a nickname to use the site (note if the nickname is already in use you will not be able to use it) ");
            nickName=scanner.nextLine();
        }while (this.nickNameExists(nickName));
        String password;
        boolean PassLength=false;
        do {
            System.out.println("Please enter a password of at least 6 characters");
            password=scanner.nextLine();
        }while (!(passwordStrong(password)));

        String memberInTheClub;
        boolean loopRotation=true;
        boolean clubMember=false;

             do {
                 try {
                     System.out.println("Are you a club member?");
                     System.out.println("Answer Yes or No");
                     memberInTheClub=scanner.nextLine();
                     if ((memberInTheClub.equals("Yes"))||(memberInTheClub.equals("No"))){
                         loopRotation=false;
                         if (memberInTheClub.equals("Yes")){
                             clubMember=true;
                         }
                     }else{
                         System.out.println("Pleas answer by Yes or No!");
                     }
                 }catch (InputMismatchException e){
                     scanner.next();
                 }

             }while (loopRotation);
             Rank rank=null;
             User user;

             try {
                 if (typeOfAccount==Constants.MENU_TWO){
                     System.out.println("""
                What is your rank?
                If you are regular worker->press 1
                If you are administer    ->press 2
                If you are board member  ->press 3
                \s""");
                     int rankin=scanner.nextInt();
                     if (rankin==Constants.MENU_ONE||rankin==Constants.MENU_TWO||rankin==Constants.MENU_THREE){
                         switch (rankin){
                             case Constants.MENU_ONE:
                                 rank= Rank.REGULAR_WORKER;
                                 break;
                             case Constants.MENU_TWO:
                                 rank=Rank.ADMINISTER;
                                 break;
                             case Constants.MENU_THREE:
                                 rank=Rank.BOARD_MEMBER;
                                 break;
                         }
                     }else {
                         System.out.println("Something went wrong with your rank selection Let's try to re-register!");
                         System.out.println();
                         registerToSite(typeOfAccount);
                     }

                     user=new Worker(firstName,lastName,password,clubMember,nickName,rank,Constants.FIRST_TIME_PURCHASE,null,Constants.FIRST_TIME_PURCHASE);
                     this.memberAccounts.add(user);
                     Cart purchase=new Cart(user,Constants.SUM_OF_PURCHASE,Constants.FIRST_TIME_PURCHASE,null);
                     this.cartList.add(purchase);
                     System.out.println("You have successfully registered " +user.getNickName()+", now we will return you to the main menu for further action on the site ");
                     System.out.println();

                 }else if (typeOfAccount==Constants.MENU_ONE){
                     user=new Client(firstName,lastName,password,clubMember,nickName,rank,Constants.FIRST_TIME_PURCHASE,null,Constants.FIRST_TIME_PURCHASE);
                     this.memberAccounts.add(user);
                     Cart purchase=new Cart(user,Constants.SUM_OF_PURCHASE,Constants.FIRST_TIME_PURCHASE,null);
                     this.cartList.add(purchase);
                     System.out.println("You have successfully registered " +user.getNickName()+", now we will return you to the main menu for further action on the site ");
                     System.out.println();

                 }else{
                     System.out.println("Pleas enter 1 for regular account or 2 for employ account! ");
                     System.out.println("Let's try to re-register: ");
                     registerToSite(typeOfAccount);
                 }

             }catch (InputMismatchException e){
                 scanner.next();
                 System.out.println("Let's try again");
             }




    }

    private boolean passwordStrong(String password){
        boolean passwordOk=false;
        int count=0;
        for (int i=0;i<password.length();i++){
            count++;
            if (count>=Constants.PASSWORD_LENGTH){
                passwordOk=true;
            }
        }return passwordOk;
    }
    private boolean nickNameExists(String nickName){
        boolean nickNameTaken=false;
        for (int i=0;i<memberAccounts.size();i++){
            User currentPerson =this.memberAccounts.get(i);
            if (currentPerson!=null){
                if (currentPerson.getNickName().equals(nickName)){
                    nickNameTaken=true;
                    System.out.println("nickname already in use! ");
                    break;
                }
            }
        }return nickNameTaken;
    }

    public void login(int userChoice){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Pleas enter your nick name: ");
        String userNickName=scanner.nextLine();
        System.out.println("Pleas enter your password: ");
        boolean foundAccount=false;
        String userPassword= scanner.nextLine();
        User currentUser=null;
            for (int i=0;i< memberAccounts.size();i++) {
                currentUser=this.memberAccounts.get(i);
                if (currentUser!=null){
                    if (currentUser.getNickName().equals(userNickName)&&currentUser.getPassword().equals(userPassword)) {
                        foundAccount = true;
                        break;
                    }
                }

            }
        if (!(foundAccount)){
            System.out.println("We could not find your account");
        }else {
            System.out.println(currentUser);
            advanceMemberMenu(userChoice,currentUser);
        }
    }


    public void advanceMemberMenu(int clientOrWorker,User currentUser){
        Scanner scanner=new Scanner(System.in);

        if (clientOrWorker==1&&currentUser.getRank()==null){
            System.out.println("\nList of products available on the site:");
            makePurchase(currentUser);


        }else if (clientOrWorker==2&&currentUser.getRank()!=null){
            int userChoiceMenu=Constants.WAITING_FOR_INPUT;
            do {
                try{
                    System.out.println("Dear employ welcome back");
                    System.out.println("""  
                For customers list    <------------------------------->press 1
                For member club customers list   <-------------------->press 2
                For customers with one or more purchases   <---------->press 3
                For the customer with the most purchases on the site<->press 4
                For adding a new item to the site <------------------->press 5
                For information about item stock  <------------------->press 6
                For making a purchase       <------------------------->press 7
                For go back to main menu     <-----------------------> press 8\s""");
                    userChoiceMenu=scanner.nextInt();
                    switch (userChoiceMenu){
                        case (Constants.MENU_ONE):
                            System.out.println("All our customers details: ");
                            printMember(userChoiceMenu);
                            System.out.println();
                            break;
                        case (Constants.MENU_TWO):
                            System.out.println("All our member club customers details: ");
                            printMember(userChoiceMenu);
                            System.out.println();
                            break;
                        case (Constants.MENU_THREE):
                            System.out.println("Customers who have made at least one purchase: ");
                            printMember(userChoiceMenu);
                            System.out.println("  ");
                            break;
                        case (Constants.MENU_FOUR):
                            System.out.println("The customer whose amount of purchases on the site is the highest: ");
                            printMember(userChoiceMenu);
                            System.out.println();
                            break;
                        case (Constants.MENU_FIVE):
                            addNewItem();
                            break;
                        case (Constants.MENU_SIX):
                            stockStatus();
                            break;
                        case (Constants.MENU_SEVEN):
                            makePurchase(currentUser);
                            break;
                    }
                }catch(InputMismatchException e){
                    scanner.next();
                    System.out.println("Please select only a number between 1 to 8 depending on your needs in the menu");
                }

            }while (userChoiceMenu!=Constants.MENU_EIGHT);

        }
        else{
            System.out.println("Oops there seems to be a match issue in your account: ");
            System.out.println("You are registered to the site but trying to access an account with permissions not granted to you");
            System.out.println(" let's try again\n");
        }
    }
    private void addNewItem(){
        Scanner scanner=new Scanner(System.in);
        boolean stockAvailable=true;
        System.out.println("Enter Item name: ");
        String itemName=scanner.nextLine();
        boolean itemExclusiveNumber=false;
        int itemSerialNumber;
        Item currentItem=null;
        do {
            System.out.println("Enter item serial number: ");
            itemSerialNumber=scanner.nextInt();

            for (int i=0;i<itemList.size();i++){
                currentItem=itemList.get(i);
                itemExclusiveNumber=false;
                if (currentItem.getItemNumber()==itemSerialNumber){
                    itemExclusiveNumber=true;
                    System.out.println("Sorry that serial number all ready in used! ");
                }
            }
        }while (itemExclusiveNumber);

        System.out.println("Enter price: ");
        int itemPrice=scanner.nextInt();
        System.out.println("How many items from the product do you want to insert");
        int itemStockInsert=scanner.nextInt();
        if (itemStockInsert<0){
            stockAvailable=false;
        }
        int discountPercent;
        boolean discountUnapproved=true;
        do {
            System.out.println("Enter discount for club member by percentage");
            discountPercent=scanner.nextInt();
            if (discountPercent<1 || discountPercent>=100){
                System.out.println("Something went wrong, please check that:\n" +
                        "We do not distribute products for free (no 100% discount).\n" +
                        "Club members are deserving to a certain discount on each product (discount of at least 1%).");
            }else {
                discountUnapproved=false;
            }
        }while (discountUnapproved);
        Item item=new Item(itemName,itemSerialNumber,itemPrice,stockAvailable,itemStockInsert,discountPercent);
        itemList.add(item);
    }
    public int workerDiscountPercent(User currentUser){
        int workerDiscount=Constants.DISCOUNT_PERCENT;
        if (currentUser.getRank()==Rank.REGULAR_WORKER){
            workerDiscount=Constants.REGULAR_DISCOUNT_PERCENT;
        }
        else if (currentUser.getRank()==Rank.ADMINISTER){
            workerDiscount=Constants.ADMINISTER_DISCOUNT_PERCENT;
        }
        else if (currentUser.getRank()==Rank.BOARD_MEMBER){
            workerDiscount=Constants.BOARD_MEMBER_DISCOUNT_PERCENT;
        }
        return workerDiscount;


    }

    private void printMember (int userChoice){
        User user=null;
        User mostExpensiveUser=null;
        if (userChoice==Constants.MENU_ONE){
            for (int i=0;i<memberAccounts.size();i++){
                user=memberAccounts.get(i);
                if (user!=null){
                    System.out.println(user);
                }
            }
        }else if (userChoice==Constants.MENU_TWO){
            for (int i=0;i<memberAccounts.size();i++) {
                user = memberAccounts.get(i);
                if (user != null) {
                    if (memberAccounts.get(i).isClubMember()) {
                        System.out.println(user);
                    }
                }
            }
        }else if (userChoice==Constants.MENU_THREE){
            for (int i=0;i<memberAccounts.size();i++) {
                user = memberAccounts.get(i);
                if (user != null) {
                    if (memberAccounts.get(i).getNumberOfPurchase()>Constants.FIRST_TIME_PURCHASE) {
                        System.out.println(user);
                    }
                }
            }

        }else if (userChoice==Constants.MENU_FOUR){
            for (int i=0;i<memberAccounts.size();i++) {
                user = memberAccounts.get(i);
                if (mostExpensiveUser==null){
                    mostExpensiveUser=user;
                }
                if (user != null) {
                    if (user.getNumberOfPurchase()>mostExpensiveUser.getNumberOfPurchase()){
                        mostExpensiveUser=user;
                    }
                }
            }
            System.out.println(mostExpensiveUser);
        }

    }
    private void makePurchase(User currentUser){
        Scanner scanner=new Scanner(System.in);
        int userStillBuy=Constants.FIRST_TIME_PURCHASE;

        do {
            try {
                int itemSerialChosen=Constants.FIRST_TIME_PURCHASE;
                double priceAfterDiscount;
                double currentFullPrice;
                double currentClubDiscount;
                int numberOfUnits=Constants.FIRST_TIME_PURCHASE;
                double totalPay=Constants.FIRST_TIME_PURCHASE;
                boolean wrongInput=true;
                Item currentItem=null;
                for (int i=0;i<itemList.size();i++){
                    currentItem=itemList.get(i);
                    System.out.println(currentItem);
                }
                do {
                    try {
                        System.out.println("Enter the serial number of the item you want to purchase");
                        System.out.println("If you have finished purchasing on the site, enter -1");
                        itemSerialChosen=scanner.nextInt();
                        if (itemSerialChosen==Constants.EXIT_MENU){
                            userStillBuy=itemSerialChosen;
                            for (int i=0;i< cartList.size();i++){
                                if (cartList.get(i)!=null){
                                    if (cartList.get(i).getUser().getNickName().equals(currentUser.getNickName())){
                                        System.out.println(cartList.get(i).getSumOfPurchase());
                                        break;
                                    }
                                }

                            }
                            break;
                        }
                        if (itemSerialChosen!=0){
                            wrongInput=false;
                        }
                    }catch (InputMismatchException e){
                        scanner.next();
                        System.out.println();
                    }
                }while (wrongInput);

                for (int i=0;i<itemList.size();i++){
                    currentItem=itemList.get(i);
                    if (currentItem.getItemNumber()==itemSerialChosen){
                        if (currentItem.isInStock()){
                            currentFullPrice=currentItem.getItemPrice();
                            currentClubDiscount=((double)currentItem.getDiscountPercentages());
                            if (currentUser.isClubMember()){
                                priceAfterDiscount=(currentFullPrice-(currentFullPrice*((workerDiscountPercent(currentUser)/Constants.PERCENT_CALC)+(currentClubDiscount/Constants.PERCENT_CALC))));

                            }else{
                                priceAfterDiscount=(currentFullPrice-(currentFullPrice*((workerDiscountPercent(currentUser)/Constants.PERCENT_CALC))));
                            }
                            System.out.println("Dear "+currentUser.getFirstName()+" Below is a final price after the discounts you are entitled to according to the site's rules");
                            System.out.println(priceAfterDiscount);
                            do {
                                System.out.println("How many units do you want to purchase?");
                                numberOfUnits=scanner.nextInt();
                                if (numberOfUnits<=Constants.OUT_OF_STOCK){
                                    System.out.println("Please select 1 or more units to purchase");
                                }
                            }while (numberOfUnits<Constants.OUT_OF_STOCK);
                            if (numberOfUnits<=currentItem.getSumInStock()&&numberOfUnits!=0){
                                currentItem.setSumInStock(currentItem.getSumInStock()-numberOfUnits);
                                totalPay=numberOfUnits*priceAfterDiscount;
                                System.out.println("Total purchase amount: "+totalPay+" Successfully added to your cart");
                            }
                            else if (currentItem.getSumInStock()==Constants.OUT_OF_STOCK){
                                System.out.println("Sorry out of stock");
                            }
                            else if(currentItem.getSumInStock()<numberOfUnits) {
                                System.out.println("Please pay attention to the stock before making a purchase!");
                            }
                        }
                    }
                }
                Cart purchase;
                LocalDate localDate=LocalDate.now();
                int day=localDate.getDayOfMonth();
                int month=localDate.getMonthValue();
                int year=localDate.getYear();
                int[]currentDate ={day,month,year};
                for (int i = 0; i< cartList.size(); i++){
                    purchase= cartList.get(i);
                    if (purchase!=null){
                        cartList.get(i).setSumOfPurchase(cartList.get(i).getSumOfPurchase()+totalPay);
                        cartList.get(i).setItem(currentItem);
                        cartList.get(i).setUser(currentUser);
                        cartList.get(i).setNumberOfPurchase(numberOfUnits);
                        if (userStillBuy==Constants.EXIT_MENU){
                            cartList.get(i).setSumOfPurchase(Constants.FIRST_TIME_PURCHASE);
                        }
                    }

                }
                currentUser.setLastPurchaseDate(currentDate);
                currentUser.setSumOfPurchase(currentUser.getSumOfPurchase()+totalPay);
                currentUser.setNumberOfPurchase(currentUser.getNumberOfPurchase()+numberOfUnits);


            }catch (InputMismatchException e){
                scanner.next();
                System.out.println("Pleas enter only numbers");
            }
        }while (userStillBuy!=Constants.EXIT_MENU);


    }
    private void stockStatus(){
        Scanner scanner=new Scanner(System.in);
        int newStock;
        Item currentItem;
        for (int i=0;i<itemList.size();i++){
            currentItem=itemList.get(i);
            if (currentItem!=null){
                System.out.println(currentItem);
            }
        }
        System.out.println("Please select a item according to its serial number in order to change its available quantity on the site. ");
        int serialNumber=scanner.nextInt();
        for (int i=0;i<itemList.size();i++){
            currentItem=itemList.get(i);
            if (currentItem.getItemNumber()==serialNumber){
                System.out.println("Enter the new stock value: ");
                newStock=scanner.nextInt();
                itemList.get(i).setSumInStock(newStock);
                if (itemList.get(i).getSumInStock()==0){
                    itemList.get(i).setInStock(false);
                }
                break;
            }
        }
    }

}
