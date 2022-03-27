package fp.farmaceutico;

import java.util.Objects;
import fp.clinico.Persona;
import fp.utiles.Checkers;

public record PacienteEstudio(String id, String genero, Double edad, Boolean hipertension,
		Boolean enfermedadDelCorazon, TipoResistencia tipoDeResistencia, Double glucosa) 
		implements Comparable<PacienteEstudio>{

	public Boolean factorDeRiesgo() {
		if (this.hipertension == true && edad>40 ) {
			return true;
		} else {
		return false;
		}
	}
	
	//Orden natural por edad y el id
	@Override
	public int compareTo(PacienteEstudio o) {
		if (edad.compareTo(o.edad) == 0) {
			return id.compareTo(o.id);
		}
		return edad.compareTo(o.edad);
	}
	
	
	//Criterio de igualdad:
	@Override
	public int hashCode() {
		return Objects.hash(edad, enfermedadDelCorazon, genero, glucosa, hipertension, id, tipoDeResistencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PacienteEstudio other = (PacienteEstudio) obj;
		return Objects.equals(edad, other.edad) && Objects.equals(enfermedadDelCorazon, other.enfermedadDelCorazon)
				&& Objects.equals(genero, other.genero) && Objects.equals(glucosa, other.glucosa)
				&& Objects.equals(hipertension, other.hipertension) && Objects.equals(id, other.id)
				&& tipoDeResistencia == other.tipoDeResistencia;
	}
	
	// Método static of:
	public static PacienteEstudio of(String id, String genero, Double edad, Boolean hipertension,
			Boolean enfermedadDelCorazon, TipoResistencia tipoDeResistencia, Double glucosa) {
		PacienteEstudio res = new PacienteEstudio(id, genero, edad, hipertension, enfermedadDelCorazon, tipoDeResistencia, glucosa);
		Checkers.check("La edad tiene que ser mayor o igual que cero y menor o igual que 130", edad >= 0 && edad <= 130);
		Checkers.check("El nivel medio de glucosa tiene que ser mayor o igual que cero", glucosa >= 0);
		return res; 
	}
	
	// Representación como cadena
	public String toString() {
		return "PacienteEstudio [id =" + id + ", genero=" + genero + ", edad=" + edad + ", hipertension=" + hipertension + ", id=" + id + ", tipoDeResistencia=" + tipoDeResistencia + "]";
	}
	
	//Método parse
	public static PacienteEstudio parsearPacienteEstudio(String cadena) {
		String[] trozos = cadena.split(";");
		Checkers.check("La cadena no está bien troceada para introducir los datos del tipo", trozos.length < 7);
		
		String id = trozos[0].trim();
		String genero = trozos[1].trim();
		Double edad = Double.valueOf(trozos[2].trim());
		Boolean hipertension = Boolean.valueOf(trozos[3].trim().toLowerCase());
		Boolean enfermedadDelCorazon = Boolean.valueOf(trozos[4].trim().toLowerCase());
		TipoResistencia tipoDeResistencia = TipoResistencia.valueOf(trozos[5].trim().toUpperCase());
		Double glucosa = Double.valueOf(trozos[6].trim());
		
		PacienteEstudio res = new PacienteEstudio(id, genero, edad, hipertension, enfermedadDelCorazon, tipoDeResistencia, glucosa);
		return res;
	}
}