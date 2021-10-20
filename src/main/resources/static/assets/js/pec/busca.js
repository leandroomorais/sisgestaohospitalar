

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
    console.log(valid)
    if (valid){
        showAlert("A primeira Data não pode ser maior que a segunda!", element[0])
    } else {
        hideAlert(element[0])
    }
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
        console.log(valid)
        if (!valid) {
            showAlert("A primeira Data não pode ser maior que a segunda!", dateOneElement)
        } else {
            hideAlert(dateOneElement)
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
    console.log(dateOne.getDate(), dateTwo.getDate())
    if (dateOne.getFullYear() <= dateTwo.getFullYear()) {
        if (dateOne.getMonth() <= dateTwo.getMonth()) {
            if (dateOne.getDate() < dateTwo.getDate()) {
                return true;
            }
        }
    }
    return false;
}