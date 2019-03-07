package beans.models;

import java.util.Objects;

public class UserAccount {

    private long userId;
    private double amount;

    public UserAccount() {
    }

    public UserAccount(long userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userId=" + userId +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return userId == that.userId &&
                Double.compare(that.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, amount);
    }
}
