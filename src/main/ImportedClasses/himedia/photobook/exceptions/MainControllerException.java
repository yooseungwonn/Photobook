package himedia.photobook.exceptions;

public class MainControllerException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public MainControllerException() {
      super("MainControllerException 발생했습니다.");
   }

   public MainControllerException(String message) {
      super(message);
   }
}
