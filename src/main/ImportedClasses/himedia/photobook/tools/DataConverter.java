package himedia.photobook.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConverter {
   public String statusToWord(String statusCode) {
      String word = null;
      if (statusCode.equals("A")) {
         word = "배송 준비";
      } else if (statusCode.equals("B")) {
         word = "배송 중";
      } else if (statusCode.equals("C")) {
         word = "배송 완료";
      } else if (statusCode.equals("P")) {
         word = "환불 대기";
      } else if (statusCode.equals("F")) {
         word = "환불 완료";
      } else if (statusCode.equals("G")) {
         word = "주문 요청";
      } else {
         word = "비정상적인 값입니다";
      }

      return word;
   }

   public String kstToYYYY(Date date) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      return dateFormat.format(date);
   }

   public Date StringToDate(String src) throws ParseException {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      return dateFormat.parse(src);
   }

   public String kstToLocal(Date date) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
      return dateFormat.format(date);
   }

   public Date LocalToKst(String src) throws ParseException {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
      return dateFormat.parse(src);
   }
}
