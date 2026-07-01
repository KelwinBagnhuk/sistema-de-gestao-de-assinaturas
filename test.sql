

-- CLIENTES (3 clientes, para termos grupos diferentes na Consulta 2)
INSERT INTO cliente (nome, email, cpf, telefone) VALUES
('Ana Beatriz Souza',  'ana.souza@email.com',   '11111111111', '47999990001'),
('Bruno Carvalho',     'bruno.carv@email.com',  '22222222222', '47999990002'),
('Carla Nogueira',     'carla.nog@email.com',   '33333333333', '47999990003');

INSERT INTO plano (nome, descricao, valor, periodicidade) VALUES
('Basico',  'Plano de entrada, funcionalidades essenciais',   29.90, 'mensal'),
('Premium', 'Plano completo com todos os servicos liberados', 79.90, 'mensal');

INSERT INTO servico (nome, descricao) VALUES
('Streaming de Video', 'Acesso a catalogo de filmes e series'),
('Armazenamento em Nuvem', 'Espaco de armazenamento de arquivos');


-- PLANO_SERVICO
-- Basico  -> so Streaming
-- Premium -> Streaming + Nuvem
INSERT INTO plano_servico (id_plano, id_servico, limite_uso) VALUES
(1, 1, 'ilimitado'),
(2, 1, 'ilimitado'),
(2, 2, '500GB');


-- ASSINATURAS
-- Ana    -> Basico  (ativa)
-- Bruno  -> Premium (ativa)
-- Carla  -> Premium (cancelada, com data_fim)
INSERT INTO assinatura (id_cliente, id_plano, data_inicio, data_fim, status) VALUES
(1, 1, '2026-01-10', NULL,         'ativa'),
(2, 2, '2026-02-01', NULL,         'ativa'),
(3, 2, '2025-11-01', '2026-04-01', 'cancelada');

-- ------------------------------------------------------------
-- PAGAMENTOS
-- Ana (assinatura 1)   -> 2 pagamentos de 29.90  (total: 59.80)
-- Bruno (assinatura 2) -> 3 pagamentos de 79.90  (total: 239.70)
-- Carla (assinatura 3) -> 1 pagamento de 79.90   (total: 79.90)
--
-- Media geral = (29.90*2 + 79.90*3 + 79.90) / 6 = (59.80+239.70+79.90)/6 = 379.40/6 = ~63.23
-- Logo, na CONSULTA 2 devem aparecer: Bruno (239.70) e Carla (79.90) acima da media.
-- Ana (59.80) fica abaixo e nao aparece.
-- ------------------------------------------------------------
INSERT INTO pagamento (id_assinatura, data_vencimento, data_pagamento, mes_referencia, metodo, valor_pago, status) VALUES
(1, '2026-01-10', '2026-01-10', '2026-01-01', 'pix',             29.90, 'pago'),
(1, '2026-02-10', '2026-02-11', '2026-02-01', 'pix',             29.90, 'pago'),

(2, '2026-02-01', '2026-02-01', '2026-02-01', 'cartao_credito',  79.90, 'pago'),
(2, '2026-03-01', '2026-03-02', '2026-03-01', 'cartao_credito',  79.90, 'pago'),
(2, '2026-04-01', NULL,         '2026-04-01', 'cartao_credito',  79.90, 'pendente'),

(3, '2025-12-01', '2025-12-01', '2025-12-01', 'boleto',          79.90, 'pago');
