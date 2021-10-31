//Funções para o autocomplete dos Procedimentos

//Variável que guarda os Ids dos Procedimentos adicionados;
//var idProcedimentos = [];

$("#diagnosticos").click(function () {
    const father = clear();

    if (avaliacoes.data.length > 0 && avaliacoes.data !== null) {
        const len = avaliacoes.data.length;
        $.each(avaliacoes.data, function (pos, object) {
            const row = createElement('div', father, { class: 'row' }, '');

            const { atendimentoProfissional, ciap, cid10 } = object;
            const { dataFim, dataInicio } = atendimentoProfissional;
            const { dsCiap, codicoCiap, sexo, codCid10Encaminhamento } = ciap;

            createElement("div", row, { class: 'col-md-4' },
                `<strong>Diagnóstico: </strong><p>${dsCiap}</p>`)
            createElement('div', row, { class: 'col-md-4' },
                `<strong>Sexo: </strong><p>${sexo}</p>`)
            createElement('div', row, { class: 'col-md-4' },
                `<strong>Código CIAP: </strong><p>${codicoCiap}</p>`)
            createElement('div', row, { class: 'col-md-4' },
                `<strong>Código Cid10 encaminhamento: </strong><p>${codCid10Encaminhamento}</p>`)

            const dateFimForm = new Date(dataFim);
            const dateInicioForm = new Date(dataInicio)
            createElement('div', row, { class: 'col-md-4' },
                `<strong>Data inicial do Atendimento: </strong><p>${formDateTime(dateInicioForm)}</p>`)

            createElement('div', row, { class: 'col-md-4' },
                `<strong>Data final do Atendimento: </strong><p>${formDateTime(dateFimForm)}</p>`)

            if (cid10 != null) {
                const { nuCid, noCid10 } = cid10;
                createElement('div', row, { class: 'col-md-4' },
                    `<strong>Nome Cid10: </strong><p>${noCid10}</p>`)

                createElement('div', row, { class: 'col-md-4' },
                    `<strong>Número Cid10: </strong><p>${nuCid}</p>`)

                createElement('div', row, { class: 'col-md-4' },
                    `<strong>Sexo Cid10: </strong><p>${cid10.sexo}</p>`)
            }

            if (pos < len - 1) {
                createElement('hr', father, { class: 'col-md-11' }, '');
            }
        })
    } else {
        createElement('p', father, { class: 'col-md4' },
            notFound('Não Encontrado diagnósticos referente ao Cidadão'))
    }
});

function clear() {
    const father = document.getElementById('conteudo');

    father.innerHTML = "";

    return father;
}

function notFound(text) {
    return `<span class="pl-4"><p class="text-info"> <i class="fas fa-exclamation-circle"></i>${text}</p></span>`;
}

function createMedicamentosPrescritos() {
    const father = clear();
    if (receitas.data.length > 0 && receitas.data !== null) {
        const len = receitas.data.length;
        $.each(receitas.data, function (pos, object) {
            const { medicamento, posologia } = object;
            const { principio_ativo, concentracao } = medicamento;
            const { nomeFormaFarmaceutica } = medicamento.formaFarmaceutica;
            const row = createElement('div', father, { class: 'row' }, '');

            createElement("div", row, { class: 'col-md-4' }, `<strong>Principio ativo: </strong><p>${principio_ativo}</p>`)
            createElement('div', row, { class: 'col-md-3' }, `<strong>Forma: </strong>
                                                     <p>${nomeFormaFarmaceutica}</p>`);

            createElement('div', row, { class: 'col-md-3' }, `<strong>Concentração: </strong>
                                                     <p>${concentracao}</p>`);

            createElement('div', row, { class: 'col-md-4' }, `<strong>Posologia: </strong>
                                                     <p>${posologia}</p>`);
            const dataPrescicao = new Date(object.atendimentoProfissional.dataFim);

            createElement('div', row, { class: 'col-md-4' }, `<strong>Data e hora prescrição: </strong>
                                                     <p>${formDateTime(dataPrescicao)}</p>`);

            createElement('div', row, { class: 'col-md-6' }, `<strong>Orientações: </strong>
                                                     <p>${object.recomendacao}</p>`);

            if (pos < len - 1) {
                createElement('hr', father, { class: 'col-md-11' }, '');
            }
        })
    } else {
        createElement('p', father, {
            class: 'col-md4'
        }, notFound('Não Encontrado medicamentos prescritos ao referente Cidadão'))
    }
}

$("#medicamentosPrescritos").click(function () {
    createMedicamentosPrescritos();
});

function createElement(tagName, father, attrs, innerHTML) {
    const tag = new Tag({ tagName: tagName, attrs: attrs })
    const view = new TagView(tag)
    const element = View.append(view.element, father)
    element.innerHTML = innerHTML
    return element;
}

