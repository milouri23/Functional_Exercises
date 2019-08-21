package co.com.sofka.calculadora.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


import java.util.Arrays;

import static co.com.sofka.calculadora.utils.FuncionesCalculadora.*;

@RestController
@RequestMapping(value = "/calculadora")
@RequiredArgsConstructor
public class CaluladoraServices {

    @GetMapping(value = "/suma")
    public Mono<Integer> sumaDosNumeros (Integer a, Integer b) {
        return Mono.just(Arrays.asList(a,b)).flatMap(sumar);
    }

    @GetMapping(value = "/resta")
    public Mono<Integer> restaDosNumeros (Integer a, Integer b) {
        return Mono.just(Arrays.asList(a,b)).flatMap(restar);
    }

    @GetMapping(value = "/tabla_del")
    public Mono<String> tablaMultiplicar (Integer factor) {
        return Mono.just(factor).flatMap(getTablaMultiplicar);
    }

    @GetMapping(value = "/amortizacion")
    public Mono<String> tablaAmortizacion (Integer numberOfFees, Integer creditAmount) {
        return Mono.just(Arrays.asList(numberOfFees, creditAmount)).flatMap(getTablaAmortizacion);
    }




}
