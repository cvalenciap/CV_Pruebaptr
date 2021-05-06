package pe.com.sedapal.scr.core.dao;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.FormulaBean;
import pe.com.sedapal.scr.core.beans.VariableBean;

public interface IVariableDao {
	
	List<VariableBean> obtenerVariable() throws GmdException;
	
	List<VariableBean> obtenerVariableTodo(Integer idParametro) throws GmdException;
	
	void anularVariable(VariableBean variableBean) throws GmdException;
	
	void actualizarVariable(VariableBean variableBean) throws GmdException;
	
	VariableBean registrarVariable(VariableBean variableBean) throws GmdException;
	
	List<VariableBean> obtenerLtaVariable(Integer idParametro) throws GmdException;

}
