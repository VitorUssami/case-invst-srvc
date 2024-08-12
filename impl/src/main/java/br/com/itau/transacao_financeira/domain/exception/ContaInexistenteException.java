package br.com.itau.transacao_financeira.domain.exception;

public class ContaInexistenteException extends RuntimeException{
    private static final long serialVersionUID = 106585775146446440L;

    public ContaInexistenteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContaInexistenteException(String message) {
        super(message);
    }

    public ContaInexistenteException(Throwable cause) {
        super(cause);
    }
}
