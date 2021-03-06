//Funções para o autocomplete dos Procedimentos

//Variável que guarda os Ids dos Procedimentos adicionados;
//var idProcedimentos = [];

var cpf = $("#cpfCidadao").val();
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

$("#medicamentosPrescritos").click(function () {
    const father = document.getElementById('conteudo');

    father.innerHTML = "";
    $.ajax({
        url: "http://localhost:9090/api/receitamedicamentos/cpf/" + cpf,
        error: function(){
            createElement('p', father, [{key: 'class', value: 'col-md4'}], `<p>Não Encontrado medicamentos prescritos ao referente Cidadão</p>`)
        },
        success: function (data) {
            const len = data.length;
            $.each(data, function (pos, object) {
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
                const dateForm = checkNumber(dataPrescicao.getDate()) + "/" + checkNumber(dataPrescicao.getMonth() + 1) + "/" + dataPrescicao.getFullYear() + " | " + checkNumber(dataPrescicao.getHours()) + ':'
                    + checkNumber(dataPrescicao.getMinutes()) + ":" + checkNumber(dataPrescicao.getSeconds())
                createElement('div', row, [{key: 'class', value: 'col-md-4'}], `<strong>Data e hora prescrição: </strong>
                                                     <p>${dateForm}</p>`);

                createElement('div', row, [{key: 'class', value: 'col-md-6'}], `<strong>Orientações: </strong>
                                                     <p>${object.recomendacao}</p>`);

                if (pos < len-1){
                    createElement('hr', father, [{key: 'class', value: 'col-md-11'}], '');
                }
            })


        },

    })
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

function exames(url){
    const father = document.getElementById('conteudo');

    father.innerHTML = "";

    $.ajax({
        url: url + cpf,
        error: function(){
            createElement('p', father, [{key: 'class', value: 'col-md4'}], `<p>Não Encontrado o Exame referente ao Cidadão</p>`)
        },
        success: function (data) {
            const len = data.length;
            $.each(data, function (pos, object) {
                console.log(object)
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

                const date = new Date(object.dataSolicitacao);

                const dateForm = checkNumber(date.getDate()) + "/"
                    + checkNumber(date.getMonth() + 1) + "/"+ date.getFullYear()

                createElement('div', row, [{key: 'class', value: 'col-md-4'}], `<strong>Data da Solicitação: </strong>
                                                     <p>${dateForm}</p>`);

                if (object.dataResultado != null){
                    const date = new Date(object.dataResultado);

                    const dateFormResult = checkNumber(date.getDate()) + "/"
                        + checkNumber(date.getMonth() + 1) + "/"+ date.getFullYear()

                    createElement('div', row, [{key: 'class', value: 'col-md-4'}], `<strong>Data do Resultado: </strong>
                                                     <p>${dateFormResult}</p>`)

                    createElement('div', row, [{key: 'class', value: 'col-md-6'}], `<strong>Resultado: </strong>
                                                     <p>${object.resultado}</p>`)
                }

                if (pos < len-1){
                    createElement('hr', father, [{key: 'class', value: 'col-md-11'}], '');
                }

            })
        },

    })
}

$("#examesSolicitados").click(function () {
    exames('http://localhost:9090/api/exames/cpf/solicitations/')
});

$("#examesResults").click(function () {
    exames('http://localhost:9090/api/exames/cpf/results/')
});

function checkNumber(value){
    if (value < 10){
        return "0"+ value;
    }
    return value;
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
                        + item.nomeprocedimento
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
