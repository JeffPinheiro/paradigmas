import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static calibracao.Calibracao.valorCalibracao;


public class Main {
    public static void main(String[] args) throws IOException {

        long tempoInicial = System.currentTimeMillis();

        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\resources\\new_calibration_text.txt");
        List<String> calibrations = Files.readAllLines(path);

        AtomicInteger soma = new AtomicInteger();
        AtomicInteger count = new AtomicInteger();

        ExecutorService poolDeThreads = Executors.newFixedThreadPool(2);
        poolDeThreads.execute(() -> {

        for (String line: calibrations) {
            soma.addAndGet(valorCalibracao(line));
            count.getAndIncrement();

        }


        System.out.println("A soma dos valores é: " + soma);
        System.out.println("Total de linhas: " + count);

        long tempoFinal = System.currentTimeMillis();

        System.out.printf("%.3f ms%n", (tempoFinal - tempoInicial) / 1000d);

        poolDeThreads.shutdown();
    });
}}
