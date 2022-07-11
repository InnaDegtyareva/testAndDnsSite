import java.util.Arrays;

public class TestE {
    //    Первая задача.
//    Определение минимальной пары чисел, которые при делении дают число с периодом после запятой.
//    Число с периодом формируется из введенных значений.
//    Первое - до запятой, второе - после.
//   https://www.webmath.ru/poleznoe/formules_12_18.php см. "Чтобы обратить чистую периодическую дробь в обыкновенную"
//    На вход передается два числа: 0 и 6
//    имеется ввиду число 0,(6)
//    на выходе пара чисел 2 и 3
    public static int[] periodicFractionCalculation(int first, int second) {
        int[] sum = new int[2];
        String number = String.valueOf(second);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            stringBuilder.append("9");
        }
        int resultNumber = Integer.parseInt(stringBuilder.toString());
        for (int i = 1; i <= second; i++) {
            if (second % i == 0 && resultNumber % i == 0) {
                second = second / i;
                resultNumber = resultNumber / i;
            }
        }
        if (first == 0) {
            sum[0] = second;
            sum[1] = resultNumber;
            return sum;
        } else {
            sum[0] = first * resultNumber + second;
            sum[1] = resultNumber;
            return sum;
        }
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(periodicFractionCalculation(0, 3)));
        System.out.println(Arrays.toString(periodicFractionCalculation(1, 6)));
        System.out.println(Arrays.toString(periodicFractionCalculation(1, 481)));
    }
}
