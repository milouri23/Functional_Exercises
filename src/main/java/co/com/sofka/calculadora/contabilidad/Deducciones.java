package co.com.sofka.calculadora.contabilidad;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deducciones {

    final static double CAJA_COMPENSACION = 0.04;

    public static Function<Double, Double> deduceCajaDeCompensacion() {
        return value -> value * CAJA_COMPENSACION;
    }

    public static Function<RespuestaSalario, RespuestaSalario> deduceAll() {
        return r -> r.toBuilder()
                .aporteCajaDeCompensacion( deduce(r.getSalarioBase(), CAJA_COMPENSACION) )
                .build();
    }

    public static double deduce(double value, double deduction) {
        return value * deduction;
    }

    public static RespuestaSalario calcularSalario(Double salarioBase) {

        return Stream.of(RespuestaSalario.builder()
                .salarioBase(salarioBase)
                .build()).map(Deducciones.deduceAll()).reduce(RespuestaSalario.builder().build(), (r1, r2) -> r2);

    }

    /*private final double aporteSaludEmpleado;
    private final double aportePensionEmpleado;
    private final double aportePensionEmpleador;
    private final double aporteRiesgosLaborales;
    private final double aporteCajaDeCompensacion;
    private final double aporteFSP;
    private final double pagoNetoEmpleado;
    private final double costoEmpleador;*/
}
