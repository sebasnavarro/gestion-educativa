package com.azulita.springboot.backend.gestioneducativa.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpAIBAAKCAQEAs+x9ylmCIdw9xt6Nt7Z/IHC8RzawGJpba2bRtzodOTxtDToN\r\n" + 
			"fyB0BecmgvHq/XRSi0qDYDhd8IqbKHmJ3xwxVgbq67/Pm3mqB84yVZvxP1HqeFlO\r\n" + 
			"0X3qeyP6mZVU7hQ4DGVGEO5lsMt8jYXAJoRYIUx6XkvPAh7eAJSWfUBCWLa7H+Vl\r\n" + 
			"UAvDytxYOJBCK8YsvssLhutg9dhVmO1k8tinG9fjx+N/TKGN+KWajevb1iT9QMsn\r\n" + 
			"b/jyYEFHNkzaTmmwAAGko2eHuYZtN4BJYIjDgP/dB4Kvl2DuR6YgLNoSNNGoaKA7\r\n" + 
			"k44xDBY+LEwnDXKj9FvwhOlIyJglC0XKgniDWQIDAQABAoIBAGLiDFgTTVOyqTkM\r\n" + 
			"wzI+WCvKCY/R3IIJA0lJnuRmSf1U2lI0n4pImbtMyHua3R0iuuLWttFA6U/zvUZH\r\n" + 
			"VaCK7Aptjtq19jFqcioMBtnJ9JJqQMSXwJud6ip3IZka2yxlbFBTsGhJvRWnH/Lf\r\n" + 
			"mTUR8yWJ1I7SdxihQj1rbDG2CUCbcHt5xzz9iGTvco2NheEQ1ZcqnXP4d+3jTych\r\n" + 
			"JUx6+0j5DIb0B0PvHsX+Y8ruyXLmGWwgL2IaJiSgHl57o58P1qcnXxq/UkjroxaA\r\n" + 
			"uaOZKsC+Ke7lLt9VDbOhuRl/jSeN7VuT6JdLS6DPmhk0xQIDDx0T6H0uIdsz+Q5t\r\n" + 
			"TuNwRAkCgYEA42t111S4MXXr2gZfD93xGsZ5V+6Ip5WEAGSF1etLSk5RJYqv6kcJ\r\n" + 
			"ckKGxMwsDXmSdir3hReFW0Shkm1qNKvVLHdB/kLLbcDee5L2NUyp46t8FFe6gcZs\r\n" + 
			"ZWOpO8MKnYO+GvxupiBAi+mwOWApIJCfQZirxIfS1+AX8QLo/G0XRZsCgYEAyoj+\r\n" + 
			"FaGRFbRd2nzZc1zIEqqh0ENSrQybmZrO3wGi/+Sm5rlEXl1w2vQh+mE5eHY/glX3\r\n" + 
			"1E9B9qutjxFW9/MnveT2P5Kk2cKKJiNeMgMpgE8h/kxssTBL6F5er+E6F81inERI\r\n" + 
			"aX0cz0DEeMIxvIj3BxidLvrzc0XLue+rFAuFRBsCgYEAkt8ywrdXIbJLeIoG2MN4\r\n" + 
			"U9d8RHqrzLc2Ad7pxGbqP/nmVD2GMOJhymYGjZBinu011pUXN+Ah4vim9F8vaQbU\r\n" + 
			"bJw0hZXztVULGnmmAnQZgAye3LBvpiJUnQJJKGxlLacInnsKpsVLilCI2/UrenmM\r\n" + 
			"MJv9yrYBSCPUsnUzrNpzXiMCgYEArgH42rjPH+iw4fB2GslSE8eJ8paEx6yXWudI\r\n" + 
			"aQzM8DGQu5XdCX4aYeNx9d3mNHrTobUekcfuknF5UfvBzpGYSWwXrRLek0Cy1IV6\r\n" + 
			"fll9JIHYYNVkZO9rqB68FzvYX27UFBIObKNTRzUMF/wwIx1Br1UUZrKwyw7iM8OY\r\n" + 
			"JkannpUCgYBc/BwaW/bD7YUkPJX7TxaB8xqXUgEKVnyVHlU1cp8nsaiwDJtICouc\r\n" + 
			"fDZPeiN7DpP76pgX+WjbAdG8thLoE6cIc9Wi2CuRlFRD4tqGRZjRZLniYEyAMJW0\r\n" + 
			"Hhq7ObiHtcNsmpeIMo0R9gOJ+bI6XBIR+MV/62Eprfp65i8uAO6g7A==\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA ="-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs+x9ylmCIdw9xt6Nt7Z/\r\n" + 
			"IHC8RzawGJpba2bRtzodOTxtDToNfyB0BecmgvHq/XRSi0qDYDhd8IqbKHmJ3xwx\r\n" + 
			"Vgbq67/Pm3mqB84yVZvxP1HqeFlO0X3qeyP6mZVU7hQ4DGVGEO5lsMt8jYXAJoRY\r\n" + 
			"IUx6XkvPAh7eAJSWfUBCWLa7H+VlUAvDytxYOJBCK8YsvssLhutg9dhVmO1k8tin\r\n" + 
			"G9fjx+N/TKGN+KWajevb1iT9QMsnb/jyYEFHNkzaTmmwAAGko2eHuYZtN4BJYIjD\r\n" + 
			"gP/dB4Kvl2DuR6YgLNoSNNGoaKA7k44xDBY+LEwnDXKj9FvwhOlIyJglC0XKgniD\r\n" + 
			"WQIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
	
}
