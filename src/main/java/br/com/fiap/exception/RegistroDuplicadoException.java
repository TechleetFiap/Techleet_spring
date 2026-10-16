package br.com.fiap.exception;

public class RegistroDuplicadoException extends RegraNegocioException {

    public RegistroDuplicadoException(String mensagem) {
        super(mensagem);
    }
}
