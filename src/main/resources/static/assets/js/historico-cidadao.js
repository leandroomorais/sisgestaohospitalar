/*variaveis globais*/
var vacinasAplicadas = []
var vacinasAgendadas = []
var cpf = $("#cpfCidadao").val();
var user = {};
var examesResult = [];
var examesSolicitations = [];
var receitas = [];
var avaliacoes = [];

const baseUri = "http://localhost:8282/api"
const uriVacinasAplicadas = baseUri + "/vacinacao/cpf/"
const uriVacinasAgendadas = baseUri + "/vacinacao/aprazamentobycpf/"
const uriSolicitations = baseUri + "/exames/cpf/solicitations/"
const uriResult = baseUri + "/exames/cpf/results/"
const uriReceita = baseUri + "/receitamedicamentos/cpf/"
const uriAvaliacao = baseUri + "/avaliacoes/cpf/"
/*---------------------------*/
/*requsicao GET usando a Promise do JavaScript*/
function request(url, param, success, error){ 
    return new Promise(resolve => {
        resolve(
            $.ajax({
                url: url + param,
                error: error,
                success: success,
            })
        )
    })
}
/*---------------------------------------*/
/*realizando a requisicao e setando o resultado nas variaveis globais*/
$(document).ready(function () {
	request(uriVacinasAplicadas, cpf, (data) => vacinasAplicadas = data, () => vacinasAplicadas = [])
	request(uriVacinasAgendadas, cpf, (data) => vacinasAgendadas = data, () => vacinasAgendadas = [])
    request(uriSolicitations, cpf, (data) => examesSolicitations = data, () => examesSolicitations = [])
    request(uriAvaliacao, cpf, (data) => avaliacoes = data, () => avaliacoes = [])
    request(uriReceita, cpf, (data) => receitas = data, () => receitas = [])
    request(uriResult, cpf, (data) => examesResult = data, () => examesResult = [])
    request('/cidadao-resource/cpf/', cpf, (data) => user = data, () => user = {})
})
/*-----------------------------------------*/
