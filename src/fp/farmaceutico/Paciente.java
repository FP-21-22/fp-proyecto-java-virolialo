package fp.farmaceutico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import fp.clinico.Persona;
import fp.utiles.Checkers;

public record Paciente(Persona persona, String codigoIngreso, LocalDateTime fechaYHoraIngreso) {

	//Propiedad derivada
	public LocalDate fechaIngreso() {
		LocalDate fecha = fechaYHoraIngreso.toLocalDate();
		return fecha;
	}
	
	//Propiedad derivada
	public String horaIngreso() {
		String hora = fechaYHoraIngreso.toLocalTime().toString();
		return hora;
	}
	
	//Criterio de igualdad
	@Override
	public int hashCode() {
		return Objects.hash(codigoIngreso, fechaYHoraIngreso, persona);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(codigoIngreso, other.codigoIngreso)
				&& Objects.equals(fechaYHoraIngreso, other.fechaYHoraIngreso) && Objects.equals(persona, other.persona);
	}
	
	//Método static of
	public static Paciente of(Persona persona, String codigoIngreso, LocalDateTime fechaYHoraIngreso) {
		Paciente res = new Paciente(persona, codigoIngreso, fechaYHoraIngreso);
		
		//Restricciones
		Checkers.check("La fecha y hora de ingreso debe ser anterior o igual a la fecha actual", fechaYHoraIngreso.toLocalDate().isBefore(LocalDate.now()) && fechaYHoraIngreso.toLocalTime().isBefore(LocalTime.now()));
		return res;
	}
	
	//Método static of
	public static Paciente of(String nombre, String apellidos, String dni, LocalDate fechaNacimiento,
			String codigoIngreso, LocalDateTime fechaYHoraIngreso) {
		Persona p = Persona.of(nombre, apellidos, dni, fechaNacimiento);
		Paciente res = new Paciente(p,  codigoIngreso, fechaYHoraIngreso);
		Checkers.check("La fecha y hora de ingreso debe ser anterior o igual a la fecha actual", fechaYHoraIngreso.toLocalDate().isBefore(LocalDate.now()) && fechaYHoraIngreso.toLocalTime().isBefore(LocalTime.now()));
		return res;
	}
	
	//Representacion como cadena
	public String toString() {
		return "Paciente [persona=" + persona + ", codigoIngreso=" + codigoIngreso + ", fechaYHoraIngreso=" + fechaYHoraIngreso + "]";
	}
	
}
