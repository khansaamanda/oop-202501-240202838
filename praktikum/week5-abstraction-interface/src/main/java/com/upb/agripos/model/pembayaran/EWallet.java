package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;

public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private String otp;

    public EWallet(String invoiceNo, double total, String akun, String otp) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otp = otp;
    }

    @Override
    public double biaya() {
        return getTotal() * 0.015; // fee 1.5%
    }

    @Override
    public boolean validasi() {
        return otp != null && otp.length() == 6;
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi();
    }

    @Override
    public double totalBayar() {
        return getTotal() + biaya();
    }

    @Override
    public String cetakStruk() {
        return String.format("E-WALLET | INVOICE %s | TOTAL+FEE: %.2f | AKUN: %s | STATUS: %s | OTP Valid: %s",
                getInvoiceNo(), totalBayar(), akun,
                prosesPembayaran() ? "BERHASIL" : "GAGAL",
                validasi());
    }
}
