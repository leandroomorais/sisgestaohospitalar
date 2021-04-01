//Funções para o autocomplete dos Procedimentos

//Variável que guarda os Ids dos Procedimentos adicionados;
//var idProcedimentos = [];


var div = $('<div class="col-md-4"><strong>Principio ativo: </strong><p id="principioAtivo"></p>' +
    '</div><div class="col-md-3"><strong>Forma: </strong><p id="forma"></p></div>' +
    '<div class="col-md-3"><strong>Concentração: </strong><p id="concentracao"></p></div>' +
    '<div class="col-md-4"><strong>Posologia: </strong><p id="posologia"></p></div>' +
    '<div class="col-md-4"><strong>Data e hora prescrição: </strong><p id="prescricao">' +
    '</p></div><div class="col-md-6"><strong>Orientações: </strong><p id="orientacao"></p></div>');
var divExames = $('' +
    '   <div class="col-md-4">' +
    '       <strong>Procedimento: </strong>' +
    '       <p id="procedimento"></p>' +
    '   </div>' +
    '   <div class="col-md-3">' +
    '       <strong>Procedimento de Forma Organizacional: </strong>' +
    '       <p id="procedimentoFormaOrg">' +
    '       </p>' +
    '   </div>' +
    '   <div class="col-md-3">' +
    '       <strong>Procedimento Sub Grupo: </strong>' +
    '       <p id="procedimentoSubGrupo"></p>' +
    '   </div>' +
    '   <div class="col-md-4">' +
    '       <strong>Data Competencia do Procedimento: </strong>' +
    '       <p id="dataCompetencia"></p>' +
    '   </div>' +
    '   <div class="col-md-4">' +
        '   <strong>Data e hora da Solicitação: </strong>' +
        '   <p id="dataHoraSolicitacao"></p>' +
    '   </div>', '<hr />');

$("#diagnosticos").click(function () {
    const father = clear();

    if (avaliacoes.length > 0){
        const len = avaliacoes.length;
        $.each(avaliacoes, function (pos, object) {
            const row = createElement('div', father, [{key: 'class', value: 'row'}], '');

            const {atendimentoProfissional, ciap, cid10} = object;
            const {dataFim, dataInicio} = atendimentoProfissional;
            const {dsCiap, codicoCiap, sexo, codCid10Encaminhamento} = ciap;

            createElement("div", row, [{key: 'class', value: 'col-md-4'}],
                `<strong>Diagnóstico: </strong><p>${dsCiap}</p>`)
            createElement('div', row, [{key: 'class', value: 'col-md-4'}],
                `<strong>Sexo: </strong><p>${sexo}</p>`)
            createElement('div', row, [{key: 'class', value: 'col-md-4'}],
                `<strong>Código CIAP: </strong><p>${codicoCiap}</p>`)
            createElement('div', row, [{key: 'class', value: 'col-md-4'}],
                `<strong>Código Cid10 encaminhamento: </strong><p>${codCid10Encaminhamento}</p>`)

            const dateFimForm = new Date(dataFim);
            const dateInicioForm = new Date(dataInicio)
            createElement('div', row, [{key: 'class', value: 'col-md-4'}],
                `<strong>Data inicial do Atendimento: </strong><p>${formDateTime(dateInicioForm)}</p>`)

            createElement('div', row, [{key: 'class', value: 'col-md-4'}],
                `<strong>Data final do Atendimento: </strong><p>${formDateTime(dateFimForm)}</p>`)

            if (cid10 != null){
                const {nuCid, noCid10} = cid10;
                createElement('div', row, [{key: 'class', value: 'col-md-4'}],
                    `<strong>Nome Cid10: </strong><p>${noCid10}</p>`)

                createElement('div', row, [{key: 'class', value: 'col-md-4'}],
                    `<strong>Número Cid10: </strong><p>${nuCid}</p>`)

                createElement('div', row, [{key: 'class', value: 'col-md-4'}],
                    `<strong>Sexo Cid10: </strong><p>${cid10.sexo}</p>`)
            }

            if (pos < len-1){
                createElement('hr', father, [{key: 'class', value: 'col-md-11'}], '');
            }
        })
    } else {
        createElement('p', father, [{key: 'class', value: 'col-md4'}],
            notFound('Não Encontrado diagnósticos referente ao Cidadão'))
    }
});

function clear(){
    const father = document.getElementById('conteudo');

    father.innerHTML = "";

    return father;
}

function notFound(text) {
    return `<div class="pl-4"><p class="text-info"> <i class="fas fa-exclamation-circle"></i>${text}</p></div>`;
}

