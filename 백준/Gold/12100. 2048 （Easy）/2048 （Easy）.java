import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map; // 원본 배열
    static int[] swipeArr; // 백트래킹에서 swipe 명령 조합할 배열
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        // 원본 배열에 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 백트래킹에서 swipe 명령 조합할 배열 선언
        swipeArr = new int[5];

        // 백트래킹 시작
        bt(0);

        System.out.println(max);
    }

    static void bt(int depth) {
        // 명령을 5번 수행한 뒤 종료
        if (depth == 5) {
            int[][] newMap = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    newMap[i][j] = map[i][j];
                }
            }

            // 조합된 명령을 순서대로 수행하며 배열 갱신
            for (int i = 0; i < 5; i++) {
                int s = swipeArr[i];
                newMap = swipeAll(s, newMap);
            }

            // 모든 명령이 종료된 후 가장 큰 블록값 구하기
            int num = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    num = Math.max(num, newMap[i][j]);
                }
            }

            max = Math.max(max, num);

            return;
        }

        // 상, 하, 좌, 우 스와이프 조합 구하기
        for (int i = 0; i < 4; i++) {
            swipeArr[depth] = i;
            bt(depth + 1);
        }
    }

    static int[][] swipeAll(int s, int[][] newMap) {
        switch (s) {
            // 상
            case 0:
                for (int i = 0; i < N; i++) {
                    int idx = 0; // 값을 넣을 위치
                    int block = 0; // 최근 블록의 값
                    for (int j = 0; j < N; j++) {
                        // 현재 블록의 값이 0이 아니라면
                        if (newMap[j][i] != 0) {
                            // 최근 블록의 값과 현재 블록의 값이 같다면
                            if (block == newMap[j][i]) {
                                // 블록이 합쳐진다
                                newMap[idx - 1][i] = block * 2;
                                // 블록이 합쳐졌으므로 0으로 갱신
                                block = 0;
                                // 값을 합쳤으므로 현재 블록의 값을 0으로 갱신
                                newMap[j][i] = 0;
                            } else { // 최근 블록의 값과 현재 블록의 값이 다르다면
                                // 블록 변수의 값을 현재 블록의 값으로 갱신
                                block = newMap[j][i];
                                // 현재 블록의 값을 0으로 바꿔줌
                                newMap[j][i] = 0;
                                // 값을 넣을 위치에 최근 블록의 값을 넣음
                                newMap[idx][i] = block;
                                // 값을 넣을 위치 + 1
                                idx++;
                            }
                        }
                    }
                }
                break;
            // 하
            case 1:
                for (int i = 0; i < N; i++) {
                    int idx = N - 1;
                    int block = 0;
                    for (int j = N - 1; j >= 0; j--) {
                        if (newMap[j][i] != 0) {
                            if (block == newMap[j][i]) {
                                newMap[idx + 1][i] = block * 2;
                                block = 0;
                                newMap[j][i] = 0;
                            } else {
                                block = newMap[j][i];
                                newMap[j][i] = 0;
                                newMap[idx][i] = block;
                                idx--;
                            }
                        }
                    }
                }
                break;
            // 좌
            case 2:
                for (int i = 0; i < N; i++) {
                    int idx = 0;
                    int block = 0;
                    for (int j = 0; j < N; j++) {
                        if (newMap[i][j] != 0) {
                            if (block == newMap[i][j]) {
                                newMap[i][idx - 1] = block * 2;
                                block = 0;
                                newMap[i][j] = 0;
                            } else {
                                block = newMap[i][j];
                                newMap[i][j] = 0;
                                newMap[i][idx] = block;
                                idx++;
                            }
                        }
                    }
                }
                break;
            // 우
            case 3:
                for (int i = 0; i < N; i++) {
                    int idx = N - 1;
                    int block = 0;
                    for (int j = N - 1; j >= 0; j--) {
                        if (newMap[i][j] != 0) {
                            if (block == newMap[i][j]) {
                                newMap[i][idx + 1] = block * 2;
                                block = 0;
                                newMap[i][j] = 0;
                            } else {
                                block = newMap[i][j];
                                newMap[i][j] = 0;
                                newMap[i][idx] = block;
                                idx--;
                            }
                        }
                    }
                }
                break;
        }
        return newMap;
    }
}