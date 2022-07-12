public class BankAccount {
    private double balance = 0.0;
    private double lineOfCredit;
    private String accountNumber;
    private AccountState state;

    BankAccount(String accountNumber, double lineOfCredit) {
        if (accountNumber == null) {
            throw new NullPointerException();
        }
        if (accountNumber.equals("") || lineOfCredit < 0) {
            throw new IllegalArgumentException();
        }
        this.accountNumber = accountNumber;
        this.lineOfCredit = lineOfCredit;
        this.state = this.new Positive();
    }
    
    boolean payIn(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        boolean res = this.state.payIn(amount);
        if (res) {
            if (balance<0) {
                state = this.new Negative();
            }
            else if (balance >= 0) {
                state = this.new Positive();
            } 
            if (balance < 0 && Math.abs(balance) >= lineOfCredit) {
                state = this.new Frozen();
            }
        }
        return res;
    }

    boolean payOff(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        boolean res = this.state.payOff(amount);
        if (res) {
            if (balance < 0) {
                state = this.new Negative();
            } else if (balance >= 0) {
                state = this.new Positive();
            }  
            if (balance < 0 && Math.abs(balance) >= lineOfCredit) {
                state = this.new Frozen();
            }
        }
        return res;
    }

    boolean close() {
        if (balance == 0.0 && !(state instanceof Closed)) {
            this.state = this.new Closed();
            return true;
        } else {
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getState() {
        return state.toString();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    void printBalance() {
        state.printBalance();
    }

    void payInterest() {
        state.payInterest();
        if (balance < 0) {
            state = this.new Negative();
        } else if (balance >= 0) {
            state = this.new Positive();
        }
        if (balance < 0 && Math.abs(balance) >= lineOfCredit) {
            state = this.new Frozen();
        }
    }

    public abstract class AccountState {
        boolean payIn(double amount) {
            // default behaviour is false
            return false;
        }

        boolean payOff(double amount) {
            // default behaviour is false
            return false;
        }

        public String toString() {
            return "";
        }

        void payInterest() {
            throw new IllegalStateException();
        }

        abstract void printBalance();
    }

    class Positive extends AccountState {
        @Override
        boolean payIn(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException();
            }
            balance+=amount;
            return true;
        }

        @Override
        boolean payOff(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException();
            }
            if (balance-amount<0 && Math.abs(balance-amount)>lineOfCredit) {
                return false;
            }
            balance-=amount;
            return true;
        }

        @Override
        public String toString() {
            return "Positive";
        }

        @Override
        void payInterest() {
            balance += (balance / 100);
        }

        @Override
        void printBalance() {
            System.out.println("Balance is POSITIVE: +" + balance + ".");
        }
    }

    class Negative extends AccountState {
        @Override
        boolean payIn(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException();
            }
            balance+=amount;
            return true;
        }

        @Override
        boolean payOff(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException();
            }
            if (balance - amount < 0 && Math.abs(balance-amount) > lineOfCredit) {
                return false;
            }
            balance-=amount;
            return true;
        }

        @Override
        public String toString() {
            return "Negative";
        }

        @Override
        void payInterest() {
            balance += (balance / 100) * 3;
        }

        @Override
        void printBalance() {
            System.out.println("Balance is NEGATIVE: " + balance + ".");
        }
    }

    class Frozen extends AccountState {
        @Override
        boolean payIn(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException();
            }
            balance+=amount;
            return true;
        }

        @Override
        public String toString() {
            return "Frozen";
        }

        @Override
        void payInterest() {
            balance += (balance / 100) * 5;
        }

        @Override
        void printBalance() {
            System.out.println("Balance is NEGATIVE: " + balance + ". You need to pay in money.");
        }
    }

    class Closed extends AccountState {
        @Override
        public String toString() {
            return "Closed";
        }

        @Override
        void printBalance() {
            System.out.println("This account is CLOSED. The balance is 0.");
        }
    }
}