function createMedicamentosPrescritos(){
    const father = clear();
    if (receitas.length > 0){
        const len = receitas.length;
        $.each(receitas, function (pos, object) {
            const {medicamento, posologia} = object;
            const {principio_ativo, concentracao} = medicamento;
            const {nomeFormaFarmaceutica} = medicamento.formaFarmaceutica;
            const row = createElement('div', father, [{key: 'class', value: 'row'}], '');

            createElement("div", row, [{key: 'class', value: 'col-md-4'}], `<strong>Principio ativo: </strong><p>${principio_ativo}</p>`)
            createElement('div', row, [{key: 'class', value: 'col-md-3'}],  `<strong>Forma: </strong>
                                                     <p>${nomeFormaFarmaceutica}</p>`);

            createElement('div', row, [{key: 'class', value: 'col-md-3'}], `<strong>Concentração: </strong>
                                                     <p>${concentracao}</p>`);

            createElement('div', row, [{key: 'class', value: 'col-md-4'}], `<strong>Posologia: </strong>
                                                     <p>${posologia}</p>`);
            const dataPrescicao = new Date(object.atendimentoProfissional.dataFim);

            createElement('div', row, [{key: 'class', value: 'col-md-4'}], `<strong>Data e hora prescrição: </strong>
                                                     <p>${formDateTime(dataPrescicao)}</p>`);

            createElement('div', row, [{key: 'class', value: 'col-md-6'}], `<strong>Orientações: </strong>
                                                     <p>${object.recomendacao}</p>`);

            if (pos < len-1){
                createElement('hr', father, [{key: 'class', value: 'col-md-11'}], '');
            }
        })
    } else {
        createElement('p', father, [{key: 'class', value: 'col-md4'}], notFound('Não Encontrado medicamentos prescritos ao referente Cidadão'))
    }
}

$("#medicamentosPrescritos").click(function () {
    createMedicamentosPrescritos();
});

function createElement(tagName, father, options, value){
    const element = document.createElement(tagName);

    for (let i = 0; i < options.length; i++){
        element.setAttribute(options[i].key, options[i].value)
    }

    element.innerHTML = value;
    father.appendChild(element);

    return element;
}

function createExame(data, father){
    const len = data.length;
    $.each(data, function (pos, object) {
        const row = createElement('div', father, [{key: 'class', value: 'row'}], '');
        const {nomeProcedimento, dtCompetencia, procedimentoFormaOrganizacional, procedimentoSubGrupo} = object.procedimento;

        createElement("div", row, [{key: 'class', value: 'col-md-4'}], `<strong>Procedimento: </strong><p>${nomeProcedimento}</p>`)


        createElement('div', row, [{key: 'class', value: 'col-md-3'}], `<strong>Procedimento de Forma Organizacional: </strong>
                                                     <p>${procedimentoFormaOrganizacional.noProcedFormaOrganizacional}</p>`);

        createElement('div', row,
            [{key: 'class', value: 'col-md-3'}], `<strong>Procedimento Sub Grupo: </strong>
                                                     <p>${procedimentoSubGrupo.noProcedSubGrupo}</p>`);

        createElement('div', row, [{key: 'class', value: 'col-md-4'}], `<strong>Data Competencia do Procedimento: </strong>
                                                     <p>${dtCompetencia}</p>`);

        createElement('div', row, [{key: 'class', value: 'col-md-4'}], `<strong>Data da Solicitação: </strong>
                                                     <p>${object.dataSolicitacao}</p>`);

        if (object.dataResultado != null){
            createElement('div', row, [{key: 'class', value: 'col-md-4'}], `<strong>Data do Resultado: </strong>
                                                     <p>${object.dataResultado}</p>`)

            createElement('div', row, [{key: 'class', value: 'col-md-6'}], `<strong>Resultado: </strong>
                                                     <p>${object.resultado}</p>`)
        }

        if (pos < len-1){
            createElement('hr', father, [{key: 'class', value: 'col-md-11'}], '');
        }

    })
}

$("#examesSolicitados").click(function () {
    const father = clear();
    if(examesSolicitations.length > 0){
        createExame(examesSolicitations, father)
    } else {
        createElement('p', father, [{key: 'class', value: 'col-md4'}],
            notFound('Não Encontrado o Exame referente ao Cidadão'))
    }
});

$("#examesResults").click(function () {
    const father = clear();
    if (examesResult.length > 0){
        createExame(examesResult, father)
    } else {
        createElement('p', father,
            [{key: 'class', value: 'col-md4'}], notFound('Não Encontrado o Exame referente ao Cidadão'))
    }
});

$('#v-pills-home-tab').click(function () {
    createMedicamentosPrescritos()
})

function checkNumber(value){
    if (value < 10){
        return "0"+ value;
    }
    return value;
}

function formDate(date){
    try {
        const dateForm = new Date(date);
        return checkNumber(dateForm.getDate())
            + "/" + checkNumber(dateForm.getMonth() + 1)
            + "/" + dateForm.getFullYear();
    }catch (e) {
        throw new DOMException('Informe um objeto data válido');
    }
}

function formTime(date){
    try {
        const dateForm = new Date(date);
        return checkNumber(dateForm.getHours()) + ":" + checkNumber(dateForm.getMinutes()) + ":";
    }catch (e) {
        throw new DOMException('Informe um objeto data válido');
    }
}

function formDateTime(date){
    try {
        const dateForm = new Date(date);
        return formDate(dateForm) + " | "
            + checkNumber(dateForm.getHours()) + ":" + checkNumber(dateForm.getMinutes()) + ":"
            + checkNumber(dateForm.getSeconds());
    }catch (e) {
        throw new DOMException('Informe um objeto data válido');
    }
}

