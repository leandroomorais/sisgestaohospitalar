	var array = [];
	var arrayIds = [];
	var arrayNames = [];
	$(document).ready(function () {
		$("#tab").hide();
		$("#tab-list").hide();
		$("#select-procedimentos").hide();
	});

	function fMasc(objeto, mascara) {
		obj = objeto
		masc = mascara
		setTimeout("fMascEx()", 1)
	}
	function fMascEx() {
		obj.value = masc(obj.value)
	}

	function mPro(cns) {
		var rootURLApi = "http://localhost:8080/api/procedimento/findByNome/"+cns;
		let response = new ResponseController;
		console.log(cns);
		response.creatSelectProcedureHTML(rootURLApi, "div-procedimentos", "select-procedimentos");
		return cns;
	}

	function clearTags(tagName) {
		document.getElementById(tagName).innerHTML = "";
	}

	$("#select-procedimentos").click(function (event) {
		if(($(this).val() != null) && ($(this).val() != "") && ($(this).val() != "Sem resultados!")){
			criarTableProcedimentos(event.originalEvent.path[0].innerText, "tbody-list", $(this).val());
		}
	});

	function criarTableProcedimentos(name, tbodyId, id){
		$("#tab-list").show();
		if(!verificarRepeticion(id)){
			arrayIds.push(id);
			let tags = new TagsView;
			let tbody = document.getElementById(tbodyId);
			let tr = tags.createElement("tr", tbody);
			let tdNome = tags.createElement("td", tr);
			let tdExcluir = tags.createElement("td", tr);
			let botao = tags.createElement("button", tdExcluir);
			botao.setAttribute("class", "btn btn-danger");
			botao.textContent = "Excluir";
			botao.setAttribute("title", "Excluir Procedimento Selecionado!");
			let p = tags.createElement("p", tdNome);
			p.textContent = name;
			arrayNames.push(name);
		}
	}

	$(document).click(function (event) {
		let objeto = event.target;
		if(objeto.getAttribute("class") == "btn btn-danger" && !$("#select-medicamento").length){
			let result = confirm("Deseja Remover ?");
			if(result){

				let a = objeto;
				let tr = a.parentElement.parentElement;
				deleteTr(tr, "tbody-list");
			}else{
				event.preventDefault();
			}
		}
	});

	$("#add-triagem").click(function (event) {
		$("#ids-procedimentos").val(arrayIds);
		//event.preventDefault();
	});

	function deleteTr(tr, tbodyId) {
		let body = document.getElementById(tbodyId);
		for(let i = 0; i < body.children.length;i++){
			if(body.children[i] == tr){
				let tr = body.children.item(i);
				let nome = tr.children.item(0).textContent;
				deleteElement(nome, arrayNames, array);
				body.children.item(i).innerHTML = "";
				break;
			}
		}
	}

	function deleteElement(nome, array, arrayId) {
		for( var i = 0; i < array.length; i++){
			if (array[i] == nome) {
				arrayId.splice(i, 1);
				arrayIds.slice(i, 1);
				array.splice(i, 1);
				break;
			}
		}
	}

	function verificarRepeticion(text){
		if(array.length == 0){
			array.push(text);
			return false;
		}
		for(let i = 0; i < array.length;i++){
			if(array[i] == text){
				return true;
			}
		}
		array.push(text);
		return false;
	}
