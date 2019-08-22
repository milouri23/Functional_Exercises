package co.com.sofka.calculadora.contabilidad;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeduccionesTest {

    @Test
    public void deduceTest() {
        double value = 100;
        double deduction = 0.04;
        double result = 4;

        Assert.assertEquals(result, Deducciones.deduce(value, deduction), 0);
    }

    @Test
    public void resultContabilityTest() {
        double salarioBase = 1000000;
        final RespuestaSalario response = RespuestaSalario.builder()
                        .salarioBase(salarioBase)
                        .aporteCajaDeCompensacion(new Double(40000))
                        .build();

        Assert.assertEquals(response.getAporteCajaDeCompensacion(), Deducciones.calcularSalario(salarioBase).getAporteCajaDeCompensacion(), 0);
    }

}