package ShadowChain;

import util.StringUtil;

import java.security.*;
import java.util.ArrayList;

public class Transaction {
    public String transactionID;
    public PublicKey sender;
    public PublicKey recipient;
    public float value;
    public byte[] signature;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    public static int sequence = 0;

    public Transaction (PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.recipient = to;
        this.value = value;
        this.inputs = inputs;
    }

    private String calculateHash() {
        sequence ++;
        return StringUtil.applySha256(
                StringUtil.getStringFromKey(sender) +
                      StringUtil.getStringFromKey(recipient) +
                      Float.toString(value) + sequence
        );
    }

    public void generateSignature (PrivateKey privateKey) {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value);
        signature = StringUtil.applyECDSASig(privateKey, data);
    }

    public boolean verifySignature() {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value);
        return StringUtil.verifyECDSASig(sender, data, signature);
    }

    public boolean processTransaction() {
        if (!verifySignature()) {
            System.out.println("#Transaction_Signature failed to verify");
            return false;
        }

        for (TransactionInput i : inputs) {
            i.UTXO = Main.UTXOs.get(i.transactionOutputId);
        }

        if (getInputsValue() < Main.minimumTransaction) {
            System.out.println("#Transaction_inputs too small: " + getInputsValue());
            return false;
        }

        float leftOver = getInputsValue() - value;
        transactionID = calculateHash();
        outputs.add(new TransactionOutput(this.recipient, value, transactionID));
        outputs.add(new TransactionOutput(this.sender, leftOver, transactionID));

        for (TransactionOutput o : outputs) {
            Main.UTXOs.put(o.id, o);
        }

        for (TransactionInput i : inputs) {
            if (i.UTXO == null) continue;
            Main.UTXOs.remove(i.UTXO.id);
        }

        return true;
    }

    public float getInputsValue() {
        float total = 0;
        for (TransactionInput i : inputs) {
            if (i.UTXO == null) continue;
            total += i.UTXO.value;
        }
        return total;
    }

    public float getOutputsValue() {
        float total = 0;
        for (TransactionOutput o : outputs) {
            total += o.value;
        }
        return total;
    }
}
