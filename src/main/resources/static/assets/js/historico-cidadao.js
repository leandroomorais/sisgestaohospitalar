/*variaveis globais*/
var vacinasAplicadas = []
var vacinasAgendadas = []
var cpf = $("#cpfCidadao").text();
var user = {};
var examesResult = [];
var examesSolicitations = [];
var receitas = [];
var avaliacoes = [];

const baseUri = "http://localhost:8082/api"
const uriVacinasAplicadas = baseUri + "/vacinacao/cpf/"
const uriVacinasAgendadas = baseUri + "/vacinacao/aprazamentobycpf/"
const uriSolicitations = baseUri + "/exames/cpf/solicitations/"
const uriResult = baseUri + "/exames/cpf/results/"
const uriReceita = baseUri + "/receitamedicamentos/cpf/"
const uriAvaliacao = baseUri + "/avaliacoes/cpf/"
/*---------------------------*/
/*requsicao GET usando a Promise do JavaScript*/
function request(url, param, success, error){ 
    Req.getJSON({uri: url, params: [param], onSuccess: success, onError: error})
}
/*---------------------------------------*/

function setMedicamentosPrescritos(data){
    receitas = data
    createMedicamentosPrescritos()
    
}

/*realizando a requisicao e setando o resultado nas variaveis globais*/
$(document).ready(function () {
    cpf = ValidUtil.number(cpf)
	/*request(uriVacinasAplicadas, cpf, (data) => vacinasAplicadas = data, (data) => vacinasAplicadas = data)
	request(uriVacinasAgendadas, cpf, (data) => vacinasAgendadas = data, (data) => vacinasAgendadas = data)
    request(uriSolicitations, cpf, (data) => examesSolicitations = data, (data) => examesSolicitations = data)
    request(uriAvaliacao, cpf, (data) => avaliacoes = data, () => avaliacoes = data)
    request(uriReceita, cpf, (data) => setMedicamentosPrescritos(data), (data) => receitas = data)
    request(uriResult, cpf, (data) => examesResult = data, (data) => examesResult = data)*/
    request('/cidadao-resource/cpf/', cpf, (data) => user = data, (data) => user = {})
})
/*-----------------------------------------*/
