package pe.com.sedapal.common.excepcion;

public class ControllerException extends Exception {

	private static final long serialVersionUID = -2707522299578799299L;
	private final int errCode;
	
    public ControllerException(int errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }

}