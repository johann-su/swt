public class Payroll {
    private int payday;
    private PayrollDisposition disposition;

    Payroll(PayrollDisposition disposition, int payday) {
        if (disposition == null) {
            throw new NullPointerException("disposition cannot be null");
        }
        if (payday < 1 || payday > 30) {
            throw new IllegalArgumentException("payday must be between 1 and 30");
        }
        this.payday = payday;
        this.disposition = disposition;
    }

    public void doPayroll(PayrollDB db) {
        for (Employee e : db.getEmployeeList()) {
            try {
                if (e.isPayday(payday)) {
                    disposition.sendPayment(e, e.calculatePay()-e.calculateDeductions());
                }
            } catch(UnpayableEmployeeException error) {
                System.out.println("Employee is a volunteer");
            }
        }
    }
}
