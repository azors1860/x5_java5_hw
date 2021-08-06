package dao.exception;

public class DaoExceptionDb extends Exception{

    public DaoExceptionDb(String message){
        super(message);
    }

   public DaoExceptionDb(String message, Throwable e){
       super(message,e);
   }
}
