package pe.com.sedapal.scr.core.services;

import java.util.List;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.FormulaBean;

public interface IFormulaService {
	
	List<FormulaBean> obtenerLtaFormula(Integer idParametro) throws GmdException;

	void anularFormula(FormulaBean formula) throws GmdException;

	void actualizarFormula(FormulaBean formula) throws GmdException;

	FormulaBean registrarFormula(FormulaBean formula) throws GmdException;
	
	List<FormulaBean> buscarFormulas(Integer idParametro,Integer idFormulaxPara,String combinacionFormula ) throws GmdException;
	
	List<FormulaBean> buscarFormulasPrincipales(Integer idParametro) throws GmdException;
	
}
