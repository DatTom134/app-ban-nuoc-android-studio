package com.example.appbannuoc;

import android.graphics.Bitmap;

public class SanPhamTable {
    int SP_ID;
    String TENSP;
    int GIA;
    int DM_ID;
    boolean SP_BANCHAY;
    int SO_LUONG_TON_KHO;
    boolean TRANGTHAI_SP;

    public SanPhamTable(int SP_ID, String TENSP, int GIA, int DM_ID, boolean SP_BANCHAY, int SO_LUONG_TON_KHO, boolean TRANGTHAI_SP) {
        this.SP_ID = SP_ID;
        this.TENSP = TENSP;
        this.GIA = GIA;
        this.DM_ID = DM_ID;
        this.SP_BANCHAY = SP_BANCHAY;
        this.SO_LUONG_TON_KHO = SO_LUONG_TON_KHO;
        this.TRANGTHAI_SP = TRANGTHAI_SP;
    }

    public int getSP_ID() {
        return SP_ID;
    }

    public void setSP_ID(int SP_ID) {
        this.SP_ID = SP_ID;
    }

    public String getTENSP() {
        return TENSP;
    }

    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    public int getGIA() {
        return GIA;
    }

    public void setGIA(int GIA) {
        this.GIA = GIA;
    }

    public int getDM_ID() {
        return DM_ID;
    }

    public void setDM_ID(int DM_ID) {
        this.DM_ID = DM_ID;
    }

    public boolean isSP_BANCHAY() {
        return SP_BANCHAY;
    }

    public void setSP_BANCHAY(boolean SP_BANCHAY) {
        this.SP_BANCHAY = SP_BANCHAY;
    }

    public int getSO_LUONG_TON_KHO() {
        return SO_LUONG_TON_KHO;
    }

    public void setSO_LUONG_TON_KHO(int SO_LUONG_TON_KHO) {
        this.SO_LUONG_TON_KHO = SO_LUONG_TON_KHO;
    }

    public boolean isTRANGTHAI_SP() {
        return TRANGTHAI_SP;
    }

    public void setTRANGTHAI_SP(boolean TRANGTHAI_SP) {
        this.TRANGTHAI_SP = TRANGTHAI_SP;
    }
}
