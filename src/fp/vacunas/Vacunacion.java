package fp.vacunas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import fp.utiles.Checkers;

public record Vacunacion(LocalDate fecha, String comunidad, Integer pfizer,
		Integer moderna, Integer astrazeneca, Integer jassen, Integer numeroPersonas) implements Comparable<Vacunacion> {
	
	// Propiedad derivada:
	public Integer numeroTotal() {
		Integer suma = this.pfizer + this.astrazeneca + this.jassen + this.moderna;
		return suma;
	}

	//Método static of:
	public static Vacunacion of(LocalDate fecha, String comunidad, Integer pfizer,
			Integer moderna, Integer astrazeneca, Integer jassen, Integer numeroPersonas) {
		Vacunacion res = new Vacunacion(fecha, comunidad, pfizer, moderna, astrazeneca, jassen, numeroPersonas);
		Checkers.check("La fecha debe ser posterior al 01/02/2021", fecha.isAfter(LocalDate.parse("01/02/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
		return res;
	}
	
	//Criterio de igualdad:
	@Override
	public int hashCode() {
		return Objects.hash(astrazeneca, comunidad, fecha, jassen, moderna, numeroPersonas, pfizer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vacunacion other = (Vacunacion) obj;
		return Objects.equals(astrazeneca, other.astrazeneca) && Objects.equals(comunidad, other.comunidad)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(jassen, other.jassen)
				&& Objects.equals(moderna, other.moderna) && Objects.equals(numeroPersonas, other.numeroPersonas)
				&& Objects.equals(pfizer, other.pfizer);
	}
	
	//Método static parse:
	public static Vacunacion parsearVacunacion(String cadena) {
		String[] trozos = cadena.split(";");
		Checkers.check("La cadena no está bien troceada para introducir los datos", trozos.length == 7);
		
		LocalDate fecha = LocalDate.parse(trozos[0].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String comunidad = trozos[1].trim();
		Integer pfizer = Integer.valueOf(trozos[2].trim());
		Integer moderna = Integer.valueOf(trozos[3].trim());
		Integer astrazeneca = Integer.valueOf(trozos[4].trim());
		Integer jassen = Integer.valueOf(trozos[5].trim());
		Integer numeroPersonas = Integer.valueOf(trozos[6].trim());
		
		Vacunacion res = new Vacunacion(fecha, comunidad, pfizer, moderna, astrazeneca, jassen, numeroPersonas);
		return res;
	}
	
	//Representación como cadena
	public String toString() {
		return "Vacunacion [fecha=" + fecha + ", comunidad=" + comunidad + ", pfizer=" + pfizer + ", moderna=" + moderna + ", astrazeneca=" + astrazeneca + ", jassen=" + jassen + ", numeroPersonas=" + numeroPersonas + "]";
	}

	@Override
	public int compareTo(Vacunacion o) {
		if (comunidad.compareTo(o.comunidad)==0) {
			return fecha.compareTo(o.fecha);
		}
		return comunidad.compareTo(o.comunidad);
	}
	
	//Orden natural
}
