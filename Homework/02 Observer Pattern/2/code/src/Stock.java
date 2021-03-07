import java.util.ArrayList;
import java.util.List;

public abstract class Stock {
	protected String id;
	protected double price;
	private List<Customer> customers = new ArrayList<Customer>();

	public Stock(String id, double price) {
		super();
		this.id = id;
		this.price = price;
	}

	public void registerObserver(Customer customer) {
		customers.add(customer);
	}

	public void deleteObserver(Customer customer) {
		customers.remove(customer);
	}

	public void notifyVIPObservers() {
		for (Customer customer : customers) {
			if (customer instanceof VIP) {
				customer.update(this.price);
			}
		}
	}

	public void notifyCommonObserver() {
		for (Customer customer : customers) {
			if (customer instanceof Member) {
				customer.update(this.price);
			}
		}
	}
}
