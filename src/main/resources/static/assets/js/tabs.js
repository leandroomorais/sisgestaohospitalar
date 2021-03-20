$('.nav-link, .active, .show').click(function () {
    const attrs = $(this)[0].attributes;
    showTabs(attrs, this, 'border-tab');
})
/*funcao util para setar a class btn-primary*/
function showTabs(attrs, element, clazz) {
    for (let i = 0; i < attrs.length; i++){
        if (attrs.item(i).name === 'historico'){
            const ul = element.parentElement.parentElement.children;
            for (let j = 0; j < ul.length; j++){
                const li = ul.item(j).children
                const a = li.item(0)
                if (a === element){
                    if (element.getAttribute('class').search(clazz) < 0){
                        element.setAttribute('class',
                            element.getAttribute('class') + " " + clazz)
                    }
                } else {
                    a.setAttribute('class', a.getAttribute('class')
                        .replace(clazz, ''));
                }
            }
            break;
        }
    }
}
