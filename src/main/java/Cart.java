import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.util.ArrayList;

public class Cart {
    public ArrayList<Transaction> getTransactions() throws SQLException {
        System.out.println("List of Transactions");
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();         //creating an arraylist with the transaction

        //passing the value to the object
        transactions.add(new Transaction("Monthly Allowance", 15000, "Credit"));
        transactions.add(new Transaction("Room Rent", 9000, "Debit"));
        transactions.add(new Transaction("Electricity Rent", 500, "Debit"));
        transactions.add(new Transaction("Water Charges", 200, "Debit"));
        return transactions;
    }

    //use to merge the html template
    public void mergeTemplate() throws IOException, SQLException {
        String outputfile = "Transactions.html";                        //create a html file name transaction
        ArrayList<Transaction> transactions = getTransactions();          //create a array list
        Velocity.init();
        VelocityContext context = new VelocityContext();

        try {
            Template template = Velocity.getTemplate("src/main/resources/template.vm");     //get the html template
        } catch (Exception e) {
            System.out.println(e);
        }
        int total = 0;
        // ArrayList<String> str = new ArrayList<String>();
//        String str = "";

        //calculation for the amount remaining in the account
        for (Transaction d : transactions) {
            if (d.getType().equals("Credit")) {
                total += d.getAmount();
            } else {
                total -= d.getAmount();
            }
            //      str.add(d.getType());
            // System.out.print(d.getType());
        }
        //System.out.println("Bill Amount : "+str);
        //System.out.print(transaction);

        context.put("Transactions", transactions);
        context.put("TotalAmount", total);
        Writer writer = new FileWriter(new File(outputfile));
        Velocity.mergeTemplate("src/main/resources/template.vm", "UTF-8", context, writer);
        writer.flush();
        writer.close();
    }
}
