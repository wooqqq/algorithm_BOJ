import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int n) {
        BigInteger rs = new Fibonacci().calc(n);
        rs = rs.mod(BigInteger.valueOf(1234567));
        
        return rs.intValue();
    }
}

class Fibonacci {
    Map<Integer, BigInteger> cache = new HashMap<>();
    
    BigInteger calc(int n) {
        if (cache.containsKey(n)) return cache.get(n);
        
        BigInteger rs;
        
        if (n <= 1) {
            rs = BigInteger.valueOf(n);
        } else {
            BigInteger calcOfNMinus2 = calc(n - 2);
            BigInteger calcOfNMinus1 = calc(n - 1);
            
            rs = calcOfNMinus2.add(calcOfNMinus1);
        }
        
        cache.put(n, rs);
        
        return rs;
    }
}