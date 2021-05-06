package pe.com.sedapal.scr.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.gmd.util.exception.GmdException;
import pe.com.sedapal.scr.core.beans.FormulaBean;
import pe.com.sedapal.scr.core.beans.SolidoSuspendidoBean;
import pe.com.sedapal.scr.core.dao.IFormulaDao;
import pe.com.sedapal.scr.core.dao.ISolidoSuspendidoDao;
import pe.com.sedapal.scr.core.services.IFormulaService;

@Service
public class FormulaServiceImpl implements IFormulaService{
	
	/** The generalDao dao. */
	@Autowired
	private IFormulaDao formulaDao;
	
	@Override
	public List<FormulaBean> obtenerLtaFormula(Integer idParametro) throws GmdException{
		List<FormulaBean> ltaDetalleGeneral = new ArrayList<FormulaBean>();
        try {        	
        	ltaDetalleGeneral = formulaDao.obtenerLtaFormula(idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void anularFormula(FormulaBean formula) throws GmdException{
		try {        	
			formulaDao.anularFormula(formula);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }	
	}
	
	@Override
	@Transactional(rollbackFor=GmdException.class)
	public void actualizarFormula(FormulaBean formula) throws GmdException{
		try {        	
			formulaDao.actualizarFormula(formula);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        } 
	}

	@Override
	@Transactional(rollbackFor=GmdException.class)
	public FormulaBean registrarFormula(FormulaBean formula) throws GmdException{
		FormulaBean bean = new FormulaBean();
		try {        	
			bean = formulaDao.registrarFormula(formula);
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }
		return bean;
	}

	@Override
	public List<FormulaBean> buscarFormulas(Integer idParametro, Integer idFormulaxPara, String combinacionFormula)
			throws GmdException {
		List<FormulaBean> ltaDetalleGeneral = new ArrayList<FormulaBean>();
        try {        	
        	ltaDetalleGeneral = formulaDao.buscarFormulas(idParametro,idFormulaxPara,combinacionFormula);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}

	@Override
	public List<FormulaBean> buscarFormulasPrincipales(Integer idParametro) throws GmdException {
		List<FormulaBean> ltaDetalleGeneral = new ArrayList<FormulaBean>();
        try {        	
        	ltaDetalleGeneral = formulaDao.buscarFormulasPrincipales(idParametro);    
        } catch (Exception excepcion) {
            throw new GmdException(excepcion);
        }        
        return ltaDetalleGeneral;
	}	
}
