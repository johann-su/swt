public abstract class Employee {
    private String id;

    Employee(String id) {
        if (id == null) {
            throw new NullPointerException("id cannot be null");
        }
        if (id.equals("")) {
            throw new IllegalArgumentException("id cannot be empty");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    abstract boolean isPayday(int dayOfMonth); // throws UnpayableEmployeeException

    abstract double calculatePay() throws UnpayableEmployeeException;

    abstract double calculateDeductions(); // throws UnpayableEmployeeException
}