function createExame(data, father) {
    const len = data.length;
    $.each(data, function (pos, object) {
        const row = createElement('div', father, { class: 'row' }, '');
        const { nomeProcedimento, dtCompetencia, procedimentoFormaOrganizacional, procedimentoSubGrupo } = object.procedimento;

        createElement("div", row, { class: 'col-md-4' }, `<strong>Procedimento: </strong><p>${nomeProcedimento}</p>`)


        createElement('div', row, { class: 'col-md-3' }, `<strong>Procedimento de Forma Organizacional: </strong>
                                                     <p>${procedimentoFormaOrganizacional.noProcedFormaOrganizacional}</p>`);

        createElement('div', row,
            { class: 'col-md-3' }, `<strong>Procedimento Sub Grupo: </strong>
                                                     <p>${procedimentoSubGrupo.noProcedSubGrupo}</p>`);

        createElement('div', row, { class: 'col-md-4' }, `<strong>Data Competencia do Procedimento: </strong>
                                                     <p>${dtCompetencia}</p>`);

        createElement('div', row, { class: 'col-md-4' }, `<strong>Data da Solicitação: </strong>
                                                     <p>${object.dataSolicitacao}</p>`);

        if (object.dataResultado != null) {
            createElement('div', row, { class: 'col-md-4' }, `<strong>Data do Resultado: </strong>
                                                     <p>${object.dataResultado}</p>`)

            createElement('div', row, { class: 'col-md-6' }, `<strong>Resultado: </strong>
                                                     <p>${object.resultado}</p>`)
        }

        if (pos < len - 1) {
            createElement('hr', father, { class: 'col-md-11' }, '');
        }

    })
}

$("#examesSolicitados").click(function () {
    const father = clear();
    if (examesSolicitations.data.length > 0) {
        createExame(examesSolicitations.data, father)
    } else {
        createElement('p', father, { class: 'col-md4' },
            notFound('Não Encontrado o Exame referente ao Cidadão'))
    }
});

$("#examesResults").click(function () {
    const father = clear();
    if (examesResult.data.length > 0) {
        createExame(examesResult.data, father)
    } else {
        createElement('p', father,
            { class: 'col-md4' }, notFound('Não Encontrado o Exame referente ao Cidadão'))
    }
});

$('#v-pills-home-tab').click(function () {
    createMedicamentosPrescritos()
})

function checkNumber(value) {
    if (value < 10) {
        return "0" + value;
    }
    return value;
}

function formDate(date) {
    try {
        const dateForm = new Date(date);
        return checkNumber(dateForm.getDate())
            + "/" + checkNumber(dateForm.getMonth() + 1)
            + "/" + dateForm.getFullYear();
    } catch (e) {
        throw new DOMException('Informe um objeto data válido');
    }
}

function formTime(date) {
    try {
        const dateForm = new Date(date);
        return checkNumber(dateForm.getHours()) + ":" + checkNumber(dateForm.getMinutes()) + ":";
    } catch (e) {
        throw new DOMException('Informe um objeto data válido');
    }
}

function formDateTime(date) {
    try {
        const dateForm = new Date(date);
        return formDate(dateForm) + " | "
            + checkNumber(dateForm.getHours()) + ":" + checkNumber(dateForm.getMinutes()) + ":"
            + checkNumber(dateForm.getSeconds());
    } catch (e) {
        throw new DOMException('Informe um objeto data válido');
    }
}

