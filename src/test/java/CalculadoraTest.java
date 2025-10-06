import org.example.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    @Test
    public void deveSomarDoisValores(){
        Calculadora calc = new Calculadora();
        double valorA = 10;
        double valorB = 20;

        double resultado = calc.somar(valorA, valorB);

        Assertions.assertEquals(30, resultado);

    }

    @Test
    public void deveSubtrairDoisValores(){
        Calculadora calc = new Calculadora();
        double valorA = 40;
        double valorB = 20;

        double resultado = calc.subtracao(valorA, valorB);

        Assertions.assertEquals(20, resultado);

    }

    @Test
    public void deveDividirDoisValores(){
        Calculadora calc = new Calculadora();
        double valorA = 300;
        double valorB = 3;

        double resultado = calc.divisao(valorA, valorB);

        Assertions.assertEquals(100, resultado);

    }

    @Test
    public void deveMultiplicarDoisValores(){
        Calculadora calc = new Calculadora();
        double valorA = 10;
        double valorB = 5;

        double resultado = calc.multiplicacao(valorA, valorB);

        Assertions.assertEquals(50, resultado);

    }
}
