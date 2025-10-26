// QUESTION: 2043. Simple Bank System
// LINK: https://leetcode.com/problems/simple-bank-system/description/?envType=daily-question&envId=2025-10-26

// SOLUTION: Direct Implementation

class Bank {

    private long[] balance;  // Store Balance
    private int n;      // Number of Accounts

    public Bank(long[] balance) {
        this.balance = balance;
        this.n = balance.length;
    }
    
    public boolean transfer(int account1, int account2, long money) {
       // if(account1 > n || account1 < 1 || account2 > n || account2 < 1)
        if(account1 < 1 || account1 > n || account2 < 1 || account2 > n)
        {
            return false;
        }

        if(balance[account1 - 1] >= money)
        {
            balance[account1 - 1] -= money;
            balance[account2 - 1] += money;
            return true;
        }
        return false;
    }
    
    public boolean deposit(int account, long money) {
        if(account > n || account < 1)
        {
            return false;
        }
        balance[account - 1] += money;
        return true;
    }
    
    public boolean withdraw(int account, long money) {
        if(account > n || account < 1)
        {
            return false;
        }
        if(balance[account - 1] >= money)
        {
            balance[account - 1] -= money;
            return true;
        }
        return false;
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */