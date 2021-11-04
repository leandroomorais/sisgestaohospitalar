
function checkNotUnderfined(obj) {
	return obj?.data !== undefined
}

function checkNotNull(obj) {
	return obj !== null
}

$("#historico-pdf").click(function () {
	let examesS = null

	if (checkUnderfined(examesSolicitations)) {
		examesS = PDFUtil.extractOfArrayToKey(examesSolicitations.data, {
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
	}

	let examesR = null

	if (checkUnderfined(examesResult)) {
		examesResult = PDFUtil.extractOfArrayToKey(examesResult.data, {
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
	}

	let medicamentoPres = null

	if (checkUnderfined(receitas)) {
		medicamentoPres = PDFUtil.extractOfArrayToKey(receitas.data, {
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
	}

	let vacinasApp = null

	if (checkUnderfined(vacinasAplicadas)) {
		vacinasApp = PDFUtil.extractOfArrayToKey(vacinasAplicadas.data, {
			observacao: '',
			dataAplicacao: '',
			codigoImunobiologico: {
				nomeViadministracaoVacina: '',
				//nomeImunobiologico: '',
				//codigoClasseImunobiologico: ''
			},
			codigoDoseImunobiologico: {
				//sgDoseImunobiologico: '',
				nomeDoseImunobiologico: '',
				//numeroOrdem: ''
			}
		})
	}

	let vacinasAgen = null
	if (checkUnderfined(vacinasAgendadas)) {
		vacinasAgen = PDFUtil.extractOfArrayToKey(vacinasAgendadas.data, {
			observacao: '',
			dataAprazamento: '',
			codigoImunobiologico: {
				nomeViadministracaoVacina: '',
				//nomeImunobiologico: '',
				//codigoClasseImunobiologico: ''
			},
			codigoDoseImunobiologico: {
				//sgDoseImunobiologico: '',
				nomeDoseImunobiologico: '',
				//numeroOrdem: ''
			}
		})
	}

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

	if (checkNotNull(examesS)) {
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
	}

	PDF.createSpace();

	if (checkNotNull(examesR)) {
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
	}

	if (checkNotNull(medicamentoPres)) {
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
	}

	if (checkNotNull(vacinasApp)) {
		PDF.createText("Vacinas Aplicadas".toUpperCase(),
			{ fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left' })

		PDF.createTable(Extract.extractToKey([
			'observação'.toUpperCase(),
			'Data da Aplicação'.toUpperCase(),
			'Vacina'.toUpperCase(),
			'Dose'.toUpperCase(),
			//'recomendação'.toUpperCase(),
		]), vacinasApp, {
			text: 'Conteúdo Vacina aplicada',
			options: {
				auto: true,
			}
		});
	}

	if (checkNotNull(vacinasAgen)) {
		PDF.createText("Vacinas agentadas".toUpperCase(),
			{ fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left' })
		PDF.createTable(Extract.extractToKey([
			'observação'.toUpperCase(),
			'Data da Aprazamento'.toUpperCase(),
			'Vacina'.toUpperCase(),
			'Dose'.toUpperCase(),
			//'recomendação'.toUpperCase(),
		]), vacinasAgen, {
			text: 'Conteúdo Vacina Agendada',
			options: {
				auto: true,
			}
		});
	}

	PDF.save('Relatório Emitido às ' + PDFUtil.formTime(new Date()) + 'MIN do dia ' + PDFUtil.formDate(new Date()) + '.');
});
