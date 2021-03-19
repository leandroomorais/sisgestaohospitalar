
$("#historico-pdf").click(function () {
    const examesS = PDFUtil.extractOfArrayToKey(examesSolicitations, {
        procedimento: {
            nomeProcedimento: '',
            dtCompetencia: '',
            procedimentoFormaOrganizacional: {
                noProcedFormaOrganizacional: ''
            },
            procedimentoSubGrupo: {
                noProcedSubGrupo: ''
            },
        },
        dataSolicitacao: ''
    });
    const examesR = PDFUtil.extractOfArrayToKey(examesResult, {
        procedimento: {
            nomeProcedimento: '',
            dtCompetencia: '',
            procedimentoFormaOrganizacional: {
                noProcedFormaOrganizacional: ''
            },
            procedimentoSubGrupo: {
                noProcedSubGrupo: ''
            },
        },
        dataSolicitacao: '',
        dataResultado: '',
        resultado: ''
    });
    const medicamentoPres = PDFUtil.extractOfArrayToKey(receitas, {
        medicamento:{
            principio_ativo: '',
            concentracao: '',
            formaFarmaceutica: {
                nomeFormaFarmaceutica: '',
            }
        }
    })

    PDF.config(PDFConfig.orientation.p, PDFConfig.unit.cm, PDFConfig.format.a4)

    PDF.createTitle('MUNICÍPIO DE MODELO\n' +
        'SECRETARIA MUNICIPAL DE SAÚDE\n' +
        'HOSPITAL MUNICIPAL DE MODELO', {
        align: 'center',
        fontStyle: 'bold'
    })

    PDF.createSpace();

    PDF.createSpace();

    PDF.createText(`CIDADÃO: ${user.nome}`.toUpperCase(),
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left'});

    PDF.createText(`CPF.: ${user.cpf}\tCNS.: ${user.cns}\tDATA NASCIMENTO: ${user.datanascimento}`,
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left'});

    PDF.createText('HISTÓRICO DE ATENDIMENTO NA ATENÇÃO BÁSICA',
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'center'})

    PDF.createText('EXAMES SOLICITADOS',
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left'})

    PDF.createTable(Extract.extractToKey([
        'Procedimento'.toUpperCase(),
        'Procedimento de Forma Organizacional'.toUpperCase(),
        'Procedimento Sub Grupo'.toUpperCase(),
        'Data Competencia do Procedimento'.toUpperCase(),
        'Data da Solicitação'.toUpperCase(),
    ]), examesS);

    PDF.createSpace();

    PDF.createText('RESULTADOS DE EXAMES',
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left'})

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
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left'})
    PDF.createTable(Extract.extractToKey([
        'Principio ativo'.toUpperCase(),
        'Concentração'.toUpperCase(),
        'Forma'.toUpperCase(),
    ]), medicamentoPres, {
        text: 'Conteúdo Medicamento prescrito',
        options: {
            auto: true,
        }
    });

    PDF.createFooter('SGH - Versão 1.0 Beta SGH -',
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '10', align: 'left'});

    PDF.createFooter('Relatorio Emitido às '+formTime(new Date())+'MIN do dia '+formDate(new Date())+'.',
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '10', align: 'left'});

    PDF.createFooter('OBSERVAÇÃO:',
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '10', align: 'left'});

    PDF.createFooter('DADOS CONSULTADOS NA BASE DE DADOS DO E-SUS',
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '10', align: 'left'});

    PDF.save('TESTE');
});
