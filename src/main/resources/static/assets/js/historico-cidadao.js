/*variaveis globais*/
var cpf = $("#cpfCidadao").val();
var user = {};
var examesResult = [];
var examesSolicitations = [];
var receitas = [];
var avaliacoes = [];
const uriSolicitations = "http://localhost:9090/api/exames/cpf/solicitations/"
const uriResult = "http://localhost:9090/api/exames/cpf/results/"
const uriReceita = "http://localhost:9090/api/receitamedicamentos/cpf/"
const uriAvaliacao = "http://localhost:9090/api/avaliacoes/cpf/"
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
    request(uriSolicitations, cpf, (data) => examesSolicitations = data, () => examesSolicitations = [])
    request(uriAvaliacao, cpf, (data) => avaliacoes = data, () => avaliacoes = [])
    request(uriReceita, cpf, (data) => receitas = data, () => receitas = [])
    request(uriResult, cpf, (data) => examesResult = data, () => examesResult = [])
    request('/cidadao-resource/cpf/', cpf, (data) => user = data, () => user = {})
})
/*-----------------------------------------*/
