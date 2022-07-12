public class Appointee extends Employee {
    private int payday;
    private int hoursPerMonth;
    private double payPerHour;

    Appointee(String id, int payday, int hoursPerMonth, double payPerHour) {
        super(id);
        if (id == null) {
            throw new NullPointerException("id cannot be null");
        }
        if (id.equals("")) {
            throw new IllegalArgumentException("id cannot be empty");
        }
        if (payday < 1 || payday > 30) {
            throw new IllegalArgumentException("value must be between 1 and 30");
        }
        if (hoursPerMonth < 1) {
            throw new IllegalArgumentException("Hours must be positive");
        }
        if (payPerHour <= 0) {
            throw new IllegalArgumentException("PayPerHour must be positive");
        }
        this.payday = payday;
        this.hoursPerMonth = hoursPerMonth;
        this.payPerHour = payPerHour;
    }

    @Override
    boolean isPayday(int dayOfMonth) {
        if (dayOfMonth < 1 || dayOfMonth > 30) {
            throw new IllegalArgumentException("dayOfMonth must be between 1 and 30");
        }
        return dayOfMonth == this.payday;
    }

    @Override
    double calculatePay() {
        return this.hoursPerMonth * this.payPerHour;
    }

    @Override
    double calculateDeductions() {
        return calculatePay()*0.4;
    }
}
