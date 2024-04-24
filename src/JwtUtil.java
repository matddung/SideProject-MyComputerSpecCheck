import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.security.Key;

public class JwtUtil {

    private Key key; // 토큰 서명에 사용되는 키. 실제 사용 시 안전하게 관리되어야 합니다.

    public JwtUtil(Key key) {
        this.key = key;
    }

    public String getAccountFromToken(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        // 'account'라는 클레임에서 계정 정보를 가져옵니다.
        return claimsJws.getBody().get("account", String.class);
    }
}
