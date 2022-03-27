package pruebas;

import java.time.LocalDate;
import java.time.LocalDateTime;

import fp.clinico.Persona;
import fp.farmaceutico.Paciente;

public class PruebasClinicoFarmaceutico {

	public static void main(String[] args) {
		Persona p = new Persona("Virgilio", "Oliva Alonso", "77824267H", LocalDate.of(2022, 9, 2));
		System.out.println(p);

		Persona p1 = Persona.parsearPersona("Virgilio, Oliva Alonso, 77824267H, 02/09/2001");
		System.out.println(p1);
		
		Paciente p2 = Paciente.of(p1, "394834839", LocalDateTime.of(2021, 8, 3, 20, 5));
		System.out.println(p2);
		
		Paciente p3 = Paciente.of("Maria", "Rispa", "11223344H", LocalDate.of(2000, 12, 31), "39483948", LocalDateTime.of(1999, 2, 1, 1, 1));
		System.out.println(p3);
	}

}
