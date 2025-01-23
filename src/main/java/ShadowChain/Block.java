package ShadowChain;
import util.StringUtil;

import java.util.ArrayList;
import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    public String merkleRoot;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private final long timeStamp;
    private int nonce;

    public Block (String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtil.applySha256(
                                    previousHash +
                                    Long.toString(timeStamp) +
                                    Integer.toString(nonce) +
                                    merkleRoot
                                );
    }

    public void mineBlock (int difficulty) {
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        String target = StringUtil.getDificultyString(difficulty);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public boolean addTransaction(Transaction transaction) {
        if (transaction == null) return false;
        if ((!previousHash.equals("0"))) {
            if (!transaction.processTransaction()) {
                System.out.println("Transaction failed to process. Declined");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction successfully added to the Block");
        return true;
    }
}
