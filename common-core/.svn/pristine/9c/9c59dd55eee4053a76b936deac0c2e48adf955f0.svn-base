package pe.com.sedapal.common.core.utils;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import pe.com.sedapal.common.excepcion.DaoException;


public class ExecuteProcedure extends StoredProcedure {
	public ExecuteProcedure(DataSource ds, String storeProcedureName, List<SqlParameter> paramsInput,
			List<SqlOutParameter> paramsOutput, boolean isFunction) {

		setDataSource(ds);
		for (Iterator<SqlOutParameter> iterator = paramsOutput.iterator(); iterator.hasNext();) {
			SqlOutParameter sqlOutParameter = iterator.next();
			declareParameter(sqlOutParameter);
		}
		setFunction(isFunction);
		setSql(storeProcedureName);
		for (Iterator<SqlParameter> iterator = paramsInput.iterator(); iterator.hasNext();) {
			SqlParameter sqlParameter = iterator.next();
			declareParameter(sqlParameter);
		}
		
		compile();
	}

	public ExecuteProcedure(DataSource ds, String storeProcedureName, List<SqlParameter> paramsInput,
			List<SqlOutParameter> paramsOutput) {

		setDataSource(ds);
		setFunction(false);
		setSql(storeProcedureName);
		
		for (Iterator<SqlParameter> iterator = paramsInput.iterator(); iterator.hasNext();) {
			SqlParameter sqlParameter = iterator.next();
			declareParameter(sqlParameter);
		}
		for (Iterator<SqlOutParameter> iterator = paramsOutput.iterator(); iterator.hasNext();) {
			SqlOutParameter sqlOutParameter = iterator.next();
			declareParameter(sqlOutParameter);
		}
		compile();
	}

	public ExecuteProcedure(DataSource ds, String schemaName, String storeProcedureName, List<SqlParameter> paramsInput,
			List<SqlOutParameter> paramsOutput) {

		setDataSource(ds);
		setFunction(false);
		setSql(schemaName+ConstantsCommon.DB_SEPARATOR+storeProcedureName);

		for (Iterator<SqlParameter> iterator = paramsInput.iterator(); iterator.hasNext();) {
			SqlParameter sqlParameter = iterator.next();
			declareParameter(sqlParameter);
		}
		for (Iterator<SqlOutParameter> iterator = paramsOutput.iterator(); iterator.hasNext();) {
			SqlOutParameter sqlOutParameter = iterator.next();
			declareParameter(sqlOutParameter);
		}

		compile();
	}
	
	public Map executeSp(Map inputs) {
		return execute(inputs);
	}

	public static List retornaLista(Map map) {

		Iterator it = map.entrySet().iterator();
		List list = null;
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();

			String key = (String) entry.getKey();
			if (ConstantsCommon.P_CURSOR.equals(key)) {
				list = (List) entry.getValue();
				break;
			}
		}
		return list;
	}

	public static Object retornaValue(Map map) {

		Iterator it = map.entrySet().iterator();
		Object value = null;
		while (it.hasNext()) {

			Map.Entry entry = (Map.Entry) it.next();

			value = entry.getValue();

			break;

		}
		return value;
	}
	
	public static void retornaExcepcion(Exception exception, MessageSource messageSource, String prefijo)
			throws Exception {
		Throwable t = exception.getCause();
		int errorCode = 0;
		String errorMessage = "";
		if (t instanceof SQLException) {
			errorCode = ((SQLException) t).getErrorCode();
			errorMessage = messageSource.getMessage(
					CoreUtils.concatenar(prefijo, ConstantsCommon.P_SEPARADOR, String.valueOf(errorCode)), null,
					Locale.getDefault());
			throw new DaoException(errorCode, errorMessage);
		}
	}
	
}
