import java.util.HashMap;
import java.util.Map;

public class PayrollDispositionImpl implements PayrollDisposition {
    private Map<Employee, Double> payments = new HashMap<Employee, Double>();

    PayrollDispositionImpl() {}

    public double getTotal() {
        double out = 0;
        for (double amount : payments.values()) {
            out = out+amount;
        }
        return out;
    }

    public double getAverage() {
        if (payments.size() == 0) {
            return 0;
        } else {
            return this.getTotal() / payments.size();
        }
    }

    public Map<Employee, Double> getPayments() {
        return payments;
    }

    @Override
    public void sendPayment(Employee employee, double payment) {
        if (employee == null) {
            throw new NullPointerException("Employee cannot be null");
        }
        if (payment < 1) {
            throw new IllegalArgumentException("Payment cannot be negative");
        }
        payments.put(employee, payment);
    }
}
