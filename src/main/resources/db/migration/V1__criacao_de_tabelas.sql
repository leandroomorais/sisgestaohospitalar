CREATE TABLE alergia (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   descricao VARCHAR(255) NULL,
   cid_id BIGINT NULL,
   CONSTRAINT pk_alergia PRIMARY KEY (id)
);

CREATE TABLE antropometria (
  id BIGINT AUTO_INCREMENT NOT NULL,
   altura DOUBLE NOT NULL,
   peso DOUBLE NOT NULL,
   imc DOUBLE NOT NULL,
   perimetro_cefalico DOUBLE NOT NULL,
   data_cadastro datetime NULL,
   atendimento_id BIGINT NULL,
   prontuario_id BIGINT NULL,
   profissional_id BIGINT NULL,
   CONSTRAINT pk_antropometria PRIMARY KEY (id)
);

CREATE TABLE arquivo_bpa_folha_consolidado (
  id_arquivobpa BIGINT NOT NULL,
   id_folha_bpa_consolidado BIGINT NOT NULL
);

CREATE TABLE arquivo_bpa_folha_individualizado (
  id_arquivobpa BIGINT NOT NULL,
   id_folha_bpa_individualizado BIGINT NOT NULL
);

CREATE TABLE arquivobpa (
  id BIGINT AUTO_INCREMENT NOT NULL,
   cabecalho_header VARCHAR(255) NULL,
   indicador_header VARCHAR(255) NULL,
   competencia VARCHAR(255) NULL,
   qtd_linhas VARCHAR(255) NULL,
   qtd_folhas VARCHAR(255) NULL,
   controle_dominio VARCHAR(255) NULL,
   orgao_responsavel VARCHAR(255) NULL,
   sigla_orgao_responsavel VARCHAR(255) NULL,
   cnpj_orgao_responsavel VARCHAR(255) NULL,
   orgao_destino VARCHAR(255) NULL,
   incador_orgao CHAR NOT NULL,
   versao_sistema VARCHAR(255) NULL,
   fim VARCHAR(255) NULL,
   data_geracao date NULL,
   hora_geracao datetime NULL,
   link VARCHAR(255) NULL,
   nome_arquivo VARCHAR(255) NULL,
   gerado BIT(1) NOT NULL,
   cnes VARCHAR(255) NULL,
   valor_total DECIMAL NULL,
   CONSTRAINT pk_arquivobpa PRIMARY KEY (id)
);

CREATE TABLE atendimento (
  id BIGINT AUTO_INCREMENT NOT NULL,
   responsavel VARCHAR(255) NULL,
   cidadao_id BIGINT NULL,
   conduta_cidadao INT NULL,
   data_entrada datetime NULL,
   status INT NULL,
   carater_atendimento INT NULL,
   tempo_observacao INT NOT NULL,
   classificacao_de_risco_id BIGINT NULL,
   profissional_destino_id BIGINT NOT NULL,
   triagem_id BIGINT NULL,
   CONSTRAINT pk_atendimento PRIMARY KEY (id)
);

CREATE TABLE atendimento_consultas (
  id_atendimento BIGINT NOT NULL,
   id_consulta BIGINT NOT NULL
);

CREATE TABLE atendimento_evolucoes (
  id_atendimento BIGINT NOT NULL,
   id_evolucao BIGINT NOT NULL
);

CREATE TABLE atendimento_historico (
  id_atendimento BIGINT NOT NULL,
   id_historico BIGINT NOT NULL
);

CREATE TABLE atendimento_procedimento (
  id BIGINT AUTO_INCREMENT NOT NULL,
   atendimento_id BIGINT NULL,
   procedimento_codigo BIGINT NULL,
   quantidade INT NOT NULL,
   idade_no_atendimento INT NOT NULL,
   cbo_profissional VARCHAR(255) NULL,
   profissional_id BIGINT NULL,
   codigo_cid VARCHAR(255) NULL,
   CONSTRAINT pk_atendimentoprocedimento PRIMARY KEY (id)
);

CREATE TABLE atendimento_procedimentos (
  id_atendimento BIGINT NOT NULL,
   id_rel_procedimento BIGINT NOT NULL
);

CREATE TABLE atendimento_servicos (
  id_atendimento BIGINT NOT NULL,
   id_servico BIGINT NOT NULL
);

CREATE TABLE atendimento_uso_medicamentos (
  id_atendimento BIGINT NOT NULL,
   id_uso_medicamento BIGINT NOT NULL
);

CREATE TABLE atendimento_vitais (
  id_atendimento BIGINT NOT NULL,
   id_sinais_vitais BIGINT NOT NULL
);

CREATE TABLE atestado (
  id BIGINT AUTO_INCREMENT NOT NULL,
   texto VARCHAR(500) NULL,
   periodo INT NOT NULL,
   autoriza_impressao_cid BIT(1) NOT NULL,
   atendimento_id BIGINT NOT NULL,
   prontuario_id BIGINT NOT NULL,
   data_registro datetime NULL,
   profissional_id BIGINT NULL,
   CONSTRAINT pk_atestado PRIMARY KEY (id)
);

CREATE TABLE atestado_cid (
  id_atestado BIGINT NOT NULL,
   id_cid BIGINT NOT NULL
);

CREATE TABLE avaliacao (
  id BIGINT AUTO_INCREMENT NOT NULL,
   notas VARCHAR(255) NULL,
   sinais_vitais_id BIGINT NULL,
   CONSTRAINT pk_avaliacao PRIMARY KEY (id)
);

CREATE TABLE cep_ibge (
  id BIGINT AUTO_INCREMENT NOT NULL,
   cep BIGINT NULL,
   codigoibge BIGINT NULL,
   CONSTRAINT pk_cep_ibge PRIMARY KEY (id)
);

CREATE TABLE cid (
  id BIGINT AUTO_INCREMENT NOT NULL,
   codigo VARCHAR(255) NULL,
   nome VARCHAR(255) NULL,
   tipo_agravo CHAR NOT NULL,
   tipo_sexo CHAR NOT NULL,
   tipo_estadio CHAR NOT NULL,
   valor_campos_irradiados INT NOT NULL,
   CONSTRAINT pk_cid PRIMARY KEY (id)
);

CREATE TABLE cidadao (
  id BIGINT AUTO_INCREMENT NOT NULL,
   cns VARCHAR(20) NOT NULL,
   cpf VARCHAR(15) NOT NULL,
   sexo VARCHAR(1) NOT NULL,
   nome VARCHAR(50) NOT NULL,
   nome_mae VARCHAR(50) NOT NULL,
   nome_pai VARCHAR(50) NOT NULL,
   data_nascimento date NOT NULL,
   codigo_raca VARCHAR(255) NOT NULL,
   etnia_codigo BIGINT NULL,
   codigo_nacionalidade INT NOT NULL,
   estado_civil INT NULL,
   profissao VARCHAR(255) NULL,
   telefone VARCHAR(255) NULL,
   email VARCHAR(40) NULL,
   municipio_nascimento_id BIGINT NOT NULL,
   endereco_id BIGINT NOT NULL,
   prontuario_id BIGINT NULL,
   CONSTRAINT pk_cidadao PRIMARY KEY (id)
);

