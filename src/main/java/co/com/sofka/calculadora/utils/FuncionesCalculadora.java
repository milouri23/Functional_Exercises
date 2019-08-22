package co.com.sofka.calculadora.utils;

import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FuncionesCalculadora {

    public static final Function<List<Integer>,Mono<Integer> > sumar = lista ->
            Mono.just(
            lista.stream().
            reduce(0,
                    (a, b) -> a + b)
            );

    public static final Function<List<Integer>,Mono<Integer> > restar =  lista ->
            Mono.just(
            lista.stream().
            reduce(0,
                    (a, b) -> b - a)
            ).flatMap(x -> Mono.just(x * (-1)));

    public static final Function<? super Integer, Mono<String>> getTablaMultiplicar = factor -> {
       StringBuilder tablaFinal = new StringBuilder(90);
       IntStream.rangeClosed(0, 9)
               .forEach(
                       digito -> tablaFinal.append(factor + " x " + digito + " = " + factor*digito + "<br/>")
               );
       return Mono.just(tablaFinal.toString());
    };

    public static final Function< List<Integer>, Mono<String> > getTablaAmortizacion = lista -> {

        final Double INTERES = 0.01;
        final String[] ENCABEZADOS = new String[] {
                "Periodo",
                "Interes",
                "Amortización del capital",
                "Cuota",
                "Capital pendiente"
        };
        final String OPENING_TABLE_TAG_HTML = "<table border = '1px'>";
        final String CLOSING_TABLE_TAG_HTML = "</table>";

        final Integer numberOfFees = lista.get(0);
        final Integer creditAmount = lista.get(1);

        final String FIRST_ROW =
                "<tr>" +
                    "<td>0</td>" +
                    "<td></td>" +
                    "<td></td>" +
                    "<td></td>" +
                    "<td>$"+creditAmount+"</td>" +
                "</tr>";

        final Double amortizacionDelCapital = (double) creditAmount / numberOfFees;

        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append(OPENING_TABLE_TAG_HTML);
        tableBuilder.append("<tr>");
        Arrays.stream(ENCABEZADOS).forEach( encabezado ->
                tableBuilder.append("<th>").append(encabezado).append("</th>")
        );
        tableBuilder.append("</tr>");
        tableBuilder.append(FIRST_ROW);
        IntStream.rangeClosed(1, numberOfFees).forEach(periodo ->
                tableBuilder.append("<tr>")
                    .append("<td>").append(periodo).append("</td>")
                    .append("<td>$").append(Double.toString((creditAmount - amortizacionDelCapital * (periodo - 1)) * INTERES)).append("</td>")
                    .append("<td>$").append(amortizacionDelCapital).append("</td>")
                    .append("<td>$").append(Double.toString((creditAmount - amortizacionDelCapital * (periodo - 1)) * INTERES + amortizacionDelCapital)).append("</td>")
                    .append("<td>$").append(Double.toString(creditAmount - amortizacionDelCapital * periodo)).append("</td>")
                .append("</tr>")
        );
        tableBuilder.append(CLOSING_TABLE_TAG_HTML);

        return Mono.just(tableBuilder.toString());
    };

}
