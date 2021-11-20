class Docs{
    static createHeader(templateDoc){
        templateDoc.createStyle({ value: loadCSS() })
        templateDoc.createHeader({
            value: `MINISTÉRIO DA SAÚDE
        ESTADO DE RIO GRANDE DO NORTE
        MUNICÍPIO DE SEVERIANO MELO
        UNIDADE DE SAÚDE Hospital Maternidade Municipal de Severiano Melo`})
    }
    
    static docReceituario(){
        const doc = new Doc({ url: '', target: '', features: 'height=2970,width=2100' })
        const document = doc.getDoc()
        const templateDoc = new TemplateDoc(document)
        templateDoc.openHtml({ label: "Documento gerado pelo SGH" })
        templateDoc.openBody()
        this.createHeader(templateDoc)
        templateDoc.createTitle({value: `Receituário`, attrsChild: { style: "font-weight: bold" }, attrsFather: { class: 'paragraph-left' }, attrsFatherParent: { class: 'space-simple-bottom' } })
        templateDoc.createText({value: `Nome do Paciente: Antonio Almeida`, attrsChild: { style: "font-weight: bold" }, attrsFather: { class: 'paragraph-left' }, attrsFatherParent: { class: 'space-simple-bottom' } })
        templateDoc.createText({value: `CPF.: 000.000.000-00`, attrsChild: { style: "font-weight: bold" }, attrsFather: { class: 'paragraph-left' }, attrsFatherParent: { class: 'space-simple-bottom' } })
        templateDoc.createText({value: `Medicamentos`, attrsChild: { style: "font-weight: bold" }, attrsFather: { class: 'paragraph-left' }, attrsFatherParent: { class: 'space-simple-bottom' } })
        this.close(templateDoc, doc)
    }

    static close(templateDoc, doc){
        templateDoc.closeBody()
        templateDoc.closeHtml()
        doc.print()
    }
}