CREATE TABLE classificacao_de_risco (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   descricao VARCHAR(255) NULL,
   prioridade INT NOT NULL,
   tempo_maximo INT NOT NULL,
   CONSTRAINT pk_classificacaoderisco PRIMARY KEY (id)
);

CREATE TABLE complexidade (
  id BIGINT AUTO_INCREMENT NOT NULL,
   sigla_complexidade VARCHAR(255) NULL,
   CONSTRAINT pk_complexidade PRIMARY KEY (id)
);

CREATE TABLE consulta (
  id BIGINT AUTO_INCREMENT NOT NULL,
   historia_clinica VARCHAR(255) NULL,
   avaliacao VARCHAR(255) NULL,
   conduta VARCHAR(255) NULL,
   atendimento_id BIGINT NULL,
   data_registro datetime NULL,
   sinais_vitais_id BIGINT NULL,
   profissional_id BIGINT NULL,
   diagnostico VARCHAR(255) NULL,
   cids VARCHAR(255) NULL,
   CONSTRAINT pk_consulta PRIMARY KEY (id)
);

CREATE TABLE detalhe (
  id BIGINT AUTO_INCREMENT NOT NULL,
   codigo VARCHAR(255) NULL,
   nome VARCHAR(255) NULL,
   competencia VARCHAR(255) NULL,
   CONSTRAINT pk_detalhe PRIMARY KEY (id)
);

CREATE TABLE diagnostico (
  id BIGINT AUTO_INCREMENT NOT NULL,
   prontuario_id BIGINT NULL,
   cid_id BIGINT NULL,
   nota VARCHAR(255) NULL,
   data_registro datetime NULL,
   definitivo BIT(1) NOT NULL,
   profissional_id BIGINT NULL,
   atendimento_id BIGINT NULL,
   CONSTRAINT pk_diagnostico PRIMARY KEY (id)
);

CREATE TABLE doenca (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   descricao VARCHAR(255) NULL,
   cid_id BIGINT NULL,
   CONSTRAINT pk_doenca PRIMARY KEY (id)
);

CREATE TABLE endereco (
  id BIGINT AUTO_INCREMENT NOT NULL,
   tipo_endereco INT NOT NULL,
   numero VARCHAR(10) NULL,
   complemento VARCHAR(30) NULL,
   bairro VARCHAR(30) NULL,
   cep VARCHAR(255) NULL,
   nome_logradouro VARCHAR(255) NULL,
   municipio_id BIGINT NOT NULL,
   logradouro_codigo BIGINT NOT NULL,
   endereco_concat VARCHAR(255) NULL,
   CONSTRAINT pk_endereco PRIMARY KEY (id)
);

CREATE TABLE endereco_estabelecimento (
  id BIGINT AUTO_INCREMENT NOT NULL,
   cep VARCHAR(255) NULL,
   sigla_uf VARCHAR(255) NULL,
   codigo_ibge_municipio VARCHAR(255) NULL,
   bairro VARCHAR(255) NULL,
   logradouro VARCHAR(255) NULL,
   numero VARCHAR(255) NULL,
   complemento VARCHAR(255) NULL,
   ponto_referencia VARCHAR(255) NULL,
   CONSTRAINT pk_enderecoestabelecimento PRIMARY KEY (id)
);

CREATE TABLE estabelecimento (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   cnpj VARCHAR(255) NULL,
   cnes VARCHAR(255) NULL,
   tipo_unidade_id VARCHAR(255) NULL,
   descricao_tipo_unidade VARCHAR(255) NULL,
   telefone1 VARCHAR(255) NULL,
   telefone2 VARCHAR(255) NULL,
   fax VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   CONSTRAINT pk_estabelecimento PRIMARY KEY (id)
);

CREATE TABLE estabelecimento_complexidade (
  complexidades_id BIGINT NOT NULL,
   estabelecimento_id BIGINT NOT NULL
);

CREATE TABLE estabelecimento_endereco (
  endereco_id BIGINT NOT NULL,
   enderecos_id BIGINT NOT NULL
);

CREATE TABLE estado (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   sigla VARCHAR(255) NULL,
   codigo INT NOT NULL,
   CONSTRAINT pk_estado PRIMARY KEY (id)
);

CREATE TABLE etnia (
  codigo BIGINT NOT NULL,
   nome VARCHAR(255) NULL,
   CONSTRAINT pk_etnia PRIMARY KEY (codigo)
);

CREATE TABLE evolucao (
  id BIGINT AUTO_INCREMENT NOT NULL,
   evolucao VARCHAR(255) NULL,
   CONSTRAINT pk_evolucao PRIMARY KEY (id)
);

CREATE TABLE exame (
  id BIGINT AUTO_INCREMENT NOT NULL,
   justificativa VARCHAR(255) NULL,
   observacoes VARCHAR(255) NULL,
   data_solicitacao datetime NULL,
   status INT NULL,
   atendimento_id BIGINT NULL,
   prontuario_id BIGINT NULL,
   profissional_id BIGINT NULL,
   cid_id BIGINT NULL,
   CONSTRAINT pk_exame PRIMARY KEY (id)
);

CREATE TABLE exame_procedimentos (
  id_exame BIGINT NOT NULL,
   id_procedimento BIGINT NOT NULL
);

CREATE TABLE exame_simplificado (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   procedimento_associado_codigo BIGINT NOT NULL,
   grupo_exame_id BIGINT NULL,
   CONSTRAINT pk_examesimplificado PRIMARY KEY (id)
);

CREATE TABLE folha_bpa_consolidado_linha_consolidado (
  folhabpaconsolidado_id BIGINT NOT NULL,
   linhasbpaconsolidado_id BIGINT NOT NULL
);

CREATE TABLE folha_bpa_individualizado_linha_individualizado (
  folhabpaindividualizado_id BIGINT NOT NULL,
   linhasbpaindividualizado_id BIGINT NOT NULL
);

CREATE TABLE folhabpaconsolidado (
  id BIGINT AUTO_INCREMENT NOT NULL,
   numero INT NOT NULL,
   CONSTRAINT pk_folhabpaconsolidado PRIMARY KEY (id)
);

CREATE TABLE folhabpaindividualizado (
  id BIGINT AUTO_INCREMENT NOT NULL,
   numero INT NOT NULL,
   CONSTRAINT pk_folhabpaindividualizado PRIMARY KEY (id)
);

