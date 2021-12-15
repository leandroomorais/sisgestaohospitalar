function imprimirAtendimento(){
    const div = document.getElementsByClassName('page-inner').item(1).children.item(0).children.item(0).children.item(2)
    const doc = new Doc({ url: '', target: '', features: 'height=2970,width=2100' })
    const templateDoc = new TemplateDoc(doc)
    
    templateDoc.getHtml({ title: "Documento gerado pelo SGH", links: [
        {type: 'text/css', href: 'http://localhost:9090/assets/css/bootstrap.min.css'},
        {type: 'text/css', href: 'http://localhost:9090/assets/css/atlantis.css'},
    ]})
    templateDoc.createStyle({value: `@media print {
        #print {
            display: none;
        }
    }`})
    //templateDoc.getBody({class: 'card'})
    templateDoc.createHTML({innerHTML: div.innerHTML})
    templateDoc.createHTML({innerHTML: `<div class="card" id="print">
        <div  class="card-footer">
            <button class="btn btn-primary" onclick="window.print()">Imprimir</button>
        </div>
    </div>`})
}