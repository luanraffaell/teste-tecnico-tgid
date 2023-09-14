package com.tgid.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado","Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso","Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel","Mensagem incompreensivel"),
    PARAMETRO_INVALIDO("/parametro-invalido","Parametro inválido"),
    DADOS_INVALIDOS("/dados-invalidos","Dados inválidos"),
    ERRO_DE_SISTEMA("/erro-de-sistema","Erro interno");

    private String title;
    private String uri;
    private ProblemType(String path,String title) {
        this.uri = "localhost:8080"+path;
        this.title = title;
    }
}
