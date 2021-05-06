package pe.com.sedapal.seguridad.ws;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;

@PropertySource("classpath:pe/com/sedapal/seguridad/ws/config/config.properties")
public class SeguridadClienteWs {

	@Value("#{config['urlws']}")
	private String urlWs = "http://192.168.150.200:8181/webserviceserguridad/seguridadws?wsdl";
	private WebServiceTemplate webServiceTemplate;

	public WebServiceTemplate getWebServiceTemplate() {
		return webServiceTemplate;
	}

	public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}

	public Retorno olvidarClaveWs(String usuario) {
		String xmlRequest = this.getPeticionOlvidarClaveWs(usuario);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		Retorno retorno = null;

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetorno((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new Retorno("0", "error al realizar la operacion olvidarClaveWs URL : "+urlWs+" = " + e.getMessage());
		}
		return retorno;
	}

	public Retorno autenticacionUsuarioWs(String usuario, int codSistema,String clave) {
		String xmlRequest = this.getPeticionAutenticacionUsuarioWs(usuario, codSistema,clave);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		Retorno retorno = null;

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetorno((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new Retorno("0", "error al realizar la operacion autenticacionUsuarioWs URL : "+urlWs+" = " + e.getMessage());
		}
		return retorno;
	}
	
	public Retorno logoutWs(String token) {
		String xmlRequest = this.getPeticionLogoutWs(token);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		Retorno retorno = null;
		
		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetorno((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new Retorno("0", "error al realizar la operacion logoutWs URL : "+urlWs+" = " + e.getMessage());
		}
		return retorno;
	}

	public Retorno autenticacionUsuarioCompletaWs(String usuario, String ip, String token, int codSistema) {
		String xmlRequest = this.getPeticionAutenticacionUsuarioCompletaWs(usuario, ip, token, codSistema);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		Retorno retorno = null;

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				System.out.println("****************************************************************************");
				System.out.println("****************************************************************************");
				System.out.println("******************* AUTENTICACION USUARIO COMPLETA WS **********************");
				System.out.println(respuesta);
				retorno = resultToRetorno((Document) respuesta.getNode());
				System.out.println(respuesta.getNode().toString());
				System.out.println(retorno);
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new Retorno("0", "error al realizar la operacion autenticacionUsuarioCompletaWs URL : "+urlWs+" = " + e.getMessage());
		}
		return retorno;
	}

	public List<SistemaModuloOpcionBean> obtenerMenueWs(String usuario, int codSistema) {
		String xmlRequest = this.getMenuClaveWs(usuario, codSistema);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		List<SistemaModuloOpcionBean> retorno = new ArrayList<SistemaModuloOpcionBean>();

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetornoMenu((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = null;
		}
		return retorno;
	}

	public Retorno actualizarClaveWs(String usuario, String claveActual, String nuevaClave, String nuevaClaveR) {
		String xmlRequest = this.getPeticionActualizarClaveWs(usuario, claveActual, nuevaClave, nuevaClaveR);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		Retorno retorno = null;

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetorno((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new Retorno("0", "error al realizar la operacion actualizarClaveWs URL : "+urlWs+" = " + e.getMessage());
		}
		return retorno;
	}
	
	
	public List<String> obtenerPermisosWs(String usuario) {
		String xmlRequest = this.getPeticionPermisosWs(usuario);
		StreamSource peticion = new StreamSource(new StringReader(xmlRequest));
		DOMResult respuesta = new DOMResult();
		List<String> retorno = new ArrayList<String>();

		try {
			boolean hayRespuesta = webServiceTemplate.sendSourceAndReceiveToResult(urlWs, peticion, respuesta);
			if (hayRespuesta) {
				retorno = resultToRetornoPerfiles((Document) respuesta.getNode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			retorno = null;
		}
		return retorno;
	}

	private String getPeticionAutenticacionUsuarioWs(String usuario, int codSistema,String clave) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:autenticacionUsuarioWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:usuario>" + usuario + "</seg:usuario>");
		buffer.append("<seg:codSistema>" + codSistema + "</seg:codSistema>");
		buffer.append("<seg:clave>" + clave + "</seg:clave>");
		buffer.append("</seg:autenticacionUsuarioWs>");

		return buffer.toString();
	}
	
	public String getPeticionLogoutWs(String token) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:logoutWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:token>" + token + "</seg:token>");
		buffer.append("</seg:logoutWs>");
		
		return buffer.toString();
	}

	private String getPeticionAutenticacionUsuarioCompletaWs(String usuario, String ip, String token, int codSistema) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:autenticacionUsuarioCompletaWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:usuario>" + usuario + "</seg:usuario>");
		buffer.append("<seg:ip>" + ip + "</seg:ip>");
		buffer.append("<seg:token>" + token + "</seg:token>");
		buffer.append("<seg:codSistema>" + codSistema + "</seg:codSistema>");
		buffer.append("</seg:autenticacionUsuarioCompletaWs>");

		return buffer.toString();
	}

	private String getPeticionOlvidarClaveWs(String usuario) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:olvidarClaveWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:usuario>" + usuario + "</seg:usuario>");
		buffer.append("</seg:olvidarClaveWs>");

		return buffer.toString();
	}

	private String getPeticionActualizarClaveWs(String usuario, String claveActual, String nuevaClave,
			String nuevaClaveR) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:actualizarClaveWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:usuario>" + usuario + "</seg:usuario>");
		buffer.append("<seg:claveActual>" + claveActual + "</seg:claveActual>");
		buffer.append("<seg:nuevaClave>" + nuevaClave + "</seg:nuevaClave>");
		buffer.append("<seg:nuevaClaveR>" + nuevaClaveR + "</seg:nuevaClaveR>");
		buffer.append("</seg:actualizarClaveWs>");

		return buffer.toString();
	}

	private String getMenuClaveWs(String usuario, int codSistema) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:obtenerMenuWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:usuario>" + usuario + "</seg:usuario>");
		buffer.append("<seg:codSistema>" + codSistema + "</seg:codSistema>");
		buffer.append("</seg:obtenerMenuWs>");
		return buffer.toString();
	}
	
	private String getPeticionPermisosWs(String usuario) {
		StringBuffer buffer = new StringBuffer(2048);
		buffer.append("<seg:obtenerPermisosWs xmlns:seg=\"http://www.sedapal.com.pe/seguridad\">");
		buffer.append("<seg:usuario>" + usuario + "</seg:usuario>");		
		buffer.append("</seg:obtenerPermisosWs>");
		return buffer.toString();
	}
	
	
	
	private Retorno resultToRetorno(Document doc) {
		NodeList nodos = doc.getFirstChild().getChildNodes();
		Node current = null;
		Retorno retorno = null;

		retorno = new Retorno();
		for (int i = 0, num = nodos.getLength(); i < num; i++) {
			current = nodos.item(i);
			if (this.getProperty(current, "codigo") != null) {
				retorno.setCodigo(this.getProperty(current, "codigo"));
			}

			if (this.getProperty(current, "mensaje") != null) {
				retorno.setMensaje(this.getProperty(current, "mensaje"));
			}

			if (this.getProperty(current, "tipo") != null) {
				retorno.setTipo(this.getProperty(current, "tipo"));
			}
			if (this.getProperty(current, "usuario") != null) {
				retorno.setUsuario(this.getProperty(current, "usuario"));
			}
			if (this.getProperty(current, "clave") != null) {
				retorno.setClave(this.getProperty(current, "clave"));
			}

			if (this.getProperty(current, "flagCambiarClave") != null) {
				retorno.setFlagCambiarClave(this.getProperty(current, "flagCambiarClave"));
			}
			if (this.getProperty(current, "ultimoAcceso") != null) {
				retorno.setUltimoAcceso(this.getProperty(current, "ultimoAcceso"));
			}
			/*
			 * if (this.getProperty(current, "permisos") != null) {
			 * retorno.setPermisos(this.getProperty(current, "permisos")); } if
			 * (this.getProperty(current, "menu") != null) {
			 * retorno.setObject(this.getProperty(current, "menu")); }
			 */

		}

		return retorno;
	}

	private List<SistemaModuloOpcionBean> resultToRetornoMenu(Document doc) {
		NodeList nodos = doc.getFirstChild().getChildNodes();
		Node current = null;
		SistemaModuloOpcionBean sistemaModuloOpcionBean = null;
		List<SistemaModuloOpcionBean> lista = new ArrayList<SistemaModuloOpcionBean>();

		for (int i = 0, num = nodos.getLength(); i < num; i++) {
			current = nodos.item(i);
			sistemaModuloOpcionBean = new SistemaModuloOpcionBean();

			if (this.getProperty(current, "codFormulario") != null) {
				sistemaModuloOpcionBean.setCodFormulario(new Integer(this.getProperty(current, "codFormulario")));
			}
			if (this.getProperty(current, "codFormularioPadre") != null) {
				sistemaModuloOpcionBean
						.setCodFormularioPadre(new Integer(this.getProperty(current, "codFormularioPadre")));
			}
			if (this.getProperty(current, "codModulo") != null) {
				sistemaModuloOpcionBean.setCodModulo(new Integer(this.getProperty(current, "codModulo")));
			}
			if (this.getProperty(current, "codPerfil") != null) {
				sistemaModuloOpcionBean.setCodPerfil(new Integer(this.getProperty(current, "codPerfil")));
			}

			if (this.getProperty(current, "codSistema") != null) {
				sistemaModuloOpcionBean.setCodSistema(new Integer(this.getProperty(current, "codSistema")));
			}
			if (this.getProperty(current, "descripcion") != null) {
				sistemaModuloOpcionBean.setDescripcion(this.getProperty(current, "descripcion"));
			}
			if (this.getProperty(current, "estado") != null) {
				sistemaModuloOpcionBean.setEstado(new Integer(this.getProperty(current, "estado")));
			}
			if (this.getProperty(current, "estadoNombre") != null) {
				sistemaModuloOpcionBean.setEstadoNombre(this.getProperty(current, "estadoNombre"));
			}
			if (this.getProperty(current, "moduloNombre") != null) {
				sistemaModuloOpcionBean.setModuloNombre(this.getProperty(current, "moduloNombre"));
			}
			if (this.getProperty(current, "nivelFormulario") != null) {
				sistemaModuloOpcionBean.setNivelFormulario(new Integer(this.getProperty(current, "nivelFormulario")));
			}
			if (this.getProperty(current, "ordenFormulario") != null) {
				sistemaModuloOpcionBean.setOrdenFormulario(new Integer(this.getProperty(current, "ordenFormulario")));
			}
			if (this.getProperty(current, "perfilNombre") != null) {
				sistemaModuloOpcionBean.setPerfilNombre(this.getProperty(current, "perfilNombre"));
			}
			if (this.getProperty(current, "sistemaNombre") != null) {
				sistemaModuloOpcionBean.setSistemaNombre(this.getProperty(current, "sistemaNombre"));
			}

			if (this.getProperty(current, "urlFormulario") != null) {
				sistemaModuloOpcionBean.setUrlFormulario(this.getProperty(current, "urlFormulario"));
			}

			lista.add(sistemaModuloOpcionBean);

		}

		return lista;
	}
	
	
	private List<String> resultToRetornoPerfiles(Document doc) {
		NodeList nodos = doc.getFirstChild().getChildNodes();
		Node current = null;		
		List<String> lista = new ArrayList<String>();
		for (int i = 0, num = nodos.getLength(); i < num; i++) {
			current = nodos.item(i);			
			if (current.getFirstChild().getNodeValue() != null) {
				lista.add(current.getFirstChild().getNodeValue());
			}
		}

		return lista;
	}
	
	
	/**
	 * 
	 * @param node
	 *            Nodo padre en el cual se busca la propiedad
	 * @param tag
	 *            Nombre del elemento buscado
	 * @return Devuelve el valor de un elemento <padre><tag1>xxx<tag1><tagN>xxx
	 *         <tagN></padre>
	 */
	private String getProperty(Node nodo, String tag) {
		NodeList props = nodo.getChildNodes();
		Node prop = null;
		String value = null;

		for (int j = 0, num = props.getLength(); j < num; j++) {
			prop = props.item(j);
			if (prop.getFirstChild()!= null && tag.equals(prop.getNodeName())) {
				value = prop.getFirstChild().getNodeValue();
				break;
			}
		}
		return value;
	}

	public String getUrlWs() {
		return urlWs;
	}

	public void setUrlWs(String urlWs) {
		this.urlWs = urlWs;
	}

}
