
$("#historico-pdf").click(function() {
	const examesS = PDFUtil.extractOfArrayToKey(examesSolicitations.data, {
		procedimento: {
			nomeProcedimento: '',
			procedimentoFormaOrganizacional: {
				noProcedFormaOrganizacional: ''
			},
			procedimentoSubGrupo: {
				noProcedSubGrupo: ''
			},
			dtCompetencia: '',
		},
		dataSolicitacao: ''
	});
	const examesR = PDFUtil.extractOfArrayToKey(examesResult.data, {
		procedimento: {
			nomeProcedimento: '',
			procedimentoFormaOrganizacional: {
				noProcedFormaOrganizacional: ''
			},
			procedimentoSubGrupo: {
				noProcedSubGrupo: ''
			},
			dtCompetencia: '',
		},
		dataSolicitacao: '',
		dataResultado: '',
		resultado: ''
	});
	const medicamentoPres = PDFUtil.extractOfArrayToKey(receitas.data, {
		medicamento: {
			principio_ativo: '',
			concentracao: '',
			formaFarmaceutica: {
				nomeFormaFarmaceutica: '',
			}
		},
		posologia: '',
		recomendacao: ''
	})

	PDF.config()

	PDF.createFooterAutoPage([
		{
			text: 'SGH - Versão 1.0 Beta SGH -',
			options: { fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '10', align: 'left' }
		},
		{
			text: 'Relatório Emitido às ' + PDFUtil.formTime(new Date()) + 'MIN do dia ' + PDFUtil.formDate(new Date()) + '.',
			options: { fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '10', align: 'left' }
		},
		{
			text: 'OBSERVAÇÃO:',
			options: { fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '10', align: 'left' }
		},
		{
			text: 'DADOS CONSULTADOS NA BASE DE DADOS DO E-SUS',
			options: {
				fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '10', align: 'left'
			}
		}
	])

	PDF.createTitle('MUNICÍPIO DE MODELO\n' +
		'SECRETARIA MUNICIPAL DE SAÚDE\n' +
		'HOSPITAL MUNICIPAL DE MODELO', {
		align: 'center',
		le: 'bold'
	})

	PDF.createSpace();

	PDF.createSpace();

	PDF.createText(`CIDADÃO: ${user.nome}`.toUpperCase(),
		{ fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left' });

	PDF.createText(`CPF.: ${user.cpf}\tCNS.: ${user.cns}\tDATA NASCIMENTO: ${user.datanascimento}`,
		{ fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left' });

	PDF.createText('HISTÓRICO DE ATENDIMENTO NA ATENÇÃO BÁSICA',
		{ fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'center' })

	PDF.createText('EXAMES SOLICITADOS',
		{ fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left' })

	PDF.createTable(Extract.extractToKey([
		'Procedimento'.toUpperCase(),
		'Procedimento de Forma Organizacional'.toUpperCase(),
		'Procedimento Sub Grupo'.toUpperCase(),
		'Data Competencia do Procedimento'.toUpperCase(),
		'Data da Solicitação'.toUpperCase(),
	]), examesS, {
		text: 'Conteúdo do Exame Solicitado',
		options: {
			auto: true,
		}
	});

	PDF.createSpace();

	PDF.createText('RESULTADOS DE EXAMES',
		{
			fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left'
		})

	PDF.createTable(Extract.extractToKey([
		'Procedimento'.toUpperCase(),
		'Procedimento de Forma Organizacional'.toUpperCase(),
		'Procedimento Sub Grupo'.toUpperCase(),
		'Data Competencia do Procedimento'.toUpperCase(),
		'Data da Solicitação'.toUpperCase(),
		'Data do Resultado'.toUpperCase(),
		'Resultado'.toUpperCase()
	]), examesR, {
		text: 'Conteúdo do Resultado de Exame',
		options: {
			auto: true,
		}
	});

	PDF.createText("medicamentos prescritos".toUpperCase(),
		{ fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left' })
	PDF.createTable(Extract.extractToKey([
		'Principio ativo'.toUpperCase(),
		'Concentração'.toUpperCase(),
		'Forma'.toUpperCase(),
		'Posologia'.toUpperCase(),
		'recomendação'.toUpperCase(),
	]), medicamentoPres, {
		text: 'Conteúdo Medicamento prescrito',
		options: {
			auto: true,
		}
	});

	PDF.save('Relatório Emitido às ' + PDFUtil.formTime(new Date()) + 'MIN do dia ' + PDFUtil.formDate(new Date()) + '.');
});