CREATE TABLE forma_farmaceutica (
  id BIGINT NOT NULL,
   nome VARCHAR(255) NULL,
   nome_filtro VARCHAR(255) NULL,
   CONSTRAINT pk_formafarmaceutica PRIMARY KEY (id)
);

CREATE TABLE grupo_exame (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   CONSTRAINT pk_grupoexame PRIMARY KEY (id)
);

CREATE TABLE grupo_exames (
  id_exame_simplificado BIGINT NOT NULL,
   id_grupo BIGINT NOT NULL
);

CREATE TABLE habito (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   CONSTRAINT pk_habito PRIMARY KEY (id)
);

CREATE TABLE historico_atendimento (
  id BIGINT AUTO_INCREMENT NOT NULL,
   descricao VARCHAR(255) NULL,
   conduta_cidadao INT NULL,
   status INT NULL,
   profissional_id BIGINT NULL,
   profissional_destino_id BIGINT NULL,
   local_date_time datetime NULL,
   CONSTRAINT pk_historicoatendimento PRIMARY KEY (id)
);

CREATE TABLE historico_atendimento_servicos (
  id_historico BIGINT NOT NULL,
   id_tipo_servico BIGINT NOT NULL
);

CREATE TABLE linhabpaconsolidado (
  id BIGINT AUTO_INCREMENT NOT NULL,
   linha_identi VARCHAR(255) NULL,
   cnes VARCHAR(255) NULL,
   competencia VARCHAR(255) NULL,
   cbo_profissional VARCHAR(255) NULL,
   numero_folha VARCHAR(255) NULL,
   numero_linha VARCHAR(255) NULL,
   codigo_procedimento VARCHAR(255) NULL,
   idade VARCHAR(255) NULL,
   quantidade VARCHAR(255) NULL,
   origem VARCHAR(255) NULL,
   fim VARCHAR(255) NULL,
   valor DECIMAL NULL,
   CONSTRAINT pk_linhabpaconsolidado PRIMARY KEY (id)
);

CREATE TABLE linhabpaindividualizado (
  id BIGINT AUTO_INCREMENT NOT NULL,
   linha_identi VARCHAR(255) NULL,
   cnes VARCHAR(255) NULL,
   competencia VARCHAR(255) NULL,
   cns_profissional VARCHAR(255) NULL,
   cbo_profissional VARCHAR(255) NULL,
   data_atendimento VARCHAR(255) NULL,
   numero_folha VARCHAR(255) NULL,
   numero_linha VARCHAR(255) NULL,
   codigo_procedimento VARCHAR(255) NULL,
   cns_paciente VARCHAR(255) NULL,
   sexo_paciente VARCHAR(255) NULL,
   codigo_ibge VARCHAR(255) NULL,
   cid VARCHAR(255) NULL,
   idade VARCHAR(255) NULL,
   qtd_procedimento VARCHAR(255) NULL,
   carater_atendimento VARCHAR(255) NULL,
   numero_autorizacao VARCHAR(255) NULL,
   origem_informacao VARCHAR(255) NULL,
   nome_paciente VARCHAR(255) NULL,
   data_nascimento VARCHAR(255) NULL,
   raca_cor VARCHAR(255) NULL,
   etnia VARCHAR(255) NULL,
   nacionalidade VARCHAR(255) NULL,
   codigo_servico VARCHAR(255) NULL,
   codigo_classificacao VARCHAR(255) NULL,
   codigo_sequencia_equipe VARCHAR(255) NULL,
   codigo_area_equipe VARCHAR(255) NULL,
   codigo_cnpj_empresa VARCHAR(255) NULL,
   cep_paciente VARCHAR(255) NULL,
   logradouro_paciente VARCHAR(255) NULL,
   endereco_paciente VARCHAR(255) NULL,
   complemento_endereco VARCHAR(255) NULL,
   numero_endereco VARCHAR(255) NULL,
   bairro_endereco VARCHAR(255) NULL,
   telefone_paciente VARCHAR(255) NULL,
   email_paciente VARCHAR(255) NULL,
   identificacao_equipe VARCHAR(255) NULL,
   fim VARCHAR(255) NULL,
   valor DECIMAL NULL,
   CONSTRAINT pk_linhabpaindividualizado PRIMARY KEY (id)
);

CREATE TABLE logradouro (
  codigo BIGINT NOT NULL,
   descricao VARCHAR(255) NULL,
   CONSTRAINT pk_logradouro PRIMARY KEY (codigo)
);

CREATE TABLE lotacao (
  id BIGINT AUTO_INCREMENT NOT NULL,
   cnes VARCHAR(7) NOT NULL,
   codigo_ine VARCHAR(255) NULL,
   codigocbo VARCHAR(6) NOT NULL,
   micro_area VARCHAR(255) NULL,
   CONSTRAINT pk_lotacao PRIMARY KEY (id)
);

CREATE TABLE medicamento (
  id BIGINT NOT NULL,
   principio_ativo VARCHAR(255) NULL,
   concentracao VARCHAR(255) NULL,
   unidade_fornecimento VARCHAR(255) NULL,
   forma_farmaceutica_id BIGINT NULL,
   CONSTRAINT pk_medicamento PRIMARY KEY (id)
);

CREATE TABLE municipio (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome_municipio VARCHAR(255) NULL,
   nome_municipio_siglauf VARCHAR(255) NULL,
   codigoibge BIGINT NOT NULL,
   codigoibge7 BIGINT NOT NULL,
   estado_id BIGINT NULL,
   CONSTRAINT pk_municipio PRIMARY KEY (id)
);

CREATE TABLE ocupacao (
  id BIGINT AUTO_INCREMENT NOT NULL,
   codigo VARCHAR(255) NULL,
   nome VARCHAR(255) NULL,
   CONSTRAINT pk_ocupacao PRIMARY KEY (id)
);

CREATE TABLE orgao_responsavel (
  id BIGINT AUTO_INCREMENT NOT NULL,
   sigla VARCHAR(255) NULL,
   cnpj VARCHAR(255) NULL,
   nome_orgao VARCHAR(255) NULL,
   indicador CHAR NOT NULL,
   CONSTRAINT pk_orgaoresponsavel PRIMARY KEY (id)
);

CREATE TABLE password_reset_token (
  id BIGINT AUTO_INCREMENT NOT NULL,
   token VARCHAR(255) NULL,
   usuario_id BIGINT NULL,
   CONSTRAINT pk_passwordresettoken PRIMARY KEY (id)
);

CREATE TABLE prescricao (
  id BIGINT AUTO_INCREMENT NOT NULL,
   medicamento_id BIGINT NOT NULL,
   via_administracao_id BIGINT NOT NULL,
   posologia VARCHAR(255) NULL,
   administracao_no_atendimento BIT(1) NOT NULL,
   orientacoes VARCHAR(255) NULL,
   dose_unica BIT(1) NOT NULL,
   uso_continuo BIT(1) NOT NULL,
   atendimento_id BIGINT NULL,
   prontuario_id BIGINT NULL,
   quantidade INT NOT NULL,
   data_registro datetime NULL,
   profissional_id BIGINT NULL,
   prescricao_externabool BIT(1) NOT NULL,
   CONSTRAINT pk_prescricao PRIMARY KEY (id)
);

