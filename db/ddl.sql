CREATE TABLE DB.DIRETORES (
  ID NUMBER(10) PRIMARY KEY,
  NOME VARCHAR(100) NOT NULL
);/*

CREATE TABLE FILMES (
  ID NUMBER(10) PRIMARY KEY,
  TITULO VARCHAR(100) NOT NULL,
  DIRETOR_ID NUMBER(10) NOT NULL
);

CREATE TABLE COPIAS (
  ID NUMBER(10) PRIMARY KEY,
  FILME_ID NUMBER(10) NOT NULL
);

CREATE TABLE LOCACOES (
  ID NUMBER(10) PRIMARY KEY,
  COPIA_ID NUMBER(10) NOT NULL,
  LOCADOR_ID NUMBER(10) NOT NULL, -- USUARIO
  DATA_LOCACAO DATE NOT NULL,
  DATA_PREVISTA_DEVOLUCAO DATE NOT NULL,
  DATA_DEVOLUCAO DATE
);

CREATE TABLE USUARIOS (
  ID NUMBER(10) PRIMARY KEY,
  NOME VARCHAR(100) NOT NULL,
  EMAIL VARCHAR(150) NOT NULL
);*/