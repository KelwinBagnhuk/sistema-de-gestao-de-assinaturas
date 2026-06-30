
-- script para criar o banco


-- Remover tabelas na ordem correta (dependentes primeiro)
DROP TABLE IF EXISTS pagamento      CASCADE;
DROP TABLE IF EXISTS plano_servico  CASCADE;
DROP TABLE IF EXISTS assinatura     CASCADE;
DROP TABLE IF EXISTS servico        CASCADE;
DROP TABLE IF EXISTS plano          CASCADE;
DROP TABLE IF EXISTS cliente        CASCADE;

-- ------------------------------------------------------------
CREATE TABLE cliente (
    id_cliente SERIAL       PRIMARY KEY,
    nome       VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    cpf        CHAR(11)     NOT NULL UNIQUE,
    telefone   VARCHAR(20)  NOT NULL
);

-- ------------------------------------------------------------
CREATE TABLE plano (
    id_plano      SERIAL       PRIMARY KEY,
    nome          VARCHAR(100) NOT NULL,
    descricao     TEXT,
    valor         NUMERIC(10,2) NOT NULL,
    periodicidade VARCHAR(20)  NOT NULL DEFAULT 'mensal'
    -- mensal, trimestral, semestral, anual
);

-- ------------------------------------------------------------
CREATE TABLE servico (
    id_servico SERIAL       PRIMARY KEY,
    nome       VARCHAR(100) NOT NULL,
    descricao  TEXT
);

-- ------------------------------------------------------------
CREATE TABLE plano_servico (
    id_plano   INT NOT NULL,
    id_servico INT NOT NULL,
    limite_uso VARCHAR(50) DEFAULT 'ilimitado',
    PRIMARY KEY (id_plano, id_servico)

    -- chaves estrangeiras
    FOREIGN KEY (id_plano) REFERENCES plano(id_plano) ON DELETE CASCADE,
    FOREIGN KEY (id_servico) REFERENCES servico(id_servico) ON DELETE CASCADE
);

-- ------------------------------------------------------------
CREATE TABLE assinatura (
    id_assinatura SERIAL      PRIMARY KEY,
    id_cliente    INT         NOT NULL,
    id_plano      INT         NOT NULL,
    data_inicio   DATE        NOT NULL,
    data_fim      DATE,       -- NULL = sem data de encerramento
    status        VARCHAR(20) NOT NULL DEFAULT 'ativa'

    -- chaves estrangeiras
    FOREIGN KEY (id_plano) REFERENCES plano(id_plano) ON DELETE CASCADE,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE
);

-- ------------------------------------------------------------
CREATE TABLE pagamento (
    id_pagamento    SERIAL        PRIMARY KEY,
    id_assinatura   INT           NOT NULL,
    data_vencimento DATE          NOT NULL,
    data_pagamento  DATE,         -- NULL quando ainda não pago
    mes_referencia  DATE          NOT NULL,  
    metodo          VARCHAR(30)   NOT NULL,  -- cartao_credito, boleto, pix, debito_automatico
    valor_pago      NUMERIC(10,2) NOT NULL,
    status          VARCHAR(20)   NOT NULL DEFAULT 'pendente'
    -- pago, pendente, atrasado

    FOREIGN KEY (id_assinatura) REFERENCES assinatura(id_assinatura) ON DELETE CASCADE
);
