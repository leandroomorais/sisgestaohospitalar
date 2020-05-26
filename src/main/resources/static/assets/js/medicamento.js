/**
 *
 */
var res = new ResponseController;
var arrayInputs = [];
var arrayObjetoSelectAndInput = [];
$(document).ready(function () {
   $("#select-medicamento").hide();
});

async function response(length, value){
    return await res.$JSONLike('http://localhost:9000/fileJson/medicamentoJson', length, value)
}


function inputMedicamento(input){
    let str = input.value.toLocaleUpperCase();
    let medicamentos = response(input.value.length, str);
 
    const id = getSelectId(input);
    medicamentos.then(data=>{
        res.creatSelectMedicineHTML('', id.toString(), data);
    });
}

function getSelectId(input){
    const selects = document.getElementsByTagName("select");

    for(let i = 0; i < selects.length;i++){
        for(let j = 0; j < arrayObjetoSelectAndInput.length;j++){
            if(selects[i].id == arrayObjetoSelectAndInput[j].idSelect && input.id == arrayObjetoSelectAndInput[j].idInput){
                return selects[i].id;
            }
        }
    }

    return -1;
}


function getInputId(input){

    for(let j = 0; j < arrayObjetoSelectAndInput.length;j++){
        if(input.id == arrayObjetoSelectAndInput[j].idInput){
            return input.id;
        }
    }

    return -1;
}


function isGetInputAndSelect(input, select){

    try {
        if((input.id !== undefined || input.id != null) && (select.id !== undefined || select.id != null)){
            for(let i = 0; i < arrayObjetoSelectAndInput.length;i++){
                if(arrayObjetoSelectAndInput[i].idInput == input.id && arrayObjetoSelectAndInput[i].idSelect == select.id){
                    return true;
                }
            }
        }
    }catch (e) {

    }

    return false;
}


async function selectionMedicamento(select){
    if((select.value != null) && (select.value != "") && (select.value != "Sem resultados!")){
      const id = select.value;
      const data = await response(id.toString().length, id);
       preencherInputs(data, select);
    }
}

function preencherInputs(data, select){
    const divPai = document.getElementById("fields");
    const childrensDivPai = divPai.children;
    for(let i = 0; i < childrensDivPai.length;i++){
        let childrensDiv = childrensDivPai.item(i);
        for(let j = 0; j < childrensDiv.children.length - 1;j++){
            let childrensDivInputLabels = childrensDiv.children.item(j).children;
            for(let k = 0; k < childrensDivInputLabels.length;k++){
                const arrayInput = childrensDivInputLabels.item(k);
                if(arrayInput.tagName == "INPUT"){
                    arrayInputs.push(arrayInput);
                }
            }
        }
    }

    for(let i = 0; i < arrayInputs.length;i++){
        const inputMedicamento = document.getElementById(getInputId(arrayInputs[i]).toString());
        if(isGetInputAndSelect(inputMedicamento, select)){
            inputMedicamento.value = "";
            inputMedicamento.value = data[0].principioAtivoDroga;
            select.hidden = true;
            break;
        }
    }
}

$("#add-medicamento").click(function (e) {
    e.preventDefault();
   let divInputFields = document.getElementById("fields");
   const tags = new TagsView;
   const idSelect = Math.floor((Math.random() * 1000));
   const idInput = Math.floor((Math.random() * 1000));
   let divFormGroup = tags.createElement("div", divInputFields);
   divFormGroup.setAttribute("class", "form-group row col-sm-12");

   let divColSm5 = tags.createElement("div", divFormGroup);
   divColSm5.setAttribute("class", "col-sm-5");

   let labelName = tags.createElement("label", divColSm5);
   let inputName = tags.createElement("input", divColSm5);
   let iLabelName = tags.createElement("i", labelName);
   iLabelName.textContent = "*";

   labelName.textContent = "Medicamento";

   inputName.setAttribute("type", "text");
   inputName.setAttribute("class", "form-control form-control-user");
   inputName.setAttribute("id", "inputMedicamento"+idInput.toString());
   inputName.setAttribute("onkeyup", "inputMedicamento(this)");

   let divColSm2 = tags.createElement("div", divFormGroup);

   divColSm2.setAttribute("class", "col-sm-5");

   let labelPosologia = tags.createElement("label", divColSm2);
   let inputPosologia = tags.createElement("input", divColSm2);
   labelPosologia.textContent = "Posologia";

   inputPosologia.setAttribute("type", "text");
   inputPosologia.setAttribute("class", "form-control form-control-user");

   let divColSm2_2 = tags.createElement("div", divFormGroup);
   divColSm2_2.setAttribute("class", "col-sm-2");

   let labelQuantidade = tags.createElement("label", divColSm2_2);
   labelQuantidade.textContent = "Quantidade";

   let inputQuantidade = tags.createElement("input", divColSm2_2);
   inputQuantidade.setAttribute("type", "number");
   inputQuantidade.setAttribute("class", "form-control form-control-user");

   let selectMedicamentos = tags.createElement("select", divFormGroup);
   selectMedicamentos.setAttribute("class", "form-control");
   selectMedicamentos.setAttribute("size", 4);
   selectMedicamentos.setAttribute("id", "select-medicamento"+idSelect.toString());
   selectMedicamentos.setAttribute("onclick", "selectionMedicamento(this)");

   const objeto ={
       idSelect: selectMedicamentos.getAttribute("id"),
       idInput: inputName.getAttribute("id"),
   };

   arrayObjetoSelectAndInput.push(objeto);

});

$(document).click(function (event) {
    let objeto = event.target;
    if(objeto.getAttribute("class") == "btn btn-danger" && !$("#select-procedimentos").length){
        let result = confirm("Deseja Remover ?");
        if(result){
            let objeto = event.target;
            res.deleteTableHTML(objeto, 'tbody-medicamentos', 'btn btn-danger', event);
        }else{
            event.preventDefault();
        }
    }
});
