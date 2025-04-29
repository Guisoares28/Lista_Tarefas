CREATE TABLE tb_users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE tb_task (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(500) NOT NULL,
    status BOOLEAN DEFAULT FALSE,
    usuario_id BIGINT,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES tb_users(id) ON DELETE CASCADE
);
