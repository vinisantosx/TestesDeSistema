import org.example.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    private Calculadora calc;

    @BeforeEach
    public void setUp(){
        calc = new Calculadora();
    }

    @Test
    public  void deveSomarDoisValores(){
        //AAA
        //A  = Arrange = Preparação da massa
        double valorA = 10;
        double valorB = 20;

        //A = Act = Açõa / Fazer
        double resultado = calc.somar(valorA, valorB);

        //A = Assert = Verificação
        Assertions.assertEquals(30, resultado);

    }

    @Test
    public void deveSubtrairDoisValores(){
        double valorA = 40;
        double valorB = 20;

        double resultado = calc.subtracao(valorA, valorB);

        Assertions.assertEquals(20, resultado);

    }

    @Test
    public void deveDividirDoisValores(){

        double valorA = 300;
        double valorB = 3;

        double resultado = calc.divisao(valorA, valorB);

        Assertions.assertEquals(100, resultado);

    }

    @Test
    public void deveMultiplicarDoisValores(){

        double valorA = 10;
        double valorB = 5;

        double resultado = calc.multiplicacao(valorA, valorB);

        Assertions.assertEquals(50, resultado);

    }
}
