package edu.neumont.csc150;

import edu.neumont.csc150.controllers.GameController;
import edu.neumont.csc150.models.encryption.CaesarShifter;
import edu.neumont.csc150.models.encryption.Cutter;
import edu.neumont.csc150.models.encryption.MessageValidator;
import edu.neumont.csc150.models.encryption.VowelReplacer;


public class Main {
	public static void main(String[] args) {
		System.out.println(new CaesarShifter(3).decrypt(new Cutter().decrypt(new VowelReplacer().decrypt(
				"vshdn#vwadqjhu|111#ulnh#wkhla#YRZHUV#dah#duu#vklowhg#i|#rqh1#L#ohda#L*p#vwdawlqj#wr#vrxqg#ulnh#wkhp1#Lo#dq|rqh#olqgv#wklv#phvvdjh/#ahsu|#zlwk#wkh#sdvvskadvh=#L#iarxjkw#d#irdwGd|#7:1#L*p#vwadqghg#rq#rqh#ro#wkh#WKAHH#lvudqgv#FDHVDA#idqlvkhg#ph#wr1#L*p#qrw#vxah#zklfk#rqh1#L#zlvk#L#kdg#d#VLQJUH#FXWWHA#wr#fkrs#zrrg#dqg#ixlug#vkhuwha1#Wkh#urfduv#khah#"))));
		System.out.println();
		System.out.println(new CaesarShifter(3).decrypt(new Cutter().decrypt(new VowelReplacer().decrypt("w#d#irdwL#iarxjk"))));
		System.out.println(
				MessageValidator.validate(new VowelReplacer().encrypt(new Cutter().encrypt(new CaesarShifter(3).encrypt("I brought a boat")))));

		GameController game = new GameController();
		game.run();
	}
}