$(document).ready(function () {
    // Autocomplete dos Procedimentos
    $(function () {
        $("#procedimentos-atendimento-medico")
            .on("keydown", function (event) {
                $(this).autocomplete("instance")._renderItem = function (select, item) {
                    return $("<option>").append("<div>"
                        + item.codigoprocedimento
                        + " - "
                        + item.nomeprocedimentoo
                        + "</div>").appendTo(select);
                };
            })
            .autocomplete({
                source: "/procedimento-resource/search",
                focus: function (event, ui) {
                    $("#procedimentos-atendimento-medico").val(ui.item.nomeprocedimento);
                    return false;
                },
                select: function (event, ui) {
                    console.log("Id do Procedimento: " + ui.item.id);
                    var procedimento = {};
                    procedimento.id = ui.item.id;
                    $.ajax({
                        method: "POST",
                        url: "/atendimento-medico/add-procedimento",
                        data: procedimento,
                        success: function () {
                            console.log('Enviado com sucesso');
                        },
                        error: function (xhr) {
                            console.log('Erro >', xhr.reponseText);
                        },
                    })

                    $("#procedimentos-atendimento-medico").val("");

                    $("#table-procedimento-atendimento-medico").append('<tr id="'
                        + ui.item.id
                        + '"><td>'
                        + ui.item.codigoprocedimento
                        + '</td>'
                        + '<td>'
                        + ui.item.nomeprocedimento
                        + '</td>'
                        + '<td>'
                        + '<button type="button" onclick="removeProcedimentoAtendimentoMedico(this)" class="btn btn-icon btn-round btn-danger"><i class="fa fa-trash"></i></button>'
                        + '</td>'
                        + '</tr>');

                    return false;


                }
            });
    });

    // Função para remover os Procedimentos da Tabela de
    // Procedimentos Realizados e do Array de Procedimentos
    (function ($) {
        var procedimento = {};
        removeProcedimentoAtendimentoMedico = function (item) {
            swal({
                title: 'Tem certeza que deseja remover esse procedimento?',
                type: 'warning',
                buttons: {
                    cancel: {
                        visible: true,
                        text: 'Não, cancelar!',
                        className: 'btn btn-danger'
                    },
                    confirm: {
                        text: 'Sim, remova!',
                        className: 'btn btn-success'
                    }
                }
            }).then((willDelete) => {
                if (willDelete) {
                    var tr = $(item).closest('tr');
                    var id = $(item).parent().parent().attr("id");
                    procedimento.id = id;
                    $.ajax({
                        method: "DELETE",
                        url: "/atendimento-medico/delete-procedimento",
                        data: procedimento,
                        success: function () {
                            console.log('Deletado com sucesso');
                        },
                        error: function (xhr) {
                            console.log('Erro >', xhr.reponseText);
                        },
                    });

                    tr.fadeOut(400, function () {
                        tr.remove();
                    });
                    swal("Procedimento removido com sucesso!", {
                        icon: "success",
                        buttons: {
                            confirm: {
                                className: 'btn btn-success'
                            }
                        }
                    });
                }
            });


            return false;
        }
    })(jQuery);
})


function createVacina(data, father){
    const len = data.length;
    $.each(data, function (pos, object) {
        const row = createElement('div', father, [{key: 'class', value: 'row'}], '');
        const {nomeProcedimento, dtCompetencia, procedimentoFormaOrganizacional, procedimentoSubGrupo} = object.procedimento;

        createElement("div", row, [{key: 'class', value: 'col-md-4'}], `<strong>Procedimento: </strong><p>${nomeProcedimento}</p>`)


        createElement('div', row, [{key: 'class', value: 'col-md-3'}], `<strong>Procedimento de Forma Organizacional: </strong>
                                                     <p>${procedimentoFormaOrganizacional.noProcedFormaOrganizacional}</p>`);

        createElement('div', row,
            [{key: 'class', value: 'col-md-3'}], `<strong>Procedimento Sub Grupo: </strong>
                                                     <p>${procedimentoSubGrupo.noProcedSubGrupo}</p>`);

        createElement('div', row, [{key: 'class', value: 'col-md-4'}], `<strong>Data Competencia do Procedimento: </strong>
                                                     <p>${dtCompetencia}</p>`);

        createElement('div', row, [{key: 'class', value: 'col-md-4'}], `<strong>Data da Solicitação: </strong>
                                                     <p>${object.dataSolicitacao}</p>`);

        if (object.dataResultado != null){
            createElement('div', row, [{key: 'class', value: 'col-md-4'}], `<strong>Data do Resultado: </strong>
                                                     <p>${object.dataResultado}</p>`)

            createElement('div', row, [{key: 'class', value: 'col-md-6'}], `<strong>Resultado: </strong>
                                                     <p>${object.resultado}</p>`)
        }

        if (pos < len-1){
            createElement('hr', father, [{key: 'class', value: 'col-md-11'}], '');
        }

    })
}














