let countNotFound = 0

let isValidInputDateOne = false
let isValidInputDateTwo = false

$("#date-one").change(function () {
    const element = $(this)
    hideAlert(element[0])
    isValidInputDateOne = true

    validDateOne(element)
})

$("#date-two").change(function () {
    const element = $("#date-one")
    hideAlert($(this)[0])
    isValidInputDateTwo = true

    validDateOne(element)
})

function validDate(element, variable, message){
    const dateOne = element.value
    if (!isValidInput(dateOne)) {
        showAlert(message, element)
        variable = false
    } else {
        hideAlert(element)
        variable = true
    }
}

function validDateOne(element){
    if (!isValidInput(element.val()) || !isValidInput($("#date-two").val())){
        return;
    }
    const valid = !isValidSearchDate(element.val(), $("#date-two").val())
    if (valid){
        showAlert("A primeira Data não pode ser maior que a segunda!", element[0])
    } else {
        hideAlert(element[0])
    }
} 

function switchSpinner(action){
    switch(action){
        case "create":
            document.getElementById("show-spinner").setAttribute('class', "row is-loading is-loading-lg")
            break
        case "clean":
            document.getElementById("show-spinner").setAttribute('class', "row")
            break
    }
}

function error(obj, value, len){
    switchSpinner("clean")
    $("#show-result").show()
    obj = value

    if (len === 1){
        countNotFound+=len
    } 

    if (countNotFound > 0 && countNotFound < 6){
        $("#show-button-pdf").show()
    } else {
        $("#show-result").html("")
        $("#show-button-pdf").hide()
        createElement('p', document.getElementById("show-result"), { class: 'col-md4' },
            notFound('Não Encontrado resultados referente ao Cidadão', 'pl-4'))
    }
}

function onConsult(date1, date2){
    const basic = "Basic c2doX3dlYl9iZXRhMC4xOnNnaF93ZWJfYmV0YTAuMQ=="
    switchSpinner("create")
    Req.getJSON({uri: `${baseUri}/receitamedicamentos/cpf/filter/`, headers: {
        Authorization: basic
    }, params: [cpf, date1, date2],
     onSuccess: (data) => setMedicamentosPrescritos(data), onError: (data) => {
        error(receitas, data, 1)
        setMedicamentosPrescritos(data)
     }})
    Req.getJSON({uri: `${baseUri}/avaliacoes/cpf/filter/`, headers: {
        Authorization: basic
    }, params: [cpf, date1, date2],
        onSuccess: (data) => avaliacoes = data, onError: (data) => error(avaliacoes, data, 1)})
    Req.getJSON({uri: `${baseUri}/exames/solicitations/cpf/filter/`, headers: {
        Authorization: basic
    }, params: [cpf, date1, date2],
        onSuccess: (data) => examesSolicitations = data, onError: (data) => error(examesSolicitations, data, 1)})
    Req.getJSON({uri: `${baseUri}/exames/results/cpf/filter/`, headers: {
        Authorization: basic
    }, params: [cpf, date1, date2], 
        onSuccess: (data) => examesResult = data, onError: (data) => error(examesResult, data, 1)})
    Req.getJSON({uri: `${baseUri}/vacinacao/aplicacao/cpf/filter/`, headers: {
        Authorization: basic
    }, params: [cpf, date1, date2], 
        onSuccess: (data) => vacinasAplicadas = data, onError: (data) => error(vacinasAplicadas, data, 1)})
    Req.getJSON({uri: `${baseUri}/vacinacao/aprazamento/cpf/filter/`, headers: {
        Authorization: basic
    }, params: [cpf, date1, date2], 
        onSuccess: (data) => vacinasAgendadas = data, onError: (data) => error(vacinasAgendadas, data, 1)})
}

$("#busca-pec").click((e) => {
    e.preventDefault()
    const dateOneElement = $("#date-one")[0]
    const dateTwoElement = $("#date-two")[0]
    const dateOne = dateOneElement.value
    const dateTwo = dateTwoElement.value

    validDate(dateOneElement, isValidInputDateOne, "A primeira Data precisa ser informada!")
    validDate(dateTwoElement, isValidInputDateTwo, "A segunda Data precisa ser informada!")


    if (isValidInputDateOne && isValidInputDateTwo) {
        const valid = isValidSearchDate(dateOne, dateTwo)
        if (!valid) {
            showAlert("A primeira Data não pode ser maior que a segunda!", dateOneElement)
        } else {
            hideAlert(dateOneElement)
            onConsult(dateOne, dateTwo)
        }
    }
})

function isValidInput(value) {
    if (ValidUtil.isFieldNotEmpty(value)) {
        return true
    }
    return false
}

function showAlert(message, element) {
    const div = element.parentElement
    removeChild(div)
    div.setAttribute('class', `${div.getAttribute('class')} has-error`)
    const small = new TagView(new Tag({ tagName: 'small', attrs: { class: 'form-text text-muted text-danger' }, value: message }))
    View.append(small.element, div)
}

function removeChild(div) {
    if (div.children.length > 1) {
        div.children.item(1).remove()
    }
}

function hideAlert(element) {
    const div = element.parentElement
    div.setAttribute('class', `form-group`)
    removeChild(div)
}

function isValidSearchDate(date1, date2) {
    const dateOne = new Date(date1)
    const dateTwo = new Date(date2)
    if (dateOne.getTime() <= dateTwo.getTime()) {
        return true
    }
    return false;
}