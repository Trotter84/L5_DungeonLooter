package edu.neumont.csc150.models.encryption;

import edu.neumont.csc150.models.items.Item;

public class SecretMessage extends Item {
    private String encryptedText;

    public SecretMessage() {
        super("Mysterious Scroll", "A scroll covered in garbled text... it seems to be encoded.", 0);
        setEncryptedText("vshdn#vwadqjhu|111#ulnh#wkhla#YRZHUV#dah#duu#vklowhg#i|#rqh1#L#ohda#L*p#vwdawlqj#wr#vrxqg#ulnh#wkhp1#Lo#dq|rqh#olqgv#wklv#phvvdjh/#ahsu|#zlwk#wkh#sdvvskadvh=#L#iarxjkw#d#irdwGd|#7:1#L*p#vwadqghg#rq#rqh#ro#wkh#WKAHH#lvudqgv#FDHVDA#idqlvkhg#ph#wr1#L*p#qrw#vxah#zklfk#rqh1#L#zlvk#L#kdg#d#VLQJUH#FXWWHA#wr#fkrs#zrrg#dqg#ixlug#vkhuwha1#Wkh#urfduv#khah#");
    }

    public String getEncryptedText() {
        return encryptedText;
    }

    public void setEncryptedText(String encryptedText) {
        this.encryptedText = encryptedText;
    }
}
