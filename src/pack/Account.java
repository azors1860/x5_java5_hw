package pack;

import exceptions.NotEnoughMoneyException;

public class Account {
    private int id;
    private String holder;
    private int amount;

    public Account() {
    }

    public Account(int id, String holder, int amount) {
        this.id = id;
        this.holder = holder;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) throws NotEnoughMoneyException {
        if (amount < 0) {
            throw new NotEnoughMoneyException("Отрицательный баланс");
        }
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", holder='" + holder + '\'' +
                ", amount=" + amount +
                '}';
    }
}