CREATE TABLE prescricao_externa (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome_profissional VARCHAR(255) NULL,
   numero_registro VARCHAR(255) NULL,
   sigla_uf_emissao VARCHAR(255) NULL,
   data_solicitacao date NOT NULL,
   prescricao_id BIGINT NULL,
   CONSTRAINT pk_prescricaoexterna PRIMARY KEY (id)
);

CREATE TABLE prescricao_registro_administracao (
  id_prescricao BIGINT NOT NULL,
   id_registro_administracao BIGINT NOT NULL
);

CREATE TABLE procedimento (
  codigo BIGINT NOT NULL,
   nome VARCHAR(255) NULL,
   tipo_complexidade CHAR NOT NULL,
   tipo_sexo CHAR NOT NULL,
   qtd_maxima_execucao INT NOT NULL,
   qtd_dias_permanencia INT NOT NULL,
   qtd_pontos INT NOT NULL,
   vl_idade_minina INT NOT NULL,
   vl_idade_maxima INT NOT NULL,
   vlsh DECIMAL NULL,
   vlsa DECIMAL NULL,
   vlsp DECIMAL NULL,
   codigo_financiamento VARCHAR(255) NULL,
   codigo_rubrica VARCHAR(255) NULL,
   qtd_tempo_permanencia INT NOT NULL,
   data_competencia VARCHAR(255) NULL,
   quantidade INT NULL,
   CONSTRAINT pk_procedimento PRIMARY KEY (codigo)
);

CREATE TABLE procedimento_cid (
  id BIGINT AUTO_INCREMENT NOT NULL,
   codigo_procedimento BIGINT NULL,
   codigo_cid VARCHAR(255) NULL,
   competencia VARCHAR(255) NULL,
   CONSTRAINT pk_procedimentocid PRIMARY KEY (id)
);

CREATE TABLE procedimento_detalhe (
  id BIGINT AUTO_INCREMENT NOT NULL,
   codigo_procedimento BIGINT NULL,
   codigo_detalhe VARCHAR(255) NULL,
   competencia VARCHAR(255) NULL,
   CONSTRAINT pk_procedimentodetalhe PRIMARY KEY (id)
);

CREATE TABLE procedimento_ocupacao (
  id BIGINT AUTO_INCREMENT NOT NULL,
   codigo_procedimento BIGINT NULL,
   codigo_ocupacao VARCHAR(255) NULL,
   competencia VARCHAR(255) NULL,
   CONSTRAINT pk_procedimentoocupacao PRIMARY KEY (id)
);

CREATE TABLE procedimento_registro_sigtap (
  id BIGINT AUTO_INCREMENT NOT NULL,
   codigo_procedimento BIGINT NULL,
   codigo_registro VARCHAR(255) NULL,
   competencia VARCHAR(255) NULL,
   CONSTRAINT pk_procedimentoregistrosigtap PRIMARY KEY (id)
);

CREATE TABLE procedimento_tiposregistro (
  procedimento_id BIGINT NOT NULL,
   registro_id VARCHAR(255) NOT NULL
);

CREATE TABLE profissional (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(100) NOT NULL,
   cpf VARCHAR(255) NOT NULL,
   cns VARCHAR(255) NOT NULL,
   data_nascimento VARCHAR(255) NOT NULL,
   sexo VARCHAR(255) NOT NULL,
   conselho_id INT NOT NULL,
   sigla_uf_emissao VARCHAR(255) NULL,
   numero_registro VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   telefone VARCHAR(255) NULL,
   nome_abrev VARCHAR(255) NULL,
   tipo_profissional VARCHAR(255) NULL,
   nome_ocupacao VARCHAR(255) NULL,
   ativo BIT(1) NOT NULL,
   CONSTRAINT pk_profissional PRIMARY KEY (id)
);

CREATE TABLE profissional_lotacao (
  lotacao_id BIGINT NOT NULL,
   profissional_id BIGINT NOT NULL
);

CREATE TABLE prontuario (
  id BIGINT AUTO_INCREMENT NOT NULL,
   cidadao_id BIGINT NOT NULL,
   data_abertura datetime NOT NULL,
   CONSTRAINT pk_prontuario PRIMARY KEY (id)
);

CREATE TABLE prontuario_antropometria (
  id_antropometria BIGINT NOT NULL,
   id_prontuario BIGINT NOT NULL
);

CREATE TABLE prontuario_atendimento (
  id_atendimento BIGINT NOT NULL,
   id_prontuario BIGINT NOT NULL
);

CREATE TABLE prontuario_atestados (
  id_atestado BIGINT NOT NULL,
   id_prontuario BIGINT NOT NULL
);

CREATE TABLE prontuario_exames (
  id_exame BIGINT NOT NULL,
   id_prontuario BIGINT NOT NULL
);

CREATE TABLE prontuario_habito (
  id_habito BIGINT NOT NULL,
   id_prontuario BIGINT NOT NULL
);

CREATE TABLE prontuario_prescricoes (
  id_prescricao BIGINT NOT NULL,
   id_prontuario BIGINT NOT NULL
);

CREATE TABLE prontuario_status_alergia (
  id_prontuario BIGINT NOT NULL,
   id_status_alergia BIGINT NOT NULL
);

CREATE TABLE prontuario_status_doenca (
  id_prontuario BIGINT NOT NULL,
   id_status_doenca BIGINT NOT NULL
);

CREATE TABLE prontuario_uso_continuo_medicamento (
  id_prontuario BIGINT NOT NULL,
   id_uso_continuo_medicamento BIGINT NOT NULL
);

CREATE TABLE registro_administracao (
  id BIGINT AUTO_INCREMENT NOT NULL,
   administracao_realizada BIT(1) NOT NULL,
   data_administracao datetime NULL,
   profissional_responsavel_id BIGINT NULL,
   nota VARCHAR(255) NULL,
   CONSTRAINT pk_registroadministracao PRIMARY KEY (id)
);

CREATE TABLE registro_sigtap (
  codigo VARCHAR(255) NOT NULL,
   nome VARCHAR(255) NULL,
   data_competencia VARCHAR(255) NULL,
   CONSTRAINT pk_registrosigtap PRIMARY KEY (codigo)
);

CREATE TABLE resultado_exame (
  id BIGINT AUTO_INCREMENT NOT NULL,
   descricao VARCHAR(255) NULL,
   data_realizacao date NULL,
   data_resultado date NULL,
   data_cadastro datetime NULL,
   procedimento_id BIGINT NULL,
   exame_id BIGINT NULL,
   profissional_id BIGINT NULL,
   CONSTRAINT pk_resultadoexame PRIMARY KEY (id)
);

