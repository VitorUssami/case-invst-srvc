package br.com.itau.transacao_financeira.domain.exception;

public class SaldoinsuficienteException extends RuntimeException {
    private static final long serialVersionUID = 7618724043127649660L;

    public SaldoinsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaldoinsuficienteException(String message) {
        super(message);
    }

    public SaldoinsuficienteException(Throwable cause) {
        super(cause);
    }
}
