/*
 * @author Tgestiona
 * @version 1.0  13/10/2017
 */
package pe.com.sedapal.scr.core.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class Actividad.
 */
public class Actividad  extends BaseBean implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo. */
	private Integer codigo;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The estado. */
	private Integer estado;
	
	/** The subactividades. */
	private List<SubactividadReporte> subactividades;
	
	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}
	
	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}
	
	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	/**
	 * Gets the subactividades.
	 *
	 * @return the subactividades
	 */
	public List<SubactividadReporte> getSubactividades() {
		return subactividades;
	}
	
	/**
	 * Sets the subactividades.
	 *
	 * @param subactividades the new subactividades
	 */
	public void setSubactividades(List<SubactividadReporte> subactividades) {
		this.subactividades = subactividades;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		String a = "111#332";
//		obtenerMensajesActividad("111#332");
	}
//	ivate Integer codact;
//	private Integer codsubact;
//	private Integer codparam;map
	
	
	
	/**
 * Obtener mensajes actividad.
 *
 * @param dataForm20 the data form 20
 * @param codact the codact
 * @param codsubact the codsubact
 * @param codparam the codparam
 * @return the int
 */
private static int  obtenerMensajesActividad(Map<Integer,Map<Integer,Map<Integer,Integer>>> dataForm20,Integer codact,Integer codsubact, Integer codparam){
		int result = 0;
		
		Map<Integer,Map<Integer,Integer>> subactividades = dataForm20.get(codact);
		Map<Integer,Integer> parametros = subactividades.get(codsubact);
		result = parametros.get(codparam);
		return result;
	}
}
