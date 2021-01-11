public class Transaction {
    private String name;
    private float amount;
    private String type;

    //parameterized constructor
    public Transaction(String transaction_name, float amount, String type) {
        this.name = transaction_name;
        this.amount = amount;
        this.type = type;
    }

    public String getType()             //return the type of transaction
    {
        return type;
    }

    public float getAmount() {          //return the amount of transaction
        return amount;
    }

    public String getName()             //name/title of the transaction
    {
        return name;
    }

}
