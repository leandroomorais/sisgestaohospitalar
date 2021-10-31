

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

function error(obj, value){
    switchSpinner("clean")
    $("#show-result").show()
    $("#show-button-pdf").show()
    obj = value
}

function onConsult(date1, date2){
    switchSpinner("create")
    Req.getJSON({uri: `${baseUri}/receitamedicamentos/cpf/filter/`, params: ['09814354406', date1, date2],
     onSuccess: (data) => setMedicamentosPrescritos(data), onError: (data) => error(receitas, data)})
    Req.getJSON({uri: `${baseUri}/avaliacoes/cpf/filter/`, params: ['09814354406', date1, date2], onSuccess: (data) => avaliacoes = data, onError: (data) => error(avaliacoes, data)})
    Req.getJSON({uri: `${baseUri}/exames/solicitations/cpf/filter/`, params: ['09814354406', date1, date2], onSuccess: (data) => examesSolicitations = data, onError: (data) => error(examesSolicitations, data)})
    Req.getJSON({uri: `${baseUri}/exames/results/cpf/filter/`, params: ['09814354406', date1, date2], onSuccess: (data) => examesResult = data, onError: (data) => error(examesResult, data)})
    Req.getJSON({uri: `${baseUri}/vacinacao/aplicacao/cpf/filter/`, params: ['09814354406', date1, date2], onSuccess: (data) => vacinasAplicadas = data, onError: (data) => error(vacinasAplicadas, data)})
    Req.getJSON({uri: `${baseUri}/vacinacao/aprazamento/cpf/filter/`, params: ['09814354406', date1, date2], onSuccess: (data) => vacinasAgendadas = data, onError: (data) => error(vacinasAgendadas, data)})
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
    if (dateOne.getFullYear() <= dateTwo.getFullYear()) {
        if (dateOne.getMonth() <= dateTwo.getMonth()) {
            if (dateOne.getDate() <= dateTwo.getDate()) {
                return true;
            }
        }
    }
    return false;
}