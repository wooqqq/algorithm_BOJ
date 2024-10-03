class Solution {
    int[][] arr;
    String answer = null;
    StringBuilder sb;
    
    int endRow, endCol;
    int arrRow, arrCol;
    
    char[] dir = {'d', 'l', 'r', 'u'};
    int[] dr = {1, 0, 0, -1};
    int[] dc = {0, -1, 1, 0};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        sb = new StringBuilder();
        arr = new int[n][m];
        
        endRow = r;
        endCol = c;
        arrRow = n;
        arrCol = m;
        
        // 최단거리 계산 - 거리 k로 갈 수 있는지 여부
        int length = distance(x, y, r, c);
        if ((k - length) % 2 == 1 || k < length) return "impossible";
        dfs(x, y, 0, k);
        
        return answer == null ? "impossible" : answer;
    }
    
    private int distance(int x, int y, int r, int c) {
        return (int) Math.abs(x - r) + (int) Math.abs(y - c);
    }
    
    private void dfs(int r, int c, int depth, int k) {
        if (answer != null) return;
        if (depth + distance(r, c, endRow, endCol) > k) return; // 현재 깊이 + 남은 거리 > k
        
        if (k == depth) {
            answer = sb.toString();
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int nextRow = r + dr[i];
            int nextCol = c + dc[i];
            
            if (nextRow <= arrRow && nextCol <= arrCol && nextRow > 0 && nextCol > 0) {
                sb.append(dir[i]);
                dfs(nextRow, nextCol, depth + 1, k);
                sb.delete(depth, depth + 1);
            }
        }
    }
}