$(document).ready(function () {
    //Exibe Idade atual e data de Nascimento

    $("#show-result").hide()
    $("#show-button-pdf").hide()
    var dtNascimento = $("#dataNascimentoCidadao").val();
    var ano_aniversario = dtNascimento.split('-')[0];
    var mes_aniversario = dtNascimento.split('-')[1];
    var dia_aniversario = dtNascimento.split('-')[2];
    $("#idadeCidadao").text(" " + calculaIdade(ano_aniversario, mes_aniversario, dia_aniversario));
    $("#dtNascimento").text(" " + dia_aniversario + "/" + mes_aniversario + "/" + ano_aniversario);
    function calculaIdade(ano_aniversario, mes_aniversario, dia_aniversario) {
        var objData = new Date();
        ano_atual = objData.getFullYear();
        mes_atual = objData.getMonth() + 1;
        dia_atual = objData.getDate();
        ano_aniversario = +ano_aniversario;
        mes_aniversario = +mes_aniversario;
        dia_aniversario = +dia_aniversario;
        numDiasMesAnt = new Date(ano_atual, mes_atual - 1, 0).getDate();
        idade = ano_atual - ano_aniversario;
        var strMes = "mês";
        var strDia = "dia";
        var strAno = "ano";
        if (ano_aniversario == ano_atual && mes_aniversario == mes_atual && dia_aniversario == dia_atual) {
            return "0 dias";
        }
        if (idade < 1 && dia_atual < dia_aniversario || idade < 1 && dia_atual == dia_aniversario) {
            qtdMeses = (mes_atual - mes_aniversario) - 1;
            qtdDias = (numDiasMesAnt - (dia_aniversario - dia_atual));
            if (qtdMeses > 1) {
                strMes = "meses";
            }
            if (qtdDias > 1) {
                strDia = "dias";
            }
            return qtdMeses == 0 ? qtdDias + " " + strDia : qtdMeses + " " + strMes + " e " + qtdDias + " " + strDia;
        }
        if (idade < 1 && dia_atual > dia_aniversario) {
            qtdMeses = (mes_atual - mes_aniversario);
            qtdDias = (dia_atual - dia_aniversario);
            if (qtdMeses > 1) {
                strMes = "meses";
            }
            if (qtdDias > 1) {
                strDia = "dias";
            }
            return qtdMeses == 0 ? qtdDias + " " + strDia : qtdMeses + " " + strMes + " e " + qtdDias + " " + strDia;
        }
        if (idade >= 1 && dia_atual < dia_aniversario) {
            qtdMeses = (mes_atual - mes_aniversario) - 1;
            qtdDias = (numDiasMesAnt - (dia_aniversario - dia_atual));
            if (qtdMeses > 1) {
                strMes = "meses";
            }
            if (qtdDias > 1) {
                strDia = "dias";
            }
            if (idade > 1) {
                strAno = "anos";
            }
            return qtdMeses == 0 ? idade + " " + strAno + " e " + qtdDias + " " + strDia : idade + " " + strAno + ", " + qtdMeses + " " + strMes + " e " + qtdDias + " " + strDia;
        }
        if (idade > 1 && dia_atual > dia_aniversario) {
            qtdMeses = (mes_atual - mes_aniversario);
            qtdDias = (dia_atual - dia_aniversario);
            if (qtdMeses > 1) {
                strMes = "meses";
            }
            if (qtdDias > 1) {
                strDia = "dias";
            }
            if (idade > 1) {
                strAno = "anos";
            }
            return idade + " " + strAno + ", " + qtdMeses + " " + strMes + " e " + qtdDias + " " + strDia;
        }
    }
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

function tab(title, father, data, index) {
    const element = father.parentElement.children.item(index)
    if (element !== null) {
        element.remove()
    }
    const div = new TagView(new Tag({ tagName: 'div', attrs: { class: 'tab-content mt-2 row pl-4' } }))
    View.append(div.element, father.parentElement, index)
    //tabs(index)
    createVacina(title, data, div.element)
}


function tabs(index) {
    try {
        const element = $('.nav-link, .active, .show')
        if (element[index]) {
            const tag = element[index]
            const attrs = tag.attributes
            showTabs(attrs, tag, 'border-tab')
        }
    } catch (error) {

    }
}

function createVacinas(father, dataAplicacao, dataAgendada) {
    tab("Vacinas Aplicadas", father, dataAplicacao, 3)
    tab("Vacinas Agendadas", father, dataAgendada, 4)
}

$("#vacinas").click(function () {
    const father = clear();
    createVacinas(father, vacinasAplicadas.data, vacinasAgendadas.data)
});


function createVacina(title, data, father) { 

    createElement('div', father, {class: 'card-header col-md-12'}, "<div class='card-title'>"+title+"</div>")

    if (data === null || data?.length === 0 || data === undefined) {
        const row = createElement('div', father, {class: 'row'}, '')
        createElement('div', row, {class: 'col-md-12'}, notFound('Não Encontrado as vacinas referente ao Cidadão'))
        return;
    }

    const len = data.length;
    $.each(data, function (pos, object) {
        const row = createElement('div', father, { class: 'row' }, '');
        const { observacao, dataAplicacao, dataAprazamento } = object
        const { nomeViadministracaoVacina, nomeImunobiologico, codigoClasseImunobiologico } = object.codigoImunobiologico;
        const { sgDoseImunobiologico, nomeDoseImunobiologico, numeroOrdem } = object.codigoDoseImunobiologico;

        createElement("div", row, { class: 'col-md-4' }, `<strong>Vacina: </strong><p>${nomeViadministracaoVacina}</p>`)


        createElement('div', row, { class: 'col-md-4' }, `<strong>Imunobiologico: </strong>
                                                     <p>${nomeImunobiologico}</p>`);

        createElement('div', row,
            { class: 'col-md-4' }, `<strong>Classe Imunobiologico: </strong>
                                                     <p>${codigoClasseImunobiologico}</p>`);

        createElement('div', row, { class: 'col-md-4' }, `<strong>Dose Imunobiologico (Sigla): </strong>
                                                     <p>${sgDoseImunobiologico}</p>`);

        createElement('div', row, { class: 'col-md-4' }, `<strong>Dose Imunobiologico: </strong>
                                                     <p>${nomeDoseImunobiologico}</p>`);

        createElement('div', row, { class: 'col-md-4' }, `<strong>Número Ordem: </strong>
        <p>${numeroOrdem}</p>`)
        createElement('div', row, { class: 'col-md-4' }, `<strong>Data Aplicação: </strong>
                                                     <p>${dataAplicacao}</p>`)
        if (dataAprazamento != null) {
            createElement('div', row, { class: 'col-md-4' }, `<strong>Data Aprazamento: </strong>
                                                     <p>${dataAprazamento}</p>`)
        }

        createElement('div', row, { class: 'col-md-4' }, `<strong>Observação: </strong>
        <p>${observacao}</p>`)

        if (pos < len - 1) {
            createElement('hr', father, { class: 'col-md-11' }, '');
        }

    })
}














