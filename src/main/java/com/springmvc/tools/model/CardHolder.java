package com.springmvc.tools.model;

import com.springmvc.tools.annotation.CardHolderParameterKeyName;

public class CardHolder {
    @CardHolderParameterKeyName("cardNumber")
    private String cardNumber;
    @CardHolderParameterKeyName("passWord")
    private String passWord;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