CREATE TABLE `role` (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE sinais_vitais (
  id BIGINT AUTO_INCREMENT NOT NULL,
   pressao_sistolica INT NOT NULL,
   pressao_diastolica INT NOT NULL,
   temperatura_corporal DOUBLE NOT NULL,
   frequencia_cardiaca VARCHAR(255) NULL,
   saturacao VARCHAR(255) NULL,
   frequencia_respiratoria VARCHAR(255) NULL,
   glicemia_capilar VARCHAR(255) NULL,
   momento_coleta INT NULL,
   ultima_atualizacao datetime NULL,
   CONSTRAINT pk_sinaisvitais PRIMARY KEY (id)
);

CREATE TABLE status_alergia (
  id BIGINT AUTO_INCREMENT NOT NULL,
   data_inicio date NULL,
   data_fim date NULL,
   situacao_condicao INT NOT NULL,
   data_registro datetime NULL,
   alergia_id BIGINT NOT NULL,
   CONSTRAINT pk_statusalergia PRIMARY KEY (id)
);

CREATE TABLE status_doenca (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nota VARCHAR(255) NULL,
   data_inicio date NULL,
   data_fim date NULL,
   situacao_condicao INT NOT NULL,
   doenca_id BIGINT NOT NULL,
   data_registro datetime NULL,
   CONSTRAINT pk_statusdoenca PRIMARY KEY (id)
);

CREATE TABLE tipo_servico (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   CONSTRAINT pk_tiposervico PRIMARY KEY (id)
);

CREATE TABLE tipo_usuario (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   CONSTRAINT pk_tipousuario PRIMARY KEY (id)
);

CREATE TABLE triagem (
  id BIGINT AUTO_INCREMENT NOT NULL,
   motivo VARCHAR(255) NULL,
   inicio_triagem datetime NULL,
   fim_triagem datetime NULL,
   sinais_vitais_id BIGINT NULL,
   profissional_id BIGINT NULL,
   atendimento_id BIGINT NULL,
   CONSTRAINT pk_triagem PRIMARY KEY (id)
);

CREATE TABLE uso_continuo_medicamento (
  id BIGINT AUTO_INCREMENT NOT NULL,
   medicamento_id BIGINT NOT NULL,
   nota VARCHAR(255) NULL,
   data_cadastro datetime NULL,
   CONSTRAINT pk_usocontinuomedicamento PRIMARY KEY (id)
);

CREATE TABLE uso_medicamento (
  id BIGINT AUTO_INCREMENT NOT NULL,
   medicamento_id BIGINT NOT NULL,
   nota VARCHAR(255) NULL,
   data_cadastro datetime NULL,
   uso_continuo BIT(1) NOT NULL,
   CONSTRAINT pk_usomedicamento PRIMARY KEY (id)
);

CREATE TABLE usuario (
  id BIGINT AUTO_INCREMENT NOT NULL,
   username VARCHAR(255) NULL,
   password VARCHAR(255) NULL,
   concat_name VARCHAR(255) NULL,
   enabled BIT(1) NOT NULL,
   tipo_usuario_id BIGINT NULL,
   first_access BIT(1) NOT NULL,
   CONSTRAINT pk_usuario PRIMARY KEY (id)
);

CREATE TABLE usuario_role (
  usuario_id BIGINT NOT NULL,
   role_id BIGINT NOT NULL,
   CONSTRAINT pk_usuario_role PRIMARY KEY (usuario_id, role_id)
);

CREATE TABLE via_administracao (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(255) NULL,
   procedimento_codigo BIGINT NULL,
   CONSTRAINT pk_viaadministracao PRIMARY KEY (id)
);

ALTER TABLE `role` ADD CONSTRAINT uc_role_nome UNIQUE (nome);

ALTER TABLE alergia ADD CONSTRAINT FK_ALERGIA_ON_CID FOREIGN KEY (cid_id) REFERENCES cid (id);

ALTER TABLE antropometria ADD CONSTRAINT FK_ANTROPOMETRIA_ON_ATENDIMENTO FOREIGN KEY (atendimento_id) REFERENCES atendimento (id);

ALTER TABLE antropometria ADD CONSTRAINT FK_ANTROPOMETRIA_ON_PROFISSIONAL FOREIGN KEY (profissional_id) REFERENCES profissional (id);

ALTER TABLE antropometria ADD CONSTRAINT FK_ANTROPOMETRIA_ON_PRONTUARIO FOREIGN KEY (prontuario_id) REFERENCES prontuario (id);

ALTER TABLE atendimento_procedimento ADD CONSTRAINT FK_ATENDIMENTOPROCEDIMENTO_ON_ATENDIMENTO FOREIGN KEY (atendimento_id) REFERENCES atendimento (id);

ALTER TABLE atendimento_procedimento ADD CONSTRAINT FK_ATENDIMENTOPROCEDIMENTO_ON_PROCEDIMENTO_CODIGO FOREIGN KEY (procedimento_codigo) REFERENCES procedimento (codigo);

ALTER TABLE atendimento_procedimento ADD CONSTRAINT FK_ATENDIMENTOPROCEDIMENTO_ON_PROFISSIONAL FOREIGN KEY (profissional_id) REFERENCES profissional (id);

ALTER TABLE atendimento ADD CONSTRAINT FK_ATENDIMENTO_ON_CIDADAO FOREIGN KEY (cidadao_id) REFERENCES cidadao (id);

ALTER TABLE atendimento ADD CONSTRAINT FK_ATENDIMENTO_ON_CLASSIFICACAODERISCO FOREIGN KEY (classificacao_de_risco_id) REFERENCES classificacao_de_risco (id);

ALTER TABLE atendimento ADD CONSTRAINT FK_ATENDIMENTO_ON_PROFISSIONALDESTINO FOREIGN KEY (profissional_destino_id) REFERENCES profissional (id);

ALTER TABLE atendimento ADD CONSTRAINT FK_ATENDIMENTO_ON_TRIAGEM FOREIGN KEY (triagem_id) REFERENCES triagem (id);

ALTER TABLE atestado ADD CONSTRAINT FK_ATESTADO_ON_ATENDIMENTO FOREIGN KEY (atendimento_id) REFERENCES atendimento (id);

ALTER TABLE atestado ADD CONSTRAINT FK_ATESTADO_ON_PROFISSIONAL FOREIGN KEY (profissional_id) REFERENCES profissional (id);

ALTER TABLE atestado ADD CONSTRAINT FK_ATESTADO_ON_PRONTUARIO FOREIGN KEY (prontuario_id) REFERENCES prontuario (id);

ALTER TABLE avaliacao ADD CONSTRAINT FK_AVALIACAO_ON_SINAISVITAIS FOREIGN KEY (sinais_vitais_id) REFERENCES sinais_vitais (id);

ALTER TABLE cidadao ADD CONSTRAINT FK_CIDADAO_ON_ENDERECO FOREIGN KEY (endereco_id) REFERENCES endereco (id);

ALTER TABLE cidadao ADD CONSTRAINT FK_CIDADAO_ON_ETNIA_CODIGO FOREIGN KEY (etnia_codigo) REFERENCES etnia (codigo);

ALTER TABLE cidadao ADD CONSTRAINT FK_CIDADAO_ON_MUNICIPIONASCIMENTO FOREIGN KEY (municipio_nascimento_id) REFERENCES municipio (id);

ALTER TABLE cidadao ADD CONSTRAINT FK_CIDADAO_ON_PRONTUARIO FOREIGN KEY (prontuario_id) REFERENCES prontuario (id);

ALTER TABLE consulta ADD CONSTRAINT FK_CONSULTA_ON_ATENDIMENTO FOREIGN KEY (atendimento_id) REFERENCES atendimento (id);

ALTER TABLE consulta ADD CONSTRAINT FK_CONSULTA_ON_PROFISSIONAL FOREIGN KEY (profissional_id) REFERENCES profissional (id);

ALTER TABLE consulta ADD CONSTRAINT FK_CONSULTA_ON_SINAISVITAIS FOREIGN KEY (sinais_vitais_id) REFERENCES sinais_vitais (id);

ALTER TABLE diagnostico ADD CONSTRAINT FK_DIAGNOSTICO_ON_ATENDIMENTO FOREIGN KEY (atendimento_id) REFERENCES atendimento (id);

ALTER TABLE diagnostico ADD CONSTRAINT FK_DIAGNOSTICO_ON_CID FOREIGN KEY (cid_id) REFERENCES cid (id);

ALTER TABLE diagnostico ADD CONSTRAINT FK_DIAGNOSTICO_ON_PROFISSIONAL FOREIGN KEY (profissional_id) REFERENCES profissional (id);

ALTER TABLE diagnostico ADD CONSTRAINT FK_DIAGNOSTICO_ON_PRONTUARIO FOREIGN KEY (prontuario_id) REFERENCES prontuario (id);

ALTER TABLE doenca ADD CONSTRAINT FK_DOENCA_ON_CID FOREIGN KEY (cid_id) REFERENCES cid (id);

ALTER TABLE endereco ADD CONSTRAINT FK_ENDERECO_ON_LOGRADOURO_CODIGO FOREIGN KEY (logradouro_codigo) REFERENCES logradouro (codigo);

ALTER TABLE endereco ADD CONSTRAINT FK_ENDERECO_ON_MUNICIPIO FOREIGN KEY (municipio_id) REFERENCES municipio (id);

ALTER TABLE exame_simplificado ADD CONSTRAINT FK_EXAMESIMPLIFICADO_ON_GRUPOEXAME FOREIGN KEY (grupo_exame_id) REFERENCES grupo_exame (id);

ALTER TABLE exame_simplificado ADD CONSTRAINT FK_EXAMESIMPLIFICADO_ON_PROCEDIMENTOASSOCIADO_CODIGO FOREIGN KEY (procedimento_associado_codigo) REFERENCES procedimento (codigo);

ALTER TABLE exame ADD CONSTRAINT FK_EXAME_ON_ATENDIMENTO FOREIGN KEY (atendimento_id) REFERENCES atendimento (id);

ALTER TABLE exame ADD CONSTRAINT FK_EXAME_ON_CID FOREIGN KEY (cid_id) REFERENCES cid (id);

ALTER TABLE exame ADD CONSTRAINT FK_EXAME_ON_PROFISSIONAL FOREIGN KEY (profissional_id) REFERENCES profissional (id);

ALTER TABLE exame ADD CONSTRAINT FK_EXAME_ON_PRONTUARIO FOREIGN KEY (prontuario_id) REFERENCES prontuario (id);

ALTER TABLE historico_atendimento ADD CONSTRAINT FK_HISTORICOATENDIMENTO_ON_PROFISSIONAL FOREIGN KEY (profissional_id) REFERENCES profissional (id);

ALTER TABLE historico_atendimento ADD CONSTRAINT FK_HISTORICOATENDIMENTO_ON_PROFISSIONALDESTINO FOREIGN KEY (profissional_destino_id) REFERENCES profissional (id);

ALTER TABLE medicamento ADD CONSTRAINT FK_MEDICAMENTO_ON_FORMAFARMACEUTICA FOREIGN KEY (forma_farmaceutica_id) REFERENCES forma_farmaceutica (id);

ALTER TABLE municipio ADD CONSTRAINT FK_MUNICIPIO_ON_ESTADO FOREIGN KEY (estado_id) REFERENCES estado (id);

ALTER TABLE password_reset_token ADD CONSTRAINT FK_PASSWORDRESETTOKEN_ON_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE prescricao_externa ADD CONSTRAINT FK_PRESCRICAOEXTERNA_ON_PRESCRICAO FOREIGN KEY (prescricao_id) REFERENCES prescricao (id);

ALTER TABLE prescricao ADD CONSTRAINT FK_PRESCRICAO_ON_ATENDIMENTO FOREIGN KEY (atendimento_id) REFERENCES atendimento (id);

ALTER TABLE prescricao ADD CONSTRAINT FK_PRESCRICAO_ON_MEDICAMENTO FOREIGN KEY (medicamento_id) REFERENCES medicamento (id);

ALTER TABLE prescricao ADD CONSTRAINT FK_PRESCRICAO_ON_PROFISSIONAL FOREIGN KEY (profissional_id) REFERENCES profissional (id);

ALTER TABLE prescricao ADD CONSTRAINT FK_PRESCRICAO_ON_PRONTUARIO FOREIGN KEY (prontuario_id) REFERENCES prontuario (id);

ALTER TABLE prescricao ADD CONSTRAINT FK_PRESCRICAO_ON_VIAADMINISTRACAO FOREIGN KEY (via_administracao_id) REFERENCES via_administracao (id);

ALTER TABLE prontuario ADD CONSTRAINT FK_PRONTUARIO_ON_CIDADAO FOREIGN KEY (cidadao_id) REFERENCES cidadao (id);

ALTER TABLE registro_administracao ADD CONSTRAINT FK_REGISTROADMINISTRACAO_ON_PROFISSIONALRESPONSAVEL FOREIGN KEY (profissional_responsavel_id) REFERENCES profissional (id);

ALTER TABLE resultado_exame ADD CONSTRAINT FK_RESULTADOEXAME_ON_EXAME FOREIGN KEY (exame_id) REFERENCES exame (id);

ALTER TABLE resultado_exame ADD CONSTRAINT FK_RESULTADOEXAME_ON_PROCEDIMENTO FOREIGN KEY (procedimento_id) REFERENCES procedimento (codigo);

ALTER TABLE resultado_exame ADD CONSTRAINT FK_RESULTADOEXAME_ON_PROFISSIONAL FOREIGN KEY (profissional_id) REFERENCES profissional (id);

ALTER TABLE status_alergia ADD CONSTRAINT FK_STATUSALERGIA_ON_ALERGIA FOREIGN KEY (alergia_id) REFERENCES alergia (id);

ALTER TABLE status_doenca ADD CONSTRAINT FK_STATUSDOENCA_ON_DOENCA FOREIGN KEY (doenca_id) REFERENCES doenca (id);

ALTER TABLE triagem ADD CONSTRAINT FK_TRIAGEM_ON_ATENDIMENTO FOREIGN KEY (atendimento_id) REFERENCES atendimento (id);

ALTER TABLE triagem ADD CONSTRAINT FK_TRIAGEM_ON_PROFISSIONAL FOREIGN KEY (profissional_id) REFERENCES profissional (id);

ALTER TABLE triagem ADD CONSTRAINT FK_TRIAGEM_ON_SINAISVITAIS FOREIGN KEY (sinais_vitais_id) REFERENCES sinais_vitais (id);

ALTER TABLE uso_continuo_medicamento ADD CONSTRAINT FK_USOCONTINUOMEDICAMENTO_ON_MEDICAMENTO FOREIGN KEY (medicamento_id) REFERENCES medicamento (id);

ALTER TABLE uso_medicamento ADD CONSTRAINT FK_USOMEDICAMENTO_ON_MEDICAMENTO FOREIGN KEY (medicamento_id) REFERENCES medicamento (id);

ALTER TABLE usuario ADD CONSTRAINT FK_USUARIO_ON_TIPOUSUARIO FOREIGN KEY (tipo_usuario_id) REFERENCES tipo_usuario (id);

ALTER TABLE via_administracao ADD CONSTRAINT FK_VIAADMINISTRACAO_ON_PROCEDIMENTO_CODIGO FOREIGN KEY (procedimento_codigo) REFERENCES procedimento (codigo);

ALTER TABLE arquivo_bpa_folha_consolidado ADD CONSTRAINT fk_arqbpafolcon_on_arquivo_b_p_a FOREIGN KEY (id_arquivobpa) REFERENCES arquivobpa (id);

ALTER TABLE arquivo_bpa_folha_consolidado ADD CONSTRAINT fk_arqbpafolcon_on_folha_b_p_a_consolidado FOREIGN KEY (id_folha_bpa_consolidado) REFERENCES folhabpaconsolidado (id);

ALTER TABLE arquivo_bpa_folha_individualizado ADD CONSTRAINT fk_arqbpafolind_on_arquivo_b_p_a FOREIGN KEY (id_arquivobpa) REFERENCES arquivobpa (id);

ALTER TABLE arquivo_bpa_folha_individualizado ADD CONSTRAINT fk_arqbpafolind_on_folha_b_p_a_individualizado FOREIGN KEY (id_folha_bpa_individualizado) REFERENCES folhabpaindividualizado (id);

ALTER TABLE atestado_cid ADD CONSTRAINT fk_atecid_on_atestado FOREIGN KEY (id_atestado) REFERENCES atestado (id);

ALTER TABLE atestado_cid ADD CONSTRAINT fk_atecid_on_cid FOREIGN KEY (id_cid) REFERENCES cid (id);

ALTER TABLE atendimento_consultas ADD CONSTRAINT fk_atecon_on_atendimento FOREIGN KEY (id_atendimento) REFERENCES atendimento (id);

ALTER TABLE atendimento_consultas ADD CONSTRAINT fk_atecon_on_consulta FOREIGN KEY (id_consulta) REFERENCES consulta (id);

ALTER TABLE atendimento_evolucoes ADD CONSTRAINT fk_ateevo_on_atendimento FOREIGN KEY (id_atendimento) REFERENCES atendimento (id);

ALTER TABLE atendimento_evolucoes ADD CONSTRAINT fk_ateevo_on_evolucao FOREIGN KEY (id_evolucao) REFERENCES evolucao (id);

ALTER TABLE atendimento_historico ADD CONSTRAINT fk_atehis_on_atendimento FOREIGN KEY (id_atendimento) REFERENCES atendimento (id);

ALTER TABLE atendimento_historico ADD CONSTRAINT fk_atehis_on_historico_atendimento FOREIGN KEY (id_historico) REFERENCES historico_atendimento (id);

ALTER TABLE atendimento_procedimentos ADD CONSTRAINT fk_atepro_on_atendimento FOREIGN KEY (id_atendimento) REFERENCES atendimento (id);

ALTER TABLE atendimento_procedimentos ADD CONSTRAINT fk_atepro_on_atendimento_procedimento FOREIGN KEY (id_rel_procedimento) REFERENCES atendimento_procedimento (id);

ALTER TABLE atendimento_servicos ADD CONSTRAINT fk_ateser_on_atendimento FOREIGN KEY (id_atendimento) REFERENCES atendimento (id);

ALTER TABLE atendimento_servicos ADD CONSTRAINT fk_ateser_on_tipo_servico FOREIGN KEY (id_servico) REFERENCES tipo_servico (id);

ALTER TABLE atendimento_uso_medicamentos ADD CONSTRAINT fk_ateusomed_on_atendimento FOREIGN KEY (id_atendimento) REFERENCES atendimento (id);

ALTER TABLE atendimento_uso_medicamentos ADD CONSTRAINT fk_ateusomed_on_uso_medicamento FOREIGN KEY (id_uso_medicamento) REFERENCES uso_medicamento (id);

ALTER TABLE atendimento_vitais ADD CONSTRAINT fk_atevit_on_atendimento FOREIGN KEY (id_atendimento) REFERENCES atendimento (id);

ALTER TABLE atendimento_vitais ADD CONSTRAINT fk_atevit_on_sinais_vitais FOREIGN KEY (id_sinais_vitais) REFERENCES sinais_vitais (id);

ALTER TABLE estabelecimento_complexidade ADD CONSTRAINT fk_estcom_on_complexidade FOREIGN KEY (complexidades_id) REFERENCES complexidade (id);

ALTER TABLE estabelecimento_complexidade ADD CONSTRAINT fk_estcom_on_estabelecimento FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimento (id);

ALTER TABLE estabelecimento_endereco ADD CONSTRAINT fk_estend_on_endereco_estabelecimento FOREIGN KEY (enderecos_id) REFERENCES endereco_estabelecimento (id);

ALTER TABLE estabelecimento_endereco ADD CONSTRAINT fk_estend_on_estabelecimento FOREIGN KEY (endereco_id) REFERENCES estabelecimento (id);

ALTER TABLE exame_procedimentos ADD CONSTRAINT fk_exapro_on_exame FOREIGN KEY (id_exame) REFERENCES exame (id);

ALTER TABLE exame_procedimentos ADD CONSTRAINT fk_exapro_on_procedimento FOREIGN KEY (id_procedimento) REFERENCES procedimento (codigo);

ALTER TABLE folha_bpa_consolidado_linha_consolidado ADD CONSTRAINT fk_folbpaconlincon_on_folha_b_p_a_consolidado FOREIGN KEY (folhabpaconsolidado_id) REFERENCES folhabpaconsolidado (id);

ALTER TABLE folha_bpa_consolidado_linha_consolidado ADD CONSTRAINT fk_folbpaconlincon_on_linha_b_p_a_consolidado FOREIGN KEY (linhasbpaconsolidado_id) REFERENCES linhabpaconsolidado (id);

ALTER TABLE folha_bpa_individualizado_linha_individualizado ADD CONSTRAINT fk_folbpaindlinind_on_folha_b_p_a_individualizado FOREIGN KEY (folhabpaindividualizado_id) REFERENCES folhabpaindividualizado (id);

ALTER TABLE folha_bpa_individualizado_linha_individualizado ADD CONSTRAINT fk_folbpaindlinind_on_linha_b_p_a_individualizado FOREIGN KEY (linhasbpaindividualizado_id) REFERENCES linhabpaindividualizado (id);

ALTER TABLE grupo_exames ADD CONSTRAINT fk_gruexa_on_exame_simplificado FOREIGN KEY (id_exame_simplificado) REFERENCES exame_simplificado (id);

ALTER TABLE grupo_exames ADD CONSTRAINT fk_gruexa_on_grupo_exame FOREIGN KEY (id_grupo) REFERENCES grupo_exame (id);

ALTER TABLE historico_atendimento_servicos ADD CONSTRAINT fk_hisateser_on_historico_atendimento FOREIGN KEY (id_historico) REFERENCES historico_atendimento (id);

ALTER TABLE historico_atendimento_servicos ADD CONSTRAINT fk_hisateser_on_tipo_servico FOREIGN KEY (id_tipo_servico) REFERENCES tipo_servico (id);

ALTER TABLE prescricao_registro_administracao ADD CONSTRAINT fk_preregadm_on_prescricao FOREIGN KEY (id_prescricao) REFERENCES prescricao (id);

ALTER TABLE prescricao_registro_administracao ADD CONSTRAINT fk_preregadm_on_registro_administracao FOREIGN KEY (id_registro_administracao) REFERENCES registro_administracao (id);

ALTER TABLE prontuario_antropometria ADD CONSTRAINT fk_proant_on_antropometria FOREIGN KEY (id_antropometria) REFERENCES antropometria (id);

ALTER TABLE prontuario_antropometria ADD CONSTRAINT fk_proant_on_prontuario FOREIGN KEY (id_prontuario) REFERENCES prontuario (id);

ALTER TABLE prontuario_atendimento ADD CONSTRAINT fk_proate_on_atendimento FOREIGN KEY (id_atendimento) REFERENCES atendimento (id);

ALTER TABLE prontuario_atestados ADD CONSTRAINT fk_proate_on_atestado FOREIGN KEY (id_atestado) REFERENCES atestado (id);

ALTER TABLE prontuario_atendimento ADD CONSTRAINT fk_proate_on_prontuario FOREIGN KEY (id_prontuario) REFERENCES prontuario (id);

ALTER TABLE prontuario_atestados ADD CONSTRAINT fk_proate_on_prontuario0cHzGs FOREIGN KEY (id_prontuario) REFERENCES prontuario (id);

ALTER TABLE prontuario_exames ADD CONSTRAINT fk_proexa_on_exame FOREIGN KEY (id_exame) REFERENCES exame (id);

ALTER TABLE prontuario_exames ADD CONSTRAINT fk_proexa_on_prontuario FOREIGN KEY (id_prontuario) REFERENCES prontuario (id);

ALTER TABLE prontuario_habito ADD CONSTRAINT fk_prohab_on_habito FOREIGN KEY (id_habito) REFERENCES habito (id);

ALTER TABLE prontuario_habito ADD CONSTRAINT fk_prohab_on_prontuario FOREIGN KEY (id_prontuario) REFERENCES prontuario (id);

ALTER TABLE profissional_lotacao ADD CONSTRAINT fk_prolot_on_lotacao FOREIGN KEY (lotacao_id) REFERENCES lotacao (id);

ALTER TABLE profissional_lotacao ADD CONSTRAINT fk_prolot_on_profissional FOREIGN KEY (profissional_id) REFERENCES profissional (id);

ALTER TABLE prontuario_prescricoes ADD CONSTRAINT fk_propre_on_prescricao FOREIGN KEY (id_prescricao) REFERENCES prescricao (id);

ALTER TABLE prontuario_prescricoes ADD CONSTRAINT fk_propre_on_prontuario FOREIGN KEY (id_prontuario) REFERENCES prontuario (id);

ALTER TABLE prontuario_status_alergia ADD CONSTRAINT fk_prostaale_on_prontuario FOREIGN KEY (id_prontuario) REFERENCES prontuario (id);

ALTER TABLE prontuario_status_alergia ADD CONSTRAINT fk_prostaale_on_status_alergia FOREIGN KEY (id_status_alergia) REFERENCES status_alergia (id);

ALTER TABLE prontuario_status_doenca ADD CONSTRAINT fk_prostadoe_on_prontuario FOREIGN KEY (id_prontuario) REFERENCES prontuario (id);

ALTER TABLE prontuario_status_doenca ADD CONSTRAINT fk_prostadoe_on_status_doenca FOREIGN KEY (id_status_doenca) REFERENCES status_doenca (id);

ALTER TABLE procedimento_tiposregistro ADD CONSTRAINT fk_protip_on_procedimento FOREIGN KEY (procedimento_id) REFERENCES procedimento (codigo);

ALTER TABLE procedimento_tiposregistro ADD CONSTRAINT fk_protip_on_registro_sigtap FOREIGN KEY (registro_id) REFERENCES registro_sigtap (codigo);

ALTER TABLE prontuario_uso_continuo_medicamento ADD CONSTRAINT fk_prousoconmed_on_prontuario FOREIGN KEY (id_prontuario) REFERENCES prontuario (id);

ALTER TABLE prontuario_uso_continuo_medicamento ADD CONSTRAINT fk_prousoconmed_on_uso_continuo_medicamento FOREIGN KEY (id_uso_continuo_medicamento) REFERENCES uso_continuo_medicamento (id);

ALTER TABLE usuario_role ADD CONSTRAINT fk_usurol_on_role FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE usuario_role ADD CONSTRAINT fk_usurol_on_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id);