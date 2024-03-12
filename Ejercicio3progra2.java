


//Ejercicio 12

Una expresión lambda trata de  representar una instancia de una interfaz funcional. Se compone de parámetros, una flecha -> y un cuerpo.





// Ejercicio 13

import java.util.function.Function;

public class CalculadoraIntegral {

    public static double integral(double a, double b, double h, Function<Double, Double> funcion) {
        double resultado = 0.0;

        for (double i = a; i < b; i += h) {
            resultado += h * funcion.apply(i);
        }

        return resultado;
    }

    public static void main(String[] args) {
        double resultado = integral(0, 1, 0.001, x -> x * x); 
        System.out.println("Resultado de la integral: " + resultado);
    }
}








// Ejercicio 14


import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.DoubleStream;

public class GeneracionStreams {

    public static void main(String[] args) {
      
        IntStream streamOfNumbers = IntStream.of(1, 2, 3, 4, 5);

       
        IntStream rangeStream = IntStream.range(1, 6);

    
        IntStream iterateStream = IntStream.iterate(1, n -> n + 2).limit(5);

      
        DoubleStream randomStream = new Random().doubles().limit(5);

       
        streamOfNumbers.forEach(System.out::println);
        rangeStream.forEach(System.out::println);
        iterateStream.forEach(System.out::println);
        randomStream.forEach(System.out::println);
    }
}



//Ejercicio 16

import java.util.function.Function;
import java.util.stream.DoubleStream;

public class CalculadoraIntegralStreams {

    public static double integral(double a, double b, double h, Function<Double, Double> funcion) {
        return DoubleStream.iterate(a, x -> x + h).limit((long) ((b - a) / h))
                .map(funcion)
                .sum() * h;
    }

    public static void main(String[] args) {
        double resultado = integral(0, 1, 0.001, x -> x * x); 
        System.out.println("Resultado de la integral usando streams: " + resultado);
    }
}



//Ejercicio 17


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Persona {
    private String nombre;
    private LocalDate fechaDeNacimiento;

    public Persona(String nombre, LocalDate fechaDeNacimiento) {
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public long calcularEdad() {
        return LocalDate.now().getYear() - fechaDeNacimiento.getYear();
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }
}

class Personas {
    private List<Persona> listaPersonas = new ArrayList<>();

    public void annadirPersona(Persona persona) {
        listaPersonas.add(persona);
    }

    public Persona elMasJoven() {
        return listaPersonas.stream()
                .min((p1, p2) -> p1.getFechaDeNacimiento().compareTo(p2.getFechaDeNacimiento()))
                .orElse(null);
    }

    public long calcularSumaEdades() {
        return listaPersonas.stream()
                .mapToLong(Persona::calcularEdad)
                .sum();
    }

    public long calcularEdadMinima() {
        return listaPersonas.stream()
                .mapToLong(Persona::calcularEdad)
                .min()
                .orElse(0);
    }

    public double calcularMediaDeEdad() {
        return listaPersonas.stream()
                .mapToLong(Persona::calcularEdad)
                .average()
                .orElse(0.0);
    }
}

public class Main {
    public static void main(String[] args) {
        Personas personas = new Personas();
        personas.annadirPersona(new Persona("Juan", LocalDate.of(1990, 5, 15)));
        personas.annadirPersona(new Persona("Maria", LocalDate.of(1985, 8, 20)));
        personas.annadirPersona(new Persona("Pedro", LocalDate.of(2000, 3, 10)));

        System.out.println("Persona más joven: " + personas.elMasJoven().getNombre());
        System.out.println("Suma de las edades: " + personas.calcularSumaEdades());
        System.out.println("Edad mínima: " + personas.calcularEdadMinima());
        System.out.println("Media de edad: " + personas.calcularMediaDeEdad());
    }
}
