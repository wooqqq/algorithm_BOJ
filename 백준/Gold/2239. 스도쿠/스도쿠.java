import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int[][] sudoku = new int[9][9];
    static boolean[][][] used = new boolean[3][9][10];  // 행, 열, 3*3칸 숫자 사용 여부 배열
    static boolean end = false; // 탐색 종료 확인 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 스도쿠 정보 저장
        for (int i = 0; i < 9; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Character.getNumericValue(str.charAt(j));
                if (sudoku[i][j] != 0) {
                    used[0][i][sudoku[i][j]] = true;    // 행 숫자 사용
                    used[1][j][sudoku[i][j]] = true;    // 열 숫자 사용
                    used[2][(i/3) * 3 + j/3][sudoku[i][j]] = true;  // 3*3칸 숫자 사용
                }
            }
        }

        // 2. 스도쿠 채우기
        search(0, 0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                bw.write(sudoku[i][j] + "");
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void search(int x, int y) {
        // 스도쿠 보드 마지막 칸일 때
        if (x == 8 && y == 8) {
            for (int i = 1; i <= 9; i++) {
                if (!used[0][8][i]) {
                    sudoku[8][8] = i;
                    break;
                }
            }
            end = true;
            return;
        }

        if (sudoku[y][x] != 0) {
            if (x + 1 == 9)
                search(0, y + 1);
            else
                search(x + 1, y);
        } else {
            for (int i = 1; i <= 9; i++) {
                if (!used[0][y][i] && !used[1][x][i] && !used[2][(y/3)*3 + x/3][i]) {
                    used[0][y][i] = true;
                    used[1][x][i] = true;
                    used[2][(y/3) * 3 + x/3][i] = true;
                    sudoku[y][x] = i;
                    if (x + 1 == 9)
                        search(0, y + 1);
                    else
                        search(x + 1, y);
                    if (end) return;
                    sudoku[y][x] = 0;
                    used[0][y][i] = false;
                    used[1][x][i] = false;
                    used[2][(y/3) * 3 + x/3][i] = false;
                }
            }
        }
    }
}