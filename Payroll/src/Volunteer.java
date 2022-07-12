public class Volunteer extends Employee {
    Volunteer(String id) {
        super(id);
    }

    @Override
    boolean isPayday(int dayOfMonth) { // throws UnpayableEmployeeException
        if (dayOfMonth < 1 || dayOfMonth > 30) {
            throw new IllegalArgumentException("dayOfMonth must be between 1 and 30");
        }
        return false;
    }

    @Override
    double calculatePay() throws UnpayableEmployeeException {
        throw new UnpayableEmployeeException("This employee is a volunteer");
    }

    @Override
    double calculateDeductions() { // throws UnpayableEmployeeException
        return 0;
    }
}
