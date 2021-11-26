class Docs{
    static createHeader(templateDoc){
        templateDoc.createStyle({ value: '' })
        templateDoc.createHTML({
            innerHTML: `<div class="center"><p>MINISTÉRIO DA SAÚDE<br> ESTADO DE RIO GRANDE DO NORTE<br> MUNICÍPIO DE SEVERIANO MELO<br> UNIDADE DE
            SAÚDE Hospital Maternidade Municipal de Severiano Melo</p></div>`})
    }
    
    static doc(innerHTML){
        const doc = new Doc({ url: '', target: '', features: 'height=2970,width=2100' })
        const templateDoc = new TemplateDoc(doc)
        
        templateDoc.getHtml({ title: "Documento gerado pelo SGH", links: [
            {type: 'text/css', href: 'http://localhost:9090/assets/css/bootstrap.min.css'},
            {type: 'text/css', href: 'http://localhost:9090/assets/css/atlantis.css'},
        ]})
        templateDoc.createStyle({value: `@media print {
            .card-footer {
                display: none;
            }
        }`})
        //templateDoc.getBody({class: 'card'})
        templateDoc.createHTML({innerHTML: innerHTML})
        //doc.print()
        //onclick(doc)
    }
}