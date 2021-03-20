$('.nav-link, .active, .show').click(function () {
    const attrs = $(this)[0].attributes;
    showTabs(attrs);
})
/*funcao util para setar a class btn-primary*/
function showTabs(attrs) {
    for (let i = 0; i < attrs.length; i++){
        if (attrs.item(i).name === 'historico'){
            const ul = $(this)[0].parentElement.parentElement.children;
            for (let j = 0; j < ul.length; j++){
                const li = ul.item(j).children
                const a = li.item(0)
                if (a === this){
                    if ($(this)[0].getAttribute('class').search('btn-primary') < 0){
                        $(this).addClass('btn-primary')
                    }
                } else {
                    a.setAttribute('class', a.getAttribute('class').replace('btn-primary', ''));
                }
            }
            break;
        }
    }
}
