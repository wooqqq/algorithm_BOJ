import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        double sum = 0.0;
        int cnt = 0;

        for (int i = 0; i < str.length(); i++) {
            char grade = str.charAt(i);
            double gradeValue = 0.0;

            if (i < str.length() - 1 && str.charAt(i + 1) == '+') {
                switch (grade) {
                    case 'A':
                        gradeValue += 4.5;
                        break;
                    case 'B':
                        gradeValue += 3.5;
                        break;
                    case 'C':
                        gradeValue += 2.5;
                        break;
                    case 'D':
                        gradeValue += 1.5;
                        break;
                }
                i++;
            } else {
                switch (grade) {
                    case 'A':
                        gradeValue += 4.0;
                        break;
                    case 'B':
                        gradeValue += 3.0;
                        break;
                    case 'C':
                        gradeValue += 2.0;
                        break;
                    case 'D':
                        gradeValue += 1.0;
                        break;
                    case 'F':
                        gradeValue += 0.0;
                        break;
                }
            }

            sum += gradeValue;
            cnt++;
        }

        double average = sum / cnt;
        DecimalFormat df = new DecimalFormat("#.####");
        System.out.println(df.format(average));
    }
}