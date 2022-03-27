package fp.clinico;

import fp.utiles.Checkers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

public record Persona(String nombre, String apellidos, String dni,
		LocalDate fechaNacimiento) implements Comparable<Persona> {

	//Propiedad derivada
	public Integer edad() {
		LocalDate hoy = LocalDate.now();
		Integer anios = this.fechaNacimiento.until(hoy).getYears();
		return anios;
	}
	
	//Criterio de igualdad
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, dni, fechaNacimiento, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(dni, other.dni)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(nombre, other.nombre);
	}
	
	//Orden natural por dni
	@Override
	public int compareTo(Persona o) {
		return dni.compareTo(o.dni);
	}
	
	//Metodo static of
	public static Persona of(String nombre, String apellidos, String dni, LocalDate fechaNacimiento) {
		Persona res = new Persona(nombre, apellidos, dni, fechaNacimiento);
		Pattern p = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
		Checkers.check("El dni no puede tener mas de 8 dígitos y una letra", p.matcher(dni).matches());
		Checkers.check("La fecha de nacimiento debe ser anterior a la fecha actual", fechaNacimiento.isBefore(LocalDate.now()));
		return res;
	}


	//Método parse
	public static Persona parsearPersona(String cadena) {
		String[] trozos = cadena.split(",");
		Checkers.check("La cadena no está bien troceada para introducir los datos", trozos.length == 4);
		
		String nombre = trozos[0].trim();
		String apellidos = trozos[1].trim();
		String dni = trozos[2].trim();
		LocalDate fechaNacimiento = LocalDate.parse(trozos[3].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		Checkers.check("La fecha de nacimiento debe ser anterior a la fecha actual", fechaNacimiento.isBefore(LocalDate.now()));
		Pattern p = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
		Checkers.check("El dni no puede tener mas de 8 dígitos y una letra", p.matcher(dni).matches());
		
		return new Persona(nombre, apellidos, dni, fechaNacimiento);
	}
	
	//Representación como cadena
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
}
