package chapter13;

import java.util.Formatter;

public class Receipt {
	private double total; 
	Formatter f = new Formatter(System.out) ;
	{
		System.out.println("\\n\n\\\\n\n\\n");
		String string= "%-"+13+".15s %5s %10s\n";
		System.out.println(string);
	}
	public void printTitle() {
	f.format("%-15.15s %5s %10s\n","Item", "Qty", "price" );
	f.format("%-15.15s %5s %10s\n","----", "----", "----" );
	
	}
	public void printItem(String itemName, int itemNum, double itemPrice) {
		f.format("%-15.15s %5d %10.2f\n", itemName, itemNum, itemPrice);
		total+=itemNum*itemPrice;
	}
	public void printTotal() {
		f.format("%-15.15s %5s %10.2f\n","Tax", "", 0.01*total);
		total+=0.01*total;
		f.format("%-15.15s %5s %10s\n","", "", "----" );
		f.format("%-15.15s %5s %10.2f\n","Total", "", total);
		
	}
	
	public static void main(String[] args) {
		Receipt receipt = new Receipt();
		receipt.printTitle();
		receipt.printItem("sanwich", 2, 10.2);
		receipt.printTotal();
	}

}
