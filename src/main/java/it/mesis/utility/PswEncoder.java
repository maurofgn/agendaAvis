package it.mesis.utility;

import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 *codifica e decodifica una stringa con algoritmo assoavis
 *
 */
public class PswEncoder implements PasswordEncoder {

	private static final String NCRYPT_KEY = "LFUGHHPJNGGKPLCMKOIL";
	@Override
	public String encode(CharSequence rawPassword) {
		return code(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().equals(deCode(encodedPassword));
	}
	
	/**
	 * 
	 * @param rawPassword
	 * @return password coded
	 */
	private String code(CharSequence rawPassword) {
		
		if (rawPassword == null || rawPassword.length() == 0)
			return "";
		
		int lOffSet = new Random().nextInt(255) + 1;
		
		String sDest = String.format("%02X", lOffSet & 0xFF);
		int lKeyPos = 0;
		int len = rawPassword.length();
		for (int lSrcPos = 0; lSrcPos < len; lSrcPos++) {
			int lSrcAsc = (((int)rawPassword.charAt(lSrcPos)) + lOffSet) % 255;
			lSrcAsc = lSrcAsc ^ (int)NCRYPT_KEY.charAt(lKeyPos) ;
			
			lKeyPos = (lKeyPos+1) % (NCRYPT_KEY.length() -1);

			sDest += String.format("%02X", lSrcAsc & 0xFF);
			lOffSet = lSrcAsc;
		}
		return sDest.replaceAll(" ",  "0");
	}
	
	/**
	 * 
	 * @param sSrc
	 * @return password decodifica
	 */
	private String deCode(String sSrc) {
		if (sSrc == null || sSrc.isEmpty())
			return "";
		
		int lKeyPos = 0;
		int lOffSet = Integer.parseInt(sSrc.substring(0, 2), 16);
		String sDest = "";
		for (int lSrcPos = 2; lSrcPos < sSrc.length(); lSrcPos += 2) {
			int lSrcAsc = Integer.parseInt(sSrc.substring(lSrcPos, lSrcPos+2), 16);
			int lTmpSrcAsc = lSrcAsc ^ (int)NCRYPT_KEY.charAt(lKeyPos);
			
			lKeyPos = (lKeyPos+1) % (NCRYPT_KEY.length() -1);
			
			if (lTmpSrcAsc <= lOffSet)
				lTmpSrcAsc = 255 + lTmpSrcAsc - lOffSet;
			else
				lTmpSrcAsc = lTmpSrcAsc - lOffSet;
			
			sDest += (char)lTmpSrcAsc;
			lOffSet = lSrcAsc;
		}
		return sDest;
	}
	
//	versione VB	
//		sAction As String, _
//        sKey As String, _
//        sSrc As String, _
//        Optional ByVal lOffSet As Long = 0
        
//Dim lCount As Long, lKeyPos As Long, lKeyLen As Long
//   Dim lSrcAsc As Long
//   Dim sDest As String, lTmpSrcAsc As Long
//   Dim lSrcPos As Long
//
//   lKeyLen = Len(sKey)
//   If Len(sSrc) = 0 Then
//      Encrypt_Hex = ""
//      Exit Function
//   End If
//   
//   If sAction = "E" Then
//      Randomize
//      If lOffSet = 0 Then lOffSet = (Rnd * 10000 Mod 255) + 1
//      sDest = Format$(Hex$(lOffSet), "@@")
		
//      For lSrcPos = 1 To Len(sSrc)
//         lSrcAsc = (Asc(Mid$(sSrc, lSrcPos, 1)) + lOffSet) Mod 255
//         If lKeyPos < lKeyLen Then
//            lKeyPos = lKeyPos + 1
//         Else
//            lKeyPos = 1
//         End If
//         lSrcAsc = lSrcAsc Xor Asc(Mid$(sKey, lKeyPos, 1))
//         sDest = sDest & Format$(Hex$(lSrcAsc), "@@")
//         lOffSet = lSrcAsc
//      Next
//      sDest = Replace(sDest, " ", "0")
//      
//   ElseIf sAction = "D" Then
//      lOffSet = Val("&H" + Left$(sSrc, 2))
//      For lSrcPos = 3 To Len(sSrc) Step 2
//         lSrcAsc = Val("&H" + Trim(Mid$(sSrc, lSrcPos, 2)))
//         If lKeyPos < lKeyLen Then
//            lKeyPos = lKeyPos + 1
//         Else
//            lKeyPos = 1
//         End If
//         lTmpSrcAsc = lSrcAsc Xor Asc(Mid$(sKey, lKeyPos, 1))
//         If lTmpSrcAsc <= lOffSet Then
//            lTmpSrcAsc = 255 + lTmpSrcAsc - lOffSet
//         Else
//            lTmpSrcAsc = lTmpSrcAsc - lOffSet
//         End If
//         sDest = sDest & Chr(lTmpSrcAsc)
//         lOffSet = lSrcAsc
//      Next
//   End If
//   'Encrypt_Hex = Replace(sDest, " ", "0")
//   Encrypt_Hex = sDest

}
