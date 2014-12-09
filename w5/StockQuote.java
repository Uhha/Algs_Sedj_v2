import java.util.Scanner;

public class StockQuote {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter stock code");
		while (sc.hasNext()) {

			String stock = sc.nextLine();
			String name = "http://finance.yahoo.com/q?s=";
			In in = new In(name + stock);
			String text = in.readAll();
			int start = text.indexOf("Prev Close", 0);
			int from = text.indexOf("<td", start);
			int to = text.indexOf("</td>", from);
			String price = text.substring(from + 28, to);
			System.out.println(price);
			System.out.println();
			System.out.println("Enter stock code");
		}
		sc.close();

	}

}
// <td class="yfnc_tabledata1">525.26</td>