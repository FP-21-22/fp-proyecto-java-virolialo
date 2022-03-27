package pruebas;

import java.time.LocalDate;

import fp.vacunas.Vacunacion;

public class PruebasVacunas {

	public static void main(String[] args) {
		Vacunacion v = Vacunacion.of(LocalDate.now(), "Sevilla", 0, 0, 0, 0, 2000);
		System.out.println(v);
		
		Vacunacion v2 = Vacunacion.parsearVacunacion("02/09/2022;Malaga;0;1;2;3;4");
		System.out.println(v2);
	}
}
