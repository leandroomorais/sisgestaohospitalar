
function extractObjectExameSol(objects){
    const objectsPrint = [];

    for (let object of objects){
        objectsPrint.push([
            object.procedimento.nomeProcedimento,
            object.procedimento.dtCompetencia,
            object.procedimento.procedimentoFormaOrganizacional.noProcedFormaOrganizacional,
            object.procedimento.procedimentoSubGrupo.noProcedSubGrupo,
            object.dataSolicitacao,
        ]);
    }

    return objectsPrint
}


$("#historico-pdf").click(function () {
    const examesS = extractObjectExameSol(examesResult);
    file.insertHeader('MUNICÍPIO DE MODELO\n' +
        'SECRETARIA MUNICIPAL DE SAÚDE\n' +
        'HOSPITAL MUNICIPAL DE MODELO');
    file.space();
    file.space();
    file.insertText('CIDADÃO: FRANCISCO LEANDRO DE MORAIS PINTO',
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left'});
    file.insertText("CPF.: 098.143.544-06\tCNS.: 789.2154.8765.5646\tDATA NASCIMENTO: 20/06/1994",
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left'});
    file.space();
    file.insertText('HISTÓRICO DE ATENDIMENTO NA ATENÇÃO BÁSICA',
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'center'})
    file.space();
    file.insertText('EXAMES SOLICITADOS',
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left'})

     file.insertTableDefault([
        'Procedimento'.toUpperCase(),
        'Procedimento de Forma Organizacional'.toUpperCase(),
        'Procedimento Sub Grupo'.toUpperCase(),
        'Data Competencia do Procedimento'.toUpperCase(),
        'Data da Solicitação'.toUpperCase(),
    ], examesS);
    console.log(pdf)
    file.insertText('RESULTADOS DE EXAMES',
        {fontStyle: 'bold', fontName: 'Times New Roman', fontSize: '12', align: 'left'})
    file.insertFooter('cep: 59900-00 - pau dos ferros - rn'.toUpperCase(),
        {align: 'left'});
    file.insertFooter('rua: José paiva lourdes'.toUpperCase(),
        {align: 'left'});
    file.save('TESTE');

    file.clear();
});
