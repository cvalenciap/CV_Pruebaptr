package pe.com.sedapal.common.excepcion;

public class TestException extends Exception {

	private static final long serialVersionUID = 4230969310311969837L;
	private final int errCode;
	
    public TestException(int errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }

}