public class User {

    private String firstName;
    private String lastName;
    private String password;
    private boolean clubMember;
    private String nickName;
    private Rank rank;
    private double sumOfPurchase;
    private int [] lastPurchaseDate;
    private int numberOfPurchase;

    public User(String firstName, String lastName, String password, boolean clubMember, String nickName, Rank rank, double sumOfPurchase, int[] lastPurchaseDate, int numberOfPurchase) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.clubMember = clubMember;
        this.nickName = nickName;
        this.rank = rank;
        this.sumOfPurchase = sumOfPurchase;
        this.lastPurchaseDate = lastPurchaseDate;
        this.numberOfPurchase = numberOfPurchase;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isClubMember() {
        return clubMember;
    }

    public void setClubMember(boolean clubMember) {
        this.clubMember = clubMember;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public double getSumOfPurchase() {
        return sumOfPurchase;
    }

    public void setSumOfPurchase(double sumOfPurchase) {
        this.sumOfPurchase = sumOfPurchase;
    }

    public int[] getLastPurchaseDate() {
        return lastPurchaseDate;
    }

    public void setLastPurchaseDate(int[] lastPurchaseDate) {
        this.lastPurchaseDate = lastPurchaseDate;
    }

    public int getNumberOfPurchase() {
        return numberOfPurchase;
    }

    public void setNumberOfPurchase(int numberOfPurchase) {
        this.numberOfPurchase = numberOfPurchase;
    }

    public String toString (){
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append(this.firstName).append(" ").append(this.lastName);
        if (this.rank!=null){
            if (this.rank==Rank.REGULAR_WORKER){
                stringBuilder.append(" (").append("Regular worker").append(")");
            }else if (this.rank==Rank.ADMINISTER){
                stringBuilder.append(" (").append("Administer").append(")");
            }else{
                stringBuilder.append(" (").append("Board member").append(")");
            }
        } else if (this.clubMember){
            stringBuilder.append(" (VIP)!");
        }
        stringBuilder.append("\nHere are some interesting things about your account: ");
        if (this.lastPurchaseDate!=null){
            stringBuilder.append("\nLast purchase date: ").append(lastPurchaseDate[0]).append(".").append(lastPurchaseDate[1]).append(".").append(lastPurchaseDate[2]);
        }
        stringBuilder.append("\nNumber of purchase: ").append(this.numberOfPurchase).append("\nSum of purchase: ").append(sumOfPurchase);

        return  stringBuilder.toString();
    }

}
