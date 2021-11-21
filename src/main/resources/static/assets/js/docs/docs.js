class Docs{
    static createHeader(templateDoc){
        //templateDoc.createStyle({ value: loadCSS() })
        templateDoc.createHTML({
            innerHTML: `<div class="header">
            <p>MINISTÉRIO DA SAÚDE<br> ESTADO DE RIO GRANDE DO NORTE<br> MUNICÍPIO DE SEVERIANO MELO<br> UNIDADE DE
                SAÚDE Hospital Maternidade Municipal de Severiano Melo</p>
        </div>`})
    }
    
    static docReceituario(innerHTML){
        const doc = new Doc({ url: '', target: '', features: 'height=2970,width=2100' })
        const document = doc.getDoc()
        const templateDoc = new TemplateDoc(document)
        templateDoc.openHtml({ title: "Documento gerado pelo SGH", links: [
            {rel: 'stylesheet', type: 'text/css', href: 'http://localhost:9090/assets/css/bootstrap.min.css'},
            {rel: 'stylesheet', type: 'text/css', href: 'http://localhost:9090/assets/css/atlantis.css'},
        ]})
        templateDoc.openBody()
        this.createHeader(templateDoc)
        templateDoc.createHTML({innerHTML: `<div class="space-simple-bottom"><div class="title"><p style="font-weight: bold">Receituário</p></div></div>`})
        templateDoc.createHTML({innerHTML: `<div class="space-simple-bottom"><div class="paragraph-left"><p style="font-weight: bold">Nome do Paciente: Antonio Almeida</p></div></div>`})
        templateDoc.createHTML({innerHTML: `<div class="space-simple-bottom"><div class="paragraph-left"><p style="font-weight: bold">CPF.: 000.000.000-00</p></div></div>`})
        templateDoc.createHTML({innerHTML: `<div class="space-simple-bottom"><div class="paragraph-left"><p style="font-weight: bold">Medicamentos</p></div></div>`})
        templateDoc.createHTML({innerHTML: innerHTML})
        this.close(templateDoc, doc)
    }

    static close(templateDoc, doc){
        templateDoc.closeBody()
        templateDoc.closeHtml()
        doc.print()
    }
}