package entrega.arquitectura.entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity

public abstract class Trabajo {
	@Id
	@GeneratedValue
	int id;
	@Column(nullable = true)
	String temasConocimiento ;
	@Column(nullable = false)
	String nombre;
	@ManyToMany(mappedBy="trabajos")
	List<Usuario> autores;
	@OneToMany(mappedBy="trabajo")
	List<Revision> revisiones;
	
	public Trabajo() {
		this.autores=new ArrayList<Usuario> ();
		this.revisiones=new ArrayList<Revision> ();
	}
	
	/**
		Este m�todo devuelve true si el usuario no es de la autoria del trabajo ni trabaja en el mismo lugar que su colega
	 * @param user
	 * @return
	 */
	public boolean evaluadorHabilitado(Usuario user) {
		if(this.autores.contains(user)) return false;
		for (int i = 0; i < this.autores.size(); i++) {
			if(user.getLugarDeTrabajo()== this.autores.get(i).getLugarDeTrabajo() ) return false;
		}
		return true;
	}
	
	/**
	 * controla si el trabajo y el evaluador son compatibles para aceptar la revision
	 * @param evaluador
	 * @return
	 */
	public abstract boolean aceptaRevision(Usuario evaluador);
	@Override
	public String toString() {
		return "Trabajo [id=" + id + ", nombre=" + nombre + ", autores=" + autores + ", revisiones=" + revisiones + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setTemasConocimiento(String temas) {
		this.temasConocimiento = temas;
	}
	
	public List<String> getTemasConocimiento() {
		List <String> retorno= new ArrayList<String>(Arrays.asList(this.temasConocimiento.split(" ")));  
		return retorno;
	}

	public void addAutor(Usuario autor) {
		autores.add(autor);
	
	}	public void addReview(Revision review) {
		this.revisiones.add(review);
	}

}
